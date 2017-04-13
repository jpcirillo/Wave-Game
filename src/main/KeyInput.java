package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Game.STATE;

public class KeyInput extends KeyAdapter{
	
	private Handler handler; 
	private boolean[] keyDown = new boolean[4]; //array to make 4 key down booleans wasd
	
	Game game; 
	
	public KeyInput(Handler handler, Game game){ //sets what our handler input is to handler
		this.handler = handler; 
		this.game = game; 
		keyDown[0]=false; 
		keyDown[1]=false; 
		keyDown[2]=false; 
		keyDown[3]=false; 
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode(); 
		for (int i = 0 ;i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i); //tempObject is now every object in our game
			
			if (tempObject.getId() == ID.Player){
				//Key Events for player 1
				if(key == KeyEvent.VK_W) {tempObject.setVelY(-5); keyDown[0] = true; }//if hitting W 
				if(key == KeyEvent.VK_S) {tempObject.setVelY(5); keyDown[1] = true;}// if hitting S
				if(key == KeyEvent.VK_D) {tempObject.setVelX(5); keyDown[2] = true;}// if hitting D
				if(key == KeyEvent.VK_A) {tempObject.setVelX(-5); keyDown[3] = true;}// if hitting A
			}
		}
		
		if(key == KeyEvent.VK_P) {
			if(game.gameState == STATE.Game){
				if(Game.paused) Game.paused = false; 
				else Game.paused = true; 
			}
		}
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode(); 
		for (int i = 0 ;i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i); //tempObject is now every object in our game
			
			if (tempObject.getId() == ID.Player){
				//Key Events for player 1
				if(key == KeyEvent.VK_W) keyDown[0] = false; //if hitting W
				if(key == KeyEvent.VK_S) keyDown[1] = false; // if hitting S
				if(key == KeyEvent.VK_D) keyDown[2] = false; // if hitting D
				if(key == KeyEvent.VK_A) keyDown[3] = false; // if hitting A
				
				//vertical movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0); 
				//horizontal movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0); 

			}
		}
	}
}
