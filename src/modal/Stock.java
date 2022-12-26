package modal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import setup.interfaces.SetUpStockData;

/**
 * Stock extends StockModal Implementation class and its methods.
 */
public class Stock extends StockModalImpl {

  private static final String COMMA_DELIMITER = ",";
  private static final String NEW_LINE_SEPARATOR = "\n";

  //Stock attributes index
  private static final int TICKER_IDX = 0;
  private static final int NUMBER_OF_SHARES = 1;

  BufferedReader fileReader = null;
  FileWriter fileWriter = null;

  @Override
  String readCsv(String fileName, String path, SetUpStockData stockDataObject) {
    try {
      String data = "";
      String line;

      File file = new File(path);
      //Create the file reader
      fileReader = new BufferedReader(new FileReader(path));

      //Read the file line by line starting from the second line
      while ((line = fileReader.readLine()) != null) {
        //Get all tokens available in line
        String[] tokens = line.split(COMMA_DELIMITER);

        if (tokens.length == 2) {
          double stockPrice = stockDataObject.getPrice(tokens[TICKER_IDX]);
          String s = tokens[NUMBER_OF_SHARES];
          if (stockPrice == -1 || !isValidInteger(s)) {
            return "";
          } else {

            data = data + tokens[TICKER_IDX] + COMMA_DELIMITER + tokens[NUMBER_OF_SHARES]
                + COMMA_DELIMITER + stockPrice + NEW_LINE_SEPARATOR;
          }


        } else {

          return "";
        }

      }

      return data;


    } catch (Exception e) {
      System.out.println("Error in CsvFileReader !!!");
      e.printStackTrace();
      return "";
    } finally {
      try {
        fileReader.close();
      } catch (IOException e) {
        System.out.println("Error while closing fileReader !!!");
        e.printStackTrace();
      }
    }

  }

  @Override
  boolean checkValidData(String data, SetUpStockData stockDataObject, String tokenSeparator) {
    String[] tokens = data.split(tokenSeparator);

    if (tokens.length == 2) {
      double stockPrice = stockDataObject.getPrice(tokens[TICKER_IDX].trim());
      String s = tokens[NUMBER_OF_SHARES].trim();
      return stockPrice != -1 && isValidInteger(s);
    } else {
      return false;
    }

  }

  private boolean isValidInteger(String string) {
    try {
      int a = Integer.parseInt(string);
      return a > 0;
    } catch (NumberFormatException e) {
      return false;
    }

  }

  @Override
  void writeCsv(String data, String filePath, String lineSeparator, String tokenSeparator) {
    File file = new File(filePath);

    try {

      file.delete();
      file.createNewFile();
      fileWriter = new FileWriter(filePath);

      String[] lines = data.split(lineSeparator);
      //Write a new student object list to the CSV file

      for (String line : lines) {

        String[] stock = line.split(tokenSeparator);
        fileWriter.append(stock[TICKER_IDX]);
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(stock[NUMBER_OF_SHARES]);
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
}
