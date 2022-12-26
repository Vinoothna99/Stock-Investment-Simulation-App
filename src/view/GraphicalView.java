package view;

import controller.Features;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class implements Graphical Interface and provides Graphical View Functions. It has event
 * listeners.
 */
public class GraphicalView extends JFrame implements GraphicalInterface {

  private static final String COMMA_DELIMITER = ",";
  private static final String NEW_LINE_SEPARATOR = "\n";

  private static final int TICKER_IDX = 0;
  private static final int NUMBER_OF_SHARES = 1;

  private static final int PRICE_OF_SHARES = 2;

  private static final DecimalFormat df = new DecimalFormat("0.00 \n");

  MainMenuFrame n = new MainMenuFrame();
  JFrame frame;

  CompositionFrame currPort = new CompositionFrame();
  JFrame currFrame = currPort.getFrame();


  CompositionFrame spdtPort = new CompositionFrame();
  JFrame spdtFrame = spdtPort.getFrame();


  CompositionFrame costObj = new CompositionFrame();
  JFrame costObjFrame = costObj.getFrame();


  InvestmentFrame invPort = new InvestmentFrame();

  JFrame invFrame = invPort.getFrame();

  DollarCostFrame dcaPort = new DollarCostFrame();
  JFrame dcaFrame = dcaPort.getFrame();
  CompositionFrame comObj = new CompositionFrame();
  JFrame compFrame = comObj.getFrame();
  CreatePortfolioFrame obj1 = new CreatePortfolioFrame();
  JFrame f3 = obj1.getFrame();

  BuyFrame objBuy = new BuyFrame();

  JFrame bf = objBuy.getFrame();

  BuyFrame objCreate = new BuyFrame();

  JFrame cf = objCreate.getFrame();

  BuyFrame objSell = new BuyFrame();

  JFrame sf = objSell.getFrame();

