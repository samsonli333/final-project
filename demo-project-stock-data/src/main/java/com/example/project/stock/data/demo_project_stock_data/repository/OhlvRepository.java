package com.example.project.stock.data.demo_project_stock_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.project.stock.data.demo_project_stock_data.enity.Ohlcv;
import com.example.project.stock.data.demo_project_stock_data.enity.StockSymbol;
import java.util.List;


public interface OhlvRepository extends JpaRepository<Ohlcv,Long>{

       public List<Ohlcv> findByStockSymbol(StockSymbol stockSymbol);

       @Query(value="with latest as (select \n" + //
                            "stock_id,\n" + //
                            "max(to_char(ohlcv.date::date,'yyyy-mm-dd')) as date\n" + //
                            "from ohlcv\n" + //
                            "group by \n" + //
                            "stock_id)\n" + //
                            "select \n" + //
                            "o.id,\n" + //
                            "o.date,\n" + //
                            "o.low,\n" + //
                            "o.high,\n" + //
                            "o.open,\n" + //
                            "o.close,\n" + //
                            "o.volume,\n" + //
                            "o.stock_id,\n" + //
                            "o.updated_at \n" + //
                            "from ohlcv o\n" + //
                            "join latest l on l.stock_id = o.stock_id \n" + //
                            "and l.date = to_char(o.date::date,'yyyy-mm-dd')",nativeQuery=true)
       public List<Ohlcv> findLatestAll();
} 
