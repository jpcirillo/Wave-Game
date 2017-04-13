package main;

import java.util.Random;

public class Spawn {

	private Handler handler; 
	private HUD hud;
	private Game game; 
	private Random r = new Random(); 
	
	private int scoreKeep = 0; 
	
	public Spawn(Handler handler, HUD hud, Game game){
	this.handler = handler; 
	this.hud = hud; 
	this.game = game; 
}

	public void tick(){
		scoreKeep++; 
		//look into higher score each time to level up
		if(scoreKeep >= 300){
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1); 
			if(game.diff == 0){
				
			if(hud.getLevel() == 2){
				handler.addObject(new BasicEnemy((Game.WIDTH/2), (Game.HEIGHT/2), ID.BasicEnemy,handler)); //adds new enemy at random place
			}else if (hud.getLevel() == 3){
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy,handler)); //adds new enemy at random place
			}else if (hud.getLevel() == 4){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.FastEnemy,handler)); //adds new enemy at random place
			}else if (hud.getLevel() == 7){
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.SmartEnemy,handler)); //adds new enemy at random place
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.SmartEnemy,handler)); //adds new enemy at random place
			}else if (hud.getLevel() == 8){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.FastEnemy,handler)); //adds new enemy at random place
			}else if (hud.getLevel() == 9){
				handler.addObject(new Health((Game.WIDTH - 70), (Game.HEIGHT-70), ID.Health,handler)); //adds new enemy at random place
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy,handler)); //adds new enemy at random place
			}else if (hud.getLevel() == 10){
				handler.clearEnemys(); 
				handler.addObject(new EnemyBoss((Game.WIDTH/2)+48, -120, ID.EnemyBoss,handler)); //adds new enemy at random place
			}else if (hud.getLevel() == 14){
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy,handler)); //adds new enemy at random place
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy,handler)); //adds new enemy at random place
			}else if (hud.getLevel() == 15){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-70), ID.FastEnemy,handler));
			}
			else if (hud.getLevel() == 16){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.FastEnemy,handler)); //adds new enemy at random place
			}
			else if (hud.getLevel() == 17){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.FastEnemy,handler)); //adds new enemy at random place
			}
			else if (hud.getLevel() == 18){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.FastEnemy,handler)); //adds new enemy at random place
			}
			else if (hud.getLevel() == 19){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.FastEnemy,handler)); //adds new enemy at random place
			}
			else if (hud.getLevel() == 20){
				handler.clearEnemys();
				handler.addObject(new Boss2((Game.WIDTH/2)+48, -120, ID.Boss2,handler)); //adds new enemy at random place
			}
			

			}else 	if(game.diff == 1){
				if(hud.getLevel() == 2){
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy,handler)); //adds new enemy at random place
				}else if (hud.getLevel() == 3){
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy,handler)); //adds new enemy at random place
				}else if (hud.getLevel() == 4){
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.FastEnemy,handler)); //adds new enemy at random place
				}else if (hud.getLevel() == 5){
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.SmartEnemy,handler)); //adds new enemy at random place
				}else if (hud.getLevel() == 6){
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.FastEnemy,handler)); //adds new enemy at random place
				}else if (hud.getLevel() == 7){
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy,handler)); //adds new enemy at random place
				}else if (hud.getLevel() == 10){
					handler.clearEnemys(); 
					handler.addObject(new EnemyBoss((Game.WIDTH/2)+48, -120, ID.EnemyBoss,handler)); //adds new enemy at random place
				}

				}
			
		}
	}
}
