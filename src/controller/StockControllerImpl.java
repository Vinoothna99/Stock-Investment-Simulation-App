package controller;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import modal.StockModal;
import setup.classes.APIStockData;
import setup.interfaces.SetUpStockData;
import view.StockView;

/**
 * StockControllerImpl implements StockController Interface which creates methods such as create
 * Portfolio, to composite Portfolio, calculate value Of Portfolio, displays Enlisted Companies, and
 * also downloads Portfolio.
 */

public abstract class StockControllerImpl implements StockController {

  final InputStream in;
  final PrintStream out;
  final StockView view;

  StockModal modal;

  SetUpStockData stockObject;
  Scanner scan;

  protected StockControllerImpl(InputStream in, PrintStream out, StockView view, StockModal modal) {

    this.in = in;
    this.out = out;
    this.view = view;
    this.scan = new Scanner(this.in);
    this.stockObject = new APIStockData();
    this.modal = modal;
  }

  @Override
  public void stockControllerExecute() {

    while (true) {
      view.displayMenu();

      try {
        String input = scan.nextLine();
        if (input.length() == 1 && Character.isDigit(input.charAt(0))) {
          int option = Integer.parseInt(input);
          switch (option) {
            case 1:
              this.createPortfolio();
              break;
            case 2:
              this.compositionOfPortfolio();
              break;
            case 3:
              this.valueOfPortfolio();
              break;
            case 4:
              this.getPortfolio();
              break;
            case 5:
              this.displayEnlistedCompanies();
              break;
            case 6:
              return;
            default:
              //Java code

          }

        } else {

          view.displayInvalidMenuOption();
        }


      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

  @Override
  public void createPortfolio() {

    String fileName = enterPortfolioName(in, out);

    if (!fileName.matches("^[a-zA-Z]*$")) {
      view.displayInvalidPortfolioName();
      createPortfolio();
    } else {
      if (!modal.checkFileName(fileName + ".csv")) {
        createPortfolioMenu(in, out, fileName);
      } else {
        view.displayReplaceConfirm();
        String input = getInputFromConsole(in, out);
        if (input.length() == 1 && Character.isDigit(input.charAt(0))) {
          if (Integer.parseInt(input) == 1) {
            createPortfolioMenu(in, out, fileName);
          } else if (Integer.parseInt(input) != 2) {
            view.displayInvalidMenuOption();
          }

        }
      }
    }

  }

  @Override
  public void compositionOfPortfolio() {
    String input = enterPortfolioName(in, out);
    if (modal.checkFileName(input + ".csv")) {
      String data = modal.compositionOfPortfolio(input, stockObject);
      if (data.equals("")) {
        view.displayFileIsCorrupt();
      } else {
        view.displayPortfolioComposition(data);
      }
    } else {
      view.displayPortfolioDoesNotExist();
    }

  }

  protected void operationsOnInflexiblePortfolio(int option, String fileName) {
    String data = "";
    switch (option) {
      case 1:
        data = modal.compositionOfPortfolio(fileName, stockObject);
        if (data.equals("")) {
          view.displayFileIsCorrupt();
        } else {
          view.displayPortfolioComposition(data);
        }
        break;
      case 2:
        data = modal.valueOfPortfolio(fileName, stockObject);
        if (data.equals("")) {
          view.displayFileIsCorrupt();
        } else {
          view.displayValueOfPortfolio(data);
        }
        break;
      case 3:
        boolean downloadSuccess = modal.getPortfolio(fileName, this.stockObject);
        if (downloadSuccess) {
          view.displayDownloadSuccessMessage();
        } else {
          view.displayDownloadFailMessage();
        }
        break;
      default:
        //Java code

    }

  }

  @Override
  public void valueOfPortfolio() {
    String input = enterPortfolioName(in, out);
    if (modal.checkFileName(input + ".csv")) {
      String data = modal.valueOfPortfolio(input, stockObject);
      if (data.equals("")) {
        view.displayFileIsCorrupt();
      } else {
        view.displayValueOfPortfolio(data);
      }
    } else {
      view.displayPortfolioDoesNotExist();
    }
  }

  @Override
  public void displayEnlistedCompanies() {
    boolean downloadSuccess = modal.displayEnlistedCompanies(this.stockObject);
    if (downloadSuccess) {
      view.displayDownloadSuccessMessage();
    } else {
      view.displayDownloadFailMessage();
    }
  }

  @Override
  public void getPortfolio() {
    String input = enterPortfolioName(in, out);
    if (modal.checkFileName(input + ".csv")) {
      boolean downloadSuccess = modal.getPortfolio(input, this.stockObject);
      if (downloadSuccess) {
        view.displayDownloadSuccessMessage();
      } else {
        view.displayDownloadFailMessage();
      }
    } else {
      view.displayPortfolioDoesNotExist();
    }
  }

  /**
   * Method takes in the portfolio name from the user.
   *
   * @param in  inputstream object
   * @param out outputstream object
   * @return the portfolio name in the string format
   */
  abstract String enterPortfolioName(InputStream in, PrintStream out);

  /**
   * Method diplays the creation of the portfolio menu.
   *
   * @param in       inputstream object
   * @param out      outputstream object
   * @param fileName is the input name taken from the user
   */
  abstract void createPortfolioMenu(InputStream in, PrintStream out, String fileName);

  /**
   * Receive  path to csv as input and pass to modal receive list of invalid parameter inform user.
   */

  abstract boolean uploadCsv(String fileName, String path);

  /**
   * Get ticker name and number of shares from user and pass to modal.
   */

  abstract boolean enterTickerValues(String fileName, String data);

  /**
   * Method takes back to StockControllerMenu.
   */

  abstract String getInputFromConsole(InputStream in, PrintStream out);

  /**
   * Constructs method to update today's prices.
   */
  public abstract void updatePricesToday();

}
