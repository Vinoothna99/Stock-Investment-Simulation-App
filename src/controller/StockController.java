package controller;

import java.io.IOException;

/**
 * This Interface constructs methods to create Portfolio, to composite Portfolio, calculate value Of
 * Portfolio, displays Enlisted Companies, and also downloads Portfolio.
 */

public interface StockController {

  /**
   * Method displays the command line interface and decided which operation to perform based on user
   * input.
   *
   * @throws IOException when invalid input is given.
   */
  void stockControllerExecute() throws IOException;

  /**
   * Constructs method createPortfolio which takes input filename, call StockModal and passes file
   * name, return 0, if file name do not exist, returns 1 if file name valid, also asks, if want to
   * replace return 1 and calls portfolioController.
   */

  void createPortfolio();

  /**
   * Constructs a method which gets input filename, call StockModal and pass file name, return 0,
   * file name do not exist, return 1, file name valid, return 1, call stockModal, receive string
   * and pass to view.
   */

  void compositionOfPortfolio();

  /**
   * This method gets value of portfolio to get input filename, call StockModal and pass file name
   * return 0, file name do not exist, return 1, file name valid, return 1, call stockModal receive
   * string and pass to view.
   */

  void valueOfPortfolio();

  /**
   * This method displays listed companies in stockModal, receive and send string to view.
   */
  void displayEnlistedCompanies();

  /**
   * This method allows to Download Portfolio.
   */
  void getPortfolio();


}
