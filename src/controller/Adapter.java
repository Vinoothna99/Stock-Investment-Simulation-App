package controller;

import controller.operationsportfolio.Buy;
import controller.operationsportfolio.Composition;
import controller.operationsportfolio.CostBasis;
import controller.operationsportfolio.Create;
import controller.operationsportfolio.InvestInPortfolio;
import controller.operationsportfolio.Operations;
import controller.operationsportfolio.PerformanceChart;
import controller.operationsportfolio.PeriodicInvestInPortfolio;
import controller.operationsportfolio.SavePortfolio;
import controller.operationsportfolio.Sell;
import controller.operationsportfolio.Value;
import controller.operationsportfolio.ValueOnDate;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import modal.InvestmentStrategyImpl;
import setup.classes.APIStockData;
import setup.interfaces.SetUpStockData;
import view.FlexiblePortfolioViewImpl;

/**
 * Adapter class that implements supports controller for Flexible and Inflexible portfolios.
 */
public class Adapter implements FlexiblePortfolioController, StockController {

  private static final String absolutePath = System.getProperty("user.dir");
  private static final String separator = System.getProperty("file.separator");
  final InputStream in;
  final PrintStream out;
  FlexiblePortfolioViewImpl view;
  InvestmentStrategyImpl modal;
  SetUpStockData stockObject;
  StockControllerChild delegate;
  Scanner scan;

  /**
   * Constructor of the class.
   *
   * @param in       inputStream object
   * @param out      outputStreamObject
   * @param view     view class Object
   * @param modal    modal object
   * @param delegate stock controller object.
   */
  public Adapter(InputStream in, PrintStream out, FlexiblePortfolioViewImpl view,
      InvestmentStrategyImpl modal, StockControllerChild delegate) {

    this.in = in;
    this.out = out;
    this.view = view;
    this.scan = new Scanner(this.in);
    this.stockObject = new APIStockData();
    this.modal = modal;
    this.delegate = delegate;
  }

  @Override
  public void stockControllerExecute() throws IOException {

    while (true) {
      view.displayMenu();

      try {
        String input = scan.nextLine();
        if ((input.length() == 1 || input.length() == 2)
            && Character.isDigit(input.charAt(0))
            && Character.isDigit(input.charAt(input.length() - 1))) {
          int option = Integer.parseInt(input);
          switch (option) {
            case 1:
              this.createPortfolio();
              break;
            case 2:
              this.executeFlexibleMenuOptions(2, "");
              break;
            case 3:
              this.executeFlexibleMenuOptions(3, "");
              break;
            case 4:
              this.compositionOfPortfolio();
              break;
            case 5:
              this.valueOfPortfolio();
              break;
            case 6:
              this.executeFlexibleMenuOptions(6, "");
              break;
            case 7:
              this.executeFlexibleMenuOptions(7, "");
              break;
            case 8:
              this.executeFlexibleMenuOptions(8, "");
              break;
            case 9:
              this.executeFlexibleMenuOptions(9, "");
              break;
            case 10:
              this.executeFlexibleMenuOptions(10, "");
              break;
            case 11:
              this.getPortfolio();
              break;
            case 12:
              this.displayEnlistedCompanies();
              break;
            case 13:
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
  public void executeFlexibleMenuOptions(int menuOption, String fileName) {
    Operations cmd = null;
    // Do all 9 flexible portfolio operations using command design pattern
    switch (menuOption) {
      case 1:
        cmd = new Create(in, out, view, modal, stockObject);
        break;
      case 2:
        cmd = new Buy(in, out, view, modal, stockObject);
        break;
      case 3:
        cmd = new Sell(in, out, view, modal, stockObject);
        break;
      case 4:
        cmd = new Composition(in, out, view, modal, stockObject);
        break;
      case 5:
        cmd = new Value(in, out, view, modal, stockObject);
        break;
      case 6:
        cmd = new ValueOnDate(in, out, view, modal, stockObject);
        break;
      case 7:
        cmd = new CostBasis(in, out, view, modal, stockObject);
        break;
      case 8:
        cmd = new PerformanceChart(in, out, view, modal, stockObject);
        break;
      case 9:
        cmd = new InvestInPortfolio(in, out, view, modal, stockObject);
        break;
      case 10:
        cmd = new PeriodicInvestInPortfolio(in, out, view, modal, stockObject);
        break;
      case 11:
        cmd = new SavePortfolio(in, out, view, modal, stockObject);
        break;
      default:
        //Java code

    }

    if (cmd != null) {
      if (fileName.equals("") && menuOption != 1 && menuOption != 10) {
        String input = enterPortfolioName(in, out);
        String type = portfolioType(input);

        if (type.equals("FLEXIBLE")) {
          //execute the command
          cmd.executeOperation(input);

        } else if (type.equals("INFLEXIBLE")) {
          view.displayWrongPortfolioType();
        }

      } else {
        //execute the command
        cmd.executeOperation(fileName);
      }

    }

  }

  @Override
  public void createPortfolio() {

    view.selectTypeOfPortfolio();
    String input = scan.nextLine();
    if ((input.length() == 1) && Character.isDigit(input.charAt(0))) {
      int option = Integer.parseInt(input);
      switch (option) {
        case 1:
          executeFlexibleMenuOptions(1, "");
          break;
        case 2:
          delegate.createPortfolio();
          break;
        case 3:
          return;
        default:
      }
    }
  }

  @Override
  public void compositionOfPortfolio() {
    String input = enterPortfolioName(in, out);
    String type = portfolioType(input);
    if (type.equals("FLEXIBLE")) {
      executeFlexibleMenuOptions(4, input);
    } else if (type.equals("INFLEXIBLE")) {
      delegate.operationsOnInflexiblePortfolio(1, input);
    }

  }

  @Override
  public void valueOfPortfolio() {
    String input = enterPortfolioName(in, out);
    String type = portfolioType(input);
    if (type.equals("FLEXIBLE")) {
      executeFlexibleMenuOptions(5, input);
    } else if (type.equals("INFLEXIBLE")) {
      delegate.operationsOnInflexiblePortfolio(2, input);
    }
  }

  @Override
  public void displayEnlistedCompanies() {
    delegate.displayEnlistedCompanies();
  }

  @Override
  public void getPortfolio() {
    String input = enterPortfolioName(in, out);
    String type = portfolioType(input);
    if (type.equals("FLEXIBLE")) {
      executeFlexibleMenuOptions(9, input);
    } else if (type.equals("INFLEXIBLE")) {
      delegate.operationsOnInflexiblePortfolio(3, input);
    }

  }

  //Helper Methods

  String portfolioType(String fileName) {
    String returnValue = "";

    String filePath = absolutePath + separator + "data" + separator + fileName + ".csv";
    if (modal.checkFileName(fileName + ".csv")) {
      String data = modal.readPortfolio(filePath);
      String[] lines = data.split("\n");
      String[] tokens = lines[0].split(",");
      if (tokens.length > 2) {
        returnValue = "FLEXIBLE";
      } else {
        returnValue = "INFLEXIBLE";
      }
    } else {
      view.displayPortfolioDoesNotExist();
    }
    return returnValue;
  }

  String enterPortfolioName(InputStream in, PrintStream out) {
    view.displayPortfolioNameMessage();

    return getInputFromConsole(in, out);
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
