#!/usr/bin/env python
# coding: utf-8

# In[ ]:


import requests
import pandas as pd
from sqlalchemy import create_engine ,Table, Column, MetaData, Integer, String, Date, Float, BigInteger ,DateTime
from datetime import datetime 


engine = create_engine("postgresql+psycopg2://postgres:admin1234@localhost:5532/final_project")


metadata = MetaData()

ohlc_data_schema = Table(
  "ohlcv", metadata,
  Column("id", BigInteger,nullable=False, primary_key=True ,autoincrement=True),
  Column("stock_id", Integer, nullable=False),
  Column("date", Date, nullable=False),
  Column("open", Float, nullable=False),
  Column("high", Float, nullable=False),
  Column("low", Float, nullable=False),
  Column("close", Float, nullable=False),
  Column("volume", BigInteger, nullable=False),
  Column("update_at", DateTime, nullable=False)
)


# In[ ]:


new_result = list()

period2 = round(datetime.now().timestamp())

query = "select symbol from stock_symbol ORDER by symbol ASC limit 51"
df_result = pd.read_sql(query,con=engine).symbol

for symbol in list(df_result):

  try:

    url=f"https://query1.finance.yahoo.com/v8/finance/chart/{symbol}?period1=1657237004&period2={period2}&interval=1d&events=history"
    print(url)

    headers = {
        "User-Agent" : "Mozilla/5.0"
      }
    response = requests.get(url, headers=headers)
    data = response.json()



    quote = data["chart"]["result"][0]["indicators"]["quote"][0]

    symbol = data["chart"]["result"][0]["meta"]["symbol"] 
    query = f"SELECT id FROM stock_symbol where symbol = '{symbol}'"
    stock_id = pd.read_sql(query,con=engine).id[0]



    df_ohlcv = pd.DataFrame({
      "date":pd.Series(data["chart"]["result"][0]["timestamp"]),
      "low":pd.Series(quote["low"]).apply(lambda x : round(x,2)),
    "high": pd.Series(quote["high"]).apply(lambda x : round(x,2)),
    "open": pd.Series(quote["open"]).apply(lambda x : round(x,2)),
    "close": pd.Series(quote["close"]).apply(lambda x : round(x,2)),
    "volume": pd.Series(quote["volume"])
    })

    is_count_valid = df_ohlcv["date"].shape[0] == df_ohlcv["low"].shape[0] \
                  and df_ohlcv["low"].shape[0] == df_ohlcv["high"].shape[0] \
                  and df_ohlcv["high"].shape[0] == df_ohlcv["open"].shape[0] \
                  and df_ohlcv["open"].shape[0] == df_ohlcv["close"].shape[0] \
                  and df_ohlcv["close"].shape[0] == df_ohlcv["volume"].shape[0]

    if not is_count_valid:
      print("Count Error")
      exit()


    is_null = df_ohlcv["date"].isnull().any()\
                    and df_ohlcv["low"].isnull().any() \
                  and df_ohlcv["high"].isnull().any() \
                  and  df_ohlcv["open"].isnull().any() \
                  and df_ohlcv["close"].isnull().any() \
                  and df_ohlcv["volume"].isnull().any()
    if is_null:
      print("Null Error")
      exit()

    df_ohlcv["date"] = df_ohlcv["date"].apply(lambda x : datetime.fromtimestamp(x).strftime("%Y-%m-%d"))
    df_ohlcv["stock_id"] = stock_id
    df_ohlcv["updated_at"] = datetime.now()


    new_result.append(df_ohlcv)

  except Exception as err:
    print(f"Download Error: {err}")


# In[3]:


new_df = pd.concat(new_result,ignore_index=True)
new_df = new_df.reset_index()
new_df["index"] = new_df["index"] + 1
new_df = new_df.rename(columns={"index":"id"})
print(f" Total: {len(new_df)} records")
new_df.to_sql(name="ohlcv",con=engine,if_exists="replace",index=False)

