package com.example.project.stock.data.demo_project_stock_data.lib;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateConvert {
     public static LocalDateTime convertDate(Long num){
      Long numNilli = num * 1000L;
      Instant instant = Instant.ofEpochMilli(numNilli);
      ZoneId zoneId =  ZoneId.systemDefault();
      LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
      return localDateTime; 
     }
}
