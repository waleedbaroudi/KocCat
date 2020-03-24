package objects.pak;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import game.pak.GameBoard;

public class Fruit extends Food {

	public int currentXpos = randomizer(), currentYpos = randomizer();
	public static final int TURN_AGE = 5;
	private String resource = "small_fruit.png";
	private Image fruitImage = new ImageIcon(getClass().getResource(resource)).getImage();
	public Point contactPoint = new Point();

	int currentWidth = FOOD_WIDTH / 3;

	@Override
	public void draw(Graphics gr) {
		contactPoint.setLocation(getX(), getY());
		if (age >= TURN_AGE)
			setImage("big_fruit.png");
		gr.drawImage(fruitImage, (int) (currentXpos - getWidth() / 2), (int) (currentYpos - getHeight() / 2),
				currentWidth, currentWidth, null);
	}

	@Override
	public double getWidth() { // TODO: 2 S E C O N D S D I F F E R E N C E I N G R O W

		return FOOD_WIDTH;
	}

	@Override
	public double getHeight() {

		return FOOD_WIDTH;
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
	public void grow() {
		if (getAge() == DECAY_AGE)
			reset();
		if (GameBoard.runTime % 170 == 0)
			currentWidth += 1;
		if (GameBoard.runTime % 1000 == 0) {
			age++;

		}
	}

	@Override
	public void consumed() {
		KocCat.score = KocCat.score + age * 5;
		KocCat.highScore = KocCat.highScore + age * 5;
		KocCat.fruitsEaten++;
		reset();
	}

	@Override
	public int getAge() {

		return age;
	}

	@Override
	public void setImage(String resource) {
		fruitImage = new ImageIcon(getClass().getResource(resource)).getImage();
	}

	public void reset() {
		age = INITIAL_AGE;
		currentWidth = FOOD_WIDTH / 3;
		currentXpos = randomizer();
		currentYpos = randomizer();
		setImage("small_fruit.png");
	}

	@Override
	public void doAction() {
		grow();
	}

	public Point getContactPoint() {
		return this.contactPoint;
	}

}
