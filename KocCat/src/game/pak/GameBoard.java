package game.pak;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import objects.pak.Ash;
import objects.pak.Casper;
import objects.pak.Dolly;
import objects.pak.Drawable;
import objects.pak.Food;
import objects.pak.Fruit;
import objects.pak.Ghost;
import objects.pak.KocCat;
import objects.pak.Poison;

@SuppressWarnings("serial")
public class GameBoard extends JPanel implements KeyListener {

	private Image background = new ImageIcon(getClass().getResource("background.jpg")).getImage();

	private final KocCat cat = new KocCat();
	public static final int BOARD_WIDTH = (int) (Game.GAME_WIDTH - Game.WIDTH_MARGIN);
	public static final int BOARD_HEIGHT = (int) (Game.GAME_HEIGHT - Game.HEIGHT_MARGIN);

	private final Food[] FOODS = new Food[Game.fruit + Game.poison];
	private final Ghost[] GHOSTS = new Ghost[Game.ghost];

	private static final String THEME_SONG = "game_music_new.wav";

	private Clip clip;
	private AudioInputStream inputStream;

	public static int runTime = 0;
	public static boolean ghostContact = false;

	public static boolean hardModeOn = false;

	public GameBoard() {
		fillGameObjects();
		if (GameMenu.getMusic())
			playThemeSong(THEME_SONG);
		setFocusable(true);
		addKeyListener(this);
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		Game.updateInfoBar();
		g.drawImage(background, 0, 0, BOARD_WIDTH + 200, BOARD_HEIGHT + 200, null); // Drawing game background

		for (Food food : FOODS) {
			food.draw(g);
			food.doAction();
			checkContact(food);
		}

		for (Ghost ghost : GHOSTS) {
			ghost.draw(g);
			ghost.doAction();
			checkContact(ghost);
		}
		cat.draw(g);
		cat.doAction();
		if (GameMenu.getMusic() && (GameBoard.ghostContact || KocCat.score < 0))
			clip.close();
		runTime += Game.PAUSE_TIME;

	}

	private void playThemeSong(String fileLocation) { // a method that handles playing music and looping it
		try {
			inputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir"), THEME_SONG));
			clip = AudioSystem.getClip();
			clip.open(inputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (Exception ex) {
			System.out.println("could not find music file");
		}
	}

	public void fillGameObjects() { // handles filling the arrays of game objects (food and ghosts)

		for (int i = 0; i < FOODS.length; i++) {
			if (i < Game.fruit)
				FOODS[i] = new Fruit();
			else
				FOODS[i] = new Poison();
		}

		for (int i = 0; i < GHOSTS.length; i++) {

			if (hardModeOn) {

				GHOSTS[i] = new Casper();

			} else {

				if (i < GHOSTS.length / 3)
					GHOSTS[i] = new Casper();
				else if (i < (2 * GHOSTS.length) / 3)
					GHOSTS[i] = new Dolly();
				else
					GHOSTS[i] = new Ash();
			}
		}

	}

	public void checkContact(Drawable object) { // checks if the cat entered the range of an object or, in case it was
												// already in an object, if it exited the object
		Point cPoint = cat.getContactPoint();
		if (!object.isEntered()) {
			object.checkEntered(cPoint);

		} else {
			object.checkExited(cPoint);
		}
	}

	// key handling

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			cat.setImage("image_down.png");
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			cat.setImage("image_up.png");
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			cat.setImage("image_right.png");
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			cat.setImage("image_left.png");
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
