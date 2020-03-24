package objects.pak;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;

import javax.swing.ImageIcon;

import game.pak.GameBoard;

public class KocCat implements Drawable {

	private String resource = "image_right.png";
	private Image catImage = new ImageIcon(getClass().getResource(resource)).getImage();
	public static int score = 0;
	public static int highScore = 0;
	public static int fruitsEaten = 0;
	public final static int CAT_WIDTH = GameBoard.BOARD_WIDTH / 10;
	public static final int INITIAL_X_POS = GameBoard.BOARD_WIDTH / 2 - CAT_WIDTH / 2;
	public static final int INITIAL_Y_POS = GameBoard.BOARD_HEIGHT / 2 - CAT_WIDTH / 2;
	public final int CONTACT_MARGIN = 30; // contact margin due to image size
	private int currentXpos = INITIAL_X_POS, currentYpos = INITIAL_Y_POS;
	private Point contactPoint = new Point(0, 0);
	public static  int pace = 2; // speed of the cat
	
	@Override
	public void draw(Graphics gr) {
		gr.drawImage(catImage, currentXpos, currentYpos, (int) getWidth(), (int) getHeight(), null);
	}

	@Override
	public double getWidth() {

		return CAT_WIDTH;
	}

	@Override
	public double getHeight() {

		return CAT_WIDTH;
	}

	@Override
	public int getX() {

		return currentXpos;
	}

	@Override
	public int getY() {

		return currentYpos;
	}

	public void setImage(String resource) {
		catImage = new ImageIcon(getClass().getResource(resource)).getImage();
		this.resource = resource;
	}

	public String getImageResource() {
		return this.resource;
	}

	public Point getContactPoint() {
		return contactPoint;
	}

	@Override
	public int randomizer() { // Randomizes initial direction NOT YET IMPLEMENTED
		Random rand = new Random();
		return rand.nextInt(3);
	}

	@Override
	public void doAction() {
		switch (resource) {
		case "image_down.png":
			setContactPoint(0);
			if (currentYpos < GameBoard.BOARD_HEIGHT - this.getHeight())
				currentYpos += pace;
			break;
		case "image_up.png":
			setContactPoint(1);
			if (currentYpos > 0)
				currentYpos -= pace;
			break;
		case "image_left.png":
			setContactPoint(2);
			if (currentXpos > 0)
				currentXpos -= pace;
			break;
		case "image_right.png":
			setContactPoint(3);
			if (currentXpos < GameBoard.BOARD_WIDTH - this.getWidth())
				currentXpos += pace;
			break;

		}

	}

	private void setContactPoint(int direction) {
		switch (direction) {
		case 0:
			contactPoint.setLocation(currentXpos + CAT_WIDTH / 2, currentYpos + CAT_WIDTH - CONTACT_MARGIN);
			break;
		case 1:
			contactPoint.setLocation(currentXpos + CAT_WIDTH / 2, currentYpos + CONTACT_MARGIN);
			break;
		case 2:
			contactPoint.setLocation(currentXpos + CONTACT_MARGIN, currentYpos + CAT_WIDTH / 2);
			break;
		case 3:
			contactPoint.setLocation(currentXpos + CAT_WIDTH - CONTACT_MARGIN, currentYpos + CAT_WIDTH / 2);
			break;

		}

	}

	@Override
	public boolean isEntered() {
		return false;
	}

	@Override
	public void setEntered(boolean b) {
		// this method does NOT function on KocCat
	}

	@Override
	public void checkEntered(Point contactPoint) {
		// this method does NOT function on KocCat
	}

	@Override
	public void checkExited(Point contactPoint) {
		// this method does NOT function on KocCat
	}

}
