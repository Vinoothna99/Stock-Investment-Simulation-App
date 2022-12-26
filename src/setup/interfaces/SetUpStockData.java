package setup.interfaces;

/**
 * Interface SetUpStockData  creates and stores prices in a text file, returns prices for certain
 * ticker and gives ticker name for company and price.
 */

public interface SetUpStockData {

  /**
   * Method creates random numbers and store price in a text file.
   */
  void updatePricesToday();

  /**
   * Method represents the price value of ticker.
   */
  double getPrice(String ticker);

  /**
   * Method represents the price value of ticker.
   *
   * @param ticker represents the ticker name
   * @param date   represents the ticker date
   * @return the price of a certain ticker
   */
  double getPrice(String ticker, String date);

  /**
   * Method represents Ticker company name and price.
   */
  void getEnlistedCompanies();


}
