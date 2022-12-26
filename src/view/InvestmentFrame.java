package view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Class represents the creation of the frame for the investment portfolio operation.
 */
public class InvestmentFrame {


  JFrame inv;

  /**
   * Constructor is used to create the frame object.
   */
  public InvestmentFrame() {
    inv = new JFrame();
    initComponents();
  }


  private void initComponents() {

    invPanel = new JPanel();
    invAmount = new JLabel();
    invComFee = new JLabel();
    invDate = new JLabel();
    invTicker = new JLabel();
    invComBox = new JTextField();
    invAmountBox = new JTextField();
    invVal = new JTextField();
    invDateBox = new JTextField();
    invBack = new JButton();
    invSave = new JButton();
    invLabel = new JLabel();

    inv.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    invAmount.setText("Investment Amount");

    invComFee.setText("Commission Fee (Only Integers)");

    invDate.setText("Date (YYYY-MM-DD)");

    invTicker.setText("Dummy Label");

    invBack.setText("Back");

    invSave.setText("Save");

    invLabel.setText("                              Investment in a Portfolio");

    GroupLayout invPanelLayout = new GroupLayout(invPanel);
    invPanel.setLayout(invPanelLayout);
    invPanelLayout.setHorizontalGroup(
        invPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(invPanelLayout.createSequentialGroup()
                .addGroup(invPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(invPanelLayout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addGroup(
                            invPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING,
                                    false)
                                .addComponent(invTicker, GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(invComFee, GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(invAmount, GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(invDate, GroupLayout.DEFAULT_SIZE, 136,
                                    Short.MAX_VALUE))
                        .addGap(76, 76, 76)
                        .addGroup(invPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(invComBox, GroupLayout.PREFERRED_SIZE, 128,
                                GroupLayout.PREFERRED_SIZE)
                            .addComponent(invAmountBox, GroupLayout.PREFERRED_SIZE, 128,
                                GroupLayout.PREFERRED_SIZE)
                            .addComponent(invVal, GroupLayout.PREFERRED_SIZE, 128,
                                GroupLayout.PREFERRED_SIZE)
                            .addComponent(invDateBox, GroupLayout.PREFERRED_SIZE, 128,
                                GroupLayout.PREFERRED_SIZE)))
                    .addGroup(invPanelLayout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(invBack)
                        .addGap(88, 88, 88)
                        .addComponent(invSave)))
                .addContainerGap(96, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, invPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(invLabel, GroupLayout.PREFERRED_SIZE, 400,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
    );
    invPanelLayout.setVerticalGroup(
        invPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(invPanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(invLabel, GroupLayout.PREFERRED_SIZE, 54,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(invPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(invAmount, GroupLayout.PREFERRED_SIZE, 30,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(invAmountBox))
                .addGap(18, 18, 18)
                .addGroup(invPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(invComFee, GroupLayout.PREFERRED_SIZE, 30,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(invComBox, GroupLayout.PREFERRED_SIZE, 26,
                        GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(invPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(invDate, GroupLayout.PREFERRED_SIZE, 32,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(invDateBox, GroupLayout.PREFERRED_SIZE, 26,
                        GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(invPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(invTicker, GroupLayout.PREFERRED_SIZE, 31,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(invVal, GroupLayout.PREFERRED_SIZE, 26,
                        GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(invPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(invBack)
                    .addComponent(invSave))
                .addContainerGap(135, Short.MAX_VALUE))
    );

    GroupLayout layout = new GroupLayout(inv.getContentPane());
    inv.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(invPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(invPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
    );
    inv.setResizable(false);
    inv.pack();
  }

  /**
   * Method is a top level container which displays the window on the screen.
   *
   * @return the instance variable
   */
  public JFrame getFrame() {
    return inv;
  }

  protected JLabel invAmount;
  protected JTextField invAmountBox;
  protected JButton invBack;
  protected JTextField invComBox;
  protected JLabel invComFee;
  protected JLabel invDate;
  protected JTextField invDateBox;
  protected JLabel invLabel;
  protected JPanel invPanel;
  protected JButton invSave;
  protected JLabel invTicker;
  protected JTextField invVal;

}
