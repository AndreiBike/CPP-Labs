package AndreiBike;

import java.awt.*;

public class Enemy {

	private double x;
	private double y;
	private int r;
	
	private double speed;
	private double dx;
	private double dy;
	
	private int type;
	private int  rank;
	
	private int health;
	
	private Color color;
	
	public Enemy(int type, int rank){
		switch(type){
		
		case 1: switch (rank){   
		            case (1):
		            	color = Color.RED;
		            	x = Math.random()*GamePanel.WIDTH;
		                y=0;
		 
		                speed = 5;
		                
		                health = 1;
		                
		                r=20;
		                
		                double angle = Math.toRadians(Math.random()*360);
		                dx = Math.sin(angle)*speed;
		                dy = Math.cos(angle)*speed;
		                break;
		                
		            case (2):
		            	color = Color.BLUE;
		            	x = Math.random()*GamePanel.WIDTH;
		                y=0;
		 
		                speed = 4;
		                
		                health = 5;
		                
		                r=40;
		                
		                double angle2 = Math.toRadians(Math.random()*360);
		                dx = Math.sin(angle2)*speed;
		                dy = Math.cos(angle2)*speed;
		                break;
		        }
		
		}
}
	
	
	public void update(){
		x += dx;
		y += dy;
		
		if (x <0 && dx <0){
			dx = -dx ;
		}
		if (x> GamePanel.WIDTH && dx > 0 ){
			dx = -dx;
		}
		if (y<0 && dy <0){
			dy = -dy;
		}
		if (y>GamePanel.HEIGHT && dy >0){
			dy=-dy;
		}
	}
	
	
	public void draw(Graphics2D g){
		g.setColor(color);
		g.fillOval((int)x-r, (int)y-r, 2*r, 2*r);
		g.setStroke(new BasicStroke(3));
		g.setColor(color.darker());
		g.drawOval((int)x-r, (int)y-r, 2*r, 2*r);
		g.setStroke(new BasicStroke(2));
	}
	
	public boolean remove(){
		if(health <= 0){
		
			return true;
		}
		return false;
	}
	
	public void hit(){
		health--;
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
