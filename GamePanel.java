package AndreiBike;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {


	

	private static final long serialVersionUID = 1L;
	public static int WIDTH = 1280;
	public static int HEIGHT = 600;
	
	private Thread thread;
	private int x;
	private BufferedImage image;
	private Graphics2D g;
	
	public static GameBack Background;
	
	public static Player player;
	public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemy;
    public static Wave wave;
    public static Menu menu;
	
	public GamePanel(int NumWave){
		super();
		x=NumWave;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		
		addKeyListener(new Listeners());
		
	}

	
	
	//Functions

	public void start(){
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		Background = new GameBack();
		player = new Player();
		bullets = new ArrayList<Bullet>();
		
		enemy = new ArrayList<Enemy>();
		menu = new Menu();
		wave = new Wave(x);
		
		while(true){
		    
			/*if(state.equals(STATES.MENUE)){
				//Background.update();
			//	Background.draw(g);
				
				//gameDraw();
			}
		    if(state.equals(STATES.PLAY)){
		    	gameUpdate();
				gameRender();
				gameDraw();	
			}*/
		    
			
		    gameUpdate();
			gameRender();
			gameDraw();
			try {
				thread.sleep(1);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			} 
			
		}
		
	}
	
	public void gameUpdate(){
		//Обновление заднего фона
		Background.update();
		// Обновление игрока
		player.update();
		//Отрисовка пуль
		for (int i=0; i<bullets.size(); i++){
		bullets.get(i).update();
		boolean remove = bullets.get(i).remove();
		if (remove){
			bullets.remove(i);
			i--;
		}
		}
		//Отрисовка врага
		
		for (int i=0;  i<enemy.size(); i++){
			enemy.get(i).update();	
		}
		
		//Столкновение пуль и врага
		
		for (int i=0; i<enemy.size(); i++){
			Enemy e = enemy.get(i);
			double ex = e.getX();
			double ey= e.getY();
			
			for (int j=0; j<bullets.size();j++){
				
				Bullet  b = bullets.get(j);
				double bx = b.getX();
				double by = b.getY();
				double dx = ex - bx;
				double dy = ey - by;
				double dist = Math.sqrt(dx*dx+dy*dy);
				if ((int)dist <(int)(e.getR()+b.getR())){
					e.hit();
					bullets.remove(j);
					j--;
					boolean remove = e.remove();
					if (remove){
						enemy.remove(i);
						i--;
					    break;
				}
				
				}
			
			}
		}
		//Столкновения игрока и врага
		for (int i=0; i<enemy.size();i++){
			Enemy e = enemy.get(i);
			double ex = e.getX();
			double ey = e.getY();
			
			double px=player.getX();
			double py=player.getY();
			double dx = ex-px;
			double dy = ey-py;
			double dist = Math.sqrt(dx*dx+dy*dy);
			if((int)dist<(int)(player.getR()+e.getR())){
				e.hit();
				player.hit();
				boolean remove = e.remove();
				if (remove){
					enemy.remove(i);
					i--;
				    break;
				
			}
			}
		}
	wave.update();	
	}
	
	public void gameRender(){
	//Отрисовка заднего фона
		Background.draw(g);
    //Отрисовка игрока
		player.draw(g);
		player.draw2(g);
	//Отрисовка пуль
		for (int i=0; i<bullets.size(); i++){
			bullets.get(i).draw(g);
		}
     //Отрисовка врага
		for (int i=0; i< enemy.size(); i++){
			enemy.get(i).draw(g);
			
		}
		if(wave.showWave()){
		   wave.draw(g);
		}
	}
	
	private void gameDraw(){
		java.awt.Graphics g2 =  this.getGraphics();
		 g2.drawImage(image, 0, 0, null );
	}
	}



	