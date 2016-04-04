package AndreiBike;

import java.awt.*;

public class Bullet {
	
	private double x;
	private double y;
	private int r;
	private Color color;
	private  int speed;
	
	//Constructor
	
	public Bullet(){
		
		x=GamePanel.player.getX();
		y=GamePanel.player.getY();
		r=2;
		color =Color.BLACK;
		speed = 10;
	}
	
	//Metods
	
	public boolean remove(){
		if (y<60){
			return true;
		}
		return false;
	}
	
	public void update(){
		y-=speed;
	}

	public void draw(Graphics2D g){
		g.setColor(color);
		g.fillOval((int)x+10, (int)y-2, r, 2*r );
		
	}

	public double getX() {
		
		return x;
	}

	public double getY() {
		
		return y;
	}
	
	public int getR(){
		return r;
	}
}
