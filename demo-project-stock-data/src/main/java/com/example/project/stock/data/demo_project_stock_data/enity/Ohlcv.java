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
import lombok.Data;

@Data
@Entity
public class Ohlcv {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Double close;
  private Double low;
  private Double high;
  private Double open;
  private Long volume;
  private LocalDate date;

  @Column(name ="updated_at")
  private LocalDateTime updateAt;

  @ManyToOne
  @JoinColumn(name = "stock_id", nullable = false)
  private StockSymbol stockSymbol;
}
