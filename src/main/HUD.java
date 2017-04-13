package main;

import java.awt.Color;
import java.awt.Graphics;

//Heads Up Display 
public class HUD {
	
	public static float HEALTH = 100; 
	private float greenValue = 255; 
	
	private int score = 0; 
	private int level = 1; 

	public void tick(){
		
		HEALTH = Game.clamp(HEALTH, 0, 100); //health can't drop below 0 or above 100; 
		greenValue = Game.clamp(greenValue, 0 , 255); //max green 0; 
		
		greenValue = HEALTH*2; 
		
		score++;
		
	}
	
	public void render(Graphics g){ //draw health bar out
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75,(int)greenValue,0)); //RGB color value; 75 red, green value, 0 blue 
		g.fillRect(15, 15, (int)HEALTH*2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: " + score, 15, 64); 
		g.drawString("Level: " + level, 15, 80); 

	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}



	
}
