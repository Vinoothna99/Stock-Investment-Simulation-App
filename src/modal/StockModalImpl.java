package modal;

import java.io.File;
import setup.interfaces.SetUpStockData;

/**
 * Class StockModal Implements StockModal Interface which allows to upload CSV, enter ticker
 * values,to composite Portfolio, values of portfolio, display Enlisted Companies, download
 * portfolio, checks file name, as well as read and write csv file and checks valid data.
 */
public abstract class StockModalImpl implements StockModal {

  private static final String COMMA_DELIMITER = ",";
  private static final String NEW_LINE_SEPARATOR = "\n";
  private static final String SPACE_SEPARATOR = " ";
  private static final String absolutePath = System.getProperty("user.dir");
  private static final String separator = System.getProperty("file.separator");

  @Override
  public boolean uploadCsv(String fileName, String path, SetUpStockData stockDataObject) {
    String data = readCsv(fileName, path, stockDataObject);

    if (!data.equals("")) {

      String filePath = absolutePath + separator + "data" + separator + fileName + ".csv";
      writeCsv(data, filePath, NEW_LINE_SEPARATOR, COMMA_DELIMITER);
      return checkFileName(fileName + ".csv");
    } else {
      return false;
    }

  }

  @Override
  public boolean enterTickerValues(String fileName, String data, SetUpStockData stockDataObject) {
    String filePath = absolutePath + separator + "data" + separator + fileName + ".csv";
    String[] lines = data.split(COMMA_DELIMITER);
    if (lines.length == 0) {
      return false;
    } else {
      for (String line : lines) {
        boolean isDataValid = checkValidData(line.trim(), stockDataObject, SPACE_SEPARATOR);
        if (!isDataValid) {
          return false;
        }
      }

      writeCsv(data, filePath, COMMA_DELIMITER, SPACE_SEPARATOR);
    }

    return checkFileName(fileName + ".csv");

  }

  @Override
  public String compositionOfPortfolio(String fileName, SetUpStockData stockDataObject) {
    String path = absolutePath + separator + "data" + separator + fileName + ".csv";
    String data;
    data = readCsv(fileName, path, stockDataObject);
    return data;

  }

  @Override
  public String valueOfPortfolio(String fileName, SetUpStockData stockDataObject) {
    String path = absolutePath + separator + "data" + separator + fileName + ".csv";
    String data;
    data = readCsv(fileName, path, stockDataObject);
    return data;
  }

  @Override
  public boolean displayEnlistedCompanies(SetUpStockData stockDataObject) {
    stockDataObject.getEnlistedCompanies();
    return checkFileName("listed_companies-copy.csv");
  }

  @Override
  public boolean getPortfolio(String fileName, SetUpStockData stockDataObject) {
    String path = absolutePath + separator + "data" + separator + fileName + ".csv";
    String data = readCsv(fileName, path, stockDataObject);
    if (!data.equals("")) {

      String filePath = absolutePath + separator + "data" + separator + fileName + "-copy.csv";
      writeCsv(data, filePath, NEW_LINE_SEPARATOR, COMMA_DELIMITER);
      return checkFileName(fileName + "-copy.csv");
    } else {
      return false;
    }

  }

  @Override
  public boolean checkFileName(String fileName) {
    fileName = absolutePath + separator + "data" + separator + fileName;
    File file = new File(fileName);
    return file.exists();
  }

  abstract String readCsv(String fileName, String path, SetUpStockData stockDataObject);

  abstract boolean checkValidData(String data, SetUpStockData stockDataObject,
      String tokenSeparator);

  abstract void writeCsv(String data, String filePath, String lineSeparator, String tokenSeparator);
}
