package objects.pak;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import game.pak.GameBoard;

public abstract class Food implements Drawable {
	public final int INITIAL_AGE = 1;
	public static final int DECAY_AGE = 10;
	public final int FOOD_WIDTH = GameBoard.BOARD_WIDTH / 13;
	protected double rate = 10;
	protected int age = INITIAL_AGE;
	private boolean isEntered = false;

	@Override
	public abstract void draw(Graphics gr);

	public abstract void grow();

	public abstract void consumed();

	public abstract int getAge();

	public boolean isEntered() {
		return isEntered;
	}

	public void setEntered(boolean b) {
		isEntered = b;
	}

	@Override
	public void checkEntered(Point contactPoint) {
		double contactRange = this.getWidth() /2;
		double catX = contactPoint.getX();
		double catY = contactPoint.getY();
		double objectX = this.getContactPoint().getX();
		double objectY = this.getContactPoint().getY();
		if (catX > objectX - contactRange && catX < objectX + contactRange && catY > objectY - contactRange
				&& catY < objectY + contactRange) {
			consumed();
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

	@Override
	public int randomizer() { // Randomizes initial position
		Random rand = new Random();
		int random = rand.nextInt((int) (GameBoard.BOARD_WIDTH - FOOD_WIDTH));
		return random > FOOD_WIDTH ? random : FOOD_WIDTH;
	}

	public abstract void setImage(String resource);

}
