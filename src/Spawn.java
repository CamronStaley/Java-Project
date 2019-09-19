import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private int scoreKeep = 0;
	private Random r  = new Random();
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep++;
		
		if(scoreKeep >= 100) { 
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);

			
			if(hud.getLevel() < 11){
				if(hud.getLevel() % 10 == 0) {
					handler.clearEnemies();
					handler.addObject(new BossEnemy((Game.WIDTH/2)-48, -100, ID.BossEnemy, handler));
				} else if(hud.getLevel() % 5 == 0) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
				} else if(hud.getLevel() % 3 == 0) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
				}else if(hud.getLevel() % 1 == 0) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				}
			}

			

		}
		
	}
	
	
}
