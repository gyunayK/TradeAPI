package com.example.tradeapi.repository;

import com.example.tradeapi.model.Trade;

import java.util.List;

public interface TradeRepository {
  Trade getTrade(int id);
  void saveTrade(Trade trade);
  boolean updateTradeComment(int id, String comment);
  List<Trade> getAllTrades();
}