package view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

/**
 * Class represents the creation of the frame for the composition operation.
 */
public class CompositionFrame {

  JFrame cf;

  /**
   * Constructor is used to create the composition frame object.
   */
  public CompositionFrame() {
    cf = new JFrame();
    initComponents();
  }

  /**
   * Method is a top level container which displays the window on the screen.
   *
   * @return the instance variable
   */
  public JFrame getFrame() {
    return cf;
  }

  protected void initComponents() {

    compPanel = new JPanel();
    portfolioComposition = new JScrollPane();
    jTextArea1 = new JTextArea();
    compDate = new JButton();
    compButton = new JTextField();
    compLabel = new JLabel();
    compBack = new JButton();

    cf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    portfolioComposition.setViewportView(jTextArea1);

    compDate.setText("Show composition");

    compLabel.setText("Enter the date in YYYY-MM-DD format");

    compBack.setText("back");

    GroupLayout compPanelLayout = new GroupLayout(compPanel);
    compPanel.setLayout(compPanelLayout);
    compPanelLayout.setHorizontalGroup(
        compPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(compPanelLayout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addGroup(compPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING,
                        compPanelLayout.createSequentialGroup()
                            .addComponent(portfolioComposition, GroupLayout.PREFERRED_SIZE,
                                368, GroupLayout.PREFERRED_SIZE)
                            .addGap(106, 106, 106))
                    .addGroup(GroupLayout.Alignment.TRAILING,
                        compPanelLayout.createSequentialGroup()
                            .addComponent(compLabel, GroupLayout.PREFERRED_SIZE, 323,
                                GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(compButton, GroupLayout.PREFERRED_SIZE, 150,
                                GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18))
                    .addGroup(GroupLayout.Alignment.TRAILING,
                        compPanelLayout.createSequentialGroup()
                            .addComponent(compBack)
                            .addGap(67, 67, 67)
                            .addComponent(compDate)
                            .addGap(139, 139, 139))))
    );
    compPanelLayout.setVerticalGroup(
        compPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(compPanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(compPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(compLabel)
                    .addComponent(compButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(compPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(compDate)
                    .addComponent(compBack))
                .addGap(33, 33, 33)
                .addComponent(portfolioComposition, GroupLayout.PREFERRED_SIZE, 191,
                    GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
    );

    GroupLayout layout = new GroupLayout(cf.getContentPane());
    cf.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(compPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(compPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
    );

    cf.setResizable(false);
    cf.pack();
  }

  protected JButton compBack;
  protected JTextField compButton;
  protected JButton compDate;
  protected JLabel compLabel;
  protected JPanel compPanel;
  protected JTextArea jTextArea1;
  protected JScrollPane portfolioComposition;
}
