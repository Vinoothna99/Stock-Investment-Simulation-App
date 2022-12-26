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
import java.util.stream.Collectors;
import modal.InvestmentStrategyImpl;
import setup.classes.APIStockData;
import setup.interfaces.SetUpStockData;
import view.FlexiblePortfolioViewImpl;

/**
 * Performs Performance Chart Operation on flexible portfolios.
 */
public class PerformanceChart implements Operations {

  final InputStream in;
  final PrintStream out;
  final FlexiblePortfolioViewImpl view;

  InvestmentStrategyImpl modal;

  SetUpStockData stockObject;
  Scanner scan;
  static final String COMMA_DELIMITER = ",";

  /**
   * Constructor of the class.
   *
   * @param in          inputStream object
   * @param out         outputStreamObject
   * @param view        view class Object
   * @param modal       modal object
   * @param stockObject stock data object.
   */
  public PerformanceChart(InputStream in, PrintStream out, FlexiblePortfolioViewImpl view,
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

    view.displayFromDate();
    String fromDate = getInputFromConsole(in, out);
    view.displayToDate();
    String toDate = getInputFromConsole(in, out);
    Date fDate = null;
    Date tDate = null;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    df.setLenient(false);
    try {
      fDate = df.parse(fromDate);
      tDate = df.parse(toDate);

      LocalDate start = LocalDate.parse(fromDate);
      LocalDate end = LocalDate.parse(toDate);
      LocalDate minStart = LocalDate.parse("2000-01-01");
      String formatted = df.format(new Date());
      LocalDate maxEnd = LocalDate.parse(formatted);
      List<LocalDate> totalDates = new ArrayList<>();
      while (!start.isAfter(end)) {
        totalDates.add(start);
        start = start.plusDays(1);
      }

      start = LocalDate.parse(fromDate);
      List<LocalDate> monthsList = start.datesUntil(end)
          .filter(e -> e.getDayOfMonth() == 1)
          .collect(Collectors.toList());

      List<LocalDate> yearsList = start.datesUntil(end)
          .filter(e -> e.getDayOfYear() == 1)
          .collect(Collectors.toList());

      String dates = "";

      if (start.isBefore(minStart) || end.isAfter(maxEnd) || end.isBefore(start) ||
          totalDates.size() < 5
          || yearsList.size() > 30) {
        view.displayValidDates();
      } else if (totalDates.size() < 30 && totalDates.size() >= 5) {

        for (int i = 0; i < totalDates.size(); i++) {
          dates += totalDates.get(i).toString() + COMMA_DELIMITER;
        }
        // Daily
        String data = modal.performanceChart(fileName, stockObject, df.format(fDate),
            df.format(tDate), dates);
        view.displayPerformanceChart(data);
      } else if (totalDates.size() >= 30 && totalDates.size() < 50) {

        for (int i = 0; i < totalDates.size(); i += 5) {
          dates += totalDates.get(i).toString() + COMMA_DELIMITER;
        }
        // Once every 5 days
        String data = modal.performanceChart(fileName, stockObject, df.format(fDate),
            df.format(tDate), dates);
        view.displayPerformanceChart(data);
      } else if (totalDates.size() >= 50 && totalDates.size() <= 150) {

        for (int i = 0; i < totalDates.size(); i += 10) {
          dates += totalDates.get(i).toString() + COMMA_DELIMITER;
        }
        // Once every 10 days
        String data = modal.performanceChart(fileName, stockObject, df.format(fDate),
            df.format(tDate), dates);
        view.displayPerformanceChart(data);
      } else if (totalDates.size() > 150 && monthsList.size() < 30) {
        for (LocalDate d : monthsList) {
          dates += d.toString() + COMMA_DELIMITER;
        }
        //once a month
        String data = modal.performanceChart(fileName, stockObject, df.format(fDate),
            df.format(tDate), dates);
        view.displayPerformanceChart(data);
      } else if (monthsList.size() >= 30 && monthsList.size() < 90) {

        for (int i = 0; i < monthsList.size(); i += 3) {
          dates += monthsList.get(i).toString() + COMMA_DELIMITER;
        }
        //once every 3 months
        String data = modal.performanceChart(fileName, stockObject, df.format(fDate),
            df.format(tDate), dates);
        view.displayPerformanceChart(data);

      } else if (monthsList.size() >= 90 && yearsList.size() <= 30) {
        for (LocalDate d : yearsList) {
          dates += d.toString() + COMMA_DELIMITER;
        }
        //once a year
        String data = modal.performanceChart(fileName, stockObject, df.format(fDate),
            df.format(tDate), dates);
        view.displayPerformanceChart(data);

      }
    } catch (Exception e) {
      view.displayDateIncorrect();
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