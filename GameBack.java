package BubbleTank;

import java.awt.Color;
import java.awt.Graphics2D;


public class GameBack implements Constants {

  private Color color;

  public GameBack() {
    color = Color.PINK;
  }

  // Functions

  public void draw(Graphics2D g) {
    g.setColor(color);
    g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
    g.setColor(Color.GRAY);
    g.fillRect(0, 0, GamePanel.WIDTH, INFO_PANEL);
  }
}
