package objects.pak;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import game.pak.GameBoard;

public class Dolly extends Ghost {

	public int currentXpos = randomizer("width"), currentYpos = randomizer("height");
	private String resource = "dolly_up.png";
	private Image dollyImage = new ImageIcon(getClass().getResource(resource)).getImage();
	public Point midPoint = new Point();
	boolean hasEntered = false;

	@Override
	public void draw(Graphics gr) {
		midPoint.setLocation((int) (getX() + getWidth() / 2), (int) (this.getY() + this.getHeight() / 2));
		gr.drawImage(dollyImage, (int) (currentXpos), (int) (currentYpos), GHOST_WIDTH, GHOST_WIDTH, null);
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
		dollyImage = new ImageIcon(getClass().getResource(resource)).getImage();
		this.resource = resource;

	}

	@Override
	public void doAction() {

		switch (resource) {
		case "dolly_down.png":
			if (currentYpos < GameBoard.BOARD_HEIGHT - this.getHeight())
				currentYpos+=pace;
			else
				setImage("dolly_up.png");
			break;
		case "dolly_up.png":
			if (currentYpos > 0)
				currentYpos-=pace;
			else
				setImage("dolly_down.png");
			break;

		}

	}

	@Override
	public Point getContactPoint() {
		return midPoint;
	}

	public boolean isEntered() {
		return hasEntered;
	}

	public void setEntered(boolean b) {
		hasEntered = b;
	}


}
