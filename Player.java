package BubbleTank;

import java.awt.*;
import java.io.IOException;

public class Player implements Constants {

  private static final double CRITICAL_DISTANCE = 100;
  private static double oldEnemyX, oldEnemyY;
  private static double enemyX, enemyY;
  private double x;
  private double y;
  private int radius = 20;
  private int health = 3;
  private int speed = 5;
  private String hpText = "Health - ";
  private String pointText = "Points - ";
  private Color color1;
  private static int index = 0;
  public static boolean up;
  public static boolean down;
  public static boolean left;
  public static boolean right;
  public static boolean isFiring;

  public Player() {
    x = GamePanel.WIDTH / 2;
    y = GamePanel.HEIGHT / 2;
    color1 = Color.RED;
    up = false;
    down = false;
    left = false;
    right = false;
    isFiring = false;
    index = 0;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public int getR() {
    return radius;
  }

  public void update() {
    if (up && y > INFO_PANEL) {
      y -= speed;
    }
    if (down && y < GamePanel.HEIGHT - radius) {
      y += speed;
    }
    if (left && x > 0) {
      x -= speed;
    }
    if (right && x < GamePanel.WIDTH - radius) {
      x += speed;
    }
    if (isFiring) {
      GamePanel.bullets.add(new Bullet());
      GamePanel.statementPlayer.add(TRUE_WAY);
    } else {
      GamePanel.statementPlayer.add(FALSE_WAY);
    }
    GamePanel.statementPlayer.add(x);
    GamePanel.statementPlayer.add(y);
  }

  public void draw(Graphics2D g) {
    g.setColor(color1);
    g.fillRect((int) (x), (int) (y), radius, radius);
  }

  public void draw2(Graphics2D g) {
    g.setColor(Color.DARK_GRAY);
    g.fillRect((int) (x + 5), (int) (y - 5), 10, 20);
    String s1 = hpText + health;
    g.setColor(Color.WHITE);
    g.setFont(new Font("consolas", Font.PLAIN, 20));
    g.drawString(s1, 50, 48);
    String s2 = pointText + Enemy.getPoint();
    g.setColor(Color.WHITE);
    g.setFont(new Font("consolas", Font.PLAIN, 20));
    g.drawString(s2, 250, 48);
  }

  public void hit() {
    health--;
  }

  public int getHealth() {
    return health;
  }

  public boolean remove() {
    if (health <= 0) {
      return true;
    }
    return false;
  }

  public void getCoord(double enemyX, double enemyY) {
    Player.enemyX = enemyX;
    Player.enemyY = enemyY;
  }

  public void botMove() {
    GamePanel.bullets.add(new Bullet());
    double distance;
    distance = Math.sqrt((enemyX - x) * (enemyX - x) + (enemyY - y) * (enemyY - y));
    if (distance < CRITICAL_DISTANCE) {
      if (enemyX > x && x > 0) {
        if (oldEnemyX < enemyX) {
          x -= speed;
        }
        if (oldEnemyX > enemyX) {
          x += speed;
        }
      }
      if (enemyX < x && x < GamePanel.WIDTH - radius) {
        if (oldEnemyX < enemyX) {
          x += speed;
        }
        if (oldEnemyX > enemyX) {
          x -= speed;
        }
      }
      if (enemyY > y && y > INFO_PANEL + radius) {
        if (oldEnemyY < enemyY) {
          y += speed;
        }
        if (oldEnemyY > enemyY) {
          y -= speed;
        }
      }
      if (enemyY < y && y < GamePanel.HEIGHT - radius) {
        if (oldEnemyY < enemyY) {
          y -= speed;
        }
        if (oldEnemyY > enemyY) {
          y += speed;
        }
      }
    } else {

      if (enemyX > x && x > 0) {
        x += speed;
      }
      if (enemyX < x && x < GamePanel.WIDTH - radius) {
        x -= speed;
      }
      if (enemyY > y && y > INFO_PANEL + radius) {
        y += speed;
      }
      if (enemyY < y && y < GamePanel.HEIGHT - radius) {
        y += speed;
      }
    }
    oldEnemyX = enemyX;
    oldEnemyY = enemyY;
    GamePanel.statementPlayer.add(TRUE_WAY);
    GamePanel.statementPlayer.add(x);
    GamePanel.statementPlayer.add(y);
  }

  public boolean updateSave() {
    try {
      if (GamePanel.statementPlayer.get(index) == TRUE_WAY) {
        GamePanel.bullets.add(new Bullet());
      }
      index++;
      x = GamePanel.statementPlayer.get(index);
      index++;
      y = GamePanel.statementPlayer.get(index);
      index++;
      if (index >= GamePanel.statementPlayer.size()) {
        throw new IOException();
      }

    } catch (IOException e) {
      return true;
    }
    return false;
  }

  public static void nullEnemyindex() {
    index = 0;
  }
}
