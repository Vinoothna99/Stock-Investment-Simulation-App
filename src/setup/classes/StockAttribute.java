package setup.classes;

/**
 * StockAttribute gets price, ticker name and value and company name.
 */
public class StockAttribute {

  private final String ticker;
  private final String companyName;
  private final double price;

  /**
   * StockAttribute gets ticker, companyName and price.
   *
   * @param ticker      ticker value of company.
   * @param companyName company name.
   * @param price       price of stock.
   */

  public StockAttribute(String ticker, String companyName, double price) {
    this.ticker = ticker;
    this.companyName = companyName;
    this.price = price;
  }

  /**
   * Method is used to access the instance variable price.
   *
   * @return the price of the stock value
   */
  public double getPrice() {
    return price;
  }

  /**
   * Method is used to access the instance variable ticker.
   *
   * @return the ticker value of a stock.
   */
  public String getTicker() {
    return ticker;
  }

  /**
   * Method is used to access the instance variable company name.
   *
   * @return the company name of a portfolio
   */
  public String getCompanyName() {
    return companyName;
  }

}
