
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{

	private Handler handler; 
	Random r = new Random(); 
	private Color col;
	
	
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler; 
		
		velX = (r.nextInt(7- -7 )+ -7); 
		velY = (r.nextInt(7- -7 )+ -7); 
		if (velX ==0)velX =1; 
		if (velY ==0)velY =1; 


		
		
		col = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16); //returns rectangle with exact xy and width height of basic enemy

	}

	public void tick() {
		x += velX; 
		y += velY; 
		
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;  //once object hits height border, reverse velocity 
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;  //once object hits width border, reverse velocity 

		handler.addObject(new Trail(x,y, ID.Trail,  16,16,0.05f,col, handler)); //all of the parameters from Trail class
	}


	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}
	
	

}
