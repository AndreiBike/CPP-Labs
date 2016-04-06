package AndreiBike;

import java.awt.*;

public class Player {

	private double x;
	private double y;
	private int rad = 20;
	private int health = 3;
	private int speed = 5;
	private String HPtext = "здоровье - ";
	private String POINTtext = "очки - ";
	private Color color1;
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
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getR() {
		return rad;
	}

	public void update() {
		if (up && y > 65) {
			y -= speed;
		}
		if (down && y < GamePanel.HEIGHT - rad) {
			y += speed;
		}
		if (left && x > 0) {
			x -= speed;
		}
		if (right && x < GamePanel.WIDTH - rad) {
			x += speed;
		}
		if (isFiring) {
			GamePanel.bullets.add(new Bullet());
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(color1);
		g.fillRect((int) (x), (int) (y), rad, rad);
	}

	public void draw2(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int) (x + 5), (int) (y - 5), 10, 20);
		String s1 = HPtext + health;
		g.setColor(Color.WHITE);
		g.setFont(new Font("consolas", Font.PLAIN, 20));
		g.drawString(s1, 50, 48);
		String s2 = POINTtext + Enemy.getPoint();
		g.setColor(Color.WHITE);
		g.setFont(new Font("consolas", Font.PLAIN, 20));
		g.drawString(s2, 250, 48);

	}

	public void hit() {
		health--;
	}

	public boolean remove() {
		if (health <= 0) {
			return true;
		}
		return false;
	}

	public void bot(double ex, double ey) {

		double rasst;
		rasst = Math.sqrt((ex - x) * (ex - x) + (ey - y) * (ey - y));
		if (rasst < 100) {
			if (ex > x && x > 0) {
				x -= speed;
			}
			if (ex < x && x < GamePanel.WIDTH - rad) {
				x += speed;
			}
			if (ey > y && y < GamePanel.HEIGHT - rad) {
				y += speed;
			}
			if (ey < y && y > 65) {
				y -= speed;
			}

		} else {
			GamePanel.bullets.add(new Bullet());
			if (ex > x && x > 0) {
				x += speed;
			}
			if (ex < x && x < GamePanel.WIDTH - rad) {
				x -= speed;
			}
		}
	}
}
