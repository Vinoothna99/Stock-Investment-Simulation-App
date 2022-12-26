package modal;

import java.text.DecimalFormat;
import setup.interfaces.SetUpStockData;

/**
 * Class implements the investment strategy operations on flexible portfolio.
 */
public class InvestmentStrategyImpl extends FlexibleStock implements InvestmentStrategy {

  private static final DecimalFormat df = new DecimalFormat("0.00");
  private static final String COMMA_DELIMITER = ",";
  private static final String NEW_LINE_SEPARATOR = "\n";
  private static final String SPACE_SEPARATOR = " ";
  private static final String absolutePath = System.getProperty("user.dir");
  private static final String separator = System.getProperty("file.separator");

  /**
   * Constructor of class.
   *
   * @param delegate object delegation.
   */
  public InvestmentStrategyImpl(Stock delegate) {
    super(delegate);
  }

  @Override
  public boolean singleTimeInvestment(String fileName, String data,
      SetUpStockData stockDataObject) {

    String filePath = absolutePath + separator + "data" + separator + fileName + ".csv";
    String[] lines = data.split(COMMA_DELIMITER);

    if (lines.length < 4) {
      return false;
    } else {
      double investmentAmount = Double.parseDouble(lines[2]) -
          (Double.parseDouble(lines[3]) * (lines.length - 4));

      boolean isDataValid = checkValidStrategy(data, stockDataObject);
      if (!isDataValid) {
        return false;
      }
      double price = stockDataObject.getPrice("AA", lines[0]);
      String result = "";

      if (price == 0) {
        result = lines[0] + ",STRATEGY," + lines[2] + "," + lines[3] + ",";
        for (int i = 4; i < lines.length; i++) {
          result = result + lines[i] + ",";
        }

      } else {

        result = lines[0] + ",BUY," + lines[3] + ",";
        for (int i = 4; i < lines.length; i++) {

          String[] tokens = lines[i].split(" ");
          double stockPrice = stockDataObject.getPrice(tokens[0].trim(), lines[0]);
          double percentage = Double.parseDouble(tokens[1]);
          if (stockPrice > 0) {
            double numberOfShares = (double) (investmentAmount * percentage / 100) / stockPrice;
            String shares = df.format(numberOfShares);
            result = result + tokens[0] + " " + shares + ",";
          } else {
            return false;
          }


        }
      }

      result = result + NEW_LINE_SEPARATOR;
      writeCsv(result, filePath, COMMA_DELIMITER, SPACE_SEPARATOR);
    }
    return checkFileName(fileName + ".csv");
  }

  @Override
  public boolean futureInvestmentStrategy(String fileName, String tickerValues,
      SetUpStockData stockDataObject) {
    String[] lines = tickerValues.split("\n");
    for (String line : lines) {
      boolean flag = singleTimeInvestment(fileName, line, stockDataObject);
      if (!flag) {
        return false;
      }
    }
    return true;
  }


  private boolean checkValidStrategy(String data, SetUpStockData stockDataObject) {
    String[] lines = data.split(",");
    double total = 0;
    for (int i = 4; i < lines.length; i++) {
      String[] tokens = lines[i].split(" ");
      double stockPrice = stockDataObject.getPrice(tokens[0].trim());
      if (stockPrice == -1) {
        return false;
      }
      try {
        double value = Double.parseDouble(tokens[1]);
        total = total + value;
      } catch (NumberFormatException e) {
        return false;
      }
    }

    return total == 100;

  }


}
