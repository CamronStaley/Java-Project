import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject{
	private Handler handler;
	Random r = new Random();
	
	public EnemyBossBullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX = (r.nextInt(5 - -5)+ -5);
		velY = 5;
		this.handler = handler;
	}


	public void tick() {
		x += velX;
		y += velY;
		
		if(y >= Game.HEIGHT) {
			handler.removeObject(this);
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, 8, 8, (float) 0.08, Color.red, handler));
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y , 16, 16);
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 8, 8);
		
	}
}
