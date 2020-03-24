package objects.pak;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import game.pak.GameBoard;

public abstract class Ghost implements Drawable {

	public final int GHOST_WIDTH = GameBoard.BOARD_WIDTH / 12;
	public static int pace= 1; //speed of the ghost
	private boolean isEntered = false;

	public abstract void setImage(String resource);

	public void checkEntered(Point contactPoint) {
		double contactRange = this.getWidth() / 2;
		double catX = contactPoint.getX();
		double catY = contactPoint.getY();
		double objectX = this.getContactPoint().getX();
		double objectY = this.getContactPoint().getY();
		if (catX > objectX - contactRange && catX < objectX + contactRange && catY > objectY - contactRange
				&& catY < objectY + contactRange) {
			GameBoard.ghostContact = true;
			setEntered(true);
		}
	}

	@Override
	public void checkExited(Point contactPoint) {
		// checks if the cat exited an object
		double contactRange = this.getWidth() / 2;
		double catX = contactPoint.getX();
		double catY = contactPoint.getY();
		double objectX = this.getContactPoint().getX();
		double objectY = this.getContactPoint().getY();
		if (!(catX > objectX - contactRange && catX < objectX + contactRange && catY > objectY - contactRange
				&& catY < objectY + contactRange)) {
			this.setEntered(false);
		}

	}

	public boolean isEntered() {
		return isEntered;
	}

	public void setEntered(boolean b) {
		isEntered = b;
	}

	public int randomizer(String dimension) { // Randomizes initial position
		Random rand = new Random();
		int random;

		if (dimension == "width") {
			do {
				random = rand.nextInt((int) (GameBoard.BOARD_WIDTH - GHOST_WIDTH));
			} while (random > KocCat.INITIAL_X_POS && random < KocCat.INITIAL_X_POS + KocCat.CAT_WIDTH);
		} else {
			do {
				random = rand.nextInt((int) (GameBoard.BOARD_HEIGHT - GHOST_WIDTH));
			} while (random > KocCat.INITIAL_Y_POS && random < KocCat.INITIAL_Y_POS + KocCat.CAT_WIDTH);
		}
		return random > GHOST_WIDTH ? random : GHOST_WIDTH;
	}

	@Override
	public int randomizer() {
		return 0;
	}

	@Override
	public abstract void draw(Graphics gr);

}
