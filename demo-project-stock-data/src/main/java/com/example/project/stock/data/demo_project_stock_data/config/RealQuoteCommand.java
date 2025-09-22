package com.example.project.stock.data.demo_project_stock_data.config;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.example.project.stock.data.demo_project_stock_data.enity.Ohlcv;
import com.example.project.stock.data.demo_project_stock_data.model.StockQuoteDTO;
import com.example.project.stock.data.demo_project_stock_data.repository.OhlvRepository;
import com.example.project.stock.data.demo_project_stock_data.repository.RealTimeQuoteRepository;


@Component
@Order(3)
public class RealQuoteCommand implements CommandLineRunner{

  @Autowired
  private OhlvRepository ohlvRepository;


  @Override
public void run(String... args) throws Exception{
    List<Ohlcv> listOhlv = ohlvRepository.findLatestAll();
    List<StockQuoteDTO> stockQuoteDTO =  listOhlv.stream().map( x -> {
     return StockQuoteDTO.builder()
     .symbol(x.getStockSymbol().getSymbol())
     .c(0.0)
     .d(0.0)
     .dp(0.0)
     .h(x.getHigh())
     .l(x.getLow())
     .pc(x.getClose())
     .o(x.getOpen())
     .t(0L)
     .build();
    }).collect(Collectors.toList());

for( StockQuoteDTO s:stockQuoteDTO){
    RealTimeQuoteRepository.quote.put(s.getSymbol(),s);
} 

}
  
}
