import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{
	private Handler handler;
	private GameObject player;
	
	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX = 5;
		velY = 5;
		this.handler = handler;
		
		for(GameObject i : handler.object) {
			if(i.getId() == ID.Player) player = i;
		}
		
	}


	public void tick() {
		x += velX;
		y += velY;
		
		float difX = x - player.getX() - 8;
		float difY = y - player.getY() - 8;
		float distance = (float) Math.sqrt((x - player.getX()) * (x-player.getX()) + (y - player.getY()) * (y - player.getY()));
		
		velX = (float) ((-1/distance)*difX); 
		velY = (float) ((-1/distance)*difY); 
		
		//if(y <=0 || y >= Game.HEIGHT - 32) velY *= -1;
		//if(x <=0 || x >= Game.WIDTH - 32) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, 16, 16, (float) 0.1, Color.green, handler));
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y , 16, 16);
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}
	
}
