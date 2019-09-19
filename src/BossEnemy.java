import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject{
	private Handler handler;
	private int timer = 60;
	private int timer2 = 50;
	Random r = new Random();
	
	public BossEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX = 0;
		velY = 2;
		this.handler = handler;
	}


	public void tick() {
		x += velX;
		y += velY;
		
		if(timer <= 0) {
			velY = 0;
		}else {
			timer--;
		}
		
		if(timer <= 0) timer2--;
		if(timer2 <= 0) {
			if(velX == 0) {
				velX = 3;
			}
			if(velX > 0)velX+=.01f;
			if(velX < 0)velX-=.01f;
			int spawn = r.nextInt(10);
			if(spawn == 0) handler.addObject(new EnemyBossBullet((int)x + 48, (int)y + 48, ID.BossEnemyBullet, handler));
		}
		
		if(x <=0 || x >= Game.WIDTH - 96) velX *= -1;
		//handler.addObject(new Trail(x, y, ID.Trail, 96, 96, (float) 0.05, Color.blue, handler));
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y , 96, 96);
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 96, 96);
		
	}
}
