import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.StockController;
import controller.StockControllerChild;
import controller.StockControllerImpl;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import modal.Stock;
import modal.StockModal;
import org.junit.Test;
import setup.classes.SetUpStock;
import setup.interfaces.SetUpStockData;
import view.StockView;
import view.StockViewImpl;

/**
 * Testing if all modal, view and controller are working together are expected.
 */
public class CompleteTest {

  String outputString;

  private static final DecimalFormat df = new DecimalFormat("0.00 \n");


  @Test
  public void verifyGo() throws IOException {
    outputString = "";
    outputString += "--------------------------------------------------" +
        "----------------------------------------- \n";
    outputString += "Please input required option number from the menu \n";
    outputString += "1. Create Portfolio \n";
    outputString += "2. View Portfolio Composition \n";
    outputString += "3. Find Value Of Portfolio \n";
    outputString += "4. Download Portfolio \n";
    outputString += "5. Download List Of Enlisted Companies \n";
    outputString += "6. Quit \n";
    outputString += "----------------------------------------------------" +
        "---------------------------------------- \n";

    InputStream in = new ByteArrayInputStream("6".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    StockModal modal = new Stock();
    StockController controller = new StockControllerChild(in, out, view, modal);
    controller.stockControllerExecute();
    assertEquals(outputString, new String(bytes.toByteArray()));
  }

  @Test
  public void verifyCreateByUploadCsv() {
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
    InputStream in = new ByteArrayInputStream("newFile\n1\nuploadContent.csv\n3\n6".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    StockModal modal = new Stock();
    StockControllerImpl controller = new StockControllerChild(in, out, view, modal);
    controller.updatePricesToday();
    controller.createPortfolio();
    assertEquals(outputString, new String(bytes.toByteArray()));
  }


  @Test
  public void verifyUploadInvalidCsvFile() {
    outputString = "";
    outputString += " \nPlease enter the Portfolio Name \n";
    outputString += " \nPlease input required option number from the menu \n";
    outputString += "1. Upload CSV \n";
    outputString += "2. Input Ticker Values \n";
    outputString += "3. Quit \n";
    outputString += " \nPlease specify the path of CSV file. \n";
    outputString += " \nPortfolio creation failed. Please try again. \n";
    outputString += " \nPlease input required option number from the menu \n";
    outputString += "1. Upload CSV \n";
    outputString += "2. Input Ticker Values \n";
    outputString += "3. Quit \n";
    File file = new File("newFile.csv");
    file.delete();
    InputStream in = new ByteArrayInputStream("newFile\n1\nuploadInvalidContent.csv\n3\n6"
        .getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    StockModal modal = new Stock();
    StockControllerImpl controller = new StockControllerChild(in, out, view, modal);
    controller.updatePricesToday();
    controller.createPortfolio();
    assertEquals(outputString, bytes.toString());
  }

  @Test
  public void verifyInvalidFileName() {
    outputString = "";
    outputString += " \nPlease enter the Portfolio Name \n";
    outputString += " \nPortfolio name should only consist of alphabets \n";
    outputString += " \nPlease enter the Portfolio Name \n";
    outputString += " \nPlease input required option number from the menu \n";
    outputString += "1. Upload CSV \n";
    outputString += "2. Input Ticker Values \n";
    outputString += "3. Quit \n";
    File file = new File("newFile.csv");
    file.delete();
    InputStream in = new ByteArrayInputStream("new123\nnewFile\n3".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    StockModal modal = new Stock();
    StockControllerImpl controller = new StockControllerChild(in, out, view, modal);
    controller.updatePricesToday();
    controller.createPortfolio();
    assertEquals(outputString, new String(bytes.toByteArray()));
  }


  @Test
  public void verifyCreateByEnteringTicker() {
    outputString = "";
    outputString += " \nPlease enter the Portfolio Name \n";
    outputString += " \nPlease input required option number from the menu \n";
    outputString += "1. Upload CSV \n";
    outputString += "2. Input Ticker Values \n";
    outputString += "3. Quit \n";
    outputString += "\nEnter one or more ticker values along with number of shares. " +
        "Ex: AAA 80,BBB 45 \n";
    outputString += " \nPortfolio Successfully Created. \n";
    File file = new File("newFile.csv");
    file.delete();
    InputStream in = new ByteArrayInputStream("newFile\n2\nAA 30,AAPB 20\n3\n6".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    StockModal modal = new Stock();
    StockControllerImpl controller = new StockControllerChild(in, out, view, modal);
    controller.updatePricesToday();
    controller.createPortfolio();
    assertEquals(outputString, new String(bytes.toByteArray()));
  }


  @Test
  public void verifyCreateByEnteringInvalidTicker_FractionShares() {
    outputString = "";
    outputString += " \nPlease enter the Portfolio Name \n";
    outputString += " \nPlease input required option number from the menu \n";
    outputString += "1. Upload CSV \n";
    outputString += "2. Input Ticker Values \n";
    outputString += "3. Quit \n";
    outputString += "\nEnter one or more ticker values along with number of shares. " +
        "Ex: AAA 80,BBB 45 \n";
    outputString += " \nPortfolio creation failed. Please try again. \n";
    outputString += " \nPlease input required option number from the menu \n";
    outputString += "1. Upload CSV \n";
    outputString += "2. Input Ticker Values \n";
    outputString += "3. Quit \n";
    File file = new File("newFile.csv");
    file.delete();
    InputStream in = new ByteArrayInputStream("newFile\n2\nAA 30,AAYTR 20\n3\n6".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    StockModal modal = new Stock();
    StockControllerImpl controller = new StockControllerChild(in, out, view, modal);
    controller.updatePricesToday();
    controller.createPortfolio();
    assertEquals(outputString, new String(bytes.toByteArray()));

    file.delete();
    in = new ByteArrayInputStream("newFile\n2\nAA 30,AAPB 20.90\n3\n6".getBytes());
    bytes = new ByteArrayOutputStream();
    out = new PrintStream(bytes);
    view = new StockViewImpl(out);
    modal = new Stock();
    controller = new StockControllerChild(in, out, view, modal);
    controller.updatePricesToday();
    controller.createPortfolio();
    assertEquals(outputString, new String(bytes.toByteArray()));

  }

  @Test
  public void verifyCompositionOfShares() {
    outputString = "";
    outputString += " \nPlease enter the Portfolio Name \n";
    outputString += "TICKER, No. Of SHARES \n";
    outputString += "AA, 30\nAAPB, 20\n";

    InputStream in = new ByteArrayInputStream("compositionCheck\n6".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    StockModal modal = new Stock();
    StockControllerImpl controller = new StockControllerChild(in, out, view, modal);
    controller.updatePricesToday();
    controller.compositionOfPortfolio();

    assertEquals(outputString, new String(bytes.toByteArray()));


  }

  @Test
  public void verifyValueOfShares() {
    outputString = "";
    outputString += " \nPlease enter the Portfolio Name \n";
    outputString += "TICKER, No. Of SHARES, PRICE of SHARES \n";
    SetUpStockData stock = new SetUpStock();
    stock.updatePricesToday();
    double value1 = stock.getPrice("AA");
    double value2 = stock.getPrice("AAPB");
    double totalValue = value1 * 30 + value2 * 20;
    outputString += "AA, 30, " + df.format(value1 * 30) + "\nAAPB, 20, " +
        df.format(value2 * 20) + "\n";
    outputString += "\n Total Value of Portfolio is: " + df.format(totalValue);

    InputStream in = new ByteArrayInputStream("compositionCheck\n6".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    StockModal modal = new Stock();
    StockControllerImpl controller = new StockControllerChild(in, out, view, modal);
    controller.updatePricesToday();
    controller.valueOfPortfolio();

    assertEquals(outputString, new String(bytes.toByteArray()));


  }

  @Test
  public void verifyDownloadPortfolio() {

    InputStream in = new ByteArrayInputStream("newFile\n6".getBytes());
    File file = new File("newFile-copy.csv");
    file.delete();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    StockModal modal = new Stock();
    StockControllerImpl controller = new StockControllerChild(in, out, view, modal);
    controller.updatePricesToday();
    controller.getPortfolio();

    assertTrue(file.exists());


  }


  @Test
  public void verifyEnlistedCompany() {
    InputStream in = new ByteArrayInputStream("6".getBytes());
    File file = new File("listed_companies-copy.csv");
    file.delete();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    StockModal modal = new Stock();
    StockControllerImpl controller = new StockControllerChild(in, out, view, modal);
    controller.updatePricesToday();
    controller.displayEnlistedCompanies();

    assertTrue(file.exists());


  }


}

