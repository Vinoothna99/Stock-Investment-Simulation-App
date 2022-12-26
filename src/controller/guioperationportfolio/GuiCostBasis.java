package controller.guioperationportfolio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import modal.InvestmentStrategyImpl;
import setup.classes.APIStockData;
import setup.interfaces.SetUpStockData;

/**
 * Class represents the costbasis of the portfolio through a GUI.
 */
public class GuiCostBasis implements GuiOperations {


  SetUpStockData stockObject = new APIStockData();

  @Override
  public String executeOperation(String data, InvestmentStrategyImpl model) {
    try {
      String[] lines = data.split("\n");
      String inputDate = lines[1];
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
      String result = model.costBasisOfPortfolio(lines[0], stockObject, df.format(date));
      if (result.equals("")) {
        return "false";
      } else {
        return result;
      }
    } catch (Exception e) {
      return "false";
    }
  }
}
