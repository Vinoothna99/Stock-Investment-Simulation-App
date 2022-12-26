import static org.junit.Assert.assertEquals;

import controller.StockControllerChild;
import controller.StockControllerImpl;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
 * Testing view in isolation.
 */
public class TestView {

  private static final DecimalFormat df = new DecimalFormat("0.00 \n");

  String outputString;

  @Test
  public void testComposition() {
    outputString = "";

    outputString += "TICKER, No. Of SHARES \n";
    outputString += "AA, 30\nAAPB, 20\n";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    view.displayPortfolioComposition("AA,30\nAAPB,20\n");

    assertEquals(outputString, new String(bytes.toByteArray()));


  }

  @Test
  public void testValue() {
    outputString = "";
    outputString += " \nPlease enter the Portfolio Name \n";
    outputString += "TICKER, No. Of SHARES, PRICE of SHARES \n";
    SetUpStockData stock = new SetUpStock();
    stock.updatePricesToday();
    double value1 = stock.getPrice("AA");
    double value2 = stock.getPrice("AAPB");
    double totalValue = value1 * 30 + value2 * 20;
    outputString += "AA, 30, " + df.format(value1 * 30) + "\nAAPB, 20, "
        + df.format(value2 * 20) + "\n";
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
}
