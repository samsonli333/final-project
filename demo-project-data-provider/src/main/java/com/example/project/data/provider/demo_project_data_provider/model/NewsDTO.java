package com.example.project.data.provider.demo_project_data_provider.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;



@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsDTO {

      private String headline;
      private String source;
      private String url;

      @JsonProperty("datetime")
      private Long dateTime;
}
