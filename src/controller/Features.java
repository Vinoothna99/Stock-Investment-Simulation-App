package controller;

import view.GraphicalView;

/**
 * Interface provides all the functionality of a flexible portfolio through a GUI.
 */
public interface Features {

  /**
   * Method takes in the graphical view object and passes it to the controller.
   *
   * @param tempView is the Graphical View Object.
   */
  void setView(GraphicalView tempView);

  /**
   * Method creates a new flexible portfolio for the user.
   *
   * @param s contains the user input such as portfolio name, date and ticker values
   * @return boolean value indicating status of portfolio creation.
   */
  boolean create(String s);

  /**
   * Method performs buy operation for the user.
   *
   * @param data contains the user input such as portfolio name, date and ticker values
   * @return boolean value indicating status of buy operation.
   */
  boolean buy(String data);

  /**
   * Method performs sell operation for the user.
   *
   * @param data contains the user input such as portfolio name, date and ticker values
   * @return boolean value indicating status of sell operation.
   */
  boolean sell(String data);

  /**
   * Method performs compostion operation for the user.
   *
   * @param data contains the user input such as portfolio name, date and ticker values
   * @return the composition value of the portfolio in string format
   */
  String composition(String data);

  /**
   * Method performs getting the current portfolio value operation for the user.
   *
   * @param data contains the user input such as portfolio name, date and ticker values
   * @return the current value of the portfolio in string format
   */
  String value(String data);

  /**
   * Method performs getting the portfolio value on a specif date operation for the user.
   *
   * @param data contains the user input such as portfolio name, date and ticker values
   * @return the value of the portfolio on a specific date in string format
   */
  String valueOnSpecificDate(String data);

  /**
   * Method performs costbasis operation for the user.
   *
   * @param data contains the user input such as portfolio name, date and ticker values
   * @return the value of the costbasis in string format
   */
  String costBasis(String data);

  /**
   * Method performs investment operation for the user.
   *
   * @param data contains the user input such as portfolio name, date, commission and ticker values
   * @return boolean value indicating status of investment operation.
   */
  boolean singleInvestment(String data);

  /**
   * Method performs dollar cost averaging operation for the user.
   *
   * @param data contains the user input such as portfolio name, date, commission, recurring amount
   *             and ticker values
   * @return boolean value indicating status of dollarcost average operation.
   */
  boolean dollarCostAveraging(String data);

  /**
   * Method performs dollar saving operation of the portfolio for the user.
   *
   * @param data contains the user input such as portfolio name, date and ticker values
   * @return boolean value indicating status of copyportfolio operation.
   */
  boolean copyPortfolio(String data);

  /**
   * Method displays the company list on the user interface for single time investment.
   *
   * @param fileName contains the portfolio name
   * @return the list of the companies in the string format
   */
  String listOfCompaniesInPortfolio(String fileName);

  /**
   * Method represents the validation for the user portfolio.
   *
   * @param s contains the portfolio name
   * @return the error message
   */
  int validatePortfolio(String s);

  /**
   * Method represents the exit portfolio.
   *
   * @param s represents the exit message for the application
   */
  void exitApplication(String s);
}
