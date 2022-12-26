package controller.guioperationportfolio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import modal.InvestmentStrategyImpl;
import setup.classes.APIStockData;
import setup.interfaces.SetUpStockData;

/**
 * Class represents the investment to a portfolio through a GUI.
 */
public class GuiInvestment implements GuiOperations {

  SetUpStockData stockObject = new APIStockData();

  @Override
  public String executeOperation(String data, InvestmentStrategyImpl model) {

    try {
      String[] lines = data.split("\n");
      if (lines.length != 6) {

        return "false";
      }
      double amountValue = Double.parseDouble(lines[4]);
      int commissionFee = Integer.parseInt(lines[5]);
      if (commissionFee >= 0 && amountValue > 0) {
        String inputDate = lines[1];
        Date date = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {

          date = df.parse(inputDate);
          String todayDate = df.format(new Date());
          String tempData = df.format(date) + ",BUY," + amountValue + "," + commissionFee + ",";

          String[] tickers = lines[2].split(",");
          String[] percentages = lines[3].split(",");
          boolean success;
          if (!lines[2].equals("") && tickers.length == percentages.length) {
            for (int i = 0; i < tickers.length; i++) {
              tempData = tempData + tickers[i] + " " + percentages[i] + ",";
            }
            success = model.singleTimeInvestment(lines[0], tempData, stockObject);
          } else {
            success = false;
          }

          if (success) {
            return "true";
          } else {
            return "false";
          }
        } catch (Exception e) {
          return "false";
        }

      } else {
        return "false";
      }
    } catch (NumberFormatException e) {
      return "false";
    }

  }
}
