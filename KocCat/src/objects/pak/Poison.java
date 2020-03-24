package objects.pak;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import game.pak.GameBoard;

public class Poison extends Food {

	public int currentXpos = randomizer(), currentYpos = randomizer();
	private String resource = "small_poison.png";
	private Image poisonImage = new ImageIcon(getClass().getResource(resource)).getImage();
	public Point contactPoint = new Point();
	int currentWidth = FOOD_WIDTH / 3;

	@Override
	public void draw(Graphics gr) {
		contactPoint.setLocation(getX(), getY());
		if (age >= DECAY_AGE)
			setImage("big_poison2.png");
		gr.drawImage(poisonImage, (int) (currentXpos - getWidth() / 2), (int) (currentYpos - getHeight() / 2),
				currentWidth, currentWidth, null);

	}

	@Override
	public double getWidth() {

		return currentWidth;
	}

	@Override
	public double getHeight() {

		return currentWidth;
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
		if (GameBoard.runTime % 170 == 0 && age < DECAY_AGE)
			currentWidth += 1;
		if (GameBoard.runTime % 1000 == 0) {
			age++;
		}
	}

	@Override
	public void consumed() {
		KocCat.score = KocCat.score - age * 10;

	}

	@Override
	public int getAge() {

		return age;
	}

	@Override
	public void setImage(String resource) {
		poisonImage = new ImageIcon(getClass().getResource(resource)).getImage();
	}

	@Override
	public void doAction() {
		grow();
	}

	public Point getContactPoint() {
		return this.contactPoint;
	}



}
