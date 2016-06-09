package BubbleTank;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

public class Menu extends JFrame implements Constants {


  private static final long serialVersionUID = 1L;
  protected static final Component JFrame = null;
  private int chooselevel = 1;
  private int chooseFileLevel;
  private boolean scalaOrJava = false;
  JLabel label = new JLabel("Âubble tank");
  JLabel levelLabel = new JLabel("Level");
  JButton playButton = new JButton("Play");
  JButton settingsButton = new JButton("Exit");
  JButton exitButton = new JButton("Settings");
  JButton sortByLevel = new JButton("SortByLevel");
  JButton sortByMoves = new JButton("SortByMoves");
  JButton sortByEnemies = new JButton("SortByEnemies");
  JButton openSaveGame = new JButton("Open save game");
  JButton playPC = new JButton("PC plays");
  JButton gameStatistic = new JButton ("Game Statistic");
  JCheckBox scalaBox = new JCheckBox("Scala");
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
    setSize(MENU_WIDTH, MENU_HEIDTH);
    setLocationRelativeTo(null);
    setResizable(false);
    panel.setBackground(Color.PINK);
    Level.setBackground(Color.PINK);
    scalaBox.setBackground(Color.PINK);
    scalaBox.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    label.setFont(new Font("Verdana", Font.ITALIC, 30));
    levelLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    levelLabel.setFont(new Font("Calibri", Font.ITALIC, 15));
    playButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIDTH));
    playButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    playButton.setForeground(Color.GREEN);
    playButton.setBackground(Color.RED);
    playButton.setFont(new Font("Times New Roman", Font.ITALIC, 15));
    openSaveGame.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIDTH));
    openSaveGame.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    settingsButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIDTH));
    settingsButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    sortByLevel.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIDTH));
    sortByLevel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    sortByEnemies.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIDTH));
    sortByEnemies.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    sortByMoves.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIDTH));
    sortByMoves.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    gameStatistic.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIDTH));
    gameStatistic.setAlignmentX(JComponent.CENTER_ALIGNMENT);
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
    panel.add(openSaveGame);
    panel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    panel.add(levelLabel);
    panel.add(Level);
    panel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    panel.add(playPC);
    panel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    panel.add(scalaBox);
    panel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    panel.add(sortByLevel);
    panel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    panel.add(sortByMoves);
    panel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    panel.add(sortByEnemies);
    panel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    panel.add(gameStatistic);
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

    sortByLevel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (scalaOrJava) {
          SortReplay sorter = new SortReplay();
          long startTime = System.currentTimeMillis();
          sorter.sort(Movement.level);
          long timeSpent = System.currentTimeMillis() - startTime;
          JOptionPane.showMessageDialog(JFrame, "TimeScala: " + timeSpent);
        } else {
          String[] data = null;
          long startTime = System.currentTimeMillis();
          data = SortJavaReplay.sorting(Movement.level);
          long timeSpent = System.currentTimeMillis() - startTime;
          new SortTable("TimeJava: " + timeSpent, data);
        }
      }
    });

    sortByMoves.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (scalaOrJava) {
          SortReplay sorter = new SortReplay();
          long startTime = System.currentTimeMillis();
          sorter.sort(Movement.movement);
          long timeSpent = System.currentTimeMillis() - startTime;
          JOptionPane.showMessageDialog(JFrame, "TimeScala: " + timeSpent);
        } else {
          String[] data = null;
          long startTime = System.currentTimeMillis();
          data = SortJavaReplay.sorting(Movement.movement);
          long timeSpent = System.currentTimeMillis() - startTime;
          new SortTable("TimeJava: " + timeSpent, data);
        }
      }
    });

    sortByEnemies.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (scalaOrJava) {
          SortReplay sorter = new SortReplay();
          long startTime = System.currentTimeMillis();
          sorter.sort(Movement.enemy);
          long timeSpent = System.currentTimeMillis() - startTime;
          JOptionPane.showMessageDialog(JFrame, "TimeScala: " + timeSpent);
        } else {
          String[] data = null;
          long startTime = System.currentTimeMillis();
          data = SortJavaReplay.sorting(Movement.enemy);
          long timeSpent = System.currentTimeMillis() - startTime;
          new SortTable("TimeJava: " + timeSpent, data);
        }
      }
    });

    gameStatistic.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        SortReplay sorter = new SortReplay();
        sorter.sort(Movement.statistic);
        int move = sorter.getMoveStatistic();
        int enemy = sorter.getEnemyStatistic();
        int level = sorter.getLevelStatistic(); 
        new StatisticTable(enemy, move, level);
      }
    });
    
    scalaBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent event) {
        if (scalaOrJava == true) {
          scalaOrJava = false;
        } else {
          scalaOrJava = true;
        }
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

    openSaveGame.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        File file = null;
        final JFileChooser openFile = new JFileChooser();
        int gameSave;
        gameSave = openFile.showOpenDialog(null);
        if (gameSave == JFileChooser.APPROVE_OPTION) {
          try {
            file = openFile.getSelectedFile();
            throw new IOException();
          } catch (IOException ex) {
          }
          String gameReadFile = new String(file.getAbsolutePath());
          System.out.println(gameReadFile);

          try {
            chooseFileLevel = FileWorker.readFromFile(gameReadFile,
                Movement.replay);
            if (chooseFileLevel == 0) {
              throw new IOException();
            }
            setVisible(false);
            GamePanel.playSaveGame = true;
            GamePanel gamePanel = new GamePanel(chooseFileLevel, false);
            gamePanel.start();

          } catch (FileNotFoundException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    });
  }
}
