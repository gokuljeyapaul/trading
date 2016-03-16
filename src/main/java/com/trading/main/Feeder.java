package com.trading.main;

import com.trading.algo.TradingAlgorithm;
import com.trading.algo.impl.TradingAlgorithmImpl;
import com.trading.model.Price;
import com.trading.model.Trade;

import java.util.Scanner;

/**
 * Created by jeygokul on 3/14/2016.
 */
public class Feeder {

  public static void main(String... args) {
    TradingAlgorithm tradingAlgorithm = new TradingAlgorithmImpl();
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      Price p = new Price();
      p.setName(scanner.next());
      p.setPrice(scanner.nextDouble());

      //Pass it on to trade algorithm
      Trade t = tradingAlgorithm.buildTrades(p);
      if(t != null){
        System.out.println(t.getName() + " " + t.getDirection() + " " + t.getPrice() + " " + t.getQty());
      }
    }
  }
}
