package modal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import setup.interfaces.SetUpStockData;

/**
 * Class Implements FlexiblePortfolio Interface.
 */
public abstract class FlexiblePortfolioModalImpl
    implements FlexiblePortfolioModal {

  private static final String COMMA_DELIMITER = ",";
  private static final String NEW_LINE_SEPARATOR = "\n";
  private static final String SPACE_SEPARATOR = " ";
  private static final String absolutePath = System.getProperty("user.dir");
  private static final String separator = System.getProperty("file.separator");
  final Stock delegate;

  /**
   * Constructor of class.
   *
   * @param delegate Inflexible Portfolio Controllerobject.
   */
  public FlexiblePortfolioModalImpl(Stock delegate) {
    this.delegate = delegate;
  }


  @Override
  public boolean uploadCsv(String fileName, String path, SetUpStockData stockDataObject) {
    String formatted = this.getTodayDate();
    String data = readPortfolio(path);
    String[] lines = data.split(NEW_LINE_SEPARATOR);
    if (lines.length < 1) {
      return false;
    } else {
      for (int i = 0; i < lines.length; i++) {
        boolean isDataValid = checkValidData(lines[i].trim(), stockDataObject, COMMA_DELIMITER);
        if (!isDataValid) {
          return false;
        }
      }
    }

    String uploadData = "";
    for (String line : lines) {
      String[] tokens = line.split(COMMA_DELIMITER);
      uploadData += tokens[0] + SPACE_SEPARATOR + tokens[1] + COMMA_DELIMITER;
    }
    if (!uploadData.equals("")) {
      uploadData =
          formatted + COMMA_DELIMITER + "BUY" + COMMA_DELIMITER + "0" + COMMA_DELIMITER + uploadData
              + NEW_LINE_SEPARATOR;
      String filePath = absolutePath + separator + "data" + separator + fileName + ".csv";
      writeCsv(uploadData, filePath, NEW_LINE_SEPARATOR, COMMA_DELIMITER);
      return checkFileName(fileName + ".csv");
    } else {
      return false;
    }

  }

  @Override
  public boolean enterTickerValues(String fileName, String data, SetUpStockData stockDataObject) {
    String filePath = absolutePath + separator + "data" + separator + fileName + ".csv";
    String[] lines = data.split(COMMA_DELIMITER);
    if (lines.length < 3) {
      return false;
    } else {
      for (int i = 3; i < lines.length; i++) {
        boolean isDataValid = checkValidData(lines[i].trim(), stockDataObject, SPACE_SEPARATOR);
        if (!isDataValid) {
          return false;
        }
      }
      data = data + NEW_LINE_SEPARATOR;
      writeCsv(data, filePath, COMMA_DELIMITER, SPACE_SEPARATOR);
    }
    return checkFileName(fileName + ".csv");
  }


  @Override
  public boolean sellShares(String fileName, String tickerValues, SetUpStockData stockDataObject,
      String date) {
    boolean flag = false;
    String path = absolutePath + separator + "data" + separator + fileName + ".csv";
    String data;


    String[] lines = tickerValues.split(COMMA_DELIMITER);
    if (lines.length < 3) {
      return false;
    } else {
      for (int i = 3; i < lines.length; i++) {
        boolean isDataValid = checkValidData(lines[i].trim(), stockDataObject, SPACE_SEPARATOR);
        if (!isDataValid) {
          return false;
        }
      }


      data = readCsv(fileName, path, stockDataObject, date);

      ArrayList<StockNode> value = compositionOnCertainDate(data, date);

      if (value.size() == 0) {
        return false;
      } else {
        for (int i = 3; i < lines.length; i++) {
          String[] tickers = lines[i].split(SPACE_SEPARATOR);
          for (StockNode node : value) {
            if (tickers[0].equals(node.getTicker())) {

              if (Integer.parseInt(tickers[1]) > node.getShares()) {

                return false;
              } else {
                flag = true;
              }

            }
          }

          if (flag) {
            flag = !flag;
          } else {

            return false;
          }

        }
      }


    }

    return true;
  }

  @Override
  public String compositionOfPortfolio(String fileName,
      SetUpStockData stockDataObject, String date) {

    String path = absolutePath + separator + "data" + separator + fileName + ".csv";
    String data;

    data = readCsv(fileName, path, stockDataObject, date);

    ArrayList<StockNode> value = compositionOnCertainDate(data, date);

    String returnData = "";
    if (value.size() == 0) {
      return returnData;
    }
    for (StockNode node : value) {
      returnData += node.getTicker() + COMMA_DELIMITER + node.getShares() + NEW_LINE_SEPARATOR;
    }

    return returnData;

  }

  private ArrayList<StockNode> compositionOnCertainDate(String data, String date) {

    String[] givenPriceDate = date.split("-");
    int givenYear = Integer.parseInt(givenPriceDate[0]);
    int givenMonth = Integer.parseInt(givenPriceDate[1]);
    int givenDay = Integer.parseInt(givenPriceDate[2]);
    LocalDate givenDate = LocalDate.of(givenYear, givenMonth, givenDay);

    ArrayList<StockNode> currentList = new ArrayList<StockNode>();
    String[] lines = data.split(NEW_LINE_SEPARATOR);

    for (String line : lines) {

      String[] transaction = line.split(COMMA_DELIMITER);
      String[] stockPriceDate = transaction[0].split("-");
      int year = Integer.parseInt(stockPriceDate[0]);
      int month = Integer.parseInt(stockPriceDate[1]);
      int day = Integer.parseInt(stockPriceDate[2]);
      LocalDate stockDate = LocalDate.of(year, month, day);
      if (stockDate.isAfter(givenDate)) {
        continue;
      }

      StockNode stock = new StockNode(transaction[0], transaction[1],
          Double.parseDouble(transaction[2]), Double.parseDouble(transaction[3]),
          Integer.parseInt(transaction[5]));

      if (transaction[4].equals("BUY")) {

        boolean flag = false;
        for (StockNode node : currentList) {
          if (node.getTicker().equals(transaction[1])) {
            flag = true;
            node.setShares(node.getShares() + Double.parseDouble(transaction[2]));
          }
        }
        if (!flag) {
          currentList.add(stock);
        }

      } else if (transaction[4].equals("SELL")) {
        for (StockNode node : currentList) {
          if (node.getTicker().equals(transaction[1])) {
            node.setShares(node.getShares() - Double.parseDouble(transaction[2]));
          }
        }

      }

    }
    return currentList;
  }

  @Override
  public String valueOfPortfolio(String fileName, SetUpStockData stockDataObject, String date) {

    String path = absolutePath + separator + "data" + separator + fileName + ".csv";
    String data;
    String newDate;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    newDate = df.format(new Date());
    data = readCsv(fileName, path, stockDataObject, date);
    ArrayList<StockNode> value = compositionOnCertainDate(data, newDate);
    String returnData = "";
    if (value.size() == 0) {
      return returnData;
    }
    for (StockNode node : value) {
      returnData += node.getTicker() + COMMA_DELIMITER + node.getShares()
          + COMMA_DELIMITER + node.getPrice() + NEW_LINE_SEPARATOR;
    }

    return returnData;

  }

  @Override
  public String valueOfPortfolioOnSpecificDate(String fileName,
      SetUpStockData stockDataObject, String date) {
    String path = absolutePath + separator + "data" + separator + fileName + ".csv";
    String data;
    data = readCsv(fileName, path, stockDataObject, date);
    ArrayList<StockNode> value = compositionOnCertainDate(data, date);
    String returnData = "";
    if (value.size() == 0) {
      return returnData;
    }
    for (StockNode node : value) {
      returnData += node.getTicker() + COMMA_DELIMITER + node.getShares()
          + COMMA_DELIMITER + node.getPrice() + NEW_LINE_SEPARATOR;
    }

    return returnData;
  }

  @Override
  public String costBasisOfPortfolio(String fileName, SetUpStockData stockDataObject, String date) {
    String path = absolutePath + separator + "data" + separator + fileName + ".csv";
    String[] givenPriceDate = date.split("-");
    int givenYear = Integer.parseInt(givenPriceDate[0]);
    int givenMonth = Integer.parseInt(givenPriceDate[1]);
    int givenDay = Integer.parseInt(givenPriceDate[2]);
    LocalDate givenDate = LocalDate.of(givenYear, givenMonth, givenDay);

    String data;
    data = readCsv(fileName, path, stockDataObject, date);

    String[] lines = data.split(NEW_LINE_SEPARATOR);
    String returnData = "";

    for (String line : lines) {
      String[] transaction = line.split(COMMA_DELIMITER);
      String[] stockPriceDate = transaction[0].split("-");
      int year = Integer.parseInt(stockPriceDate[0]);
      int month = Integer.parseInt(stockPriceDate[1]);
      int day = Integer.parseInt(stockPriceDate[2]);
      LocalDate stockDate = LocalDate.of(year, month, day);
      if (stockDate.isAfter(givenDate)) {
        continue;
      }

      if (transaction[4].equals("BUY")) {
        returnData += transaction[0] + COMMA_DELIMITER + transaction[4]
            + COMMA_DELIMITER + transaction[1] + COMMA_DELIMITER
            + transaction[2] + COMMA_DELIMITER + stockDataObject.getPrice(transaction[1],
            transaction[0]) + COMMA_DELIMITER + transaction[5] + NEW_LINE_SEPARATOR;


      } else {
        returnData += transaction[0] + COMMA_DELIMITER + transaction[4]
            + COMMA_DELIMITER + transaction[1] + COMMA_DELIMITER
            + transaction[2] + COMMA_DELIMITER + "0.00"
            + COMMA_DELIMITER + transaction[5] + NEW_LINE_SEPARATOR;

      }

    }
    return returnData;
  }

  @Override
  public String performanceChart(String fileName, SetUpStockData stockDataObject, String fDate,
      String tDate, String datesString) {

    String[] dates = datesString.split(COMMA_DELIMITER);
    String returnData = "\nPerformance of portfolio " + fileName + " from " + fDate
        + " to " + tDate + "\n";

    List<Integer> prices = new ArrayList<>();

    for (String date : dates) {
      int portfolioValue = (int) (getTotalValue(fileName, stockDataObject, date) / 1000);
      prices.add(portfolioValue);
    }

    List<Integer> sortedlist = new ArrayList<>(prices);
    // sort list in natural order
    Collections.sort(sortedlist);
    int maxValue = sortedlist.get(prices.size() - 1);

    if (maxValue <= 50) {
      for (int i = 0; i < prices.size(); i++) {
        returnData += dates[i] + " : ";
        for (int j = 1; j < prices.get(i); j++) {
          returnData += "*";
        }
        returnData += NEW_LINE_SEPARATOR;
      }
      returnData += "Scale: * = $1000\n";
    } else if (maxValue > 50 && maxValue <= 250) {
      for (int i = 0; i < prices.size(); i++) {
        returnData += dates[i] + " : ";
        for (int j = 5; j < prices.get(i); j = j + 5) {
          returnData += "*";
        }
        returnData += NEW_LINE_SEPARATOR;
      }
      returnData += "Scale: * = $5000\n";
    } else if (maxValue > 250) {
      for (int i = 0; i < prices.size(); i++) {
        returnData += dates[i] + " : ";
        for (int j = 55; j < prices.get(i); j = j + 5) {
          returnData += "*";
        }
        returnData += NEW_LINE_SEPARATOR;
      }
      returnData += "Base Amount : $50000, Scale: * = $5000\n";
    }

    return returnData;
  }

  private double getTotalValue(String fileName, SetUpStockData stockDataObject, String date) {
    String data = valueOfPortfolioOnSpecificDate(fileName, stockDataObject, date);
    if (data.equals("")) {
      return 0.00;
    } else {

      String[] lines = data.split(NEW_LINE_SEPARATOR);
      double totalValue = 0;
      for (String line : lines) {

        String[] stock = line.split(COMMA_DELIMITER);
        double stockPrice =
            (Double.parseDouble(stock[1].trim())
                * Double.parseDouble(stock[2].trim()));

        totalValue = totalValue + stockPrice;

      }
      BigDecimal bd = new BigDecimal(totalValue).setScale(2, RoundingMode.HALF_DOWN);
      return bd.doubleValue();
    }

  }


  @Override
  public boolean getPortfolio(String fileName, SetUpStockData stockDataObject) {
    String formatted = this.getTodayDate();
    String data = compositionOfPortfolio(fileName, stockDataObject, formatted);
    if (!data.equals("")) {
      String filePath = absolutePath + separator + "data" + separator + fileName + "-copy.csv";
      File file = new File(filePath);
      FileWriter fileWriter = null;
      try {
        fileWriter = new FileWriter(filePath);
        fileWriter.append(data);

      } catch (Exception e) {
        System.out.println("Error in CsvFileWriter !!!");
        e.printStackTrace();

      } finally {

        try {
          fileWriter.flush();
          fileWriter.close();
        } catch (IOException e) {
          System.out.println("Error while flushing/closing fileWriter !!!");
          e.printStackTrace();

        }

      }
      return checkFileName(fileName + "-copy.csv");
    } else {
      return false;
    }

  }

  @Override
  public boolean checkFileName(String fileName) {
    return delegate.checkFileName(fileName);
  }

  abstract String readCsv(String fileName, String path,
      SetUpStockData stockDataObject, String date);

  abstract boolean checkValidData(String data, SetUpStockData stockDataObject,
      String tokenSeparator);

  abstract void writeCsv(String data, String filePath,
      String lineSeparator, String tokenSeparator);

  private String getTodayDate() {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    return df.format(new Date());
  }

}
