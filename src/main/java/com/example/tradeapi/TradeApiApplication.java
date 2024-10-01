package com.example.tradeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TradeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeApiApplication.class, args);
	}

}
