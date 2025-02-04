package wordle.gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Keyboard extends JPanel {

	private Map<Character, JButton> keys;

	public Keyboard(WordleFrame frame) {

		setLayout(new GridLayout(3, 9, 5, 5));
		keys = new HashMap<>();

		String keyboardLayout = "QWERTYUIOPASDFGHJKLZXCVBNM";
		for (char letter : keyboardLayout.toCharArray()) {
			JButton button = new JButton(String.valueOf(letter));
//			button.addActionListener(e -> frame.handleGuess(letter + ""));
			keys.put(letter, button);
			add(button);
		}

	}

	public void updateKeys(String guess, Map<Integer, Character> feedback) {
		System.out.println("update keyboard");
	}

}
