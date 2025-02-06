package wordle.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;

public class Keyboard extends JPanel {

	private final HashMap<Character, JLabel>[] keys;
	private final HashSet<Character> keyHistory = new HashSet<>();

	private final JPanel[] rows;

	private final char green = 'o';
	private final char yellow = '%';
	private final char gray = 'x';

	// variáveis de estilo
	private final Font buttonFont = new Font("Helvetica", Font.BOLD, 16);
	private Color GREEN;
	private Color YELLOW;
	private Color DARK_GRAY;
	private Color WINNER_GRAY;

	public Keyboard() {

		setLayout(new GridLayout(3,1));
		setBackground(Color.BLACK);
		setSize(400, 700);
		setBorder(new EmptyBorder(20, 20, 20, 20));

		keys = new HashMap[3];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = new HashMap<>();
		}

		rows = new JPanel[3];
		for (int i = 0; i < rows.length; i++) {
			rows[i] = new JPanel();
			rows[i].setBackground(Color.BLACK);
		}

		String[] keyboardKeys = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
		for (int i = 0; i < keyboardKeys.length; i++) {
			for (char letter : keyboardKeys[i].toCharArray()) {

				JLabel button = new JLabel(String.valueOf(letter));

				button.setOpaque(true);
				button.setBackground(Color.GRAY);
				button.setForeground(Color.WHITE);
				button.setFont(buttonFont);

				button.setPreferredSize(new Dimension(30, 30));
				button.setHorizontalAlignment(SwingConstants.CENTER);
				button.setVerticalAlignment(SwingConstants.CENTER);

				keys[i].put(letter, button);
				rows[i].add(button);
			}
		}

        for (JPanel row : rows) {
            add(row);
        }

	}

	public void setColors(Color correct, Color positionRight, Color wrong, Color winner) {
		GREEN = correct;
		YELLOW = positionRight;
		DARK_GRAY = wrong;
		WINNER_GRAY = winner;
	}

	public void updateKeys(String guess, char[] feedback, int attempt, boolean isWin, boolean chancesOver) {

		for (int i = 0; i < guess.length(); i++) {
			keyHistory.add(guess.charAt(i));
		}


		if (isWin || chancesOver) {

			for (HashMap<Character, JLabel> kbRow : keys) {
				for (Character key : kbRow.keySet()) {

					JLabel keyLabel = kbRow.get(key);

					if (!keyHistory.contains(key)) {
						keyLabel.setBackground(WINNER_GRAY);

					} else {

						if (isWin && guess.contains(key.toString())) {
							keyLabel.setBackground(GREEN);
						}

						else if (!isWin && guess.contains(key.toString())) {
							int index = guess.indexOf(key);
							if (index != -1) {
								readFeedback(feedback[index], keyLabel);
							}
						}

					}
				}

			}

		} else {
			for (int j = 0; j < feedback.length; j++) {
				Character letter = guess.charAt(j);
				JLabel key = getKeyLabel(letter);
				readFeedback(feedback[j], key);
			}
		}
	}

	private JLabel getKeyLabel(Character letter) {
		for (HashMap<Character, JLabel> kbRow : keys) {
			if (kbRow.containsKey(letter)) {
				return kbRow.get(letter);
			}
		}
		return null;
	}

	private void readFeedback(char feedback, JLabel key) {
		if (key != null) {
			switch (feedback) {
				case green:
					key.setBackground(GREEN);
					break;
				case yellow:
					key.setBackground(YELLOW);
					break;
				case gray:
					key.setBackground(DARK_GRAY);
					break;
			}
		}
	}


	public void resetKeyboard() {
		for (HashMap<Character, JLabel> kbRow : keys) {
			for (Character key : kbRow.keySet()) {
				kbRow.get(key).setBackground(Color.GRAY);
			}
		}
	}


}
