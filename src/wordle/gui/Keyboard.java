package wordle.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Keyboard extends JPanel {

	private HashMap<Character, JLabel>[] keys;
	private JPanel[] rows;

	private final int PADDING = 20;

	public Keyboard(WordleFrame frame) {

		setLayout(new GridLayout(3,1));
		setBackground(Color.BLACK);
		setSize(400, 700);
		setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));

		keys = new HashMap[3];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = new HashMap<>();
		}

		rows = new JPanel[3];
		for (int i = 0; i < rows.length; i++) {
			rows[i] = new JPanel();
		}

		String[] keyboardKeys = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
		for (int i = 0; i < keyboardKeys.length; i++) {
			for (char letter : keyboardKeys[i].toCharArray()) {
				JLabel button = new JLabel(String.valueOf(letter));
				button.setBackground(Color.red);
//				button.addActionListener(e -> frame.handleGuess(letter + ""));
				keys[i].put(letter, button);
				rows[i].add(button);
			}
		}

        for (JPanel row : rows) {
            add(row);
        }

	}

	public void updateKeys(String guess, Map<Integer, Character> feedback) {
		System.out.println("update keyboard");
	}

}
