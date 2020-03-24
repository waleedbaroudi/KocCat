package objects.pak;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;

import javax.swing.ImageIcon;

import game.pak.GameBoard;

public class Casper extends Ghost {

	public int currentXpos = randomizer("width"), currentYpos = randomizer("height");
	private String resource = "casper.png";
	private Image casperImage = new ImageIcon(getClass().getResource(resource)).getImage();
	private int currentDirection;
	private final int TURN_RATE = 3000;
	public Point contactPoint = new Point();
	boolean hasEntered = false;

	@Override
	public void draw(Graphics gr) {
		contactPoint.setLocation((int) (getX() + getWidth() / 2), (int) (this.getY() + this.getHeight() / 2));
		gr.drawImage(casperImage, currentXpos, currentYpos, GHOST_WIDTH, GHOST_WIDTH, null);
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
		casperImage = new ImageIcon(getClass().getResource(resource)).getImage();

	}

	@Override
	public void doAction() {
		if (GameBoard.runTime % TURN_RATE == 0)
			turn();
		switch (currentDirection) {
		case 0:
			if (currentXpos < GameBoard.BOARD_WIDTH - this.getWidth())
				currentXpos+=pace;
			else
				turn();
			break;
		case 1:
			if (currentXpos > 0)
				currentXpos-=pace;
			else
				turn();
			break;
		case 2:
			if (currentYpos < GameBoard.BOARD_HEIGHT - this.getHeight())
				currentYpos+=pace;
			else
				turn();
			break;
		case 3:
			if (currentYpos > 0)
				currentYpos-=pace;
			else
				turn();
			break;
		}
	}

	public void turn() {
		Random rand = new Random();
		currentDirection = rand.nextInt(4);

	}

	public Point getContactPoint() {
		return this.contactPoint;
	}
	public boolean isEntered() {
		return hasEntered;
	}
	
	public void setEntered(boolean b) {
		hasEntered=b;
	}

}
