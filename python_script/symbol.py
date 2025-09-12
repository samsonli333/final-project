#!/usr/bin/env python
# coding: utf-8

# In[ ]:


import string
import pandas as pd
from sqlalchemy import create_engine , MetaData , Table ,Column ,BigInteger,String,Date,text,update
from datetime import datetime 

url = "https://raw.githubusercontent.com/datasets/s-and-p-500-companies/master/data/constituents.csv"

engine = create_engine("postgresql+psycopg2://"":""@localhost:5432/final_project")

metadata = MetaData()

stock_symbol_update_schema = Table(
  "stock_symbol_update",
  metadata,
 Column("id", BigInteger , primary_key=True),
  Column("symbol", String(10) , nullable=True),
  Column("sector", String , nullable=False),
Column("founded", Date , primary_key=True),
Column("updated_at", Date , nullable=False),
)


metadata.create_all(engine)

df_csv = pd.read_csv(url)

filter_df_csv = df_csv[["Symbol","GICS Sector","Founded"]]

is_null = filter_df_csv["Symbol"].isnull().any \
          and filter_df_csv["Founded"].isnull().any() \
          and filter_df_csv["GICS Sector"].isnull.any()

if is_null:
  print("null error")
  exit()

def format(x):
  text = x.replace(" ","")
  new_text = "".join([char for char in text if not char in string.punctuation])
  return new_text[0:4] + "0101"



formatted = filter_df_csv["Founded"] = filter_df_csv["Founded"].apply(format)

filter_df_csv["Founded"] = pd.to_datetime(formatted,format="%Y%m%d").dt.date

filter_df_csv["updated_at"] = datetime.now()

new_column = {"Symbol":"symbol","GICS Sector":"sector","Founded":"founded"}

new_df_csv = filter_df_csv.rename(columns=new_column)


new_df_csv.to_sql(name="stock_symbol_update",con=engine,if_exists="replace",index=True)
 


update_query = """
  insert into stock_symbol(
  symbol,sector,founded,updated_at
  )
  select ssu.symbol,ssu.sector,ssu.founded,ssu.updated_at
  from stock_symbol_update ssu
  ON CONFLICT(symbol)
  Do update SET 
    symbol = EXCLUDED.symbol,
    sector = EXCLUDED.sector,
    founded = EXCLUDED.founded,
    updated_at = EXCLUDED.updated_at
  """
        
with engine.connect() as conn:
    conn.execute(text(update_query))
    conn.commit()














