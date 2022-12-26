package view;

import java.io.PrintStream;
import java.text.DecimalFormat;

/**
 * Implements FlexiblePortfolioView and extends StockViewImpl to support View for Flexible and
 * Inflexible Portfolios.
 */
public class FlexiblePortfolioViewImpl
    extends StockViewImpl implements FlexiblePortfolioView {

  private static final String COMMA_DELIMITER = ",";
  private static final String NEW_LINE_SEPARATOR = "\n";


  private static final int NUMBER_OF_SHARES = 1;

  private static final int PRICE_OF_SHARES = 2;


  private static final DecimalFormat df = new DecimalFormat("0.00 \n");

  /**
   * Creating StockViewImpl to printout statement.
   *
   * @param out PrintStream object.
   */
  public FlexiblePortfolioViewImpl(PrintStream out) {

    super(out);

  }

  @Override
  public void displayMenu() {
    this.out.printf("-------------------------------------------------"
        + "------------------------------------------ \n");
    this.out.printf("Please input required option number from the menu \n");
    this.out.printf("1. Create Portfolio \n");
    this.out.printf("2. Buy Shares on a Specific Date \n");
    this.out.printf("3. Sell Shares on a Specific Date \n");
    this.out.printf("4. View Composition of a Portfolio\n");
    this.out.printf("5. Value of a Current Portfolio \n");
    this.out.printf("6. Value of a Portfolio on a Specific Date\n");
    this.out.printf("7. Cost Basis of a Portfolio\n");
    this.out.printf("8. Performance of Portfolio Over a Period\n");
    this.out.printf("9. Invest in a Portfolio\n");
    this.out.printf("10. Dollar-Cost Averaging\n");
    this.out.printf("11. Get a Copy of Current Portfolio \n");
    this.out.printf("12. Get a List of Enlisted Companies \n");
    this.out.printf("13. Quit \n");
    this.out.printf("----------------------------------------------------------"
        + "---------------------------------- \n");
  }

  @Override
  public void displayPortfolioComposition(String data) {
    if (data.equals("")) {
      this.out.printf("\nThe Portfolio is Empty\n");
    } else {
      this.out.printf("TICKER, No. Of SHARES\n");
      String[] lines = data.split(NEW_LINE_SEPARATOR);
      for (String line : lines) {
        this.out.printf(line);
        this.out.printf(NEW_LINE_SEPARATOR);
      }
    }

  }

  @Override
  public void displayValueOfPortfolio(String data) {

    String[] lines = data.split(NEW_LINE_SEPARATOR);
    //Write a new student object list to the CSV file
    double totalValue = 0;
    if (data.equals("")) {
      this.out.printf("\nTotal Value of Portfolio is: 0.00 \n");
    } else {
      this.out.printf("TICKER, No. Of SHARES, PRICE\n");
      for (String line : lines) {
        this.out.printf(line);
        this.out.printf(NEW_LINE_SEPARATOR);
        String[] stock = line.split(COMMA_DELIMITER);
        double stockPrice =
            Double.parseDouble(stock[PRICE_OF_SHARES].trim())
                * Double.parseDouble(stock[NUMBER_OF_SHARES].trim());

        totalValue = totalValue + stockPrice;

      }
      this.out.printf("\nTotal Value of Portfolio is: " + df.format(totalValue) + "\n");
    }


  }

  @Override
  public void displayEnterTickerPercentageMessage() {
    this.out.printf("\nEnter one or more ticker values along with percentage of investment such" +
        " that it adds up to 100." +
        " Ex: AAA 30,AAPB 20,IBM 10,A 40 \n");

  }

  @Override
  public void displayPortfolioDate() {
    this.out.printf(" \nPlease enter the date in yyyy-mm-dd format: \n");
  }

  @Override
  public void displayBuySellSuccessMessage() {
    this.out.printf(" \nPortfolio Successfully Modified. \n");
  }

  @Override
  public void displayBuySellFailureMessage() {
    this.out.printf(" \nUnable to Modify Portfolio. Please Try Again. \n");
  }

  @Override
  public void displayDateIncorrect() {
    this.out.printf(" \nDate format is incorrect\n");
  }

  @Override
  public void displayInvalidTransactionMessage() {
    this.out.printf(" \nThese shares cannot be sold. Please enter valid shares to sell.\n");
  }

  @Override
  public void displayConfirmPurchase() {
    this.out.printf(" \nPlease enter the fixed commission fee you would like to pay" +
        " (Only Integer value). The " +
        "commission fee will be added to every company bought. \n");
  }

  @Override
  public void displayCostBasis(String data) {

    if (data.equals("")) {
      this.out.printf("\nTotal Cost Basis is: 0.00 \n");
    } else {
      this.out.printf("DATE,OPERATION, TICKER, No. Of SHARES, PRICE, COMMISSION FEE\n");
      String[] lines = data.split(NEW_LINE_SEPARATOR);
      //Write a new student object list to the CSV file
      double totalValue = 0;
      for (String line : lines) {

        String[] stock = line.split(COMMA_DELIMITER);
        double stockPrice =
            (Double.parseDouble(stock[3].trim())
                * Double.parseDouble(stock[4].trim()))
                + Double.parseDouble(stock[5].trim());
        this.out.printf(
            stock[0] + "," + stock[1] + "," + stock[2] + "," + stock[3] + "," + stockPrice + ","
                + stock[5]);
        this.out.printf(NEW_LINE_SEPARATOR);

        totalValue = totalValue + stockPrice;

      }
      this.out.printf("\nTotal Cost Basis of Portfolio is: " + df.format(totalValue) + "\n");
    }


  }

  @Override
  public void displayPerformanceChart(String data) {
    this.out.printf(NEW_LINE_SEPARATOR + data + NEW_LINE_SEPARATOR);
  }

  @Override
  public void displayFromDate() {

    this.out.printf("\nEnter From Date in yyyy-mm-dd format\n");

  }

  @Override
  public void displayToDate() {
    this.out.printf("\nEnter From To in yyyy-mm-dd format\n");

  }

  @Override
  public void displayValidDates() {
    this.out.printf("\nThe dates are invalid. \nPlease only enter dates from 2000-01-01 " +
        "until today." +
        "\nThe From and to dates shall be at-least 5 days apart.\n");
  }

  @Override
  public void selectTypeOfPortfolio() {
    this.out.printf(" \nPlease select the type of portfolio from the menu \n");
    this.out.printf("1. FLEXIBLE Portfolio\n");
    this.out.printf("2. INFLEXIBLE Portfolio\n");
    this.out.printf("3. Quit \n");
  }

  @Override
  public void displayWrongPortfolioType() {
    this.out.printf(" \nThis operation cannot be performed. Please try again.\n");
  }

  @Override
  public void displayPercentageForEachCompany() {
    this.out.printf(" \nPlease enter the percentage of investment for the following company.\n");
  }

  @Override
  public void amountToInvest() {
    this.out.printf(" \nPlease enter the amount you would like to invest\n");
  }


  @Override
  public void recurringPeriod() {
    this.out.printf(" \nPlease enter the recurring investment period in number of days\n");
  }

  @Override
  public void invalidInput() {
    this.out.printf(" \nYou have entered invalid inputs. Please try again.\n");
  }
}
