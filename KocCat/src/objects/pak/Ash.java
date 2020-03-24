package objects.pak;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import game.pak.GameBoard;

public class Ash extends Ghost {
	public int currentXpos = randomizer("width"), currentYpos = randomizer("height");
	private String resource = "ash_right.png";
	private Image ashImage = new ImageIcon(getClass().getResource(resource)).getImage();
	public Point midPoint = new Point();
	boolean hasEntered = false;

	@Override
	public void draw(Graphics gr) {
		midPoint.setLocation((int) (getX() + getWidth() / 2), (int) (this.getY() + this.getHeight() / 2));
		gr.drawImage(ashImage,  currentXpos,  currentYpos, GHOST_WIDTH, GHOST_WIDTH, null);
	}

	@Override
	public double getWidth() {

		return GHOST_WIDTH;
	}

	@Override
	public double getHeight() {

		return GHOST_WIDTH;
	}

	@Override
	public int getX() {

		return currentXpos;
	}

	@Override
	public int getY() {

		return currentYpos;
	}

	@Override
	public void setImage(String resource) {
		ashImage = new ImageIcon(getClass().getResource(resource)).getImage();
		this.resource = resource;
	}

	@Override
	public void doAction() {

		switch (resource) {
		case "ash_left.png":
			if (currentXpos > 0)
				currentXpos-=pace;
			else
				setImage("ash_right.png");
			break;
		case "ash_right.png":
			if (currentXpos < GameBoard.BOARD_WIDTH - this.getWidth())
				currentXpos+=pace;
			else
				setImage("ash_left.png");
			break;
		}

	}

	public Point getContactPoint() {
		return this.midPoint;
	}
	public boolean isEntered() {
		return hasEntered;
	}
	public void setEntered(boolean b) {
		hasEntered=b;
	}

}
