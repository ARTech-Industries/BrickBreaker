package Game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import GUIControls.Window;
import Utils.Direction;

public class GamePanel extends JPanel {
	private Timer timer;
	private GameLoop gameLoop;
	public static int WIDTH;
	public static int HEIGHT;

	public GamePanel() {
		Window.setTitle("Brick Breaker");
		setDoubleBuffered(true);
		gameLoop = new GameLoop(this);

		// every tick of the timer is a frame of the game
		// tells game loop to update and draw itself every tick
		timer = new Timer(1, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameLoop.update();
				repaint();
			}
		});

		// keyboard key press detection
		this.addKeyListener(new KeyAdapter() {

			// will tell game loop whether the player has the A key down (move paddle left) or the D key down (move paddle right)
			// also checks to see if the P button was pressed to toggle the pause behavior
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_A) {
					gameLoop.setDirectionPressed(Direction.LEFT);
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					gameLoop.setDirectionPressed(Direction.RIGHT);
				}
				if (e.getKeyCode() == KeyEvent.VK_P) {
					gameLoop.togglePause();
				}
			}

			// will tell game loop whether the player has released the move keys, which signals that the paddle should stop moving
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_A) {
					if (gameLoop.getDirectionPressed() == Direction.LEFT) {
						gameLoop.setDirectionPressed(null);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					if (gameLoop.getDirectionPressed() == Direction.RIGHT) {
						gameLoop.setDirectionPressed(null);
					}
				}
			}

		});

		this.setFocusable(true);

	}

	public void startGame() {
		// set static vars for panel width and height for GameLoop to use whenever needed
		HEIGHT = getHeight();
		WIDTH = getWidth();

		// tell game loop to set itself up in preparation for the game to be played
		gameLoop.setup();

		// start the timer, which will tell the game loop to update and draw every tick
		timer.start();

		this.grabFocus();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;

		// tell game loop to draw its desired graphics to the screen
		gameLoop.draw(graphics);
	}
	
	
}
