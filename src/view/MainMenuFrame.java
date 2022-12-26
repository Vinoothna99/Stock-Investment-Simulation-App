package view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Class represents the creation of the frame for the main menu portfolio operation.
 */
public class MainMenuFrame {


  JFrame j1;

  /**
   * Constructor is used to create the frame object and displays the welcome message on the menu.
   */
  public MainMenuFrame() {
    j1 = new JFrame("Welcome to the main menu of the Graphical view user interface");
    initComponents();
  }

  private void initComponents() {

    jPanel = new JPanel();
    createPortf = new JButton();
    buyShare = new JButton();
    sellShare = new JButton();
    viewComp = new JButton();
    currentPortfolio = new JButton();
    specificdatePortfolio = new JButton();
    costBasis = new JButton();
    copyPortfolio = new JButton();
    listedCompany = new JButton();
    investAmount = new JButton();
    dollarcostAverage = new JButton();
    finish = new JButton();

    j1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    createPortf.setText("Create Portfolio");

    buyShare.setText("Buy Shares on a Specific Date");

    sellShare.setText("Sell shares on a Specific Date");

    viewComp.setText("View Composition of a Portfolio");

    currentPortfolio.setText("Value of a Current Portfolio");

    specificdatePortfolio.setText("Value of a Portfolio on a Specific Date");

    costBasis.setText("Cost Basis Of a Portfolio");

    copyPortfolio.setText("Get a Copy of Current Portfolio");

    listedCompany.setText("Get a list of Enlisted Companies");
    listedCompany.setVisible(false);

    investAmount.setText("Invest Fixed amount of Existing Portfolio");

    dollarcostAverage.setText("Dollar Cost Averaging");

    finish.setText("Quit");

    GroupLayout jPanelLayout = new GroupLayout(jPanel);
    jPanel.setLayout(jPanelLayout);
    jPanelLayout.setHorizontalGroup(
        jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(finish)
                    .addComponent(dollarcostAverage)
                    .addComponent(investAmount)
                    .addComponent(listedCompany)
                    .addComponent(copyPortfolio)
                    .addComponent(costBasis)
                    .addComponent(specificdatePortfolio)
                    .addComponent(currentPortfolio)
                    .addComponent(viewComp)
                    .addComponent(sellShare)
                    .addComponent(buyShare)
                    .addComponent(createPortf))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanelLayout.setVerticalGroup(
        jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(createPortf)
                .addGap(18, 18, 18)
                .addComponent(buyShare)
                .addGap(18, 18, 18)
                .addComponent(sellShare)
                .addGap(18, 18, 18)
                .addComponent(viewComp)
                .addGap(18, 18, 18)
                .addComponent(currentPortfolio)
                .addGap(18, 18, 18)
                .addComponent(specificdatePortfolio)
                .addGap(18, 18, 18)
                .addComponent(costBasis)
                .addGap(18, 18, 18)
                .addComponent(copyPortfolio)
                .addGap(18, 18, 18)
                .addComponent(listedCompany)
                .addGap(18, 18, 18)
                .addComponent(investAmount)
                .addGap(18, 18, 18)
                .addComponent(dollarcostAverage)
                .addGap(18, 18, 18)
                .addComponent(finish)
                .addContainerGap(48, Short.MAX_VALUE))
    );

    GroupLayout layout = new GroupLayout(j1.getContentPane());
    j1.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE)
                .addContainerGap())
    );
    j1.setLocationRelativeTo(null);
    j1.setResizable(false);
    j1.pack();
  }

  /**
   * Method is a top level container which displays the window on the screen.
   *
   * @return the instance variable
   */
  JFrame getFrame() {
    return j1;
  }

  protected JButton buyShare;
  protected JButton copyPortfolio;
  protected JButton costBasis;
  protected JButton createPortf;
  protected JButton currentPortfolio;
  protected JButton dollarcostAverage;
  protected JButton finish;
  protected JButton investAmount;
  protected JPanel jPanel;
  protected JButton listedCompany;
  protected JButton sellShare;
  protected JButton specificdatePortfolio;
  protected JButton viewComp;

}
