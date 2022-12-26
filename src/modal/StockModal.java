package modal;

import setup.interfaces.SetUpStockData;

/**
 * Interface StockModal constructs methods to upload CSV file, enters ticker values calculate values
 * of portfolio.
 */
public interface StockModal {

  /**
   * This method is used to upload the csv for a portfolio.
   *
   * @param fileName        represents the filename to be uploaded
   * @param path            represents the path of the portfolio
   * @param stockDataObject represents the stock data object
   * @return the csv for a portfolio
   */
  boolean uploadCsv(String fileName, String path, SetUpStockData stockDataObject);

  /**
   * This method is used to take the ticker values from the user.
   *
   * @param fileName        represents the filename to be uploaded
   * @param tickerValues    represents the tickervalues for the portfolio
   * @param stockDataObject represents the stockdata object
   * @return the ticker values from the user
   */
  boolean enterTickerValues(String fileName, String tickerValues, SetUpStockData stockDataObject);

  /**
   * This method is used to retrieve the composition of the portfolio.
   *
   * @param fileName        represents the filename to be uploaded
   * @param stockDataObject represents the stockdata object
   * @return
   */
  String compositionOfPortfolio(String fileName, SetUpStockData stockDataObject);

  /**
   * This method represents the value of the portfolio.
   *
   * @param fileName        represents the filename to be uploaded
   * @param stockDataObject represents the stockdata object
   * @return value of the portfolio
   */
  String valueOfPortfolio(String fileName, SetUpStockData stockDataObject);

  /**
   * This represents the list of enlisted companies.
   *
   * @param stockDataObject represents the stockdata object
   * @return the lsit of companies for specific portfolio.
   */
  boolean displayEnlistedCompanies(SetUpStockData stockDataObject);

  /**
   * This method retrieves the potrfolio of the user.
   *
   * @param fileName        represents the filename to be uploaded
   * @param stockDataObject represents the stockdata object
   * @return the potrfolio of the user.
   */
  boolean getPortfolio(String fileName, SetUpStockData stockDataObject);

  /**
   * This method is used to check if the filename entered by the user is right.
   *
   * @param fileName represents the filename to be uploaded
   * @return filename entered by the user is right.
   */
  boolean checkFileName(String fileName);
}
