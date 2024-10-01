package com.example.tradeapi.service;

import com.example.tradeapi.model.Trade;
import com.example.tradeapi.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TradeService {
  private final TradeRepository repository;

  @Autowired
  public TradeService(@Value("${persistence.type}") String persistenceType, Map<String, TradeRepository> repositories) {
    this.repository = repositories.get(persistenceType.toLowerCase() + "Repository");
    if (this.repository == null) {
      throw new IllegalArgumentException("Invalid persistence type: " + persistenceType);
    }
  }

  public Trade getTrade(int id) {
    return repository.getTrade(id);
  }

  public void saveTrade(Trade trade) {
    repository.saveTrade(trade);
  }

  public List<Trade> getAllTrades() {
    return repository.getAllTrades();
  }

  public boolean updateTradeComment(int id, String comment) {
    return repository.updateTradeComment(id, comment);
  }
}
