package view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

/**
 * Class represents the creation of the frame for the buy operation.
 */
public class BuyFrame {


  JFrame bf;

  /**
   * Constructor is used to create the buy frame object.
   */
  public BuyFrame() {
    bf = new JFrame();
    initComponents();
  }

  private void initComponents() {

    buyPanel = new JPanel();
    comLabel = new JLabel();
    buyDate = new JLabel();
    buyTicker = new JLabel();
    buyShare = new JLabel();
    buydtBox = new JTextField();
    buytkBox = new JTextField();
    buyshBox = new JTextField();
    buyvalButton = new JButton();
    savevalButton = new JButton();
    buyBack = new JButton();
    buyComFee = new JLabel();
    buyComBox = new JTextField();

    bf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    comLabel.setText("Commission fee of 10$ charged for buy/sell of stocks");

    buyDate.setText("Date (YYYY-MM-DD)");

    buyTicker.setText("Ticker Value");

    buyShare.setText("No of Shares");

    buyvalButton.setText("Add Values");

    savevalButton.setText("Save");

    buyBack.setText("back");

    buyComFee.setText("Commission");

    GroupLayout buyPanelLayout = new GroupLayout(buyPanel);
    buyPanel.setLayout(buyPanelLayout);
    buyPanelLayout.setHorizontalGroup(
        buyPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(buyPanelLayout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(comLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(buyPanelLayout.createSequentialGroup()
                .addGroup(buyPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(GroupLayout.Alignment.LEADING, buyPanelLayout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(buyDate)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 87,
                            Short.MAX_VALUE)
                        .addComponent(buydtBox, GroupLayout.PREFERRED_SIZE, 125,
                            GroupLayout.PREFERRED_SIZE))
                    .addGroup(buyPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(buyPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(buyShare)
                            .addComponent(buyTicker))
                        .addGap(114, 114, 114)
                        .addGroup(
                            buyPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING,
                                    false)
                                .addComponent(buyComBox, GroupLayout.DEFAULT_SIZE, 125,
                                    Short.MAX_VALUE)
                                .addComponent(buytkBox)
                                .addComponent(buyshBox))))
                .addGap(229, 229, 229))
            .addGroup(buyPanelLayout.createSequentialGroup()
                .addGroup(buyPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(buyPanelLayout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(buyBack)
                        .addGap(98, 98, 98)
                        .addComponent(buyvalButton)
                        .addGap(78, 78, 78)
                        .addComponent(savevalButton))
                    .addGroup(buyPanelLayout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(buyComFee, GroupLayout.PREFERRED_SIZE, 115,
                            GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    buyPanelLayout.setVerticalGroup(
        buyPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(buyPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(comLabel, GroupLayout.PREFERRED_SIZE, 82,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(buyPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(buyDate)
                    .addComponent(buydtBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(buyPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(buyTicker)
                    .addComponent(buytkBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(buyPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(buyShare)
                    .addComponent(buyshBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(buyPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING,
                        false)
                    .addComponent(buyComBox)
                    .addComponent(buyComFee, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addGroup(buyPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(buyBack)
                    .addComponent(buyvalButton)
                    .addComponent(savevalButton))
                .addContainerGap(146, Short.MAX_VALUE))
    );

    GroupLayout layout = new GroupLayout(bf.getContentPane());
    bf.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(buyPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(buyPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
    );
    bf.setResizable(false);
    bf.pack();
  }

  /**
   * Method is a top level container which displays the window on the screen.
   *
   * @return the instance variable
   */
  public JFrame getFrame() {
    return bf;
  }

  protected JButton buyBack;
  protected JTextField buyComBox;
  protected JLabel buyComFee;
  protected JLabel buyDate;
  protected JPanel buyPanel;
  protected JLabel buyShare;
  protected JLabel buyTicker;
  protected JTextField buydtBox;
  protected JTextField buyshBox;
  protected JTextField buytkBox;
  protected JButton buyvalButton;
  protected JLabel comLabel;
  protected JButton savevalButton;

}
