package lambdaPractice;

public class Product {
  public String userName;
  public String name;
  public Integer price;
  public Double discount;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Double getDiscount() {
    return discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Product(String name) {
    this.name = name;
  }

  public Product() {}

  public Product(String name, Integer price) {
    this.name = name;
    this.price = price;
  }

  public Product(String userName, String name, Integer price) {
    this.userName = userName;
    this.name = name;
    this.price = price;
  }

  @Override
  public String toString() {
    return "Product{"
        + "userName='"
        + userName
        + '\''
        + ", name='"
        + name
        + '\''
        + ", price="
        + price
        + ", discount="
        + discount
        + '}';
  }
}
