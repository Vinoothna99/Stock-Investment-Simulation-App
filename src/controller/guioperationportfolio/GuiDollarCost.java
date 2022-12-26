package controller.guioperationportfolio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modal.InvestmentStrategyImpl;
import setup.classes.APIStockData;
import setup.interfaces.SetUpStockData;

/**
 * Class represents the dollar cost average of the portfolio through a GUI.
 */
public class GuiDollarCost implements GuiOperations {

  SetUpStockData stockObject = new APIStockData();

  @Override
  public String executeOperation(String data, InvestmentStrategyImpl model) {
    String[] lines = data.split("\n");
    String fileName = lines[0];
    String amount = lines[5];
    String permission = lines[6];
    try {
      double amountValue = Double.parseDouble(amount);
      int commissionFee = Integer.parseInt(permission);

      if (commissionFee >= 0 && amountValue > 0) {

        String from = lines[1];
        String to = lines[2];

        String period = lines[7];
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

          String[] tickers = lines[3].split(",");
          String[] values = lines[4].split(",");
          String tickerValues = "";
          if (tickers.length == values.length) {
            for (int i = 0; i < tickers.length; i++) {
              tickerValues += tickers[i] + " " + values[i] + ",";
            }

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
            String result = "";
            if (start.isBefore(minStart) || end.isBefore(start)) {

              return "false";
            } else {

              for (int i = 0; i < totalDates.size(); i += days) {
                result += totalDates.get(i).toString() + ",BUY," + amountValue + ","
                    + commissionFee + ","
                    + tickerValues + "\n";
              }

            }
            boolean success = model.futureInvestmentStrategy(fileName, result, stockObject);
            if (success) {
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

      } else {
        return "false";
      }

    } catch (NumberFormatException e) {
      return "false";
    }


  }
}
