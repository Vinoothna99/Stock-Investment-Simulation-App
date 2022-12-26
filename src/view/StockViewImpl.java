package view;

import java.io.PrintStream;
import java.text.DecimalFormat;

/**
 * StockViewImpl implements StockView.
 */

public class StockViewImpl implements StockView {

  private static final String COMMA_DELIMITER = ",";
  private static final String NEW_LINE_SEPARATOR = "\n";

  private static final int TICKER_IDX = 0;
  private static final int NUMBER_OF_SHARES = 1;

  private static final int PRICE_OF_SHARES = 2;

  private static final DecimalFormat df = new DecimalFormat("0.00 \n");
  final PrintStream out;

  /**
   * Creating StockViewImpl to printout statement.
   *
   * @param out PrintStream object.
   */
  public StockViewImpl(PrintStream out) {
    this.out = out;
  }

  @Override
  public void displayMenu() {
    this.out.printf("-------------------------------------------------"
        + "------------------------------------------ \n");
    this.out.printf("Please input required option number from the menu \n");
    this.out.printf("1. Create Portfolio \n");
    this.out.printf("2. View Portfolio Composition \n");
    this.out.printf("3. Find Value Of Portfolio \n");
    this.out.printf("4. Download Portfolio \n");
    this.out.printf("5. Download List Of Enlisted Companies \n");
    this.out.printf("6. Quit \n");
    this.out.printf("----------------------------------------------------------"
        + "---------------------------------- \n");
  }

  @Override
  public void displayInvalidMenuOption() {
    this.out.printf(" \nInvalid input. Please input a number within the limits "
        + "of provided menu. \n");
  }

  @Override
  public void displayPortfolioMenu() {
    this.out.printf(" \nPlease input required option number from the menu \n");
    this.out.printf("1. Upload CSV \n");
    this.out.printf("2. Input Ticker Values \n");
    this.out.printf("3. Quit \n");
  }

  @Override
  public void displayPortfolioNameMessage() {
    this.out.printf(" \nPlease enter the Portfolio Name \n");
  }

  @Override
  public void displayReplaceConfirm() {
    this.out.printf(" \nA portfolio already exists with this name. " +
        "Would you like to replace it? \n");
    this.out.printf("1. Yes \n");
    this.out.printf("2. No \n");
  }

  @Override
  public void displayInvalidPortfolioName() {
    this.out.printf(" \nPortfolio name should only consist of alphabets \n");
  }

  @Override
  public void displayPath() {
    this.out.printf(" \nPlease specify the path of CSV file. \n");
  }

  @Override
  public void displaySuccessMessage() {
    this.out.printf(" \nPortfolio Successfully Created. \n");
  }

  @Override
  public void displayFailureMessage() {
    this.out.printf(" \nPortfolio creation failed. Please try again. \n");
  }

  @Override
  public void displayEnterTickerMessage() {
    this.out.printf("\nEnter one or more ticker values along with number of " +
        "shares. Ex: AAA 80,BBB 45 \n");
  }

  @Override
  public void displayPathDoesNotExist() {
    this.out.printf("\nFile doesn't exist at provided path. \n");
  }

  @Override
  public void displayPortfolioDoesNotExist() {
    this.out.printf("\nPortfolio doesn't exist with given name. \n");
  }

  @Override
  public void displayDownloadSuccessMessage() {
    this.out.printf("\nDownloaded Successfully! \n");
  }

  @Override
  public void displayDownloadFailMessage() {
    this.out.printf("\nDownloaded Failed :( \n");
  }


  @Override
  public void displayPortfolioComposition(String data) {

    this.out.printf("TICKER, No. Of SHARES \n");
    String[] lines = data.split(NEW_LINE_SEPARATOR);
    //Write a new student object list to the CSV file

    for (String line : lines) {
      String[] stock = line.split(COMMA_DELIMITER);

      this.out.printf(stock[TICKER_IDX].trim() + ", ");
      double shares = Double.parseDouble(stock[NUMBER_OF_SHARES].trim());
      this.out.printf(df.format(shares));
      this.out.printf(NEW_LINE_SEPARATOR);

    }

  }

  @Override
  public void displayValueOfPortfolio(String data) {

    this.out.printf("TICKER, No. Of SHARES, PRICE of SHARES \n");
    String[] lines = data.split(NEW_LINE_SEPARATOR);
    //Write a new student object list to the CSV file
    double totalValue = 0;
    for (String line : lines) {
      String[] stock = line.split(COMMA_DELIMITER);

      this.out.printf(stock[TICKER_IDX].trim() + ", ");
      double shares = Double.parseDouble(stock[NUMBER_OF_SHARES].trim());
      this.out.printf(df.format(shares) + ", ");
      double stockPrice =
          Double.parseDouble(stock[PRICE_OF_SHARES].trim())
              * Double.parseDouble(stock[NUMBER_OF_SHARES].trim());
      this.out.printf(df.format(stockPrice));
      totalValue = totalValue + stockPrice;
      this.out.printf(NEW_LINE_SEPARATOR);

    }
    this.out.printf("\n Total Value of Portfolio is: " + df.format(totalValue));

  }

  @Override
  public void displayFileIsCorrupt() {
    this.out.printf("\nPortfolio File Corrupted. Cannot Retrieve data :( \n");
  }
}
