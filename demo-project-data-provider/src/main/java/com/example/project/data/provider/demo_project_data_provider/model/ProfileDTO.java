package com.example.project.data.provider.demo_project_data_provider.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileDTO {
private String country;
private String currency;
//private String estimateCurrency;
//private String exchange;
//private String finnhubIndustry;
//private String ipo;
//private String logo;
//private Double marketCapitalization;
//private String name;
//private String phone;
//private Double shareOutstanding;
//private String ticker;
//private String weburl;

}