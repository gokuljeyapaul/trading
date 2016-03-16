package com.trading.storage;

import com.trading.model.Price;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by jeygokul on 3/16/2016.
 */
public interface PriceStorage {

  Integer ROLLING_WINDOW_SIZE = 4;

  boolean storePrice(Price price);

  LinkedBlockingDeque<Price> getPrices(String productName);

}
