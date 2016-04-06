package AndreiBike;

import java.awt.*;

public class Wave implements Constants {

	private int waveNumber;
	private long waveTimer;
	private long waveDelay;
	private long waveTimerDiff;
	private String waveText = "ÂÎËÍÀ - ";
	private boolean win = false;
	private int waveMultiplier;

	public Wave(int n) {
		waveNumber = n;
		waveTimer = 0;
		waveDelay = WAVEDELAY;
		waveTimerDiff = 0;
		waveMultiplier = WAVEMULTIPLIER;
	}

	public boolean update() {

		if (GamePanel.enemy.size() == 0 && waveTimer == 0) {
			waveTimer = System.nanoTime();
		}
		if (waveTimer > 0) {
			waveTimerDiff += (System.nanoTime() - waveTimer) / 1000000;
			waveTimer = System.nanoTime();
		}
		if (waveTimerDiff > waveDelay) {
			win = createEnemy();
			waveTimer = 0;
			waveTimerDiff = 0;
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
		waveNumber++;
		return false;
	}

	public boolean showWave() {
		if (waveTimer != 0) {
			return true;
		} else
			return false;
	}

	public void draw(Graphics2D g) {
		String s;
		double divider = waveDelay / 180;
		double alpha = waveTimerDiff / divider;
		alpha = 255 * Math.sin(Math.toRadians(alpha));
		if (alpha < 0)
			alpha = 0;
		if (alpha > 255)
			alpha = 255;
		g.setFont(new Font("consolas", Font.PLAIN, 20));
		g.setColor(new Color(255, 255, 255, (int) alpha));
		if (waveNumber < WAVE_3) {
			 s = waveText + waveNumber;
		} else {
			 s = WIN_MESSAGE;
		}
		long length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
		g.drawString(s, GamePanel.WIDTH / 2 - (int) (length / 2), GamePanel.HEIGHT / 2);
	}
}
