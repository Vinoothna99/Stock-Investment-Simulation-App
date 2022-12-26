package modal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import setup.interfaces.SetUpStockData;

/**
 * Performs operations for Flexible Portfolio.
 */
public class FlexibleStock extends FlexiblePortfolioModalImpl {

  private static final DecimalFormat df = new DecimalFormat("0.00");
  private static final String COMMA_DELIMITER = ",";
  private static final String NEW_LINE_SEPARATOR = "\n";

  BufferedReader fileReader = null;
  FileWriter fileWriter = null;

  /**
   * Constructor of class.
   *
   * @param delegate object delegation.
   */
  public FlexibleStock(Stock delegate) {
    super(delegate);
  }


  @Override
  String readCsv(String fileName, String path, SetUpStockData stockDataObject, String date) {
    try {
      String data = "";
      String line;
      //Create the file reader
      fileReader = new BufferedReader(new FileReader(path));
      //Read the file line by line starting from the second line
      while ((line = fileReader.readLine()) != null) {
        //Get all tokens available in line
        String[] tokens = line.split(COMMA_DELIMITER);
        if (tokens.length > 2) {
          if (tokens[1].equals("STRATEGY")) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate strategyDate = LocalDate.parse(tokens[0]);
            LocalDate givenDate = LocalDate.parse(date);
            String formatted = dateFormat.format(new Date());
            LocalDate todayDate = LocalDate.parse(formatted);
            if (strategyDate.isAfter(todayDate) || strategyDate.isAfter(givenDate)) {
              continue;
            }
            for (int i = 4; i < tokens.length; i++) {
              double stockPrice = stockDataObject.getPrice(tokens[i].split(" ")[0], date);
              String s = tokens[i].split(" ")[1];

              if (stockPrice > 0 && isValidDouble(s)) {

                if (Double.parseDouble(s) <= 0) {
                  continue;
                }

                double investmentAmount = Double.parseDouble(tokens[2]) -
                    (Double.parseDouble(tokens[3]) * (tokens.length - 4));
                double percentage = Double.parseDouble(s);
                double numberOfShares = (double) (investmentAmount * percentage / 100) / stockPrice;
                String shares = df.format(numberOfShares);

                data += tokens[0] + COMMA_DELIMITER + tokens[i].split(" ")[0]
                    + COMMA_DELIMITER
                    + shares + COMMA_DELIMITER + stockPrice + COMMA_DELIMITER
                    + "BUY" + COMMA_DELIMITER + tokens[3] + NEW_LINE_SEPARATOR;

              }

            }


          } else {
            for (int i = 3; i < tokens.length; i++) {
              double stockPrice = stockDataObject.getPrice(tokens[i].split(" ")[0], date);
              String s = tokens[i].split(" ")[1];
              if (stockPrice == -1 || !isValidDouble(s)) {
                return "";
              } else {
                if (Double.parseDouble(s) <= 0) {
                  continue;
                }
                data += tokens[0] + COMMA_DELIMITER + tokens[i].split(" ")[0]
                    + COMMA_DELIMITER
                    + s + COMMA_DELIMITER + stockPrice + COMMA_DELIMITER
                    + tokens[1] + COMMA_DELIMITER + tokens[2] + NEW_LINE_SEPARATOR;
              }
            }
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
  public String readPortfolio(String path) {
    try {
      String data = "";
      String line;

      //Create the file reader
      fileReader = new BufferedReader(new FileReader(path));

      //Read the file line by line starting from the second line
      while ((line = fileReader.readLine()) != null) {
        data = data + line + NEW_LINE_SEPARATOR;
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
    return delegate.checkValidData(data, stockDataObject, tokenSeparator);
  }

  private boolean isValidDouble(String string) {
    try {
      double a = Double.parseDouble(string);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }

  }

  @Override
  void writeCsv(String data, String filePath, String lineSeparator, String tokenSeparator) {
    File file = new File(filePath);

    try {
      if (file.exists()) {
        data = readPortfolio(filePath) + data;
      }

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

  }
}
