package com.example.project.stock.data.demo_project_stock_data.config;

import java.io.IOException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class OhlcvCommand implements CommandLineRunner{
  
  @Override
  public void run(String... args) throws Exception{
     Runtime runtime = Runtime.getRuntime();
       System.out.println("Total memory: " + runtime.totalMemory() /(1024 * 1024) + " MB");
       System.out.println("Free Memory: " + runtime.freeMemory() / (1024 * 1024) + " MB");

       try{

        Process process = runtime.exec("jupyter nbconvert --to script /Users/samsonli/Desktop/venturenix_vincent/final-project/python_script/ohlcv.ipynb");
        System.out.println("Retrieve Ohlcv Command 1 launhed");
        int exitVal = process.waitFor();
        System.out.println(exitVal == 0 ? "success" : "fail");

        Process process2 = runtime.exec("python /Users/samsonli/Desktop/venturenix_vincent/final-project/python_script/ohlcv.py");
         System.out.println("Retrieve Ohlcv Command 2 launhed");
        int exitVal2 = process2.waitFor();
        System.out.println(exitVal2 == 0 ? "success" : "fail"); 


       }catch(IOException e){
         e.printStackTrace();
       }
  }
}
