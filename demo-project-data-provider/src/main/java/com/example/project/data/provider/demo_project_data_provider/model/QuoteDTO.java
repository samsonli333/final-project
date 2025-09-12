package com.example.project.data.provider.demo_project_data_provider.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteDTO {
    private Double c;
    private Double d;
    private Double dp;
    private Double h;
    private Double l;
    private Double o;
    private Double pc;
    private Long t;
}
