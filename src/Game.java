import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random r;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public enum STATE {
		Menu,
		Game
	};
	
	public STATE gameState;
	
	public Game() {
		gameState = STATE.Menu;
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);

		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		new Window(WIDTH, HEIGHT, "Game", this);
		
		
		spawner = new Spawn(handler, hud);
		r = new Random();
	}
	
	public void setGameState(int state) {
		if(state == 1) {
			gameState = STATE.Game;
		} 
		if(state == 2) {
			gameState = STATE.Menu;
		}
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}
	
	private void tick() {
		handler.tick();
		
		if(hud.HEALTH == 0) { //game over menu
			gameState = STATE.Menu;
			menu.gameOver = true;
		}
		if(gameState == STATE.Game) {
			hud.tick();
			spawner.tick();
		} else {
			menu.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		
		if(gameState == STATE.Game) {
			hud.render(g);
		} else if(gameState == STATE.Menu) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float border(float var, float min, float max) {
		if(var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}
	
	public static void main(String[] args) {
		new Game();

	}
}
