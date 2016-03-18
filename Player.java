package AndreiBike;

import java.awt.*;

public class Player {

	//
	private double x;
	private double y;
	private int r;
	private int health;
	private int speed;
	
	private Color color1;
	public static boolean up;
	public static boolean down;
	public static boolean left;
	public static boolean right;
	public static boolean isFiring;
	
	public Player(){
		x = GamePanel.WIDTH/2;
		y= GamePanel.HEIGHT /2;
		health =3;
		r=5;
		speed=5;
		color1= Color.RED;
		up=false;
		down=false;
		left=false;
		right=false;
		isFiring=false;
	}
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	public int getR(){
		return r;
	}
	
	//
	public void update (){
		if (up && y>r){
			y-=speed;
		}
		if (down && y<GamePanel.HEIGHT){
			y+=speed;
		}
		if (left && x>r){
		    x-=speed;	
		}
		if (right && x<GamePanel.WIDTH){
			x+=speed;	
			}
		if (isFiring ){
			GamePanel.bullets.add(new Bullet());
		}
	}
	
	public void draw(Graphics2D g){
		g.setColor(color1);
		g.fillRect((int)(x),(int)(y),20,20);
		
	}

	public void draw2(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int)(x+5),(int)(y-5),10,20);
	}
	
	public void hit() {
		health--;
	}
	
}
