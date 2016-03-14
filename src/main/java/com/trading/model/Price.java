package com.trading.model;

/**
 * Created by jeygokul on 3/13/2016.
 */
public class Price {

  private String name;

  private Double price;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Price)) return false;

    Price price1 = (Price) o;

    if (!getName().equals(price1.getName())) return false;
    return getPrice().equals(price1.getPrice());

  }

  @Override
  public int hashCode() {
    int result = getName().hashCode();
    result = 31 * result + getPrice().hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Price{" +
      "name='" + name + '\'' +
      ", price=" + price +
      '}';
  }
}
