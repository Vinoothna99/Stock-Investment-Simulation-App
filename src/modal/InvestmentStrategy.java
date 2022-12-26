package modal;

import setup.interfaces.SetUpStockData;

/**
 * Interface provides all the functionality of investment for a user portfolio.
 */
public interface InvestmentStrategy {

  /**
   * Method allows the user to invest money in percentages on a portfolio on user certified dates.
   *
   * @param fileName        represents the portfolio name entered by the user.
   * @param tickerValues    represents the ticker value entered by the user.
   * @param stockDataObject used to access the API to get stock data values for particular tickers
   * @return the status message of the single time investment portfolio
   */
  boolean singleTimeInvestment(String fileName, String tickerValues,
      SetUpStockData stockDataObject);

  /**
   * Method allows user to invest money recurring over a period of time.
   *
   * @param fileName        represents the portfolio name entered by the user.
   * @param tickerValues    represents the ticker value entered by the user.
   * @param stockDataObject used to access the API to get stock data values for particular tickers
   * @return the status message of the future investment strategy portfolio
   */
  boolean futureInvestmentStrategy(String fileName, String tickerValues,
      SetUpStockData stockDataObject);
}
