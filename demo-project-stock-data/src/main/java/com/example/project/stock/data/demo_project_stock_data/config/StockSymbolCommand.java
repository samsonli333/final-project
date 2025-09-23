package com.example.project.stock.data.demo_project_stock_data.config;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(1)
public class StockSymbolCommand implements CommandLineRunner{

  @Value("${scriptPath.stockSymbol1}")
  private String commandPath1;

   @Value("${scriptPath.stockSymbol2}")
  private String commandPath2;

  @Override
public void run(String... args) throws Exception{

 Runtime runtime = Runtime.getRuntime();
       System.out.println("Total memory: " + runtime.totalMemory() /(1024 * 1024) + " MB");
       System.out.println("Free Memory: " + runtime.freeMemory() / (1024 * 1024) + " MB");

       try{

         Process process = runtime.exec(this.commandPath1);
        System.out.println("Retrieve Symbol Command 1 launhed");
        int exitVal = process.waitFor();
        System.out.println(exitVal == 0 ? "success" : "fail");

        Process process2 = runtime.exec(this.commandPath2);
         System.out.println("Retrieve Symbol Command 2 launhed");
        int exitVal2 = process2.waitFor();
        System.out.println(exitVal2 == 0 ? "success" : "fail"); 


       }catch(IOException e){
         e.printStackTrace();
       }
}

}
