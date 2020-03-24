package objects.pak;

import java.awt.Graphics;
import java.awt.Point;

public interface Drawable {

	public void draw(Graphics gr);

	public double getWidth();

	public double getHeight();

	public int getX();

	public int getY();

	public int randomizer(); // randomizes locations

	public void doAction();

	public Point getContactPoint(); // gets the point where the object contacts the cat, or cat contacts object

	/*
	 * the methods below are implemented as such to handle all cases of contact
	 * between KocCat and other objects, including the case where the cat enters the
	 * range of a poison but the damage does not kill it, so it counts the damage of
	 * the poison one time instead of counting it continuously as long as the cat is
	 * within the poison
	 */

	public boolean isEntered();

	public void setEntered(boolean b);

	public void checkEntered(Point contactPoint);

	public void checkExited(Point contactPoint);
}
