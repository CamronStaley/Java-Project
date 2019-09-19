import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject{
	
	private float alpha = 1;
	private Handler handler;
	private Color color;
	private int width, height;
	private float life;
	
	public Trail(float x, float y, ID id, int width, int height, float life, Color color, Handler handler) {
		super(x, y, id);
		this.color = color;
		this.width = width;
		this.height = height;
		this.handler = handler;
		this.life = life;
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public void tick() {
		if(alpha > life) {
			alpha -= (life - .0001f);
		}else {
			handler.removeObject(this);
		}
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect((int)x,(int)y, width, height);
		
		g2d.setComposite(makeTransparent(1));
		
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

}
