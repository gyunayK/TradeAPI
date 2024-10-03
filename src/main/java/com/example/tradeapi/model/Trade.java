package com.example.tradeapi.model;

import java.util.ArrayList;
import java.util.List;

public class Trade {
  private int id;
  private String ccyPair;
  private double rate;
  private double amount;
  private TradeStatus status;
  private String userComment;
  private List<String> traderComments = new ArrayList<>();

  public Trade() {
    this.traderComments = new ArrayList<>();
  }

  public Trade(int id, String ccyPair, double rate, double amount, TradeStatus status, String userComment) {
    this.id = id;
    this.ccyPair = ccyPair;
    this.rate = rate;
    this.amount = amount;
    this.status = status;
    this.userComment = userComment;
    this.traderComments = new ArrayList<>();
  }

  // Getters
  public int getId() { return id; }

  public String getCcyPair() { return ccyPair; }

  public double getRate() { return rate; }

  public double getAmount() { return amount; }

  public TradeStatus getStatus() { return status; }

  public String getUserComment() { return userComment; }

  public List<String> getTraderComments() { return traderComments; }

  // Setters
  public void setId(int id) { this.id = id; }

  public void setCcyPair(String ccyPair) { this.ccyPair = ccyPair; }

  public void setRate(double rate) { this.rate = rate; }

  public void setAmount(double amount) { this.amount = amount; }

  public void setStatus(TradeStatus status) { this.status = status; }

  public void setUserComment(String userComment) { this.userComment = userComment; }

  public void addTraderComment(String comment) {
    traderComments.add(comment);
  }
}
