package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import main.Game.STATE;

public class Menu extends MouseAdapter {
	
	private Game game; 
	private Handler handler; 
	private HUD hud; //added for score printing 
	private Random r = new Random(); 
	
	public Menu(Game game, Handler handler, HUD hud){
		this.game = game; 
		this.hud = hud; 
		this.handler = handler; 
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY(); 
		
		if(game.gameState == STATE.Menu){
			//play button
			if(mouseOver(mx,my,210, 150, 200, 64)){
				game.gameState = STATE.Select; 
				AudioPlayer.getSound("menu_sound").play(); 
				return; 
				
			}
			
			//Help button
			if(mouseOver(mx, my,210, 250, 200, 64)){
				game.gameState = STATE.Help; 
				AudioPlayer.getSound("menu_sound").play(); 

			}
			
			
			//quit button 
			if(mouseOver(mx,my,210, 350, 200, 64)){
				System.exit(1);
				
			}
			
		}
		
		if(game.gameState == STATE.Select){
			//normal button
			if(mouseOver(mx,my,210, 150, 200, 64)){
				game.gameState = STATE.Game; 
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy,handler)); //adds new enemy at random place
				game.diff = 0;  
				AudioPlayer.getSound("menu_sound").play(); 

			}
			
			//Hard button
			if(mouseOver(mx, my,210, 250, 200, 64)){
				game.gameState = STATE.Game; 
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));
				handler.clearEnemys();
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy,handler)); //adds new enemy at random place
				game.diff = 1;  
				AudioPlayer.getSound("menu_sound").play(); 

			}
			
			
			//quit button 
			if(mouseOver(mx,my,210, 350, 200, 64)){
				game.gameState = STATE.Menu; 
				AudioPlayer.getSound("menu_sound").play();
				return; 				
			}
			
		}
		
		//back
		if(game.gameState == STATE.Help){
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				game.gameState = STATE.Menu; 
				AudioPlayer.getSound("menu_sound").play();
				return; 
			}
		}
		
		//Try Again
		if(game.gameState == STATE.End){
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				game.gameState = STATE.Menu; 
				hud.setLevel(1);
				hud.setScore(0);
				AudioPlayer.getSound("menu_sound").play(); 
			
			}
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x+width){
			if (my > y && my < y + height){
				return true; 
			}else return false; 
		}else return false; 
	}
	
	public void tick(){
		
	}

	public void render(Graphics g){
		if(game.gameState == STATE.Menu){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.white);

			g.drawString("WAVE", 225, 70);
			
			g.setFont(fnt2);
			g.setColor(Color.green);

			g.drawRect(210, 150, 200, 64);
			g.setColor(Color.white);
			g.drawString("Play", 270, 190);
			g.setColor(Color.magenta);
			g.drawRect(210, 250, 200, 64);
			g.setColor(Color.white);
			g.drawString("Help", 270, 290);
			g.setColor(Color.red);
			g.drawRect(210, 350, 200, 64);
			g.setColor(Color.white);
			g.drawString("Quit", 270, 390);

		}else if(game.gameState == STATE.Help){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);


			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt3);
			g.drawString("Use WASD buttons to move player and dodge enemies", 70, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);

		}else if(game.gameState == STATE.End){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);


			g.setFont(fnt);
			g.setColor(Color.red);
			g.drawString("Game Over", 180, 70);
			g.setColor(Color.white);
			g.setFont(fnt3);
			g.drawString("You lost with a score of: " + hud.getScore(), 175, 200);
			g.setColor(Color.yellow);
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Menu", 270, 390);

		}else if(game.gameState == STATE.Select){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.white);

			g.drawString("SELECT DIFFICULTY", 60, 70);
			g.setColor(Color.green);
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Normal", 255, 190);
			g.setColor(Color.red);
			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard", 270, 290);
			g.setColor(Color.blue);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);

		}
	
	}
}
