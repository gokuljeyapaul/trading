package com.trading.algo;

import com.trading.model.Price;
import com.trading.model.Trade;

/**
 * Created by jeygokul on 3/13/2016.
 */
public interface TradingAlgorithm {

  Integer TRADING_CALCULATION_APPROVED_SIZE = 4;

  Trade buildTrades(Price price);
}
