package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 6691247796639148462L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; // gives a 16:9ish aspect ratio
	private Thread thread;
	private boolean running = false;
	public static boolean paused = false;
	public int diff = 0 ;
	//0 = normal
	//1 = hard
	private Random r; 
	private Handler handler; 
	private HUD hud;
	private Spawn spawner; 
	private Menu menu; 
	
	public enum STATE {
		Help,
		Menu,
		Select,
		Game,
		End
	};
	
	public static STATE gameState = STATE.Menu; 
	
	public Game() {
		handler = new Handler();
		hud = new HUD(); 
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop();
		
		new Window(WIDTH, HEIGHT, "Wave after wave", this); // this refers the the game class
		  
		spawner = new Spawn(handler, hud, this);  
		r = new Random();
		
		if(gameState == STATE.Game){
			handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32,ID.Player,handler));
			handler.addObject(new BasicEnemy(WIDTH/2-40, HEIGHT/2-40, ID.BasicEnemy,handler)); //adds new enemy at random place	
		}else{
			for(int i = 0; i<15; i++){
				handler.addObject(new MenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT),ID.MenuParticle,handler));
			}
		}
		
	}

	public synchronized void start() {
		thread = new Thread(this); // initialize thread as new thread. this
									// refers to the game class
									// this is where the thread will be run
		thread.start(); // starts thread
		running = true; 
	}

	public synchronized void stop() {
		try{
			thread.join(); //stopping thread
			running = false; 
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run() {  //run method that handles screen updates and what not (not original code)
		this.requestFocus();
		long lastTime = System.nanoTime(); 
		double amountOfTicks = 60.0; 
		double ns = 1000000000 / amountOfTicks; 
		double delta = 0; 
		long timer = System.currentTimeMillis(); 
		int frames = 0; 
		while(running){
			long now = System.nanoTime(); 
			delta += (now - lastTime) / ns; 
			lastTime = now; 
			while(delta >= 1) {
				tick(); 
				delta--; 
			}
			if(running)
				render(); 
			frames++; 
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000; 
				//System.out.println("FPS: " + frames);
				frames = 0; 
			}
		}
		stop(); 
	}
	
	private void tick(){ //tick() method is used to update the game logic
		
		if(gameState == STATE.Game){
			if(!paused){
				hud.tick(); 
				spawner.tick(); 
				handler.tick();
				if(HUD.HEALTH <= 0){
					HUD.HEALTH = 100;
					gameState = STATE.End;
					handler.clearEnemys();
					for(int i = 0; i<15; i++){
						handler.addObject(new MenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT),ID.MenuParticle,handler));
					}
					
				}
			}
			
		}else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select){
			menu.tick(); 
			handler.tick();
		}
		
	}
	
	private void render(){ //render() method is used to update what is on screen
		BufferStrategy bs = this.getBufferStrategy(); 
		if(bs == null){
			this.createBufferStrategy(3);
			return; 
		}
		Graphics g = bs.getDrawGraphics(); 
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		handler.render(g); //render everything in handler class
		
		if(paused){
			g.setColor(Color.white);
			g.drawString("PAUSED", 100, 100);
		}
		
		if(gameState == STATE.Game)
		{
			hud.render(g);     //render everything in HUD class
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select){
			menu.render(g); 
		}
		
		g.dispose(); 
		bs.show(); 
	}
	
	public static float clamp(float var, float min, float max){
		if(var >= max){ //if x value is ever > than room width
			return var = max;  //return x value = to room width max
		}
		else if (var <= min){ //same with the min
			return var = min; 
		}
		else return var; 
	}

	public static void main(String[] args) {
		new Game();
	}
}
