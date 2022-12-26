package controller.operationsportfolio;


import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import modal.InvestmentStrategyImpl;
import setup.classes.APIStockData;
import setup.interfaces.SetUpStockData;
import view.FlexiblePortfolioViewImpl;

/**
 * Performs save Operation on flexible portfolios.
 */
public class SavePortfolio implements Operations {

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
  public SavePortfolio(InputStream in, PrintStream out, FlexiblePortfolioViewImpl view,
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

    boolean downloadSuccess = modal.getPortfolio(fileName, this.stockObject);
    if (downloadSuccess) {
      view.displayDownloadSuccessMessage();
    } else {
      view.displayDownloadFailMessage();
    }

  }
}