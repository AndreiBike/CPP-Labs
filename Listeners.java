package AndreiBike;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listeners implements KeyListener {

  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    switch (key) {
      case (KeyEvent.VK_W):
        Player.up = true;
        break;
      case (KeyEvent.VK_A):
        Player.left = true;
        break;
      case (KeyEvent.VK_S):
        Player.down = true;
        break;
      case (KeyEvent.VK_D):
        Player.right = true;
        break;
      case (KeyEvent.VK_SPACE):
        Player.isFiring = true;
        break;
      case (KeyEvent.VK_ESCAPE):
        if (GamePanel.pause == false) {
          GamePanel.pause = true;
        } else {
          GamePanel.pause = false;
        }
        break;
    }
  }

  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
    switch (key) {
      case (KeyEvent.VK_W):
        Player.up = false;
        break;
      case (KeyEvent.VK_A):
        Player.left = false;
        break;
      case (KeyEvent.VK_S):
        Player.down = false;
        break;
      case (KeyEvent.VK_D):
        Player.right = false;
        break;
      case (KeyEvent.VK_SPACE):
        Player.isFiring = false;
        break;
    }
  }

  public void keyTyped(KeyEvent arg0) {};

}
