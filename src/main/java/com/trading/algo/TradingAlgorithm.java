package com.trading.algo;

import com.trading.model.Price;
import com.trading.model.Trade;

/**
 * Created by jeygokul on 3/13/2016.
 */
public interface TradingAlgorithm {

    Trade buildTrades(Price price);
}