  @Override
  public void addFeatures(Features f) {
    frame = n.getFrame();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    n.createPortf.addActionListener(evt -> {
      String s = JOptionPane.showInputDialog("Enter the portfolio Name");

      //make  a call to the controller to validate portfolio name
      int error = f.validatePortfolio(s);
      //boolean b =true;
      if (error == 0) {
        objCreate.comLabel.setText("Enter ticker and share values for the creation of portfolio");
        frame.setVisible(false);
        cf.setVisible(true);
        objCreate.buyvalButton.setVisible(false);
        objCreate.buyTicker.setText("Tickers in the format: AA,AAPB,IBM");
        objCreate.buyShare.setText("Shares in order of tickers above like: 10,20,80");
        objCreate.buydtBox.setText("");
        objCreate.buytkBox.setText("");
        objCreate.buyshBox.setText("");
        objCreate.buyComBox.setText("");
        objCreate.savevalButton.addActionListener(e -> {
          String date = objCreate.buydtBox.getText();
          String tickers = objCreate.buytkBox.getText();
          String shares = objCreate.buyshBox.getText();
          String commissionFee = objCreate.buyComBox.getText();

          String data = s + "\n" + date + "\n" + tickers + "\n" + shares + "\n" + commissionFee;

          boolean result = f.create(data);
          if (result) {
            cf.setVisible(false);
            JOptionPane.showMessageDialog(null,
                "Portfolio Successfully Created",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            frame.setVisible(true);

          } else {
            JOptionPane.showMessageDialog(null, "Invalid inputs",
                "Invalid Inputs", JOptionPane.ERROR_MESSAGE);
            objCreate.buydtBox.setText("");
            objCreate.buytkBox.setText("");
            objCreate.buyshBox.setText("");
            objCreate.buyComBox.setText("");
          }
        });

        //f.testing();

      } else if (error == 1) {
        JOptionPane.showMessageDialog(null,
            "Portfolio already exists, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);
      } else if (error == 2) {
        JOptionPane.showMessageDialog(null,
            "Portfolio Name incorrect, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);
      }
    });

    n.buyShare.addActionListener(evt -> {
      String s = JOptionPane.showInputDialog("Enter the portfolio Name");
      //make  a call to the controller to validate portfolio name
      int b = f.validatePortfolio(s);
      if (b == 0) {
        JOptionPane.showMessageDialog(null,
            "Portfolio does not exist, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);

      } else if (b == 1) {
        frame.setVisible(false);
        objBuy.buyDate.setText("Date (YYYY-MM-DD)");
        objBuy.comLabel.setText("Operation for the buying of portfolio stocks");
        bf.setVisible(true);
        objBuy.buyvalButton.setVisible(false);
        objBuy.buyTicker.setText("Tickers in the format: AA,AAPB,IBM");
        objBuy.buyShare.setText("Shares in order of tickers above like: 10,20,80");
        objBuy.buydtBox.setText("");
        objBuy.buytkBox.setText("");
        objBuy.buyshBox.setText("");
        objBuy.buyComBox.setText("");
        objBuy.savevalButton.addActionListener(e -> {
          String date = objBuy.buydtBox.getText();
          String tickers = objBuy.buytkBox.getText();
          String shares = objBuy.buyshBox.getText();
          String commissionFee = objBuy.buyComBox.getText();

          String data = s + "\n" + date + "\n" + tickers + "\n" + shares + "\n" + commissionFee;

          boolean result = f.buy(data);
          if (result) {
            bf.setVisible(false);
            JOptionPane.showMessageDialog(null,
                "Portfolio Successfully Modified",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            frame.setVisible(true);

          } else {
            JOptionPane.showMessageDialog(null, "Invalid inputs",
                "Invalid Inputs", JOptionPane.ERROR_MESSAGE);
            objBuy.buydtBox.setText("");
            objBuy.buytkBox.setText("");
            objBuy.buyshBox.setText("");
            objBuy.buyComBox.setText("");
          }
        });

        //f.testing();

      } else if (b == 2) {
        JOptionPane.showMessageDialog(null,
            "Portfolio Name incorrect, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);
      }
    });

    n.sellShare.addActionListener((evt -> {
      String s = JOptionPane.showInputDialog("Enter the portfolio Name");
      //make  a call to the controller to validate portfolio name
      int b = f.validatePortfolio(s);
      //boolean b =true;
      if (b == 0) {
        JOptionPane.showMessageDialog(null,
            "Portfolio does not exist, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);

      } else if (b == 1) {
        frame.setVisible(false);
        objSell.buyDate.setText("Date (YYYY-MM-DD)");
        objSell.comLabel.setText("Operation for the selling of portfolio stocks");
        sf.setVisible(true);
        objSell.buyvalButton.setVisible(false);
        objSell.buyTicker.setText("Tickers in the format: AA,AAPB,IBM");
        objSell.buyShare.setText("Shares in order of tickers above like: 10,20,80");
        objSell.buydtBox.setText("");
        objSell.buytkBox.setText("");
        objSell.buyshBox.setText("");
        objSell.buyComBox.setText("");

        objSell.savevalButton.addActionListener(e -> {
          String date = objSell.buydtBox.getText();
          String tickers = objSell.buytkBox.getText();
          String shares = objSell.buyshBox.getText();
          String commissionFee = objSell.buyComBox.getText();

          String data = s + "\n" + date + "\n" + tickers + "\n" + shares + "\n" + commissionFee;

          boolean result = f.sell(data);
          if (result) {
            sf.setVisible(false);
            JOptionPane.showMessageDialog(null,
                "Portfolio Successfully Created",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            frame.setVisible(true);

          } else {
            JOptionPane.showMessageDialog(null, "Invalid inputs",
                "Invalid Inputs", JOptionPane.ERROR_MESSAGE);
            objSell.buydtBox.setText("");
            objSell.buytkBox.setText("");
            objSell.buyshBox.setText("");
            objSell.buyComBox.setText("");
          }
        });


      } else {
        JOptionPane.showMessageDialog(null,
            "Portfolio Name incorrect, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);

      }
    }));

    objCreate.buyBack.addActionListener(evt -> {
      cf.setVisible(false);
      frame.setVisible(true);
    });

    objSell.buyBack.addActionListener(evt -> {
      sf.setVisible(false);
      frame.setVisible(true);
    });
    objBuy.buyBack.addActionListener(evt -> {
      bf.setVisible(false);
      frame.setVisible(true);
    });

    n.finish.addActionListener(e -> {
      f.exitApplication("a");
    });

    n.viewComp.addActionListener(evt -> {

      String s = JOptionPane.showInputDialog("Enter the portfolio Name");
      //make  a call to the controller to validate portfolio name
      int b = f.validatePortfolio(s);
      //boolean b =true;
      if (b == 0) {
        JOptionPane.showMessageDialog(null,
            "Portfolio does not exist, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);

      } else if (b == 1) {
        frame.setVisible(false);
        compFrame.setVisible(true);
        comObj.compButton.setText("");
        comObj.jTextArea1.setText("");
        comObj.compDate.addActionListener(e -> {
          String date = comObj.compButton.getText();
          String data = s + "\n" + date;
          String result = f.composition(data);
          if (result.equals("false")) {
            comObj.jTextArea1.setText("Invalid Date");

          } else {
            String display = "TICKER, No. Of SHARES\n";

            String[] lines = result.split(NEW_LINE_SEPARATOR);
            for (String line : lines) {
              display += line + NEW_LINE_SEPARATOR;
            }
            comObj.jTextArea1.setText(display);
          }

        });

        //f.testing();

      } else {
        JOptionPane.showMessageDialog(null,
            "Portfolio Name incorrect, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);
      }
    });

    n.currentPortfolio.addActionListener(evt -> {

      String s = JOptionPane.showInputDialog("Enter the portfolio Name");
      //make  a call to the controller to validate portfolio name
      int b = f.validatePortfolio(s);
      //boolean b =true;
      if (b == 0) {
        JOptionPane.showMessageDialog(null,
            "Portfolio does not exist, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);

      } else if (b == 1) {
        currPort.compDate.setText("Current Portfolio");
        frame.setVisible(false);
        currFrame.setVisible(true);
        currPort.compButton.setText("");
        currPort.jTextArea1.setText("");
        currPort.compDate.addActionListener(e -> {
          String date = currPort.compButton.getText();
          String data = s + "\n" + date;
          String result = f.value(data);
          if (result.equals("false")) {
            currPort.jTextArea1.setText("Invalid Date");
          } else {
            String display = "TICKER, No. Of SHARES, PRICE\n";
            double totalValue = 0;
            String[] lines = result.split(NEW_LINE_SEPARATOR);
            for (String line : lines) {
              display += line + NEW_LINE_SEPARATOR;
              String[] stock = line.split(COMMA_DELIMITER);
              double stockPrice =
                  Double.parseDouble(stock[PRICE_OF_SHARES].trim())
                      * Double.parseDouble(stock[NUMBER_OF_SHARES].trim());

              totalValue = totalValue + stockPrice;

            }
            display += "\nTotal Value of Portfolio is: " + df.format(totalValue) + "\n";
            currPort.jTextArea1.setText(display);
          }

        });


      } else {
        JOptionPane.showMessageDialog(null,
            "Portfolio Name incorrect, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);
      }
    });

    n.specificdatePortfolio.addActionListener(evt -> {

      String s = JOptionPane.showInputDialog("Enter the portfolio Name");
      //make  a call to the controller to validate portfolio name
      int b = f.validatePortfolio(s);
      //boolean b =true;
      if (b == 0) {
        JOptionPane.showMessageDialog(null,
            "Portfolio does not exist, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);

      } else if (b == 1) {
        spdtPort.compDate.setText("Specific Date Portfolio");
        frame.setVisible(false);
        spdtFrame.setVisible(true);
        spdtPort.compButton.setText("");
        spdtPort.jTextArea1.setText("");
        spdtPort.compDate.addActionListener(e -> {
          String date = spdtPort.compButton.getText();
          String data = s + "\n" + date;
          String result = f.valueOnSpecificDate(data);
          if (result.equals("false")) {
            spdtPort.jTextArea1.setText("Invalid Date");
          } else {
            String display = "TICKER, No. Of SHARES, PRICE\n";
            double totalValue = 0;
            String[] lines = result.split(NEW_LINE_SEPARATOR);
            for (String line : lines) {
              display += line + NEW_LINE_SEPARATOR;
              String[] stock = line.split(COMMA_DELIMITER);
              double stockPrice =
                  Double.parseDouble(stock[PRICE_OF_SHARES].trim())
                      * Double.parseDouble(stock[NUMBER_OF_SHARES].trim());

              totalValue = totalValue + stockPrice;

            }
            display += "\nTotal Value of Portfolio is: " + df.format(totalValue) + "\n";

            spdtPort.jTextArea1.setText(display);
          }

        });


      } else {
        JOptionPane.showMessageDialog(null,
            "Portfolio Name incorrect, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);
      }
    });

    n.costBasis.addActionListener(evt -> {

      String s = JOptionPane.showInputDialog("Enter the portfolio Name");
      //make  a call to the controller to validate portfolio name
      int b = f.validatePortfolio(s);
      //boolean b =true;
      if (b == 0) {
        JOptionPane.showMessageDialog(null,
            "Portfolio does not exist, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);

      } else if (b == 1) {
        costObj.compDate.setText("Cost Basis");
        frame.setVisible(false);
        costObjFrame.setVisible(true);
        costObj.compButton.setText("");
        costObj.jTextArea1.setText("");
        costObj.compDate.addActionListener(e -> {
          String date = costObj.compButton.getText();
          String data = s + "\n" + date;
          String result = f.costBasis(data);
          if (result.equals("false")) {
            costObj.jTextArea1.setText("Invalid Date");
          } else {
            String display = "DATE,OPERATION, TICKER, No. Of SHARES, PRICE, COMMISSION FEE\n";

            String[] lines = result.split(NEW_LINE_SEPARATOR);
            //Write a new student object list to the CSV file
            double totalValue = 0;
            for (String line : lines) {

              String[] stock = line.split(COMMA_DELIMITER);
              double stockPrice =
                  (Double.parseDouble(stock[3].trim())
                      * Double.parseDouble(stock[4].trim()))
                      + Double.parseDouble(stock[5].trim());
              display +=
                  stock[0] + "," + stock[1] + "," + stock[2] + "," + stock[3] + "," + stockPrice
                      + "," + stock[5];
              display += NEW_LINE_SEPARATOR;

              totalValue = totalValue + stockPrice;

            }
            display += "\nTotal Cost Basis of Portfolio is: " + df.format(totalValue) + "\n";

            costObj.jTextArea1.setText(display);
          }

        });

      } else {
        JOptionPane.showMessageDialog(null,
            "Portfolio Name incorrect, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);
      }
    });

    n.investAmount.addActionListener((evt -> {
      String s = JOptionPane.showInputDialog("Enter the portfolio Name");
      //make  a call to the controller to validate portfolio name
      int b = f.validatePortfolio(s);
      //boolean b =true;
      if (b == 0) {
        JOptionPane.showMessageDialog(null,
            "Portfolio does not exist, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);

      } else if (b == 1) {
        frame.setVisible(false);
        invFrame.setVisible(true);
        invPort.invAmountBox.setText("");
        invPort.invComBox.setText("");
        invPort.invDateBox.setText("");
        invPort.invVal.setText("");
        String tickers = f.listOfCompaniesInPortfolio(s);
        if (tickers.equals("")) {
          invFrame.setVisible(false);
          JOptionPane.showMessageDialog(null, "The Portfolio is Empty",
              "Invalid!", JOptionPane.ERROR_MESSAGE);
          frame.setVisible(true);
        } else {
          invPort.invTicker.setText(tickers);
          invPort.invSave.addActionListener(e -> {
            String date = invPort.invDateBox.getText();
            String amount = invPort.invAmountBox.getText();
            String commission = invPort.invComBox.getText();
            String values = invPort.invVal.getText();
            String data = s + "\n" + date + "\n" + tickers + "\n" + values + "\n" + amount + "\n"
                + commission;
            boolean result = f.singleInvestment(data);
            if (result) {
              invFrame.setVisible(false);
              JOptionPane.showMessageDialog(null,
                  "Portfolio Successfully Modified",
                  "Success", JOptionPane.INFORMATION_MESSAGE);
              frame.setVisible(true);

            } else {
              JOptionPane.showMessageDialog(null, "Invalid inputs",
                  "Invalid Inputs", JOptionPane.ERROR_MESSAGE);
              invPort.invAmountBox.setText("");
              invPort.invComBox.setText("");
              invPort.invDateBox.setText("");
              invPort.invVal.setText("");
            }
          });
        }


      } else {
        JOptionPane.showMessageDialog(null,
            "Portfolio Name incorrect, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);
      }
    }));

    n.dollarcostAverage.addActionListener((evt -> {
      String s = JOptionPane.showInputDialog("Enter the portfolio Name");
      //make  a call to the controller to validate portfolio name
      int b = f.validatePortfolio(s);
      //boolean b =true;
      if (b == 0 || b == 1) {
        frame.setVisible(false);
        dcaFrame.setVisible(true);
        dcaPort.dcAmountBox.setText("");
        dcaPort.dcComBox.setText("");
        dcaPort.dcFromDateBox.setText("");
        dcaPort.dcToDateBox.setText("");
        dcaPort.dcRecBox.setText("");
        dcaPort.dcTickerBox.setText("");
        dcaPort.dcTickerValBox.setText("");
        dcaPort.dcSave.addActionListener(e -> {
          String fromDate = dcaPort.dcFromDateBox.getText();
          String toDate = dcaPort.dcToDateBox.getText();
          String amount = dcaPort.dcAmountBox.getText();
          String tickers = dcaPort.dcTickerBox.getText();
          String percentage = dcaPort.dcTickerValBox.getText();
          String period = dcaPort.dcRecBox.getText();
          String commission = dcaPort.dcComBox.getText();

          String data = s + "\n" + fromDate + "\n" + toDate + "\n"
              + tickers + "\n" + percentage + "\n" + amount + "\n" + commission + "\n" + period;
          boolean result = f.dollarCostAveraging(data);
          if (result) {
            dcaFrame.setVisible(false);
            JOptionPane.showMessageDialog(null,
                "Portfolio Successfully Modified",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            frame.setVisible(true);

          } else {
            JOptionPane.showMessageDialog(null, "Invalid inputs",
                "Invalid Inputs", JOptionPane.ERROR_MESSAGE);
            dcaPort.dcAmountBox.setText("");
            dcaPort.dcComBox.setText("");
            dcaPort.dcFromDateBox.setText("");
            dcaPort.dcToDateBox.setText("");
            dcaPort.dcRecBox.setText("");
            dcaPort.dcTickerBox.setText("");
            dcaPort.dcTickerValBox.setText("");
          }
        });

      } else {
        JOptionPane.showMessageDialog(null,
            "Portfolio Name incorrect, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);
      }
    }));

    costObj.compBack.addActionListener(evt -> {
      costObjFrame.setVisible(false);
      frame.setVisible(true);
    });
    currPort.compBack.addActionListener(evt -> {
      currFrame.setVisible(false);
      frame.setVisible(true);
    });
    invPort.invBack.addActionListener(evt -> {
      invFrame.setVisible(false);
      frame.setVisible(true);
    });
    dcaPort.jButton1.addActionListener(evt -> {
      dcaFrame.setVisible(false);
      frame.setVisible(true);
    });
    spdtPort.compBack.addActionListener(evt -> {
      spdtFrame.setVisible(false);
      frame.setVisible(true);
    });
    comObj.compBack.addActionListener(evt -> {
      compFrame.setVisible(false);
      frame.setVisible(true);
    });

    n.copyPortfolio.addActionListener(evt -> {

      String s = JOptionPane.showInputDialog("Enter the portfolio Name");
      //make  a call to the controller to validate portfolio name
      int b = f.validatePortfolio(s);
      //boolean b =true;
      if (b == 0) {
        JOptionPane.showMessageDialog(null,
            "Portfolio does not exist, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);

      } else if (b == 1) {
        boolean result = f.copyPortfolio(s);
        if (result) {
          JOptionPane.showMessageDialog(null, "Downloaded Successfully!",
              "Success", JOptionPane.INFORMATION_MESSAGE);
          frame.setVisible(true);

        } else {
          JOptionPane.showMessageDialog(null, "Invalid inputs",
              "Invalid Inputs", JOptionPane.ERROR_MESSAGE);
        }

      } else {
        JOptionPane.showMessageDialog(null,
            "Portfolio Name incorrect, Try again",
            "Invalid portfolio", JOptionPane.ERROR_MESSAGE);
      }
    });


  }


}
