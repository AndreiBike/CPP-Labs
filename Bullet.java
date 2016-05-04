package BubbleTank;

import java.awt.*;

public class Bullet implements Constants {

  private double x;
  private double y;
  private final int RADIUS = 2;
  private Color color;
  private final int SPEED = 10;

  // Constructor

  public Bullet() {
    x = GamePanel.player.getX();
    y = GamePanel.player.getY();
    color = Color.BLACK;
  }

  // Methods

  public boolean remove() {
    return y < INFO_PANEL_SIZE ? true : false;
  }

  public void update() {
    y -= SPEED;
  }

  public void draw(Graphics2D g) {
    g.setColor(color);
    g.fillOval((int) x + SHIFT_X_BULLET, (int) y - SHIFT_Y_BULLET, RADIUS, 2 * RADIUS);

  }

  public double getX() {

    return x;
  }

  public double getY() {

    return y;
  }

  public int getRadius() {
    return RADIUS;
  }
}
