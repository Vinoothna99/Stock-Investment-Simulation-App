import controller.Adapter;
import controller.GraphicalControllerImpl;
import controller.StockController;
import controller.StockControllerChild;
import java.io.IOException;
import java.util.Scanner;
import modal.InvestmentStrategyImpl;
import modal.Stock;
import modal.StockModal;
import view.FlexiblePortfolioViewImpl;


/**
 * It creates objects of Modal, View and Controllers and it initiates the program.
 */

public class MainClass {

  /**
   * Main method handles all test cases.
   *
   * @param args argument.
   */
  public static void main(String[] args) {
    int i;
    System.out.println("Welcome to the StockPortfolio Project");
    System.out.println("1. Enter choice 1 for the text based view interface");
    System.out.println("2. Enter choice 2 for the graphical view user interface");
    Scanner sc = new Scanner(System.in);
    i = sc.nextInt();
    if (i == 1) {
      try {
        FlexiblePortfolioViewImpl view = new FlexiblePortfolioViewImpl(System.out);

        StockModal controllerModal = new Stock();
        StockControllerChild delegate = new StockControllerChild(System.in,
            System.out, view, controllerModal);

        Stock delegateModal = new Stock();
        InvestmentStrategyImpl modal = new InvestmentStrategyImpl(delegateModal);
        StockController adapter = new Adapter(System.in, System.out, view, modal, delegate);

        adapter.stockControllerExecute();

      } catch (IOException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    if (i == 2) {
      Stock delegateModal = new Stock();
      InvestmentStrategyImpl modal = new InvestmentStrategyImpl(delegateModal);
      GraphicalControllerImpl gc = new GraphicalControllerImpl(modal);
    }

  }
}
