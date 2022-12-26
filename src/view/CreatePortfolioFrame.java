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
 * Class represents the creation of the frame for the create portfolio operation.
 */
public class CreatePortfolioFrame {


  JFrame j2;

  /**
   * Constructor is used to create the frame object.
   */
  public CreatePortfolioFrame() {
    j2 = new JFrame();
    initComponents();
  }

  private void initComponents() {

    portPanel = new JPanel();
    tickerVal = new JLabel();
    shareVal = new JLabel();
    tickerBox = new JTextField();
    shareBox = new JTextField();
    inputDate = new JLabel();
    dateBox = new JTextField();
    addButton = new JButton();
    saveButton = new JButton();

    j2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    tickerVal.setText("Ticker Values");

    shareVal.setText("No of Shares");

    inputDate.setText("Date");

    addButton.setText("Add Value");

    saveButton.setText("Save");

    GroupLayout portPanelLayout = new GroupLayout(portPanel);
    portPanel.setLayout(portPanelLayout);
    portPanelLayout.setHorizontalGroup(
        portPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(portPanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addButton)
                .addGap(59, 59, 59)
                .addComponent(saveButton)
                .addGap(93, 93, 93))
            .addGroup(portPanelLayout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addGroup(portPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(tickerVal)
                    .addGroup(portPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(inputDate)
                        .addComponent(shareVal)))
                .addGroup(portPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING,
                        portPanelLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tickerBox, GroupLayout.PREFERRED_SIZE, 133,
                                GroupLayout.PREFERRED_SIZE))
                    .addGroup(portPanelLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(shareBox, GroupLayout.DEFAULT_SIZE, 136,
                            Short.MAX_VALUE))
                    .addGroup(portPanelLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(dateBox))))
    );
    portPanelLayout.setVerticalGroup(
        portPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(portPanelLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(portPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(tickerVal)
                    .addComponent(tickerBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(portPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(shareVal)
                    .addComponent(shareBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(portPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(inputDate)
                    .addComponent(dateBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(portPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(addButton))
                .addContainerGap(166, Short.MAX_VALUE))
    );

    GroupLayout layout = new GroupLayout(j2.getContentPane());
    j2.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(portPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(portPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    j2.pack();
  }

  /**
   * Method is a top level container which displays the window on the screen.
   *
   * @return the instance variable.
   */
  public JFrame getFrame() {
    return j2;
  }

  protected JButton addButton;
  protected JTextField dateBox;
  protected JLabel inputDate;
  protected JPanel portPanel;
  protected JButton saveButton;
  protected JTextField shareBox;
  protected JLabel shareVal;
  protected JTextField tickerBox;
  protected JLabel tickerVal;
}
