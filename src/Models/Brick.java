package Models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import SwingShapes.Rectangle;

public class Brick {
	private Rectangle brick;
	private boolean isHit;

	public Brick(int xLocation, int yLocation, int width, int height, Color color) {
		brick = new Rectangle();
		// brick.setColor(Color.decode("#564787"));
		brick.setColor(color);
		brick.setSize(width, height);
		brick.setLocation(xLocation, yLocation);
		brick.setBorderColor(Color.decode("#000"));
		brick.setBorderThickness(2);
		isHit = false;
	}

	public Color getRandomColor() {
		Random random = new Random();
		int baseColor = 175;
		int red = baseColor + random.nextInt(56);
		int green = baseColor + random.nextInt(56);
		int blue = baseColor + random.nextInt(56);
		return new Color(red, green, blue);
	}

	public int getXLocation() {
		return brick.getXLocation();
	}

	public int getYLocation() {
		return brick.getYLocation();
	}

	public int getWidth() {
		return brick.getWidth();
	}

	public int getHeight() {
		return brick.getHeight();
	}

	public boolean getIsHit() {
		return isHit;
	}

	public void setIsHit(boolean isHit) {
		this.isHit = isHit;
	}

	public void draw(Graphics2D g) {
		brick.paint(g);
	}
}
