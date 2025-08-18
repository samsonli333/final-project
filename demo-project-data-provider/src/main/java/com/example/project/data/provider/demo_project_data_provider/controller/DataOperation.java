package com.example.project.data.provider.demo_project_data_provider.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.project.data.provider.demo_project_data_provider.model.ProfileDTO;
import com.example.project.data.provider.demo_project_data_provider.model.QuoteDTO;


@RequestMapping(value="/api")
public interface DataOperation {

  @GetMapping(value="/data")
  String getData();

  @GetMapping(value = "/profile")
  List<ProfileDTO> getProfile();
} 
