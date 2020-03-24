package game.pak;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import exceptions.pak.InvalidPreconditionsException;
import objects.pak.KocCat;

public class Game {
	public static int fruit, poison, ghost;

	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final GameMenu menuFrame = new GameMenu();
	private static JFrame gameFrame;
	private static final JFrame gameOverFrame = new JFrame("Game Over");
	public static final int PAUSE_TIME = 10;
	public static final int INFO_BAR_MARGIN = 30;
	public static final double WIDTH_MARGIN = screenSize.getWidth() / 240,
			HEIGHT_MARGIN = screenSize.getHeight() / 30 + INFO_BAR_MARGIN;
	public static final int GAME_WIDTH = (int) screenSize.getHeight() - 100;
	public static final int GAME_HEIGHT = (int) screenSize.getHeight() - 100;

	public static JLabel score = new JLabel();
	public static JLabel time = new JLabel();

	/*
	 * THIS CODE IS MY OWN WORK. I DID NOT CONSULT TO ANY PROGRAM WRITTEN BY OTHER
	 * STUDENTS. I READ AND FOLLOWED THE GUIDELINE GIVEN IN THE PROGRAMMING
	 * ASSIGNMENT.
	 * NAME: Walid Baroudi.
	 */

	public static void main(String[] args) {
		menuFrame.setSize(200, 360);
		menuFrame.setResizable(false);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setLocation(screenSize.width / 2 - menuFrame.getSize().width / 2,
				screenSize.height / 2 - menuFrame.getSize().height / 2);
		showInstructions();

	}

