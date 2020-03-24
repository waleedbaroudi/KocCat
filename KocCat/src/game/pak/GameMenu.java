package game.pak;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import exceptions.pak.InvalidPreconditionsException;
import objects.pak.Ghost;
import objects.pak.KocCat;

@SuppressWarnings("serial")
public class GameMenu extends JFrame {
	private static final int MEDIUM_MODE_CAT_PACE = 3;
	private static final int MEDIUM_MODE_GHOST_PACE = 2;
	private static final int HARD_MODE_CAT_PACE = 4;
	private static final int HARD_MODE_GHOST_PACE = 3;
	private JLabel diffLabel, foodLabel, poisonLabel, ghostLabel, musicLable;
	private JTextField foodInput, poisonInput, ghostInput;
	private JComboBox<String> difficulty;
	private static JCheckBox music;
	private JButton confirm;

	public GameMenu() {

		setLayout(new FlowLayout());

		add(new JLabel("                                              ")); // separator
		diffLabel = new JLabel("Difficulty:");
		add(diffLabel);
		difficulty = new JComboBox<>();
		difficulty.addItem("Custom");
		difficulty.addItem("Medium");
		difficulty.addItem("Hard");
		difficulty.addItemListener(menuListener());
		add(difficulty);
		add(new JLabel("                                        ")); // separator

		foodLabel = new JLabel("Food Count:");
		foodInput = new JTextField(15);
		foodInput.setHorizontalAlignment(JTextField.CENTER);
		add(foodLabel);
		add(foodInput);

		poisonLabel = new JLabel("poison Count:");
		poisonInput = new JTextField(15);
		poisonInput.setHorizontalAlignment(JTextField.CENTER);
		add(poisonLabel);
		add(poisonInput);

		ghostLabel = new JLabel("ghost Count:");
		ghostInput = new JTextField(15);
		ghostInput.setHorizontalAlignment(JTextField.CENTER);
		add(ghostLabel);
		add(ghostInput);
		add(new JLabel("                                      ")); // separator

		musicLable = new JLabel("in-game music:");
		music = new JCheckBox();
		music.addItemListener(menuListener());
		add(musicLable);
		add(music);

		add(new JLabel("                                      ")); // separator
		confirm = new JButton("Start Game");
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Game.fruit = Integer.parseInt(foodInput.getText());
					Game.poison = Integer.parseInt(poisonInput.getText());
					Game.ghost = Integer.parseInt(ghostInput.getText());
					Game.startGame();
				} catch (InvalidPreconditionsException ex) {
					JOptionPane.showMessageDialog(null, "input has to be at least 1", "Invalid input",
							JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "input has to be a number", "Invalid input",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(confirm);
	}

	public static boolean getMusic() {
		return music.isSelected();
	}

	public void setMediumDif() {
		KocCat.pace = MEDIUM_MODE_CAT_PACE;
		poisonInput.setText("6");
		Ghost.pace = MEDIUM_MODE_GHOST_PACE;
	}

	public void setHardDif() {
		KocCat.pace = HARD_MODE_CAT_PACE;
		poisonInput.setText("8");
		Ghost.pace = HARD_MODE_GHOST_PACE;
		GameBoard.hardModeOn=true;
	}

	public void setCustomDif() {
		foodInput.setEditable(true);
		poisonInput.setEditable(true);
		ghostInput.setEditable(true);
	}

	private ItemListener menuListener() {
		ItemListener menuListener = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getSource() == difficulty) {

					switch (difficulty.getSelectedIndex()) {
					case 0:
						setCustomDif();
						break;
					case 1:
						setMediumDif();
						break;
					case 2:
						setHardDif();
						break;

					}
				}

			}
		};
		return menuListener;
	}

}
