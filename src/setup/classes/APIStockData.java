package setup.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import setup.classesabstract.SetUpStockDataImpl;
import setup.interfaces.SetUpStockData;

/**
 * New Implementation of SetUpStockData to use AlphaVantage API.
 */

public class APIStockData
    extends SetUpStockDataImpl
    implements SetUpStockData {

  private static final String COMMA_DELIMITER = ",";
  private static final String NEW_LINE_SEPARATOR = "\n";
  //Stock attributes index
  private static final int TICKER_IDX = 0;
  private static final int COMPANY_NAME_IDX = 1;
  private static final int STOCK_PRICE = 2;
  String absolutePath = System.getProperty("user.dir");
  String separator = System.getProperty("file.separator");
  private final String fileName = absolutePath + separator + "data"
      + separator + "listed_companies.csv";
  String apiKey = "D32EVZCEU7HZUFYQ";
  URL url = null;
  BufferedReader fileReader = null;
  FileWriter fileWriter = null;

  @Override
  protected String getTodayDate() {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    return df.format(new Date());
  }

  @Override
  public double getPrice(String ticker) {
    double returnValue;
    try {
      url = new URL("https://www.alphavantage"
          + ".co/query?function=TIME_SERIES_DAILY_ADJUSTED"
          + "&outputsize=full"
          + "&symbol"
          + "=" + ticker + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
          + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      BufferedReader bufferReader = new BufferedReader(
          new InputStreamReader(url.openStream()));

      String inputLine;
      while ((inputLine = bufferReader.readLine()) != null) {
        output.append(inputLine).append(NEW_LINE_SEPARATOR);
      }
      bufferReader.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + ticker);
    }
    String result = output.toString();
    String errorMessage = "{\n"
        + "    \"Error Message\": \"Invalid API call. Please retry or visit the documentation"
        + " (https://www.alphavantage.co/documentation/) for TIME_SERIES_DAILY.\"\n" + "}";
    if (result.equals(errorMessage)) {
      return -1;
    } else {
      String[] lines = result.split(NEW_LINE_SEPARATOR);
      String[] prices = lines[1].split(COMMA_DELIMITER);
      try {
        returnValue = Double.parseDouble(prices[5]);
      } catch (ArrayIndexOutOfBoundsException exception) {
        return -1;
      }
    }
    return returnValue;
  }


  @Override
  public double getPrice(String ticker, String date) {
    double returnValue;
    String[] givenPriceDate = date.split("-");
    int givenYear = Integer.parseInt(givenPriceDate[0]);
    int givenMonth = Integer.parseInt(givenPriceDate[1]);
    int givenDay = Integer.parseInt(givenPriceDate[2]);
    LocalDate givenDate = LocalDate.of(givenYear, givenMonth, givenDay);
    try {
      url = new URL("https://www.alphavantage"
          + ".co/query?function=TIME_SERIES_DAILY_ADJUSTED"
          + "&outputsize=full"
          + "&symbol"
          + "=" + ticker + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
          + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      BufferedReader bufferReader = new BufferedReader(
          new InputStreamReader(url.openStream()));

      String inputLine;
      while ((inputLine = bufferReader.readLine()) != null) {
        output.append(inputLine).append(NEW_LINE_SEPARATOR);
      }
      bufferReader.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + ticker);
    }
    String result = output.toString();
    String errorMessage = "{\n" +
        "    \"Error Message\": \"Invalid API call. Please retry or visit the documentation" +
        " (https://www.alphavantage.co/documentation/) for TIME_SERIES_DAILY.\"\n" +
        "}";
    if (result.equals(errorMessage)) {
      return -1;
    } else {
      String[] lines = result.split(NEW_LINE_SEPARATOR);
      String index = "0";
      for (int i = 1; i < lines.length; i++) {
        String[] prices = lines[i].split(COMMA_DELIMITER);
        String[] stockPriceDate = prices[0].split("-");
        int year = Integer.parseInt(stockPriceDate[0]);
        int month = Integer.parseInt(stockPriceDate[1]);
        int day = Integer.parseInt(stockPriceDate[2]);
        LocalDate stockDate = LocalDate.of(year, month, day);
        //Getting the current date

        if (givenDate.isEqual(stockDate)) {
          index = i + "";
          break;
        } else if (givenDate.isAfter(stockDate)) {
          if (i > 1) {
            int r = i - 1;
            index = r + "";
            break;
          } else {
            index = "0";
            break;
          }

        }

      }

      try {
        if (index.equals("0")) {
          returnValue = 0.00;
        } else {
          String[] prices = lines[Integer.parseInt(index)].split(COMMA_DELIMITER);
          returnValue = Double.parseDouble(prices[4]);
        }

      } catch (ArrayIndexOutOfBoundsException exception) {
        return -1;
      }
    }
    return returnValue;

  }

  @Override
  public void getEnlistedCompanies() {
    String filePath = absolutePath + separator + "data" + separator + "listed_companies-copy.csv";
    writeToCsvFile(filePath);

  }

  @Override
  protected boolean readCsvFile(String fileName) {
    return false;
  }

  @Override
  protected void writeToCsvFile(String path) {

    File file = new File(path);
    String fileHeader = "ticker,name";
    try {
      file.delete();
      file.createNewFile();

      fileWriter = new FileWriter(path);

      //Write the CSV file header
      fileWriter.append(fileHeader);

      //Add a new line separator after the header
      fileWriter.append(NEW_LINE_SEPARATOR);
      String line;
      //Create the file reader
      fileReader = new BufferedReader(new FileReader(fileName));

      //Read the CSV file header to skip it and also grab last modified date.
      fileReader.readLine();

      //Read the file line by line starting from the second line
      while ((line = fileReader.readLine()) != null) {
        String[] tokens = line.split(COMMA_DELIMITER);
        if (tokens.length == 1) {
          fileWriter.append(tokens[0]);
          fileWriter.append(NEW_LINE_SEPARATOR);
        } else {
          fileWriter.append(tokens[0]);
          fileWriter.append(COMMA_DELIMITER);
          fileWriter.append(tokens[1]);
          fileWriter.append(NEW_LINE_SEPARATOR);
        }

        //Get all tokens available in line
      }


    } catch (Exception e) {
      System.out.println("Error in CsvFileWriter !!!");
      e.printStackTrace();
    } finally {

      try {
        fileWriter.flush();
        fileWriter.close();
        file.setWritable(false);
        file.setReadable(true);
        file.setExecutable(false);
      } catch (IOException e) {
        System.out.println("Error while flushing/closing fileWriter !!!");
        e.printStackTrace();
      }

    }

  }


}
