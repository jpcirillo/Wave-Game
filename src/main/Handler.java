package main;

import java.awt.Graphics;
import java.util.LinkedList;

//loops through all objects in game and individually updates and renders them
public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>(); //list of all game objects in our game
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){ //looping through every single game object
			GameObject tempObject = object.get(i);  //gets id for all objects (hence the i). Loop runs through to i
			
			tempObject.tick(); 
		}
	 
	}
	
	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++){ //looping through every single game object
			GameObject tempObject = object.get(i);  //gets id for all objects (hence the i). Loop runs through to i
			
			tempObject.render(g); 
		}
	}
	
	public void clearEnemys(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				object.clear(); 
				if(Game.gameState != Game.STATE.End)
				addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player,this));
			}
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object); 
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object); 
	}
}
