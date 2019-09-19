import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

	private Handler handler;
	
	public BasicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX = 5;
		velY = 5;
		this.handler = handler;
	}


	public void tick() {
		x += velX;
		y += velY;
		
		if(y <=0 || y >= Game.HEIGHT - 32) velY *= -1;
		if(x <=0 || x >= Game.WIDTH - 32) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, 16, 16, (float) 0.1, Color.red, handler));
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y , 16, 16);
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}

}