package AndreiBike;

import java.awt.*;

public class Bullet implements Constants {

  private double x;
  private double y;
  private final int radius = 2;
  private Color color;
  private final int speed = 10;

  // Constructor

  public Bullet() {

    x = GamePanel.player.getX();
    y = GamePanel.player.getY();
    color = Color.BLACK;
  }

  // Metods

  public boolean remove() {
    if (y < INFO_PANEL) {
      return true;
    }
    return false;
  }

  public void update() {
    y -= speed;
  }

  public void draw(Graphics2D g) {
    g.setColor(color);
    g.fillOval((int) x + SMXB, (int) y - SMYB, radius, 2 * radius);

  }

  public double getX() {

    return x;
  }

  public double getY() {

    return y;
  }

  public int getRadius() {
    return radius;
  }
}
