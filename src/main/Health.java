package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Health extends GameObject{

	private Handler handler; 
	
	public Health(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler; 
		
		velX = 3; 
		velY = 3; 
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,10,10); //returns rectangle with exact xy and width height of basic enemy

	}

	public void tick() {
		x += velX; 
		y += velY; 
		
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;  //once object hits height border, reverse velocity 
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;  //once object hits width border, reverse velocity 

		handler.addObject(new Trail(x,y, ID.Trail,  10,10,0.05f,Color.green, handler)); //all of the parameters from Trail class
	}


	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 10, 10);
		
	}
	
	

}
