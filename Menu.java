package AndreiBike;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import java.awt.Dimension;

public class Menu extends JFrame implements Constants {


  private static final long serialVersionUID = 1L;

  private int chooselevel = LMIN;
  JLabel label = new JLabel("Âubble tank");
  JLabel levelLabel = new JLabel("Level");
  JButton playButton = new JButton("Play");
  JButton settingsButton = new JButton("Exit");
  JButton exitButton = new JButton("Settings");
  JButton playPC = new JButton("PC plays");
  static int min = LMIN;
  static int max = LMAX;
  JPanel panel = new JPanel();
  JFrame startframe = new JFrame("TanksAndreiBike");
  JSlider Level = new JSlider(JSlider.HORIZONTAL, min, max, 1);

  public Menu() {
    Level.setMajorTickSpacing(10);
    Level.setMinorTickSpacing(1);
    Level.setPaintTicks(true);
    Level.setPaintLabels(true);
    Font font = new Font("Serif", Font.ITALIC, 10);
    Level.setFont(font);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    setSize(MWIDTH, MHEIDTH);
    setLocationRelativeTo(null);
    setResizable(false);
    panel.setBackground(Color.PINK);
    Level.setBackground(Color.PINK);
    label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    label.setFont(new Font("Verdana", Font.ITALIC, 30));
    levelLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    levelLabel.setFont(new Font("Calibri", Font.ITALIC, 15));
    playButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIDTH));
    playButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    settingsButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIDTH));
    settingsButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    exitButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIDTH));
    exitButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    playPC.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIDTH));
    playPC.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    Level.setMaximumSize(new Dimension(BUTTON_WIDTH, 50));
    Level.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    panel.add(label);
    panel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    panel.add(playButton);
    panel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    panel.add(levelLabel);
    panel.add(Level);
    panel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    panel.add(playPC);
    panel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    panel.add(settingsButton);
    panel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    add(panel);
    pushButton();

  }

  public void pushButton() {
    playButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        setVisible(false);
        GamePanel panel2 = new GamePanel(chooselevel, false);
        panel2.start();
      }
    });

    settingsButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        System.exit(0);
      }
    });

    Level.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent event) {
        JSlider source = (JSlider) event.getSource();
        chooselevel = source.getValue();
      }
    });

    playPC.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        setVisible(false);
        GamePanel gamePanel = new GamePanel(chooselevel, true);
        gamePanel.start();
      }
    });
  }
}
