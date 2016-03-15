package com.trading.algo.impl;

import com.trading.algo.TradingAlgorithm;
import com.trading.model.Price;
import com.trading.model.Trade;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNull;

/**
 * Created by jeygokul on 3/14/2016.
 */
public class TradingAlgorithmImplTest {

  private TradingAlgorithm tradingAlgorithm = null;

  private Trade t = null;

  @Before
  public void setUp() {
    tradingAlgorithm = new TradingAlgorithmImpl();
   }

  @Test
  public void testTradingAlgorithmImpl() {

    Price p0 = new Price();
    p0.setName("AAPL");
    p0.setPrice(2201.00);
    t = tradingAlgorithm.buildTrades(p0);

    assertNull("Trade for "+p0+" has to be null but was "+t, t);

    Price p1 = new Price();
    p1.setName("AAPL");
    p1.setPrice(2202.00);
    t = tradingAlgorithm.buildTrades(p1);

    assertNull("Trade for "+p0+" "+p1+" has to be null but was "+t, t);

    Price p2 = new Price();
    p2.setName("AAPL");
    p2.setPrice(2203.00);
    t = tradingAlgorithm.buildTrades(p2);

    assertNull("Trade for "+p0+" "+p1+" "+p2+" has to be null but was "+t, t);




  }

}
