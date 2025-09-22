package com.example.project.stock.data.demo_project_stock_data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.project.stock.data.demo_project_stock_data.enity.StockProfile;
import com.example.project.stock.data.demo_project_stock_data.enity.StockSymbol;
import jakarta.transaction.Transactional;


@Repository
public interface StockProfileRepository extends JpaRepository<StockProfile, Long> {
  
  @Transactional
  @Modifying
  @Query(value="insert into stock_profile (\"name\" , country ,estimate_currency,\n" + //
        "exchange,finnhub_industry,ipo_date,logo,\n" + //
        "market_capitalization,phone, share_outstanding,weburl,stock_id,\n" + //
        "last_update_at\n" + //
        ")\n" + //
        "select \"name\" , country ,estimate_currency,\n" + //
        "exchange,finnhub_industry,ipo_date,logo,\n" + //
        "market_capitalization,phone, share_outstanding,weburl,stock_id,\n" + //
        "now()\n" + //
        "from temp_stock_profile \n" + //
        "ON CONFLICT(stock_id)\n" + //
        "DO UPDATE SET\n" + //
        "name = EXCLUDED.name, \n" + //
        "country  = EXCLUDED.country,\n" + //
        "estimate_currency = EXCLUDED.estimate_currency,\n" + //
        "exchange  = EXCLUDED.exchange,\n" + //
        "finnhub_industry  = EXCLUDED.finnhub_industry,\n" + //
        "ipo_date  = EXCLUDED.ipo_date,\n" + //
        "logo  = EXCLUDED.logo,\n" + //
        "last_update_at = EXCLUDED.last_update_at,\n" + //
        "market_capitalization = EXCLUDED.market_capitalization,\n" + //
        "phone = EXCLUDED.phone, \n" + //
        "share_outstanding = EXCLUDED.share_outstanding,\n" + //
        "weburl = EXCLUDED.weburl,\n" + //
        "stock_id = EXCLUDED.stock_id",nativeQuery = true)
  public int upsert();

  public StockProfile findByStockSymbol(StockSymbol stockSymbol);
  
}
