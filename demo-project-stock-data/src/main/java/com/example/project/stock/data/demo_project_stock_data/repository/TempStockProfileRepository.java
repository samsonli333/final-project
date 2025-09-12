package com.example.project.stock.data.demo_project_stock_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.project.stock.data.demo_project_stock_data.enity.TempStockProfile;



@Repository
public interface TempStockProfileRepository extends JpaRepository<TempStockProfile,Long> {
   
  
}