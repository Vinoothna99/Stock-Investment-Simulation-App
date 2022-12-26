package controller;

import controller.guioperationportfolio.GuiComposition;
import controller.guioperationportfolio.GuiCostBasis;
import controller.guioperationportfolio.GuiCreate;
import controller.guioperationportfolio.GuiDollarCost;
import controller.guioperationportfolio.GuiInvestment;
import controller.guioperationportfolio.GuiOperations;
import controller.guioperationportfolio.GuiSell;
import controller.guioperationportfolio.GuiValue;
import controller.guioperationportfolio.GuiValueOnSpecificDate;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import modal.InvestmentStrategyImpl;
import setup.classes.APIStockData;
import setup.interfaces.SetUpStockData;
import view.GraphicalView;

/**
 * Class represents the graphical controller implementation of the features interface.
 */
public class GraphicalControllerImpl implements Features {

  private static final String absolutePath = System.getProperty("user.dir");
  private static final String separator = System.getProperty("file.separator");
  SetUpStockData stockObject = new APIStockData();
  protected InvestmentStrategyImpl modal;
  GraphicalView view = new GraphicalView();

  private GuiOperations cmd;

  /**
   * Constructor sets the object of the modal and calls the setview function.
   *
   * @param model represents the model object
   */
  public GraphicalControllerImpl(InvestmentStrategyImpl model) {
    modal = model;
    this.setView(view);
  }

  @Override
  public void setView(GraphicalView tempView) {
    view = tempView;
    view.addFeatures(this);

  }

  @Override
  public boolean create(String data) {
    cmd = new GuiCreate();
    String result = cmd.executeOperation(data, modal);
    return result.equals("true");
  }

  @Override
  public boolean buy(String data) {
    cmd = new GuiCreate();
    String result = cmd.executeOperation(data, modal);
    return result.equals("true");
  }

  @Override
  public boolean sell(String data) {
    cmd = new GuiSell();
    String result = cmd.executeOperation(data, modal);
    return result.equals("true");
  }

  @Override
  public String composition(String data) {
    cmd = new GuiComposition();
    return cmd.executeOperation(data, modal);
  }

  @Override
  public String value(String data) {
    cmd = new GuiValue();
    return cmd.executeOperation(data, modal);
  }

  @Override
  public String valueOnSpecificDate(String data) {
    cmd = new GuiValueOnSpecificDate();
    return cmd.executeOperation(data, modal);
  }

  @Override
  public String costBasis(String data) {
    cmd = new GuiCostBasis();
    return cmd.executeOperation(data, modal);
  }

  @Override
  public boolean singleInvestment(String data) {
    cmd = new GuiInvestment();
    String result = cmd.executeOperation(data, modal);
    return result.equals("true");
  }

  @Override
  public boolean dollarCostAveraging(String data) {
    cmd = new GuiDollarCost();
    String result = cmd.executeOperation(data, modal);
    return result.equals("true");
  }

  @Override
  public boolean copyPortfolio(String data) {

    return modal.getPortfolio(data, stockObject);
  }


  @Override
  public String listOfCompaniesInPortfolio(String fileName) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    df.setLenient(false);
    String todayDate = df.format(new Date());
    String data = modal.compositionOfPortfolio(fileName, stockObject, todayDate);
    String result = "";
    String[] lines = data.split("\n");
    for (int i = 0; i < lines.length; i++) {
      String[] tokens = lines[i].split(",");
      result += tokens[0] + ",";
    }
    return result;

  }


  @Override
  public void exitApplication(String s) {
    System.exit(0);
  }

  @Override
  public int validatePortfolio(String s) {
    try {
      if (s.matches("^[a-zA-Z]*$") && !checkIfFileExists(s + ".csv") && !s.equals("")
          && s != null) {
        return 0;
      } else if (checkIfFileExists(s + ".csv")) {
        return 1;
      } else if (!s.matches("^[a-zA-Z]*$") || s.equals("") || s == null) {
        return 2;
      }
      return 2;
    } catch (NullPointerException e) {
      return 3;
    }

  }


  private boolean checkIfFileExists(String s) {
    s = absolutePath + separator + "data" + separator + s;
    File file = new File(s);
    return file.exists();
  }


}
