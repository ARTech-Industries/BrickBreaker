package Models;

import java.awt.Color;
import java.awt.Graphics2D;

import SwingShapes.Rectangle;
import Utils.Direction;

public class Ball {
	private Rectangle ball;
	private Direction xDirection;
	private Direction yDirection;
	private int speed = 4;

	public Ball() {
		ball = new Rectangle();
		ball.setColor(Color.decode("#557786"));
		ball.setSize(10, 10);
		xDirection = Direction.LEFT;
		yDirection = Direction.UP;
		ball.setBorderColor(Color.decode("#000"));
		ball.setBorderThickness(2);
	}

	public int getXLocation() {
		return ball.getXLocation();
	}

	public void setXLocation(int xLocation) {
		ball.setLocation(xLocation, ball.getYLocation());
	}

	public int getYLocation() {
		return ball.getYLocation();
	}

	public void setYLocation(int yLocation) {
		ball.setLocation(ball.getXLocation(), yLocation);
	}

	public Direction getXDirection() {
		return xDirection;
	}

	public void setXDirection(Direction xDirection) {
		this.xDirection = xDirection;
	}

	public Direction getYDirection() {
		return yDirection;
	}

	public void setYDirection(Direction yDirection) {
		this.yDirection = yDirection;
	}

	public int getWidth() {
		return ball.getWidth();
	}

	public int getHeight() {
		return ball.getHeight();
	}

	public int getSpeed() {
		return speed;
	}

	public void moveX() {
		int amountToMoveX = xDirection.getVelocity() * speed;
		ball.setLocation(ball.getXLocation() + amountToMoveX, ball.getYLocation());
	}

	public void moveY() {
		int amountToMoveY = yDirection.getVelocity() * speed;
		ball.setLocation(ball.getXLocation(), ball.getYLocation() + amountToMoveY);
	}

	public void draw(Graphics2D g) {
		ball.paint(g);
	}

	public boolean intersects(Paddle paddle) {
		return paddle.getXLocation() < ball.getXLocation() + ball.getWidth()
				&& paddle.getXLocation() + paddle.getWidth() > ball.getXLocation() &&
				paddle.getYLocation() < ball.getYLocation() + ball.getHeight()
				&& paddle.getYLocation() + paddle.getHeight() > ball.getYLocation();
	}

	public boolean intersects(Brick brick) {
		return brick.getXLocation() < ball.getXLocation() + ball.getWidth()
				&& brick.getXLocation() + brick.getWidth() > ball.getXLocation() &&
				brick.getYLocation() < ball.getYLocation() + ball.getHeight()
				&& brick.getYLocation() + brick.getHeight() > ball.getYLocation();
	}
}
