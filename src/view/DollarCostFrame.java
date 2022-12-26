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
 * Class represents the creation of the frame for the dollar cost portfolio operation.
 */
public class DollarCostFrame {

  JFrame dcf;

  /**
   * Constructor is used to create the frame object.
   */
  public DollarCostFrame() {
    dcf = new JFrame();
    initComponents();
  }

  private void initComponents() {

    panel = new JPanel();
    dcLabel = new JLabel();
    dcAmount = new JLabel();
    dcComFee = new JLabel();
    dcFromDate = new JLabel();
    dcToDate = new JLabel();
    dcRecAmount = new JLabel();
    dcTickerName = new JLabel();
    dcTickerVal = new JLabel();
    jButton1 = new JButton();
    dcSave = new JButton();
    dcAmountBox = new JTextField();
    dcComBox = new JTextField();
    dcFromDateBox = new JTextField();
    dcToDateBox = new JTextField();
    dcRecBox = new JTextField();
    dcTickerBox = new JTextField();
    dcTickerValBox = new JTextField();

    dcf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    dcLabel.setText("Dollar Cost Averaging of Portfolio");

    dcAmount.setText("Investment Amount");

    dcComFee.setText("Commission Fee (Only Integers)");

    dcFromDate.setText("From Date");

    dcToDate.setText("To Date");

    dcRecAmount.setText("Recurring Investment Period");

    dcTickerName.setText("Enter ticker names in the format: AA,IBM,AAPB");

    dcTickerVal.setText("Enter the corresponding percentages for above tickers in the format:" +
        "30,60,10");

    jButton1.setText("Back");

    dcSave.setText("Save");

    GroupLayout panelLayout = new GroupLayout(panel);
    panel.setLayout(panelLayout);
    panelLayout.setHorizontalGroup(
        panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(190, Short.MAX_VALUE)
                .addComponent(dcLabel)
                .addGap(298, 298, 298))
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addGroup(
                            panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING,
                                    false)
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addComponent(dcComFee)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcComBox, GroupLayout.PREFERRED_SIZE, 71,
                                        GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addComponent(dcAmount)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcAmountBox, GroupLayout.PREFERRED_SIZE, 71,
                                        GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addComponent(dcTickerVal)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcTickerValBox, GroupLayout.PREFERRED_SIZE,
                                        71,
                                        GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addComponent(dcFromDate)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcFromDateBox, GroupLayout.PREFERRED_SIZE,
                                        71, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addComponent(dcToDate)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcToDateBox, GroupLayout.PREFERRED_SIZE, 71,
                                        GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addComponent(dcRecAmount)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                        54, Short.MAX_VALUE)
                                    .addComponent(dcRecBox, GroupLayout.PREFERRED_SIZE, 71,
                                        GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addComponent(dcTickerName)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcTickerBox, GroupLayout.PREFERRED_SIZE, 71,
                                        GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jButton1)
                        .addGap(102, 102, 102)
                        .addComponent(dcSave)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    panelLayout.setVerticalGroup(
        panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(dcLabel)
                .addGap(55, 55, 55)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dcAmount)
                    .addComponent(dcAmountBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dcComFee)
                    .addComponent(dcComBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dcFromDate)
                    .addComponent(dcFromDateBox, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dcToDate)
                    .addComponent(dcToDateBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dcRecAmount)
                    .addComponent(dcRecBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dcTickerName)
                    .addComponent(dcTickerBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dcTickerVal)
                    .addComponent(dcTickerValBox, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(dcSave))
                .addGap(257, 257, 257))
    );

    GroupLayout layout = new GroupLayout(dcf.getContentPane());
    dcf.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
    );

    dcf.pack();
  }

  /**
   * Method is a top level container which displays the window on the screen.
   *
   * @return the instance variable
   */
  public JFrame getFrame() {
    return dcf;
  }

  JLabel dcAmount;
  JTextField dcAmountBox;
  JTextField dcComBox;
  JLabel dcComFee;
  JLabel dcFromDate;
  JTextField dcFromDateBox;
  JLabel dcLabel;
  JLabel dcRecAmount;
  JTextField dcRecBox;
  JButton dcSave;
  JTextField dcTickerBox;
  JLabel dcTickerName;
  JLabel dcTickerVal;
  JTextField dcTickerValBox;
  JLabel dcToDate;
  JTextField dcToDateBox;
  JButton jButton1;
  JPanel panel;
}
