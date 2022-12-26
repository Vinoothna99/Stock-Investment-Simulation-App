package controller;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import modal.StockModal;
import view.StockView;

/**
 * StockControllerChild extends StockControllerImpl and methods to enter the name of portfolio,
 * creates portfolio menu, uploads csv, enterTickerValues, gets the input from console and, updates
 * today's prices.
 */

public class StockControllerChild extends StockControllerImpl {

  private static final String absolutePath = System.getProperty("user.dir");
  private static final String separator = System.getProperty("file.separator");

  /**
   * StockController-child implements InputeStream, OutputStream, view and modal.
   *
   * @param in    represents the user input.
   * @param out   represents the output.
   * @param view  represents the command line interface of the  view object.
   * @param modal represents the modal for the flexible portfolio.
   */

  public StockControllerChild(InputStream in, PrintStream out, StockView view, StockModal modal) {
    super(in, out, view, modal);
  }

  @Override
  protected String enterPortfolioName(InputStream in, PrintStream out) {
    view.displayPortfolioNameMessage();
    return getInputFromConsole(in, out);
  }

  @Override
  void createPortfolioMenu(InputStream in, PrintStream out, String fileName) {
    view.displayPortfolioMenu();
    String input = getInputFromConsole(in, out);
    boolean success;
    if (input.length() == 1 && Character.isDigit(input.charAt(0))) {
      if (Integer.parseInt(input) == 1) {
        view.displayPath();
        String path = getInputFromConsole(in, out);
        success = uploadCsv(fileName, path);
        if (success) {
          view.displaySuccessMessage();
        } else {
          view.displayFailureMessage();
          createPortfolioMenu(in, out, fileName);
        }
      } else if (Integer.parseInt(input) == 2) {
        view.displayEnterTickerMessage();
        String tickerValues = getInputFromConsole(in, out);
        success = enterTickerValues(fileName, tickerValues);
        if (success) {
          view.displaySuccessMessage();
        } else {
          view.displayFailureMessage();
          createPortfolioMenu(in, out, fileName);
        }

      } else if (Integer.parseInt(input) != 3) {
        view.displayInvalidMenuOption();
        createPortfolioMenu(in, out, fileName);
      }


    } else {
      view.displayInvalidMenuOption();
      createPortfolioMenu(in, out, fileName);
    }


  }

  @Override
  boolean uploadCsv(String fileName, String path) {
    String filePath = absolutePath + separator + "data" + separator + path;
    File file = new File(filePath);
    if (file.exists()) {
      return modal.uploadCsv(fileName, filePath, this.stockObject);
    } else {
      view.displayPathDoesNotExist();
      return false;
    }

  }

  @Override
  boolean enterTickerValues(String fileName, String data) {
    return modal.enterTickerValues(fileName, data, this.stockObject);
  }

  @Override
  String getInputFromConsole(InputStream in, PrintStream out) {
    try {

      return scan.nextLine();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void updatePricesToday() {
    stockObject.updatePricesToday();
  }

}
