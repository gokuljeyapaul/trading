package com.trading.algo.impl;

import com.trading.algo.TradingAlgorithm;
import com.trading.model.Direction;
import com.trading.model.Price;
import com.trading.model.Trade;
import com.trading.storage.PriceStorage;
import com.trading.storage.impl.InMemoryPriceStorageImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Predicate;


/**
 * Created by jeygokul on 3/13/2016.
 */
public class TradingAlgorithmImpl implements TradingAlgorithm {

  private Map<String, LinkedBlockingDeque<Price>> possibleTradePriceMap = new ConcurrentHashMap<String, LinkedBlockingDeque<Price>>();

  private PriceStorage priceStorage = new InMemoryPriceStorageImpl();

  public synchronized Trade buildTrades(Price price) {

    Trade t = null;
    LinkedBlockingDeque<Price> filteredPrices = new LinkedBlockingDeque<Price>();
    LinkedBlockingDeque<Price> allPrices = null;
    String productName = price.getName();

    //Store the price
    priceStorage.storePrice(price);
    //Retrieve the prices for a product
    allPrices = priceStorage.getPrices(productName);
    
    if(allPrices == null || allPrices.size() != TRADING_CALCULATION_APPROVED_SIZE) {
      return t;
    }

    allPrices.stream().filter(this.isOldestPriceGreaterThanAveragePrice(allPrices)).forEach(filteredPrices::add);
    possibleTradePriceMap.put(productName, filteredPrices);

    if (possibleTradePriceMap.get(productName)!= null &&
            possibleTradePriceMap.get(productName).size() > 0) {
      t = new Trade();
      t.setName(price.getName());
      t.setDirection(Direction.BUY);
      t.setPrice(price.getPrice());
      t.setQty(1000);
      possibleTradePriceMap.clear();
    }
    return t;
  }

  private static Predicate<Price> isOldestPriceGreaterThanAveragePrice(LinkedBlockingDeque<Price> priceList) {
    return p -> (priceList.stream().mapToDouble(Price::getPrice).average().getAsDouble() > priceList.peekFirst().getPrice());
  }
}
