package AndreiBike;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JFrame implements Runnable, Constants {

	private static final long serialVersionUID = 1L;
	public static int WIDTH = 1200;
	public static int HEIGHT = 600;
	private Thread thread;
	private int numWave;
	private BufferedImage image;
	private Graphics2D g;
	private boolean gameOver = false;
	private boolean pobeda = false;
	private boolean botOrPlayer;
	public static GameBack Background;
	public static Player player;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<Enemy> enemy;
	public static Wave wave;
	public static Menu menu;
	public static boolean pause = false;

	public GamePanel(int numWave, boolean botOrPlayer) {
		super();
		this.botOrPlayer = botOrPlayer;
		this.numWave = numWave;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		addKeyListener(new Listeners());
		setSize(WIDTH, HEIGHT);
		setTitle("BubbleTank AndreiBike");
		setLocationRelativeTo(null);
		setResizable(false);
	}

	// Functions

	public void start() {
		setVisible(true);
		thread = new Thread(this);
		thread.start();
	}

	@SuppressWarnings("deprecation")
	public void playingProcess() {
		while (!gameOver && !pobeda) {
			if (!pause) {
				gameOver = gameUpdate();
			} else {
				gamePause();
				thread.suspend();
			}
			gameRender();
			gameDraw();
			try {
				Thread.sleep(ZADERGKA);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	private void gamePause() {
		JButton cont = new JButton("����������");
		JFrame pframe = new JFrame("�����");
		JPanel ppanel = new JPanel();
		JLabel plabel = new JLabel("�����");
		setVisible(false);
		pframe.setVisible(true);
		cont.setMaximumSize(new Dimension(200, 100));
		cont.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		plabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		plabel.setFont(new Font("Verdana", Font.ITALIC, 20));
		pframe.setSize(new Dimension(400, 250));
		pframe.setLocationRelativeTo(null);
		setResizable(false);
		ppanel.add(Box.createRigidArea(new Dimension(0, 25)));
		ppanel.setLayout(new BoxLayout(ppanel, BoxLayout.Y_AXIS));
		ppanel.add(plabel);
		ppanel.add(Box.createRigidArea(new Dimension(0, 15)));
		ppanel.add(cont);
		ppanel.add(Box.createRigidArea(new Dimension(0, 25)));
		pframe.add(ppanel);
		ppanel.setVisible(true);

		cont.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent event) {
				pframe.setVisible(false);
				pause = false;
				setVisible(true);
				thread.resume();
			}
		});
	}

	public void GameOver() {

		JButton gotomenu = new JButton("� ����");
		JFrame over = new JFrame("Game Over");
		JPanel opanel = new JPanel();
		JLabel glabel = new JLabel("�� ���������");
		JLabel wlabel = new JLabel("������!!!");
		JLabel point = new JLabel();
		gotomenu.setMaximumSize(new Dimension(200, 100));
		gotomenu.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		glabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		glabel.setFont(new Font("Verdana", Font.ITALIC, 20));
		point.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		point.setFont(new Font("Verdana", Font.ITALIC, 15));
		point.setText("���� - " + Integer.toString(Enemy.getPoint()));
		wlabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		wlabel.setFont(new Font("Calibri", Font.ITALIC, 30));
		over.setSize(new Dimension(400, 250));
		over.setLocationRelativeTo(null);
		setResizable(false);
		opanel.add(Box.createRigidArea(new Dimension(0, 25)));
		opanel.setLayout(new BoxLayout(opanel, BoxLayout.Y_AXIS));

		if (!pobeda) {
			opanel.add(glabel);
		} else {
			opanel.add(wlabel);
		}

		opanel.add(Box.createRigidArea(new Dimension(0, 15)));
		opanel.add(point);
		opanel.add(Box.createRigidArea(new Dimension(0, 25)));
		opanel.add(gotomenu);
		opanel.add(Box.createRigidArea(new Dimension(0, 25)));
		over.add(opanel);
		over.setVisible(true);
		
		gotomenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				over.setVisible(false);
				menu.setVisible(true);
			}
		});
		
		Enemy.nullPoint();
	}

	public void run() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		Background = new GameBack();
		player = new Player();
		bullets = new ArrayList<Bullet>();
		enemy = new ArrayList<Enemy>();
		menu = new Menu();
		wave = new Wave(numWave);
		playingProcess();
		setVisible(false);
		GameOver();
	}

	public boolean gameUpdate() {
		// ���������� ������
		if (botOrPlayer == false) {
			player.update();
		}
		// ��������� ����
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
			boolean remove = bullets.get(i).remove();
			if (remove) {
				bullets.remove(i);
				i--;
			}
		}
		// ��������� �����

		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).update();
		}

		// ������������ ���� � �����

		for (int i = 0; i < enemy.size(); i++) {
			Enemy e = enemy.get(i);
			double ex = e.getX();
			double ey = e.getY();

			for (int j = 0; j < bullets.size(); j++) {

				Bullet b = bullets.get(j);
				double bx = b.getX();
				double by = b.getY();
				double dx = ex - bx;
				double dy = ey - by;
				double dist = Math.sqrt(dx * dx + dy * dy);
				if ((int) dist < (int) (e.getR() + b.getR())) {
					e.hit();
					bullets.remove(j);
					j--;
					boolean remove = e.remove();
					if (remove) {
						enemy.remove(i);
						i--;
						break;
					}
				}
			}
		}
		// ������������ ������ � �����
		for (int i = 0; i < enemy.size(); i++) {
			Enemy e = enemy.get(i);
			double ex = e.getX();
			double ey = e.getY();
			if (botOrPlayer == true) {
				player.bot(ex, ey);
			}
			double px = player.getX();
			double py = player.getY();
			double dx = ex - px;
			double dy = ey - py;
			double dist = Math.sqrt(dx * dx + dy * dy);
			if ((int) dist < (int) (player.getR() + e.getR())) {
				e.hit();
				player.hit();
				boolean removeEnemy = e.remove();
				boolean removePlayer = player.remove();

				if (removePlayer) {
					return true;
				}

				if (removeEnemy) {
					enemy.remove(i);
					i--;
					break;
				}
			}
		}
		pobeda = wave.update();
		return false;
	}

	public void gameRender() {
		// ��������� ������� ����
		Background.draw(g);
		// ��������� ������
		player.draw(g);
		player.draw2(g);
		// ��������� ����
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}
		// ��������� �����
		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).draw(g);

		}
		if (wave.showWave()) {
			wave.draw(g);
		}
	}

	private void gameDraw() {
		java.awt.Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
	}
}