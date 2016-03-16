package com.trading.storage.impl;

import com.trading.model.Price;
import com.trading.storage.PriceStorage;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by jeygokul on 3/16/2016.
 */
public class InMemoryPriceStorageImpl implements PriceStorage {

  private ConcurrentHashMap<String, LinkedBlockingDeque<Price>> priceMap = new ConcurrentHashMap<String, LinkedBlockingDeque<Price>>();

  @Override
  public synchronized boolean storePrice(Price price) {
    String productName = price.getName();
    if (priceMap.containsKey(productName)) {
      if (priceMap.get(productName).size() > ROLLING_WINDOW_SIZE) {
        priceMap.get(productName).removeFirst();
      }
      priceMap.get(productName).addLast(price);
    } else {
      LinkedBlockingDeque<Price> priceList = new LinkedBlockingDeque<Price>();
      priceList.addLast(price);
      priceMap.put(productName, priceList);
    }
    return true;
  }

  @Override
  public synchronized LinkedBlockingDeque<Price> getPrices(String productName) {
    return priceMap.get(productName);
  }
}
