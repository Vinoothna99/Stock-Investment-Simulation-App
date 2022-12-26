package controller.guioperationportfolio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import modal.InvestmentStrategyImpl;
import setup.classes.APIStockData;
import setup.interfaces.SetUpStockData;

/**
 * Class represents the selling operation of the portfolio through a GUI.
 */
public class GuiSell implements GuiOperations {

  SetUpStockData stockObject = new APIStockData();

  @Override
  public String executeOperation(String data, InvestmentStrategyImpl model) {
    try {
      String[] lines = data.split("\n");
      String inputDate = lines[1];
      Date date = null;
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      //Validating date
      date = df.parse(inputDate);
      String formatted = df.format(new Date());
      LocalDate givenDate = LocalDate.parse(inputDate);
      LocalDate todayDate = LocalDate.parse(formatted);
      //Validating date
      if (givenDate.isAfter(todayDate)) {
        throw new NumberFormatException();
      }
      df.setLenient(false);

      String[] tickers = lines[2].split(",");
      String[] values = lines[3].split(",");
      String str = "";

      //Commission validation
      int commission = Integer.parseInt(lines[4]);

      if (tickers.length != values.length || commission < 0) {

        return "false";
      } else {

        //Setting tickers in a format model can understand and validate
        for (int i = 0; i < tickers.length; i++) {
          str += tickers[i] + " " + values[i] + ",";
        }
      }

      data = df.format(date) + "," + "SELL" + "," + lines[4] + "," + str;
      boolean flag = model.sellShares(lines[0], data, stockObject, df.format(date));
      System.out.println("controller flag" + flag);
      if (flag) {
        boolean result = model.enterTickerValues(lines[0], data, stockObject);
        if (result) {
          return "true";
        } else {

          return "false";
        }
      } else {

        return "false";
      }

    } catch (Exception e) {

      return "false";
    }
  }
}