	public static void showInstructions() {
		JFrame instructionsFrame = new JFrame("Instructions");
		final int DIALOG_WIDTH = (int) (GAME_WIDTH * 0.55), DIALOG_HEIGHT = (int) (GAME_HEIGHT * 0.75);
		instructionsFrame.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
		instructionsFrame.setLocation(screenSize.width / 2 - instructionsFrame.getSize().width / 2,
				screenSize.height / 2 - instructionsFrame.getSize().height / 2);
		instructionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instructionsFrame.setLayout(new GridLayout(15, 1));
		JLabel insLabel = new JLabel("Instructions:");
		insLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		instructionsFrame.add(insLabel);
		instructionsFrame.add(new JLabel("-Eat fruits, not mushrooms!"));
		instructionsFrame.add(new JLabel("-Let fruits get bigger to get more score, but don't let them die"));
		instructionsFrame.add(new JLabel("-Mushrooms become more poisonous with age"));
		instructionsFrame.add(new JLabel("-Avoid ghosts, there's 3 types of them and the can kill you instantly."));
		instructionsFrame.add(new JLabel("    1- Ash: moves horizontally back and forth"));
		instructionsFrame.add(new JLabel("    2- Dolly: moves vertically up and down"));
		instructionsFrame.add(new JLabel("    3- Casper: the scary one.. you never know how it moves."));
		instructionsFrame.add(new JLabel("-Game objects only affect you when you hit them head-on"));
		instructionsFrame.add(new JLabel("-Fruits need to be strictly on the cats mouth in order to be eaten."));
		instructionsFrame.add(new JLabel("-Medium difficulty speeds up the cat and the ghosts."));
		instructionsFrame.add(
				new JLabel("-Hard difficulty speeds up the cat and the ghosts, and makes all ghosts of type casper."));
		instructionsFrame.add(new JLabel("-If your score drops below zero, the cat dies"));
		instructionsFrame.add(new JLabel("-Have fun!"));
		JButton startGame = new JButton("Continue");
		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuFrame.setVisible(true);
				instructionsFrame.setVisible(false);

			}
		});
		instructionsFrame.add(startGame);
		instructionsFrame.setVisible(true);
	}

	public static void startGame() throws InvalidPreconditionsException { // method that starts the frame on which that
																			// game runs
		if (fruit < 1 || poison < 1 || ghost < 1)
			throw new InvalidPreconditionsException(); // an exceptions in case of entering invalid ghost, fruit, or
														// poison count.

		menuFrame.setVisible(false); // hiding the menu
		// setting up the game board frame.
		setGameFrame();
		gameFrame.setVisible(true);

		GameBoard board = new GameBoard();
		gameFrame.add(board);
		JPanel infoBar = new JPanel();
		setInfoBar(infoBar);

		gameFrame.add(infoBar, BorderLayout.NORTH);
		new Timer(PAUSE_TIME, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(GameBoard.ghostContact) && KocCat.score >= 0)
					board.repaint();
				else {

					gameOver();
				}
			}
		}).start();
		;

	}

	private static void setGameFrame() {
		gameFrame = new JFrame("KocCat");
		gameFrame.setSize(GAME_WIDTH, GAME_HEIGHT);
		gameFrame.setResizable(false);
		gameFrame.setLocation(screenSize.width / 2 - GAME_WIDTH / 2, screenSize.height / 2 - GAME_HEIGHT / 2);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void gameOver() { // method that sets up the frame that shows when the game is over.
		// setting up the Game Over frame
		setGameOverFrame();
		// adding "Game Over" label
		JLabel over = new JLabel("Game Over");
		over.setForeground(Color.ORANGE);
		over.setFont(new Font(Font.SERIF, Font.PLAIN, 55));
		over.setHorizontalAlignment(JLabel.CENTER);
		gameOverFrame.add(over, BorderLayout.NORTH);
		// adding stats to frame
		gameOverFrame.add(statsPanel(), BorderLayout.CENTER);
		// adding quit button
		JButton quit = new JButton("Quit");
		quit.setMaximumSize(new Dimension(30, 30));
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		quit.setBackground(Color.LIGHT_GRAY);
		gameOverFrame.add(quit, BorderLayout.SOUTH);

		gameOverFrame.setVisible(true); // displaying the Game Over frame
	}

	public static void setGameOverFrame() { // a method to setup the frame shown when the game ends.
		final int DIALOG_WIDTH = (int) (GAME_WIDTH * 0.75), DIALOG_HEIGHT = GAME_HEIGHT / 2;
		gameOverFrame.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
		gameOverFrame.setLocation(screenSize.width / 2 - gameOverFrame.getSize().width / 2,
				screenSize.height / 2 - gameOverFrame.getSize().height / 2);
		gameOverFrame.getContentPane().setBackground(Color.DARK_GRAY);
		gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static JPanel statsPanel() { // a method that sets up the statistics panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2, 30, 30));
		panel.setBackground(Color.DARK_GRAY);
		panel.add(finishLabel("Run time:"));
		panel.add(finishLabel(new TimerFormat(GameBoard.runTime).toString()));
		panel.add(finishLabel("Total fruits eaten:"));
		panel.add(finishLabel("" + KocCat.fruitsEaten));
		panel.add(finishLabel("High Score:"));
		panel.add(finishLabel("" + KocCat.highScore));
		return panel;
	}

	private static JLabel finishLabel(String text) { // method that creates stats labels
		JLabel label = new JLabel(text);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
		return label;

	}

	private static void setInfoBar(JPanel panel) { // method that sets up the top bar containing momentary runtime and
													// score
		panel.setLayout(new GridLayout(1, 2, 30, 0));
		Font infoFont = new Font(Font.SERIF, Font.PLAIN, 20);

		score.setHorizontalAlignment(JLabel.CENTER);
		score.setFont(infoFont);
		panel.add(score);

		time.setHorizontalAlignment(JLabel.CENTER);
		time.setFont(infoFont);
		panel.add(time);
	}

	public static void updateInfoBar() { // method that updates the info in the top info bar
		score.setText("" + KocCat.score);
		time.setText(new TimerFormat(GameBoard.runTime).toString());

	}

	private static class TimerFormat { // an inner class that shows the runtime in the proper form (e.g. 80000
										// millisecond ->
										// 1:20)
		private double time;

		public TimerFormat(double time) {
			this.time = time / 1000;
		}

		@Override
		public String toString() {
			double sec = time % 60;
			double min = time / 60;

			return (int) min + " : " + (int) sec;
		}

	}

}
