package com.example.tradeapi.controller;

import com.example.tradeapi.dto.TradeDTO;
import com.example.tradeapi.model.Trade;
import com.example.tradeapi.model.TradeStatus;
import com.example.tradeapi.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/trade")
public class TradeController {
  private final TradeService tradeService;

  @Autowired
  public TradeController(TradeService tradeService) {
    this.tradeService = tradeService;
  }

  // POST endpoint to create a new trade
  @PostMapping
  public ResponseEntity<String> createTrade(@RequestBody TradeDTO tradeDTO) {
    Trade trade = convertDTOToTrade(tradeDTO);
    tradeService.saveTrade(trade);
    return ResponseEntity.ok("Trade created");
  }

  // PUT endpoint to add a trader comment to a trade
  @PutMapping
  public ResponseEntity<String> addTraderComment(@RequestParam int id, @RequestBody Map<String, String> payload) {
    String comment = payload.get("comment");
    if (comment == null || comment.isEmpty()) {
      return ResponseEntity.badRequest().body("Comment is required");
    }

    boolean updated = tradeService.updateTradeComment(id, comment);
    if (!updated) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trade not found");
    }
    return ResponseEntity.ok("Comment added successfully");
  }

  // GET endpoint to retrieve a trade by its ID
  @GetMapping("/{id}")
  public ResponseEntity<Trade> getTrade(@PathVariable int id) {
    Trade trade = tradeService.getTrade(id);
    if (trade == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(trade);
  }

  // GET endpoint to retrieve all trades
  @GetMapping
  public ResponseEntity<List<Trade>> getAllTrades() {
    List<Trade> trades = tradeService.getAllTrades();
    return ResponseEntity.ok(trades);
  }

  private Trade convertDTOToTrade(TradeDTO dto) {
    Trade trade = new Trade();
    trade.setId(Math.abs(UUID.randomUUID().hashCode()));
    trade.setCcyPair(dto.getCurrency() + "/CAD");
    trade.setAmount(dto.getAmount());
    trade.setRate(getExchangeRate(trade.getCcyPair()));
    trade.setUserComment(dto.getComment());
    trade.setStatus(TradeStatus.IN_PROCESS);
    return trade;
  }

  private double getExchangeRate(String ccyPair) {
    switch (ccyPair) {
      case "USD/CAD":
        return 1.35;
      case "EUR/CAD":
        return 1.5;
      default:
        return 1.0;
    }
  }
}
