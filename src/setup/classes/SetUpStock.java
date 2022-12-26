package setup.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import setup.classesabstract.SetUpStockDataImpl;

/**
 * This is use for read and write stock data file.
 */

public class SetUpStock extends SetUpStockDataImpl {

  private static final String COMMA_DELIMITER = ",";
  private static final String NEW_LINE_SEPARATOR = "\n";
  //Stock attributes index
  private static final int TICKER_IDX = 0;
  private static final int COMPANY_NAME_IDX = 1;
  private static final int STOCK_PRICE = 2;
  BufferedReader fileReader = null;
  FileWriter fileWriter = null;
  private final ArrayList<StockAttribute> stocksList;


  public SetUpStock() {
    stocksList = new ArrayList<StockAttribute>();
  }


  @Override
  public boolean readCsvFile(String fileName) {
    boolean dataUptoDate;
    try {

      String line;

      //Create the file reader
      fileReader = new BufferedReader(new FileReader(fileName));

      //Read the CSV file header to skip it and also grab last modified date.
      String[] findDate = fileReader.readLine().split(COMMA_DELIMITER);
      String formatted = this.getTodayDate();
      dataUptoDate = formatted.equals(findDate[3]);

      //Read the file line by line starting from the second line
      while ((line = fileReader.readLine()) != null) {
        //Get all tokens available in line
        String[] tokens = line.split(COMMA_DELIMITER);

        Random random = new Random();

        if (tokens.length > 0) {
          int number = random.nextInt(1000);
          int decimal = random.nextInt(100);
          double price;
          if (dataUptoDate) {
            price = Double.parseDouble(tokens[STOCK_PRICE]);
          } else {
            price = Double.parseDouble(number + "." + decimal);
          }

          //Create a new student object and fill his  data
          StockAttribute stock = new StockAttribute(tokens[TICKER_IDX],
              tokens[COMPANY_NAME_IDX], price);
          stocksList.add(stock);
        }

      }

      return dataUptoDate;

    } catch (Exception e) {
      System.out.println("Error in CsvFileReader !!!");
      e.printStackTrace();
    } finally {
      try {
        fileReader.close();
      } catch (IOException e) {
        System.out.println("Error while closing fileReader !!!");
        e.printStackTrace();
      }
    }

    return false;
  }

  @Override
  public void writeToCsvFile(String fileName) {

    File file = new File(fileName);
    String date = this.getTodayDate();
    String fileHeader = "ticker,name,price_of_stock," + date;
    try {

      file.delete();
      file.createNewFile();

      fileWriter = new FileWriter(fileName);

      //Write the CSV file header
      fileWriter.append(fileHeader);

      //Add a new line separator after the header
      fileWriter.append(NEW_LINE_SEPARATOR);

      //Write a new student object list to the CSV file

      for (StockAttribute stockAttribute : stocksList) {
        fileWriter.append(stockAttribute.getTicker());
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(stockAttribute.getCompanyName());
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(String.valueOf(stockAttribute.getPrice()));
        fileWriter.append(NEW_LINE_SEPARATOR);
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


  @Override
  public String getTodayDate() {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    return df.format(new Date());
  }


  @Override
  public double getPrice(String ticker) {
    for (StockAttribute stockAttribute : stocksList) {
      String stockTicker = stockAttribute.getTicker();
      if (stockTicker.equals(ticker)) {
        return stockAttribute.getPrice();
      }
    }
    return -1;
  }

  @Override
  public double getPrice(String ticker, String date) {
    return 0;
  }


}
