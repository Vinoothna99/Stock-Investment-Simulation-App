package controller.operationsportfolio;

import java.io.InputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import modal.InvestmentStrategyImpl;
import setup.classes.APIStockData;
import setup.interfaces.SetUpStockData;
import view.FlexiblePortfolioViewImpl;

/**
 * This class is used to perform Dollar-Cost Averaging on a portfolio.
 */
public class PeriodicInvestInPortfolio implements Operations {

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
  public PeriodicInvestInPortfolio(InputStream in, PrintStream out, FlexiblePortfolioViewImpl view,
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
    view.displayPortfolioNameMessage();
    fileName = getInputFromConsole(in, out);

    view.amountToInvest();
    String amount = getInputFromConsole(in, out);

    view.displayConfirmPurchase();
    String permission = getInputFromConsole(in, out);
    try {
      double amountValue = Double.parseDouble(amount);
      int commissionFee = Integer.parseInt(permission);

      if (commissionFee >= 0 && amountValue > 0) {
        view.displayFromDate();
        String from = getInputFromConsole(in, out);
        view.displayToDate();
        String to = getInputFromConsole(in, out);
        view.recurringPeriod();
        String period = getInputFromConsole(in, out);
        Date fromDate = null;
        Date toDate = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
          fromDate = df.parse(from);
          if (to.equals("")) {
            to = "2023-12-31";
          } else {
            toDate = df.parse(to);
          }

          int days = Integer.parseInt(period);

          view.displayEnterTickerPercentageMessage();
          String tickerValues = getInputFromConsole(in, out);
          LocalDate start = LocalDate.parse(from);
          LocalDate end = LocalDate.parse(to);
          LocalDate minStart = LocalDate.parse("2000-01-01");
          LocalDate maxEnd = LocalDate.parse("2023-12-31");
          List<LocalDate> totalDates = new ArrayList<>();
          while (!start.isAfter(end)) {
            totalDates.add(start);
            start = start.plusDays(1);
          }

          start = LocalDate.parse(from);

          String data = "";
          if (start.isBefore(minStart) || end.isBefore(start)) {
            view.displayValidDates();
          } else {

            for (int i = 0; i < totalDates.size(); i += days) {
              data += totalDates.get(i).toString() + ",BUY," + amountValue + ","
                  + commissionFee + ","
                  + tickerValues + "\n";
            }

          }
          boolean success = modal.futureInvestmentStrategy(fileName, data, stockObject);
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
