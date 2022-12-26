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
 * This class is used to perform Investment Operation on Portfolio.
 */
public class InvestInPortfolio implements Operations {

  final InputStream in;
  final PrintStream out;
  FlexiblePortfolioViewImpl view;
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
  public InvestInPortfolio(InputStream in, PrintStream out, FlexiblePortfolioViewImpl view,
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
    view.amountToInvest();
    String amount = getInputFromConsole(in, out);
    view.displayConfirmPurchase();
    String permission = getInputFromConsole(in, out);
    try {
      double amountValue = Double.parseDouble(amount);
      int commissionFee = Integer.parseInt(permission);
      if (commissionFee >= 0 && amountValue > 0) {
        view.displayPortfolioDate();
        String inputDate = getInputFromConsole(in, out);
        Date date = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {

          date = df.parse(inputDate);
          String todayDate = df.format(new Date());
          String data = df.format(date) + ",BUY," + amount + "," + commissionFee + ",";
          String composition = modal.compositionOfPortfolio(fileName, stockObject, todayDate);
          String[] tickers = composition.split("\n");
          boolean success;
          if (!composition.equals("")) {
            for (int i = 0; i < tickers.length; i++) {
              view.displayPercentageForEachCompany();
              String[] tokens = tickers[i].split(",");
              System.out.println(tokens[0] + " : ");
              String percentage = getInputFromConsole(in, out);
              data = data + tokens[0] + " " + percentage + ",";
            }
            success = modal.singleTimeInvestment(fileName, data, stockObject);
          } else {
            success = false;
          }

          if (success) {
            view.displayBuySellSuccessMessage();
          } else {
            view.displayBuySellFailureMessage();
          }
        } catch (Exception e) {
          view.displayDateIncorrect();
        }

      } else {
        view.invalidInput();
      }
    } catch (NumberFormatException e) {
      view.invalidInput();
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
