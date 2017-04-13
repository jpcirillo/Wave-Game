package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{

	private Handler handler; 
	private GameObject player; 
	
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler; 
		
		for(int i = 0; i < handler.object.size(); i++){
			//if object gets ID of player, set GameObject player to player
			if(handler.object.get(i).getId()==ID.Player) player = handler.object.get(i);
		}
	
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16); //returns rectangle with exact xy and width height of basic enemy

	}

	public void tick() {
		 x += velX;
         y += velY;
         float diffX = x - player.getX() - 16;
         float diffY = y - player.getY() - 16;
         float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX())+(y-player.getY())*(y-player.getY()));
         velX = ((-2 / distance) * diffX);
         velY = ((-2 / distance) * diffY);

		handler.addObject(new Trail(x,y, ID.Trail,  16,16,0.03f,Color.orange, handler)); //all of the parameters from Trail class
	}


	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}
	
	

}
