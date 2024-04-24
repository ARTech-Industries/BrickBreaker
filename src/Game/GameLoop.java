package Game;

import java.awt.Graphics2D;
import Models.Ball;
import Models.Brick;
import Models.Paddle;
import Utils.Axis;
import Utils.Direction;
import java.awt.Color;
import java.awt.Font;


public class GameLoop {
	private Paddle paddle;
	private Ball ball;
	private Direction directionPressed;
	private Brick[][] bricks;
	private int totalNumberOfBricks;
	private int numberOfBricksHit;
    private CollisionHandler collisionHandler;
    
    // called once on game start
    // sets up all game objects in preparation for the game to be played
    public void setup() {
        // create game objects
        paddle = new Paddle();
		ball = new Ball();
		bricks = new Brick[3][6];
        numberOfBricksHit = 0;
		
		int brickWidth = 120;
		int brickHeight = 20;
		int horizontalSpacing = 5;
		int verticalSpacing = 5;
		int spacingFromWallX = 20;
		int spacingFromWallY = 5;
		
        // create brick for each slot available in 2D array
        totalNumberOfBricks = 0;
		for (int i = 0; i < bricks.length; i++) {
			for (int j = 0; j < bricks[i].length; j++) {
				int brickXLocation = j * brickWidth + (j * horizontalSpacing) + spacingFromWallX;
				int brickYLocation = i * brickHeight + (i * verticalSpacing) + spacingFromWallY;
				bricks[i][j] = new Brick(brickXLocation, brickYLocation, brickWidth, brickHeight);
				totalNumberOfBricks++;
			}
		}

        // start paddle in middle of screen
        paddle.setXLocation(GamePanel.WIDTH / 2 - paddle.getWidth() / 2);
		paddle.setYLocation(GamePanel.HEIGHT - paddle.getHeight() - 10);

        // start ball on top of the paddle and center it
		int paddleX2 = paddle.getXLocation() + paddle.getWidth();
		int difference = paddleX2 - paddle.getXLocation();
		ball.setXLocation(paddle.getXLocation() + (difference / 2) - (ball.getWidth() / 2));
		ball.setYLocation(paddle.getYLocation() - ball.getHeight() - 5);

        // create collision handler instance to use throughout program for detecting collisions such as the ball hitting the paddle or the ball hitting a brick
        collisionHandler = new CollisionHandler(ball, paddle, bricks);
    }

    // called every frame while the game runs
    public void update() {
        // if a direction is being pressed (either left or right), move paddle
        // if when paddle is moved, it runs into the ball, it pushes the ball over a little bit
        if (directionPressed != null) {
            if (directionPressed == Direction.LEFT) {
                paddle.moveLeft();
                if (ball.intersects(paddle)) {
                    ball.setXLocation(paddle.getXLocation() - ball.getWidth());
                }
            } 
            else if (directionPressed == Direction.RIGHT) {
                paddle.moveRight();
                if (ball.intersects(paddle)) {
                    ball.setXLocation(paddle.getXLocation() + paddle.getWidth());
                }
            }
        }

        // move ball either up or down based on its current y direction
        ball.moveY();

        // check if ball hit ceiling -- if so, reverse its direction so it starts to move downwards
        // this makes it appear as if the ball is bouncing off the ceiling
        if (ball.getYDirection() == Direction.UP && ball.getYLocation() < 0) {
            ball.setYDirection(Direction.DOWN);
            ball.setYLocation(0);
        } 
        
        // check if ball has collided with paddle while moving downwards
        collisionHandler.paddleBallCollisionHandler(Axis.Y);
        
        // check if ball has collided with any of the bricks after it has moved up or down
        boolean isBrickYCollision = collisionHandler.brickBallCollisionHandler(Axis.Y);
        if (isBrickYCollision) {
            numberOfBricksHit++;
        }

        // if all bricks hit, game over, player wins
        if (allBricksHit()) {
            System.exit(0);
        }

        // move ball either left or right based on its current x direction
        ball.moveX();
        
        // check if ball hit either the left or right walls -- if so, reverse its direction
        // this makes it appear as if the ball is bouncing off the walls
        if (ball.getXDirection() == Direction.LEFT && ball.getXLocation() < 0) {
            ball.setXDirection(Direction.RIGHT);
            ball.setXLocation(0);
        } 
        else if (ball.getXDirection() == Direction.RIGHT && ball.getXLocation() + ball.getWidth() > GamePanel.WIDTH) { 
            ball.setXDirection(Direction.LEFT);
            ball.setXLocation(GamePanel.WIDTH - ball.getWidth());
        }

        // check if ball has collided with paddle while moving left or right
        // very rare to actually happen but here just in case :)
        collisionHandler.paddleBallCollisionHandler(Axis.X);
        
        // check if ball has collided with any of the bricks after it has moved left or right
        boolean isBrickXCollision = collisionHandler.brickBallCollisionHandler(Axis.X);
        if (isBrickXCollision) {
            numberOfBricksHit++;
        }

        // if all bricks hit, game over, player wins
        if (allBricksHit()) {
            System.exit(1);
        }
        
        // if ball hits bottom of the screen, game over, player loses
        if (ball.getYLocation() + ball.getHeight() >= GamePanel.HEIGHT) {
            System.exit(0);
        }
    }

    // draws game graphics to the screen
    public void draw(Graphics2D graphics) {
        // draw paddle and ball rectangle graphics to screen
        paddle.draw(graphics);
		ball.draw(graphics);
        
        // draw each brick to the screen that hasn't yet been hit
		for (Brick[] brickArray : bricks) {
			for (Brick brick : brickArray) {
				if (!brick.getIsHit()) {
					brick.draw(graphics);
				}
			}
		}
         // Display the score
    graphics.setColor(Color.BLACK); // Set the color for the score text
    graphics.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font for the score text
    graphics.drawString("Score: " + numberOfBricksHit, 10, GamePanel.HEIGHT - 50); // Draw the score text in the bottom left 

    }

    // returns if all bricks have been hit yet or not
	private boolean allBricksHit() {
		return numberOfBricksHit == totalNumberOfBricks;
	}

    public Direction getDirectionPressed() {
        return this.directionPressed;
    }

    // GamePanel file uses this to tell GameLoop which direction was pressed (left or right)
    public void setDirectionPressed(Direction directionPressed) {
        this.directionPressed = directionPressed;
    }
}
