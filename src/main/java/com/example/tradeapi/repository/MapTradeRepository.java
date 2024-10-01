package com.example.tradeapi.repository;

import com.example.tradeapi.model.Trade;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.UUID;


@Repository("mapRepository")
public class MapTradeRepository implements TradeRepository {
  private Map<Integer, Trade> trades = new HashMap<>();

  @Override
  public Trade getTrade(int id) {
    return trades.get(id);
  }

  @Override
  public void saveTrade(Trade trade) {
    if (trade.getId() == 0) {
      trade.setId(UUID.randomUUID().hashCode());
    }
    trades.put(trade.getId(), trade);
  }

  @Override
  public boolean updateTradeComment(int id, String comment) {
    Trade trade = trades.get(id);
    if (trade != null) {
      trade.addTraderComment(comment);
      return true;  // Return true if the comment is added
    }
    return false;  // Return false if the trade was not found
  }

  @Override
  public List<Trade> getAllTrades() {
    // Convert the values in the map to a list
    return trades.values().stream().collect(Collectors.toList());
  }
}