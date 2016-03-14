package com.trading.model;

/**
 * Created by jeygokul on 3/13/2016.
 */
public class Trade {

  private String name;

  private Direction direction;

  private Double price;

  private Integer qty;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getQty() {
    return qty;
  }

  public void setQty(Integer qty) {
    this.qty = qty;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Trade)) return false;

    Trade trade = (Trade) o;

    if (!getName().equals(trade.getName())) return false;
    if (getDirection() != trade.getDirection()) return false;
    if (!getPrice().equals(trade.getPrice())) return false;
    return getQty().equals(trade.getQty());

  }

  @Override
  public int hashCode() {
    int result = getName().hashCode();
    result = 31 * result + getDirection().hashCode();
    result = 31 * result + getPrice().hashCode();
    result = 31 * result + getQty().hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Trade{" +
      "name='" + name + '\'' +
      ", direction=" + direction +
      ", price=" + price +
      ", qty=" + qty +
      '}';
  }
}
