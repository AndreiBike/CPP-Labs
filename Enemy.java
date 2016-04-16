package BubbleTank;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy implements Constants {

  private double x;
  private double y;
  private int radius;
  private double speed;
  private double dx;
  private double dy;
  private int health;
  private static int point;
  private static int index;
  private Color color;

  public Enemy(int type, int rank) {

    switch (rank) {
      case (1):
        color = Color.RED;
        y = INFO_PANEL + 2 * RAD1;
        speed = SPEED1;
        health = HEALTH1;
        radius = RAD1;
        double angle;
        if (!GamePanel.playSaveGame) {
          x = Math.random() * GamePanel.WIDTH;
          GamePanel.statementEnemy.add(x);
          angle = Math.toRadians(Math.random() * CIRCLE);
          GamePanel.statementEnemy.add(angle);
        } else {
          x = GamePanel.statementEnemy.get(index);
          System.out.println(x);
          index++;
          angle = GamePanel.statementEnemy.get(index);
          System.out.println(angle);
          index++;
        }
        dx = Math.sin(angle) * speed;
        dy = Math.cos(angle) * speed;
        break;

      case (2):
        color = Color.BLUE;
        y = INFO_PANEL + 2 * RAD2;
        speed = SPEED2;
        health = HEALTH2;
        radius = RAD2;
        double angle2;
        if (!GamePanel.playSaveGame) {
          x = Math.random() * GamePanel.WIDTH;
          GamePanel.statementEnemy.add(x);
          angle2 = Math.toRadians(Math.random() * CIRCLE);
          GamePanel.statementEnemy.add(angle2);
        } else {
          x = GamePanel.statementEnemy.get(index);
          index++;
          angle2 = GamePanel.statementEnemy.get(index);
          index++;
        }
        dx = Math.sin(angle2) * speed;
        dy = Math.cos(angle2) * speed;
        break;

      case (3):
        color = Color.GRAY;
        y = INFO_PANEL + 2 * RAD3;
        speed = SPEED3;
        health = HEALTH3;
        radius = RAD3;
        double angle3;
        if (!GamePanel.playSaveGame) {
          x = Math.random() * GamePanel.WIDTH;
          GamePanel.statementEnemy.add(x);
          angle3 = Math.toRadians(Math.random() * CIRCLE);
          GamePanel.statementEnemy.add(angle3);
        } else {
          x = GamePanel.statementEnemy.get(index);
          index++;
          angle3 = GamePanel.statementEnemy.get(index);
          index++;
        }
        dx = Math.sin(angle3) * speed;
        dy = Math.cos(angle3) * speed;
        break;
    }
  }

  public void update() {
    x += dx;
    y += dy;
    if (x < radius && dx < 0) {
      dx = -dx;
    }
    if (x > GamePanel.WIDTH - radius && dx > 0) {
      dx = -dx;
    }
    if (y < INFO_PANEL + radius && dy < 0) {
      dy = -dy;
    }
    if (y > GamePanel.HEIGHT - radius && dy > 0) {
      dy = -dy;
    }
  }

  public void draw(Graphics2D g) {
    g.setColor(color);
    g.fillOval((int) x - radius, (int) y - radius, 2 * radius, 2 * radius);
    g.setStroke(new BasicStroke(3));
    g.setColor(color.darker());
    g.drawOval((int) x - radius, (int) y - radius, 2 * radius, 2 * radius);
    g.setStroke(new BasicStroke(2));
  }

  public boolean remove() {
    if (health <= 0) {
      return true;
    }
    return false;
  }

  public void hit() {
    health--;
    point++;
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

  public static int getPoint() {
    return point;
  }

  public static void nullPoint() {
    point = 0;
  }

  public static void nullEnemyIndex() {
    index = 0;
  }
}
