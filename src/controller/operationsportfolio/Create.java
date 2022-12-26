package controller.operationsportfolio;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import modal.InvestmentStrategyImpl;
import setup.classes.APIStockData;
import setup.interfaces.SetUpStockData;
import view.FlexiblePortfolioViewImpl;

/**
 * Performs Create Operation on flexible portfolios.
 */
public class Create implements Operations {

  private static final String COMMA_DELIMITER = ",";
  private static final String absolutePath = System.getProperty("user.dir");
  private static final String separator = System.getProperty("file.separator");
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
  public Create(InputStream in, PrintStream out, FlexiblePortfolioViewImpl view,
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

    if (!fileName.matches("^[a-zA-Z]*$")) {
      view.displayInvalidPortfolioName();
      executeOperation("");
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


  String getInputFromConsole(InputStream in, PrintStream out) {
    try {
      return scan.nextLine();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = df.format(new Date());
        success = enterTickerValues(fileName, tickerValues, formatted);
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


  boolean uploadCsv(String fileName, String path) {
    String filePath = absolutePath + separator + "data" + separator + path;
    File file = new File(filePath);
    if (file.exists()) {
      String fileNamePath = absolutePath + separator + "data" + separator + fileName + ".csv";
      try {
        File fileN = new File(fileNamePath);
        fileN.delete();
        fileN.createNewFile();
      } catch (Exception e) {
        System.out.println("Error in CsvFileWriter !!!");
        e.printStackTrace();
      } finally {
        return modal.uploadCsv(fileName, filePath, this.stockObject);
      }
    } else {
      view.displayPathDoesNotExist();
      return false;
    }
  }


  boolean enterTickerValues(String fileName, String data, String date) {

    data = date + COMMA_DELIMITER + "BUY" + COMMA_DELIMITER + "0" + COMMA_DELIMITER + data;
    return modal.enterTickerValues(fileName, data, this.stockObject);

  }

}
