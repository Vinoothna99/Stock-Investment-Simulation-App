package view;

/**
 * Additional view methods for flexible portfolio.
 */
public interface FlexiblePortfolioView {

  /**
   * Method represents the display message when the the user inputs the ticker value percentages.
   */
  void displayEnterTickerPercentageMessage();

  /**
   * Method represents the date credentials which are to be entered by the user.
   */
  void displayPortfolioDate();

  /**
   * Method represents the success message on successful buy/sell operation.
   */
  void displayBuySellSuccessMessage();

  /**
   * Method represents the failure message on successful buy/sell operation.
   */
  void displayBuySellFailureMessage();

  /**
   * Method displays the error message when the user input date is invalid.
   */
  void displayDateIncorrect();

  /**
   * Method displays the invalid transaction message when the user input date is invalid.
   */
  void displayInvalidTransactionMessage();

  /**
   * Method asks the user to input commission fee.
   */
  void displayConfirmPurchase();

  /**
   * Method represents the costbasis of the portfolio.
   *
   * @param data retrieves the data from the controller to be displayed to the user
   */
  void displayCostBasis(String data);

  /**
   * Method displays the performance chart of portfolio on correct user input.
   *
   * @param data retrieves the data from the controller to be displayed to the user
   */
  void displayPerformanceChart(String data);

  /**
   * Method asks the user to input the from date.
   */
  void displayFromDate();

  /**
   * Method asks the user to input the to date.
   */
  void displayToDate();

  /**
   * Method checks if the date is valid on user input.
   */
  void displayValidDates();

  /**
   * Methods allows the choice to user to select the type of portfolio one wants.
   */
  void selectTypeOfPortfolio();

  /**
   * Method displays the error message when the user input is incorrect for the portfolio type.
   */
  void displayWrongPortfolioType();

  /**
   * Method takes in the percentage values for each companies.
   */
  void displayPercentageForEachCompany();

  /**
   * Method takes in the amount to be invested to a portfolio on user input.
   */
  void amountToInvest();

  /**
   * Method takes in the reccurring period value on user input.
   */
  void recurringPeriod();

  /**
   * Method displays error message on invalid input.
   */
  void invalidInput();

}
