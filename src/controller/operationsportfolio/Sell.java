package controller.operationsportfolio;


import java.io.InputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import modal.InvestmentStrategyImpl;
import setup.classes.APIStockData;
import setup.interfaces.SetUpStockData;
import view.FlexiblePortfolioViewImpl;

/**
 * Performs Sell Operation on flexible portfolios.
 */
public class Sell implements Operations {

  final InputStream in;
  final PrintStream out;
  final FlexiblePortfolioViewImpl view;

  InvestmentStrategyImpl modal;

  SetUpStockData stockObject;
  Scanner scan;

  /**
   * Constructor of the class.
   *
   * @param in          inputStream object
   * @param out         outputStreamObject
   * @param view        view class Object
   * @param modal       modal object
   * @param stockObject stock data object.
   */
  public Sell(InputStream in, PrintStream out, FlexiblePortfolioViewImpl view,
      InvestmentStrategyImpl modal, SetUpStockData stockObject) {
    this.in = in;
    this.out = out;
    this.view = view;
    this.scan = new Scanner(this.in);
    this.stockObject = new APIStockData();
    this.modal = modal;
  }

  @Override
  public void executeOperation(String fileName) {
    view.displayConfirmPurchase();
    String permission = getInputFromConsole(in, out);
    try {
      int commissionFee = Integer.parseInt(permission);
      if (commissionFee >= 0) {
        view.displayPortfolioDate();
        String inputDate = getInputFromConsole(in, out);
        Date date = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
          date = df.parse(inputDate);
          view.displayEnterTickerMessage();
          String tickerValues = getInputFromConsole(in, out);
          String data = inputDate + "," + "SELL" + "," + commissionFee + "," + tickerValues;
          boolean flag = modal.sellShares(fileName, data, stockObject, df.format(date));
          if (flag) {
            boolean success = modal.enterTickerValues(fileName, data, stockObject);
            if (success) {
              view.displayBuySellSuccessMessage();
            } else {
              view.displayBuySellFailureMessage();
            }
          } else {
            view.displayInvalidTransactionMessage();
          }
        } catch (Exception e) {
          view.displayDateIncorrect();
        }

      } else {
        this.executeOperation(fileName);
      }

    } catch (NumberFormatException e) {
      this.executeOperation(fileName);
    }


  }

  String getInputFromConsole(InputStream in, PrintStream out) {
    try {
      return scan.nextLine();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
