package AndreiBike;

import java.awt.*;

public class Enemy implements Constants {

  private double x;
  private double y;
  private int r;
  private double speed;
  private double dx;
  private double dy;
  private int health;
  private static int point;
  private Color color;

  public Enemy(int type, int rank) {

    switch (rank) {
      case (1):
        color = Color.RED;
        x = Math.random() * GamePanel.WIDTH;
        y = INFO_PANEL + 2 * RAD1;
        speed = SPEED1;
        health = HEALTH1;
        r = RAD1;
        double angle = Math.toRadians(Math.random() * 360);
        dx = Math.sin(angle) * speed;
        dy = Math.cos(angle) * speed;
        break;

      case (2):
        color = Color.BLUE;
        x = Math.random() * GamePanel.WIDTH;
        y = INFO_PANEL + 2 * RAD2;
        speed = SPEED2;
        health = HEALTH2;
        r = RAD2;
        double angle2 = Math.toRadians(Math.random() * 360);
        dx = Math.sin(angle2) * speed;
        dy = Math.cos(angle2) * speed;
        break;

      case (3):
        color = Color.GRAY;
        x = Math.random() * GamePanel.WIDTH;
        y = INFO_PANEL + 2 * RAD3;
        speed = SPEED3;
        health = HEALTH3;
        r = RAD3;
        double angle3 = Math.toRadians(Math.random() * CIRCLE);
        dx = Math.sin(angle3) * speed;
        dy = Math.cos(angle3) * speed;
        break;
    }
  }

  public void update() {
    x += dx;
    y += dy;
    if (x < r && dx < 0) {
      dx = -dx;
    }
    if (x > GamePanel.WIDTH - r && dx > 0) {
      dx = -dx;
    }
    if (y < INFO_PANEL + r && dy < 0) {
      dy = -dy;
    }
    if (y > GamePanel.HEIGHT - r && dy > 0) {
      dy = -dy;
    }
  }

  public void draw(Graphics2D g) {
    g.setColor(color);
    g.fillOval((int) x - r, (int) y - r, 2 * r, 2 * r);
    g.setStroke(new BasicStroke(3));
    g.setColor(color.darker());
    g.drawOval((int) x - r, (int) y - r, 2 * r, 2 * r);
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
    return r;
  }

  public static int getPoint() {
    return point;
  }

  public static void nullPoint() {
    point = 0;
  }
}
