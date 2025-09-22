package com.example.project.stock.data.demo_project_stock_data.model;

import com.example.project.stock.data.demo_project_stock_data.lib.DateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsDTO {

      private String headline;
      private String source;
      private String url;


      @JsonSerialize(using = DateSerializer.class)
      @JsonProperty("datetime")
      private Long dateTime;
}