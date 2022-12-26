package controller.operationsportfolio;


import java.io.InputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import modal.InvestmentStrategyImpl;
import setup.classes.APIStockData;
import setup.interfaces.SetUpStockData;
import view.FlexiblePortfolioViewImpl;

/**
 * Performs CostBasis Operation on flexible portfolios.
 */
public class CostBasis implements Operations {

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
  public CostBasis(InputStream in, PrintStream out, FlexiblePortfolioViewImpl view,
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

    try {
      view.displayPortfolioDate();
      String inputDate = scan.nextLine();
      Date date = null;
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      String formatted = df.format(new Date());
      LocalDate givenDate = LocalDate.parse(inputDate);
      LocalDate todayDate = LocalDate.parse(formatted);
      if (givenDate.isAfter(todayDate)) {
        throw new NumberFormatException();
      }
      df.setLenient(false);
      date = df.parse(inputDate);
      String data = modal.costBasisOfPortfolio(fileName, stockObject, df.format(date));
      view.displayCostBasis(data);
    } catch (Exception e) {
      view.displayDateIncorrect();
    }


  }
}