package view;

/**
 * This Interface handles all the output of the program.
 */
public interface StockView {

  /**
   * Method displays the main menu on program execution.
   */
  void displayMenu();

  /**
   * Method displays an error message on invalid user input.
   */
  void displayInvalidMenuOption();

  /**
   * Method displays the portfolio menu on valid user input.
   */
  void displayPortfolioMenu();

  /**
   * Method displays an error message on invalid user input.
   */
  void displayPortfolioNameMessage();

  /**
   * Method tells the user when a portfolio already exists.
   */
  void displayReplaceConfirm();

  /**
   * Method displays an error message on invalid user input of portfolio name.
   */
  void displayInvalidPortfolioName();

  /**
   * Method displays the path entered by the user.
   */
  void displayPath();

  /**
   * Method displays the success message for portfolio creation.
   */
  void displaySuccessMessage();

  /**
   * Method displays the failure message on invalid user input for portfolio creation.
   */
  void displayFailureMessage();

  /**
   * Method asks to enter the ticker values for the portfolio.
   */
  void displayEnterTickerMessage();

  /**
   * Method displays an error message when the path doesn't exist.
   */
  void displayPathDoesNotExist();

  /**
   * Method displays a message when the user input portfolio doesn't exist.
   */
  void displayPortfolioDoesNotExist();

  /**
   * Method displays success when portfolio is successfully downloaded.
   */
  void displayDownloadSuccessMessage();

  /**
   * Method displays failure message when portfolio is not successfully downloaded.
   */
  void displayDownloadFailMessage();

  /**
   * Method displays the composition of a portfolio.
   *
   * @param data retrieves the data from the controller to be displayed to the user
   */
  void displayPortfolioComposition(String data);

  /**
   * Method displays the value of a portfolio.
   *
   * @param data retrieves the data from the controller to be displayed to the user
   */
  void displayValueOfPortfolio(String data);

  /**
   * Method checks if the file is corrupt or not.
   */
  void displayFileIsCorrupt();
}
