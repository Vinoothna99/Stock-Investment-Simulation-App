package modal;

/**
 * Class is used to maintain an arraylist to stock type nodes.
 */
public class StockNode {

  private final String ticker;
  private final double price;
  private final String date;
  private double shares;

  private int commissionFee;

  /**
   * StockAttribute gets ticker, companyName and price.
   *
   * @param ticker represents ticker value of company.
   * @param date   represents company name.
   * @param price  represents price of stock.
   */

  public StockNode(String date, String ticker, double shares, double price, int commissionFee) {
    this.ticker = ticker;
    this.shares = shares;
    this.price = price;
    this.date = date;
    this.commissionFee = commissionFee;
  }

  /**
   * Returns price of stock.
   *
   * @return price.
   */
  public double getPrice() {
    return price;
  }

  /**
   * Returns ticker of stock.
   *
   * @return ticker.
   */
  public String getTicker() {
    return ticker;
  }

  /**
   * Returns date of stock.
   *
   * @return date.
   */
  public String getDate() {
    return date;
  }

  /**
   * Returns shares of stock.
   *
   * @return shares.
   */
  public double getShares() {
    return shares;
  }

  /**
   * Sets number of shares of stock.
   */
  public void setShares(double shares) {
    this.shares = shares;
  }

  /**
   * Returns commission of stock.
   *
   * @return commission.
   */
  public double getCommission() {
    return commissionFee;
  }

}
