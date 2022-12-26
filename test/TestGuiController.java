import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import controller.GraphicalControllerImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.Timer;
import modal.InvestmentStrategyImpl;
import modal.Stock;
import org.junit.Test;
import view.BuyFrame;
import view.CompositionFrame;
import view.CreatePortfolioFrame;
import view.DollarCostFrame;
import view.InvestmentFrame;
import view.MainMenuFrame;

/**
 * This class tests if the GUI is being initialized as expected and the Investment Operations.
 */

public class TestGuiController extends JFrame {

  private static final DecimalFormat df = new DecimalFormat("0.00 \n");
  MainMenuFrame n = new MainMenuFrame();
  JFrame frame = n.getFrame();

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

  private static final String absolutePath = System.getProperty("user.dir");
  private static final String separator = System.getProperty("file.separator");

  @Test
  public void verifyGo() {
    Stock delegateModal = new Stock();
    InvestmentStrategyImpl modal = new InvestmentStrategyImpl(delegateModal);
    GraphicalControllerImpl gc = new GraphicalControllerImpl(modal);

    Timer timer = new Timer(6000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        assertTrue(frame.isVisible());
      }
    });
    timer.setRepeats(false); // Only execute once
    timer.start();

  }

  @Test
  public void testVerifyCreate() {
    Stock delegateModal = new Stock();
    InvestmentStrategyImpl modal = new InvestmentStrategyImpl(delegateModal);
    GraphicalControllerImpl gc = new GraphicalControllerImpl(modal);
    assertTrue(gc.create("alpha\n2022-11-19\nAA,AAPB,IBM\n30,100,60\n20"));
    assertFalse(gc.create("alpha\n2022-11-19\nAA,AAPB,IBM\n30,9.98,60\n20"));

    String value = "2022-11-19,BUY,20,AA 30,AAPB 100,IBM 60,\n";

    assertEquals(value, modal.readPortfolio(absolutePath + separator + "data" + separator
        + "alpha.csv"));
  }

  @Test
  public void testSingleTimeInvestment() {
    Stock delegateModal = new Stock();
    InvestmentStrategyImpl modal = new InvestmentStrategyImpl(delegateModal);
    GraphicalControllerImpl gc = new GraphicalControllerImpl(modal);
    //
    assertTrue(gc.singleInvestment("invest\n" //portfolio name
        + "2022-12-15\n" //date
        + "AAA,IBM\n"  //tickers
        + "30,70\n" // corresponding percentages
        + "4000\n" //Amount invested
        + "20"));   // Commission Fee

    String value = "2022-12-15,STRATEGY,4000,20,AAA 30,IBM 20,\n";

    assertEquals(value, modal.readPortfolio(absolutePath + separator + "data" + separator
        + "invest.csv"));

    assertFalse(gc.singleInvestment("invest\n2022-12-15\nAAA,IBM\n110,70\n4000\n20"));
    assertFalse(gc.singleInvestment("invest\n2022-12-15\nAAA,IBM\n-30,70\n4000\n20"));
    assertFalse(gc.singleInvestment("invest\n2022-12-15\nAAA,IBM\n30,70\n-4000\n20"));
    assertFalse(gc.singleInvestment("invest\n2022-12-212\nAAA,IBM\n30,70\n4000\n-20"));


  }

  @Test
  public void testDollarCostAveraging() {
    Stock delegateModal = new Stock();
    InvestmentStrategyImpl modal = new InvestmentStrategyImpl(delegateModal);
    GraphicalControllerImpl gc = new GraphicalControllerImpl(modal);
    assertTrue(gc.dollarCostAveraging("invest\n" //Portfolio Name
        + "2022-12-15\n" // from date
        + "2023-05-01\n" // to date
        + "AAA,IBM\n"  // ticker values
        + "30,70\n" //Corresponding percentages
        + "4000\n" // Amount invested
        + "20\n" //commission fee
        + "30")); // Recurring Period

    String value = "2022-12-15,STRATEGY,4000,20,AAA 30,IBM 70,\n"
        + "2022-01-14,STRATEGY,4000,20,AAA 30,IBM 70,\n"
        + "2022-02-13,STRATEGY,4000,20,AAA 30,IBM 70,\n"
        + "2022-03-15,STRATEGY,4000,20,AAA 30,IBM 70,\n"
        + "2022-04-14,STRATEGY,4000,20,AAA 30,IBM 70,\n";

    assertEquals(value, modal.readPortfolio(absolutePath + separator + "data" + separator
        + "invest.csv"));

    assertFalse(
        gc.dollarCostAveraging("invest\n2022-12-15\n2022-05-01\nAAA,IBM\n110,70\n4000\n20\n30"));
    assertFalse(
        gc.dollarCostAveraging("invest\n2022-12-15\n2022-05-01\nAAA,IBM\n30,70\n4000\n-20\n45"));
    assertFalse(
        gc.dollarCostAveraging("invest\n2022-12-15\n2022-05-35\nAAA,IBM\n110,70\n-4000\n20\n45"));
    assertFalse(
        gc.dollarCostAveraging("invest\n2022-12-15\n2022-05-35\nAAA,IBM\n110,70\n4000\n20\n-45"));

  }
}

