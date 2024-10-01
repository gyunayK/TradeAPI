package com.example.tradeapi.repository;

import com.example.tradeapi.model.Trade;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("listRepository")
public class ListTradeRepository implements TradeRepository {
  private List<Trade> trades = new ArrayList<>();

  @Override
  public Trade getTrade(int id) {
    return trades.stream()
            .filter(t -> t.getId() == id)
            .findFirst()
            .orElse(null);
  }

  @Override
  public void saveTrade(Trade trade) {
    if (trade.getId() == 0) {
      trade.setId(UUID.randomUUID().hashCode());
    }
    trades.removeIf(t -> t.getId() == trade.getId());
    trades.add(trade);
  }

  @Override
  public boolean updateTradeComment(int id, String comment) {
    return trades.stream()
            .filter(t -> t.getId() == id)
            .findFirst()
            .map(t -> {
              t.addTraderComment(comment);
              return true;
            })
            .orElse(false);
  }

  @Override
  public List<Trade> getAllTrades() {
    return new ArrayList<>(trades);
  }
}