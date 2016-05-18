package BubbleTank;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JFrame implements Runnable, Constants {

  private static final long serialVersionUID = 1L;
  public static int WIDTH = 1200;
  public static int HEIGHT = 600;
  public static ArrayList<Double> statementPlayer = new ArrayList<Double>();
  public static ArrayList<Double> statementEnemy = new ArrayList<Double>();
  private int numWave;
  private Thread thread;
  private BufferedImage image;
  private Graphics2D graphics;
  private boolean gameOver = false;
  private boolean win = false;
  private boolean endSave = false;
  private boolean botOrPlayer;
  public static boolean playSaveGame;
  public static GameBack Background;
  public static Player player;
  public static ArrayList<Bullet> bullets;
  public static ArrayList<Enemy> enemy;
  public static Wave wave;
  public static Menu menu;
  public static boolean pause = false;


  public GamePanel(int numWave, boolean botOrPlayer) {
    super();
    this.numWave = numWave;
    System.out.println(playSaveGame);
    this.botOrPlayer = botOrPlayer;
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setFocusable(true);
    requestFocus();
    addKeyListener(new Listeners());
    setSize(WIDTH, HEIGHT);
    setTitle("BubbleTank AndreiBike");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
  }

  // Functions


  public void start() {
    setVisible(true);
    thread = new Thread(this);
    thread.start();
  }

  @SuppressWarnings("deprecation")
  public void playingProcess() {
    while (!gameOver && !win) {
      if (!pause) {
        gameOver = gameUpdate();
      } else {
        gamePause();
        thread.suspend();
      }
      gameRender();
      gameDraw();
      try {
        Thread.sleep(WAIT_TIME);
      } catch (InterruptedException e) {

        e.printStackTrace();
      }
    }
  }

  private void gamePause() {
    JButton cont = new JButton("Continue");
    JFrame pframe = new JFrame("Pause");
    JPanel ppanel = new JPanel();
    JLabel plabel = new JLabel("Pause");
    setVisible(false);
    pframe.setVisible(true);
    cont.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIDTH));
    cont.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    plabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    plabel.setFont(new Font("Verdana", Font.ITALIC, 20));
    pframe.setSize(new Dimension(PAUSE_WIDTH, PAUSE_HEIDTH));
    pframe.setLocationRelativeTo(null);
    setResizable(false);
    ppanel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    ppanel.setLayout(new BoxLayout(ppanel, BoxLayout.Y_AXIS));
    ppanel.add(plabel);
    ppanel.add(Box.createRigidArea(new Dimension(0, YAREA_15)));
    ppanel.add(cont);
    ppanel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    pframe.add(ppanel);
    ppanel.setVisible(true);

    cont.addActionListener(new ActionListener() {
      @SuppressWarnings("deprecation")
      public void actionPerformed(ActionEvent event) {
        pframe.setVisible(false);
        pause = false;
        setVisible(true);
        thread.resume();
      }
    });
  }

  public void GameOver() {
    JButton goToMenu = new JButton("Go to menu");
    JButton save = new JButton("Save replay in . . .");
    JButton saveInDirectory = new JButton("Save");
    JFrame over = new JFrame("Game Over");
    JPanel overPanel = new JPanel();
    JLabel overLabel = new JLabel("You lose");
    JLabel winLabel = new JLabel("You win!!!");
    JLabel point = new JLabel();
    JLabel replayFinished = new JLabel("Replay finished");
    goToMenu.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIDTH));
    goToMenu.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    save.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIDTH));
    save.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    saveInDirectory.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIDTH));
    saveInDirectory.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    overLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    overLabel.setFont(new Font("Verdana", Font.ITALIC, 20));
    point.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    point.setFont(new Font("Verdana", Font.ITALIC, 15));
    point.setText("Points - " + Integer.toString(Enemy.getPoint()));
    winLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    winLabel.setFont(new Font("Calibri", Font.ITALIC, 30));
    replayFinished.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    replayFinished.setFont(new Font("Calibri", Font.ITALIC, 30));
    over.setSize(new Dimension(PAUSE_WIDTH, PAUSE_HEIDTH));
    over.setLocationRelativeTo(null);
    setResizable(false);
    overPanel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    overPanel.setLayout(new BoxLayout(overPanel, BoxLayout.Y_AXIS));

    if (!playSaveGame) {
      if (win) {
        overPanel.add(winLabel);
      } else {
        overPanel.add(overLabel);
      }
    } else {
      overPanel.add(replayFinished);
      point.setVisible(false);
      save.setEnabled(false);
      saveInDirectory.setEnabled(false);
    }

    overPanel.add(Box.createRigidArea(new Dimension(0, YAREA_15)));
    overPanel.add(point);
    overPanel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    overPanel.add(goToMenu);
    overPanel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    overPanel.add(save);
    overPanel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    overPanel.add(saveInDirectory);
    overPanel.add(Box.createRigidArea(new Dimension(0, YAREA_25)));
    over.add(overPanel);
    over.setVisible(true);

    goToMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        playSaveGame = false;
        Player.nullEnemyindex();
        Enemy.nullEnemyIndex();
        Enemy.nullPoint();
        GamePanel.statementPlayer.clear();
        GamePanel.statementEnemy.clear();
        over.setVisible(false);
        menu.setVisible(true);
      }
    });

    saveInDirectory.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        File file = null;
        try {
          FileWorker.writeToFile(file, numWave, statementPlayer, 
              statementEnemy, false);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    save.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        File file = null;
        final JFileChooser saveFile = new JFileChooser();
        int gameSave;
        gameSave = saveFile.showSaveDialog(null);
        if (gameSave == JFileChooser.APPROVE_OPTION) {
          try {
            file = saveFile.getSelectedFile();
            System.out.println(file.getName());
            throw new IOException();
          } catch (IOException ex) {
          }
          try {
            FileWorker.writeToFile(file, numWave, statementPlayer, 
                statementEnemy, true);
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      }
    });
  }

  public void run() {
    image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    graphics = (Graphics2D) image.getGraphics();
    Background = new GameBack();
    player = new Player();
    bullets = new ArrayList<Bullet>();
    enemy = new ArrayList<Enemy>();
    menu = new Menu();
    System.out.println(numWave);
    wave = new Wave(numWave);

    playingProcess();
    setVisible(false);
    GameOver();
  }

  public boolean gameUpdate() {
    // Update player
    if (botOrPlayer == false) {
      if (!playSaveGame) {
        player.update();

      } else {
        endSave = player.updateSave();
      }
    }
    // Update bullet
    for (int i = 0; i < bullets.size(); i++) {
      bullets.get(i).update();
      boolean remove = bullets.get(i).remove();
      if (remove) {
        bullets.remove(i);
        i--;
      }
    }
    // Update enemy

    for (int i = 0; i < enemy.size(); i++) {
      enemy.get(i).update();
    }

    // Crash enemy and bullet

    crashEnemyAndBullet();


    // Crash enemy and player
    if (crashEnemyAndPlayer()) {
      return true;
    }

    if (botOrPlayer == true) {
      player.botMove();
    }
    win = wave.update();
    if (endSave) {
      return true;
    }
    return false;
  }

  public void crashEnemyAndBullet() {
    for (int i = 0; i < enemy.size(); i++) {
      Enemy e = enemy.get(i);
      double ex = e.getX();
      double ey = e.getY();

      for (int j = 0; j < bullets.size(); j++) {

        Bullet b = bullets.get(j);
        double bx = b.getX();
        double by = b.getY();
        double dx = ex - bx;
        double dy = ey - by;
        double dist = Math.sqrt(dx * dx + dy * dy);
        if ((int) dist < (int) (e.getR() + b.getRadius())) {
          e.hit();
          bullets.remove(j);
          j--;
          boolean remove = e.remove();
          if (remove) {
            enemy.remove(i);
            i--;
            break;
          }
        }
      }
    }
  }

  public boolean crashEnemyAndPlayer() {
    for (int i = 0; i < enemy.size(); i++) {
      Enemy e = enemy.get(i);
      double enemyX = e.getX();
      double enemyY = e.getY();
      if (botOrPlayer == true) {
        player.getCoord(enemyX, enemyY);
      }
      double playerX = player.getX();
      double playerY = player.getY();
      double dx = enemyX - playerX;
      double dy = enemyY - playerY;
      double dist = Math.sqrt(dx * dx + dy * dy);
      if ((int) dist < (int) (player.getR() + e.getR())) {
        e.hit();
        player.hit();
        boolean removeEnemy = e.remove();
        boolean removePlayer = player.remove();

        if (removePlayer) {
          return true;
        }

        if (removeEnemy) {
          enemy.remove(i);
          i--;
          break;
        }
      }
    }
    return false;
  }

  public void gameRender() {
    // Draw background
    Background.draw(graphics);
    wave.draw(graphics);
    // Draw player
    player.draw(graphics);
    player.draw2(graphics);
    // Draw bullet
    for (int i = 0; i < bullets.size(); i++) {
      bullets.get(i).draw(graphics);
    }
    // Draw enemy
    for (int i = 0; i < enemy.size(); i++) {
      enemy.get(i).draw(graphics);
    }

  }

  private void gameDraw() {
    java.awt.Graphics graphics2 = this.getGraphics();
    graphics2.drawImage(image, 0, 0, null);
  }
}
