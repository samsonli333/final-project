package com.example.project.data.provider.demo_project_data_provider.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

public interface DataOperation {

  @GetMapping(value="/api/data")
  List<String> getData();
} 
