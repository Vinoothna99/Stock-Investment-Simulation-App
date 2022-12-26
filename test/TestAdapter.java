import static org.junit.Assert.assertEquals;

import controller.Adapter;
import controller.StockController;
import controller.StockControllerChild;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import modal.InvestmentStrategyImpl;
import modal.Stock;
import modal.StockModal;
import org.junit.Test;
import view.FlexiblePortfolioViewImpl;

/**
 * Testing if all modal, view and controller are working together are expected.
 */
public class TestAdapter {

  String outputString;

  private static final DecimalFormat df = new DecimalFormat("0.00 \n");

  @Test
  public void verifyMainMenu() throws IOException {
    outputString = "";
    outputString += "-------------------------------------------------" +
        "------------------------------------------ \n";
    outputString += "Please input required option number from the menu \n";
    outputString += "1. Create Portfolio \n";
    outputString += "2. Buy Shares on a Specific Date \n";
    outputString += "3. Sell Shares on a Specific Date \n";
    outputString += "4. View Composition of a Portfolio\n";
    outputString += "5. Value of a Current Portfolio \n";
    outputString += "6. Value of a Portfolio on a Specific Date\n";
    outputString += "7. Cost Basis of a Portfolio\n";
    outputString += "8. Performance of Portfolio Over a Period\n";
    outputString += "9. Get a Copy of Current Portfolio \n";
    outputString += "10. Get a List of Enlisted Companies \n";
    outputString += "11. Quit \n";
    outputString += "----------------------------------------------------------" +
        "---------------------------------- \n";

    InputStream in = new ByteArrayInputStream("11".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    FlexiblePortfolioViewImpl view = new FlexiblePortfolioViewImpl(out);

    StockModal controllerModal = new Stock();
    StockControllerChild delegate = new StockControllerChild(in, out, view, controllerModal);

    Stock delegateModal = new Stock();
    InvestmentStrategyImpl modal = new InvestmentStrategyImpl(delegateModal);

    StockController adapter = new Adapter(in, out, view, modal, delegate);
    adapter.stockControllerExecute();
    assertEquals(outputString, new String(bytes.toByteArray()));
  }

  @Test
  public void verifyCreateFlexiblePortfolioByUploadCsv() throws IOException {
    outputString = "";
    outputString += " \nPlease enter the Portfolio Name \n";
    outputString += " \nPlease input required option number from the menu \n";
    outputString += "1. Upload CSV \n";
    outputString += "2. Input Ticker Values \n";
    outputString += "3. Quit \n";
    outputString += " \nPlease specify the path of CSV file. \n";
    outputString += " \nPortfolio Successfully Created. \n";
    File file = new File("data\\newFile.csv");
    file.delete();
    InputStream in = new ByteArrayInputStream("newFile\n1\nuploadContent.csv\n3\n11".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    FlexiblePortfolioViewImpl view = new FlexiblePortfolioViewImpl(out);

    StockModal controllerModal = new Stock();
    StockControllerChild delegate = new StockControllerChild(in, out, view, controllerModal);

    Stock delegateModal = new Stock();
    InvestmentStrategyImpl modal = new InvestmentStrategyImpl(delegateModal);

    Adapter adapter = new Adapter(in, out, view, modal, delegate);
    adapter.executeFlexibleMenuOptions(1, "");
    assertEquals(outputString, new String(bytes.toByteArray()));
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String date = df.format(new Date());
    String value = date + ",BUY,AAC-U 90,AAME 30,AAN 40,AAN-W 10,AAOI 30,AAON 50,\n";
    assertEquals(value, modal.readPortfolio("C:\\Users\\Vinoothna\\Downloads\\" +
        "Assignment-5\\assignment\\data\\newFile.csv"));
  }


  @Test
  public void verifyBuyShares() {
    outputString = " \nYou will be charged $10 commission fee for buying/selling " +
        "stocks of a company. \n" +
        " Press 'Y' to continue and 'N' to exit. \n";
    outputString += " \nPlease enter the date in yyyy-mm-dd format: \n";
    outputString += "\nEnter one or more ticker values along with number of " +
        "shares. Ex: AAA 80,BBB 45 \n";
    outputString += " \nPortfolio Successfully Modified. \n";

    InputStream in = new ByteArrayInputStream("Y\n2022-11-09\nAAME 100\n11".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    FlexiblePortfolioViewImpl view = new FlexiblePortfolioViewImpl(out);

    StockModal controllerModal = new Stock();
    StockControllerChild delegate = new StockControllerChild(in, out, view, controllerModal);

    Stock delegateModal = new Stock();
    InvestmentStrategyImpl modal = new InvestmentStrategyImpl(delegateModal);
    Adapter adapter = new Adapter(in, out, view, modal, delegate);
    adapter.executeFlexibleMenuOptions(2, "newFile");
    assertEquals(outputString, new String(bytes.toByteArray()));
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String date = df.format(new Date());
    String value = date + ",BUY,AAC-U 90,AAME 30,AAN 40,AAN-W 10,AAOI 30,AAON 50,\n"
        + "2022-11-09,BUY,AAME 100,\n";
    assertEquals(value, modal.readPortfolio("C:\\Users\\Vinoothna\\Downloads" +
        "\\Assignment-5\\assignment\\data\\newFile.csv"));
  }

  @Test
  public void verifyInvalidBuyShares() {
    outputString = " \nYou will be charged $10 commission fee for buying/selling " +
        "stocks of a company. \n" +
        " Press 'Y' to continue and 'N' to exit. \n";
    outputString += " \nPlease enter the date in yyyy-mm-dd format: \n";
    outputString += " \nDate format is incorrect\n";

    InputStream in = new ByteArrayInputStream("Y\n2022-11-99\nAAME 100\n11".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    FlexiblePortfolioViewImpl view = new FlexiblePortfolioViewImpl(out);

    StockModal controllerModal = new Stock();
    StockControllerChild delegate = new StockControllerChild(in, out, view, controllerModal);

    Stock delegateModal = new Stock();
    InvestmentStrategyImpl modal = new InvestmentStrategyImpl(delegateModal);
    Adapter adapter = new Adapter(in, out, view, modal, delegate);
    adapter.executeFlexibleMenuOptions(2, "newFile");
    assertEquals(outputString, new String(bytes.toByteArray()));

  }


  @Test
  public void verifySellShares() {
    outputString = " \nYou will be charged $10 commission fee for buying/selling " +
        "stocks of a company. \n" +
        " Press 'Y' to continue and 'N' to exit. \n";
    outputString += " \nPlease enter the date in yyyy-mm-dd format: \n";
    outputString += "\nEnter one or more ticker values along with number of " +
        "shares. Ex: AAA 80,BBB 45 \n";
    outputString += " \nPortfolio Successfully Modified. \n";

    InputStream in = new ByteArrayInputStream("Y\n2022-11-09\nAAME 100\n11".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    FlexiblePortfolioViewImpl view = new FlexiblePortfolioViewImpl(out);

    StockModal controllerModal = new Stock();
    StockControllerChild delegate = new StockControllerChild(in, out, view, controllerModal);

    Stock delegateModal = new Stock();
    InvestmentStrategyImpl modal = new InvestmentStrategyImpl(delegateModal);
    Adapter adapter = new Adapter(in, out, view, modal, delegate);
    adapter.executeFlexibleMenuOptions(3, "newFile");
    assertEquals(outputString, new String(bytes.toByteArray()));
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String date = df.format(new Date());
    String value = date + ",BUY,AAC-U 90,AAME 30,AAN 40,AAN-W 10,AAOI 30,AAON 50,\n"
        + "2022-11-09,SELL,AAME 100,\n";
    assertEquals(value, modal.readPortfolio("C:\\Users\\Vinoothna\\Downloads" +
        "\\Assignment-5\\assignment\\data\\newFile.csv"));
  }

  @Test
  public void verifyInvalidSellShares() {
    outputString = " \nYou will be charged $10 commission fee for buying/selling " +
        "stocks of a company. \n" +
        " Press 'Y' to continue and 'N' to exit. \n";
    outputString += " \nPlease enter the date in yyyy-mm-dd format: \n";
    outputString += " \nDate format is incorrect\n";

    InputStream in = new ByteArrayInputStream("Y\n11/12/9999\nAAME 100\n11".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    FlexiblePortfolioViewImpl view = new FlexiblePortfolioViewImpl(out);

    StockModal controllerModal = new Stock();
    StockControllerChild delegate = new StockControllerChild(in, out, view, controllerModal);

    Stock delegateModal = new Stock();
    InvestmentStrategyImpl modal = new InvestmentStrategyImpl(delegateModal);
    Adapter adapter = new Adapter(in, out, view, modal, delegate);
    adapter.executeFlexibleMenuOptions(3, "newFile");
    assertEquals(outputString, new String(bytes.toByteArray()));

  }


  @Test
  public void verifyUnavailableSellShares() {
    outputString = " \nYou will be charged $10 commission fee for buying/selling " +
        "stocks of a company. \n" +
        " Press 'Y' to continue and 'N' to exit. \n";
    outputString += " \nPlease enter the date in yyyy-mm-dd format: \n";
    outputString += "\nEnter one or more ticker values along with number of " +
        "shares. Ex: AAA 80,BBB 45 \n";
    outputString += " \nThese shares cannot be sold. Please enter valid shares to sell.\n";

    InputStream in = new ByteArrayInputStream("Y\n2022-11-10\nIBM 100\n11".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    FlexiblePortfolioViewImpl view = new FlexiblePortfolioViewImpl(out);

    StockModal controllerModal = new Stock();
    StockControllerChild delegate = new StockControllerChild(in, out, view, controllerModal);

    Stock delegateModal = new Stock();
    InvestmentStrategyImpl modal = new InvestmentStrategyImpl(delegateModal);
    Adapter adapter = new Adapter(in, out, view, modal, delegate);
    adapter.executeFlexibleMenuOptions(3, "newFile");
    assertEquals(outputString, new String(bytes.toByteArray()));

  }

  @Test
  public void verifyValueOfPortfolioOnSpecificDate() {
    outputString = "Total Value of Portfolio is: 293.00 ";

    InputStream in = new ByteArrayInputStream("2022-11-09\nAAME 100\n11".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    FlexiblePortfolioViewImpl view = new FlexiblePortfolioViewImpl(out);

    StockModal controllerModal = new Stock();
    StockControllerChild delegate = new StockControllerChild(in, out, view, controllerModal);

    Stock delegateModal = new Stock();
    InvestmentStrategyImpl modal = new InvestmentStrategyImpl(delegateModal);
    Adapter adapter = new Adapter(in, out, view, modal, delegate);
    adapter.executeFlexibleMenuOptions(6, "newFile");
    String line = new String(bytes.toByteArray());
    String[] lines = line.split("\n");
    assertEquals(outputString, lines[lines.length - 1]);


  }

  @Test
  public void verifyCostBasisOfPortfolio() {
    outputString = "Total Cost Basis of Portfolio is: 303.00 ";

    InputStream in = new ByteArrayInputStream("2022-11-09\nAAME 100\n11".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    FlexiblePortfolioViewImpl view = new FlexiblePortfolioViewImpl(out);

    StockModal controllerModal = new Stock();
    StockControllerChild delegate = new StockControllerChild(in, out, view, controllerModal);

    Stock delegateModal = new Stock();
    InvestmentStrategyImpl modal = new InvestmentStrategyImpl(delegateModal);
    Adapter adapter = new Adapter(in, out, view, modal, delegate);
    adapter.executeFlexibleMenuOptions(7, "newFile");
    String line = new String(bytes.toByteArray());
    String[] lines = line.split("\n");
    assertEquals(outputString, lines[lines.length - 1]);

  }

}

