import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r;
	private boolean help = false;
	private HUD hud;
	public boolean gameOver = false;
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		r = new Random();
		
		if(help == false && gameOver == false) {
			if(mouseOver(mx, my, 210, 150, 200, 64)) { //start game
				game.setGameState(1);
				handler.addObject(new Player(game.WIDTH/2 - 32, game.HEIGHT/2 - 32, ID.Player, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
		
			if(mouseOver(mx, my, 210, 250, 200, 64)) { //help 
				help = true;
			}
			if(mouseOver(mx, my, 210, 350, 200, 64)) { //exit
				System.exit(1);
			}
		}
		if(help == true && gameOver == false) {
			if(mouseOver(mx, my, 5, 5, 135, 60)) { //back
				help = false;
			}
		}
		if(help == false && gameOver == true) {
			if(mouseOver(mx, my, 180, 340, 215, 50)) { //back
				System.exit(1);
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} 
		} 
		return false;
	}
	
	public void render(Graphics g) {
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Menu", 240, 70);
		
		g.setFont(fnt2);
		
		g.drawRect(210, 150, 200, 64);
		g.drawString("Play", 280, 190);
		
		g.drawRect(210, 250, 200, 64);
		g.drawString("Help", 280, 290);
		
		g.drawRect(210, 350, 200, 64);
		g.drawString("Quit", 280, 390);
		
		if(gameOver) { //game over menu

			g.setColor(Color.black);
			g.fillRect(0, 0, 640, (640/ 12 * 9));
			
			Graphics2D g2;
			g2 = (Graphics2D) g;
			g2.setFont(fnt);
			
			g2.setColor(Color.white);
			g2.drawString("Game Over", 180, 70);
			
			g2.setFont(fnt2);
			g2.drawString("You got to level: " + hud.getLevel(), 180, 170);
			g2.drawString("You earned: " + hud.getScore() + " points", 180, 270);
			
			g2.drawRect(180, 340, 160, 50);
			g2.drawString("Exit Game", 185, 375);
			
		}
		
		if(help == true) { // help menu
			g.setColor(Color.black);
			g.fillRect(0, 0, 640, (640/ 12 * 9));
			
			Graphics2D g2;
			g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(10));
			
			g2.setColor(Color.white);
			g2.drawRect(5, 5, 625, 440);
			
			
			
			//back button
			g2.setFont(fnt);
			g2.setStroke(new BasicStroke(2));
			g2.drawString("Back", 15, 55);
			g2.drawRect(5, 5, 135, 60);
			g2.drawString("Help", 270, 70);
			
			//making multi line output
			g2.setFont(fnt2);
			String text = "This game is a beta version to "
					+ "improve \njava gui and game development skills :)";
			int x = 50;
			int y = 200;
			
			for(String line : text.split("\n")) {
				g2.drawString(line, x, y += g2.getFontMetrics().getHeight());
			}			
			
		}
		
		
	}
	
	public void tick() {
		
	}
	
}
