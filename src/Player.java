import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{

	Handler handler;
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			
			if(temp.getId() == ID.BasicEnemy || temp.getId() == ID.SmartEnemy || 
					temp.getId() == ID.FastEnemy || temp.getId() == ID.BossEnemy || temp.getId() == ID.BossEnemyBullet) {
				if(getBounds().intersects(temp.getBounds())) {
					HUD.HEALTH -= 2;	
				}
			}
		}
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		collision();
		x = Game.border(x, 0, Game.WIDTH - 38);
		y = Game.border(y, 0, Game.HEIGHT - 60);
		handler.addObject(new Trail(x, y, ID.Trail, 32, 32, (float) 0.1, Color.white, handler));
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y , 32, 32);
	}
	@Override
	public void render(Graphics g) {

		g.setColor(Color.white);

		g.fillRect((int)x, (int)y, 32, 32);
	}
	
}
