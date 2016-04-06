package AndreiBike;

import java.awt.*;

public class Bullet implements Constants {

  private double x;
  private double y;
  private final int r = 2;
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
    if (y < INFOPANEL) {
      return true;
    }
    return false;
  }

  public void update() {
    y -= speed;
  }

  public void draw(Graphics2D g) {
    g.setColor(color);
    g.fillOval((int) x + SMXB, (int) y - SMYB, r, 2 * r);

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
}
