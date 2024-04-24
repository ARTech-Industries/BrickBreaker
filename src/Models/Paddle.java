package Models;

import java.awt.Color;
import java.awt.Graphics2D;

import SwingShapes.Rectangle;
import Utils.Direction;

public class Paddle {
	private Rectangle paddle;
	private int speed = 4;

	public Paddle() {
		paddle = new Rectangle();
		paddle.setSize(60, 10);
		paddle.setColor(Color.decode("#101935"));
		paddle.setBorderColor(Color.decode("#000"));
		paddle.setBorderThickness(2);
	}

	public int getXLocation() {
		return paddle.getXLocation();
	}

	public void setXLocation(int xLocation) {
		paddle.setLocation(xLocation, paddle.getYLocation());
	}

	public int getYLocation() {
		return paddle.getYLocation();
	}

	public void setYLocation(int yLocation) {
		paddle.setLocation(paddle.getXLocation(), yLocation);
	}

	public int getWidth() {
		return paddle.getWidth();
	}

	public int getHeight() {
		return paddle.getHeight();
	}

	public int getSpeed() {
		return speed;
	}

	public void moveLeft() {
		int amountToMoveX = Direction.LEFT.getVelocity() * speed;
		paddle.setLocation(paddle.getXLocation() + amountToMoveX, paddle.getYLocation());
	}

	public void moveRight() {
		int amountToMoveX = Direction.RIGHT.getVelocity() * speed;
		paddle.setLocation(paddle.getXLocation() + amountToMoveX, paddle.getYLocation());
	}

	public void draw(Graphics2D g) {
		paddle.paint(g);
	}
}
