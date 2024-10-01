package com.example.tradeapi.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RepositoryConfig {
  @Bean
  public Map<String, TradeRepository> repositories(
          MapTradeRepository mapRepository,
          ListTradeRepository listRepository
  ) {
    Map<String, TradeRepository> repos = new HashMap<>();
    repos.put("mapRepository", mapRepository);
    repos.put("listRepository", listRepository);
    return repos;
  }
}