import static org.junit.Assert.assertEquals;

import controller.StockController;
import controller.StockControllerChild;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import modal.StockModal;
import org.junit.Test;
import view.StockView;
import view.StockViewImpl;

/**
 * Testing Controller in isolation.
 */
public class TestController {

  @Test
  public void verifyUploadCsvFile() {

    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuffer output = new StringBuffer();
    File file = new File("uploadContent.csv");
    InputStream in = new ByteArrayInputStream("newFile\n1\nuploadContent.csv\n3\n6".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    StockModal modal = new MockModal(log, "1234321");
    StockController controller = new StockControllerChild(in, out, view, modal);
    controller.createPortfolio();
    assertEquals("File Name: newFile.csvFile Name: newFile, " +
            "Path: C:\\Users\\Vinoothna\\Downloads\\Assignment-5\\" +
            "assignment\\data\\uploadContent.csv",
        log.toString()); //inputs reached the model correctly

  }

  @Test
  public void verifyCreateCsvTickerValues() throws IOException {

    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuffer output = new StringBuffer();
    File file = new File("uploadContent.csv");
    InputStream in = new ByteArrayInputStream("newFile\n2\nAA 30,AAPB 20\n3\n6".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    StockModal modal = new MockModal(log, "1234321");
    StockController controller = new StockControllerChild(in, out, view, modal);
    controller.createPortfolio();
    assertEquals("File Name: newFile.csvFile Name: newFile, Ticker Values: AA 30,AAPB 20",
        log.toString()); //inputs reached the model correctly

  }

  @Test
  public void verifyCompositionOfPortfolio() throws IOException {

    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuffer output = new StringBuffer();
    File file = new File("uploadContent.csv");
    InputStream in = new ByteArrayInputStream("compositionCheck\n6".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    StockModal modal = new MockModal(log, "1234321");
    StockController controller = new StockControllerChild(in, out, view, modal);
    controller.compositionOfPortfolio();
    assertEquals("File Name: compositionCheck.csv", log.toString());
    //inputs reached the model correctly

  }

  @Test
  public void verifyValueOfPortfolio() throws IOException {

    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuffer output = new StringBuffer();
    File file = new File("uploadContent.csv");
    InputStream in = new ByteArrayInputStream("compositionCheck\n6".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    StockModal modal = new MockModal(log, "1234321");
    StockController controller = new StockControllerChild(in, out, view, modal);
    controller.valueOfPortfolio();
    assertEquals("File Name: compositionCheck.csv", log.toString());
    //inputs reached the model correctly

  }

  @Test
  public void testDownloadPortfolio() throws IOException {

    StringBuilder log = new StringBuilder(); //log for mock model
    StringBuffer output = new StringBuffer();
    File file = new File("uploadContent.csv");
    InputStream in = new ByteArrayInputStream("compositionCheck\n6".getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    StockView view = new StockViewImpl(out);
    StockModal modal = new MockModal(log, "1234321");
    StockController controller = new StockControllerChild(in, out, view, modal);
    controller.getPortfolio();
    assertEquals("File Name: compositionCheck.csv", log.toString());
    //inputs reached the model correctly

  }
}
