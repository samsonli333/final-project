package com.example.project.stock.data.demo_project_stock_data.enity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "temp_stock_profile")
public class TempStockProfile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String country;
  private String estimateCurrency;
  private String exchange;
  private String finnhubIndustry;

  @Column(name = "ipo_date")
  private LocalDate ipoDate;

  private String logo;
  private Double marketCapitalization;
  private String name;
  private String phone;
  private Double shareOutstanding;
  private String weburl;

  @Column(name = "last_update_at")
  private LocalDateTime lastUpdateAt;

  @ManyToOne
  @JoinColumn(name = "stock_id", nullable = false)
  private StockSymbol stockSymbol;
}