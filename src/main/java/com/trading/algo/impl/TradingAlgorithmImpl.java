package com.trading.algo.impl;

import com.trading.algo.TradingAlgorithm;
import com.trading.model.Direction;
import com.trading.model.Price;
import com.trading.model.Trade;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * Created by jeygokul on 3/13/2016.
 */
public class TradingAlgorithmImpl implements TradingAlgorithm {

    private ConcurrentHashMap<String, LinkedList<Price>> priceMap = new ConcurrentHashMap<String, LinkedList<Price>>();
    private ConcurrentHashMap<String, LinkedList<Price>> possibleTradePriceMap = new ConcurrentHashMap<String, LinkedList<Price>>();

    public Trade buildTrades(Price price) {

        Trade t = new Trade();
        LinkedList<Price> filteredPrices = new LinkedList<Price>();

        //Add the price to the list
        if(priceMap.containsKey(price.getName())) {
            if(priceMap.get(price.getName()).size()>=4) {
                priceMap.get(price.getName()).removeFirst();
            }
            priceMap.get(price.getName()).addLast(price);
        } else {
            LinkedList<Price> priceList = new LinkedList<Price>();
            priceList.addLast(price);
            priceMap.put(price.getName(), priceList);
        }
        priceMap.forEach((productName, prices) -> {
            prices.stream().filter(this.isOldestPriceGreaterThanAveragePrice(prices)).forEach(filteredPrices::add);
            possibleTradePriceMap.put(productName, filteredPrices);
        });

        if(possibleTradePriceMap.get(price.getName()).size() > 0) {
            t.setName(price.getName());
            t.setDirection(Direction.BUY);
            t.setPrice(price.getPrice());
            t.setQty(1000);
            possibleTradePriceMap.clear();
        }
        return t;
    }

    private static Predicate<Price> isOldestPriceGreaterThanAveragePrice(LinkedList<Price> priceList) {
        // System.out.println("Average : "+priceList.stream().mapToDouble(Price::getPrice).average().getAsDouble());
        // System.out.println("First Price : "+priceList.peekFirst().getPrice());
        return p -> (priceList.stream().mapToDouble(Price::getPrice).average().getAsDouble() > priceList.peekFirst().getPrice());
    }
}
