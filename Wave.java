package BubbleTank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Wave implements Constants {

  private int waveNumber;
  private boolean win = false;
  private int waveMultiplier;
  private final String wave = "Wave - ";

  public Wave(int n) {
    waveNumber = n - 1;
    waveMultiplier = WAVEMULTIPLIER;
  }

  public int getWaveNumber() {
    return waveNumber;
  }

  public boolean update() {
    if (GamePanel.enemy.size() == 0) {
      waveNumber++;
      win = createEnemy();
    }
    return win;
  }

  private boolean createEnemy() {
    int enemyCount = waveNumber * waveMultiplier;
    if (waveNumber < WAVE_1) {
      while (enemyCount > 0) {
        int rank = RANK_1;
        int type = TYPE;
        GamePanel.enemy.add(new Enemy(type, rank));
        enemyCount -= type * rank;
      }
    }
    if (waveNumber >= WAVE_1 && waveNumber < WAVE_2) {
      while (enemyCount > 0) {
        int rank = RANK_2;
        int type = TYPE;
        GamePanel.enemy.add(new Enemy(type, rank));
        enemyCount -= type * rank;
      }
    }
    if (waveNumber >= WAVE_2 && waveNumber < WAVE_3) {
      while (enemyCount > 0) {
        int rank = RANK_3;
        int type = TYPE;
        GamePanel.enemy.add(new Enemy(type, rank));
        enemyCount -= type * rank;
      }
    }
    if (waveNumber >= WAVE_3) {
      return true;
    }


    return false;
  }

  public void upLevel() {
    waveNumber++;
  }

  public void draw(Graphics2D g) {
    String s2 = wave + waveNumber;
    g.setColor(Color.WHITE);
    g.setFont(new Font("consolas", Font.PLAIN, 20));
    g.drawString(s2, 500, 48);
  }
}
