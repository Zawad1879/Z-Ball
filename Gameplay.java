import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	
	private boolean play = false;
	private int score = 0;
	
	
	private int totalBricks = 21;
	
	private Timer timer;
	private int delay = 10;
	
	private int playerX = 310;
	
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -5;
	private int ballYdir = -2;
	
	private MapGenerator map;
	
	public Gameplay(){
		map = new MapGenerator(3, 7);
		addKeyListener(this);
		setFocusable(true); //if not set to true, events will not be dispatched to this component
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) { //draw stuff
		// background
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
		
		//drawing map
		map.draw((Graphics2D) g);
		
		//borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString(""+score, 590, 30);
		
		//the paddle
		g.setColor(Color.green);
		g.fillRect(playerX,  550,  100,  8);
		
		//ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX,  ballposY,  20,  20);
		
		if(ballposY>570){  //ball reaches the bottom, GAME OVER!
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("GAME OVER!", 300, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to restart", 290, 350);
		}
		
		if(totalBricks <= 0){
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("YOU WON!", 300, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to restart", 290, 350);
			
		}
		
		g.dispose();
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		if(play){
			ballposX += ballXdir;
			ballposY += ballYdir;
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
				ballYdir = -ballYdir;
			}
			
			A: for(int i =0; i < map.map.length; i++){
				for(int j = 0; j < map.map[0].length; j++){
					if(map.map[i][j] > 0){
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						
						if (ballRect.intersects(brickRect)){
							map.setBrickValue(i, j, 0);
							totalBricks -= 1;
							score +=5;
							
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width){
								ballXdir = -ballXdir;
							} else {
								ballYdir = -ballYdir;
							}
							break A;
						}
						
					}
				}
				
			}
			if(ballposX < 0){
				ballXdir = -ballXdir;
			}
			if(ballposY < 0){
				System.out.println("Less than zero");
				ballYdir = -ballYdir;
			}
			if(ballposX > 670){
				ballXdir = -ballXdir;
			}
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(playerX >= 590){
				playerX = 590;
			} else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(playerX <= 10){
				playerX = 10;
			} else {
				moveLeft();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(!play){
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -5;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				map = new MapGenerator(3, 7);
				repaint();
			}
			
		}
		
	}
	
	public void moveRight(){
		play = true;
		playerX+=20;
	}

	public void moveLeft(){
		play = true;
		playerX-=20;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
		
	}
	

}
