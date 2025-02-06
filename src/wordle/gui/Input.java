package wordle.gui;

import wordle.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input extends JPanel {

	private final int WORD_LENGTH;
	private final WordleFrame frame;

	private final JTextField inputField;
	private final JLabel statusLabel;
	private final Font statusFont = new Font("Helvetica", Font.PLAIN, 16);
	private final Font inputFont = new Font("Helvetica", Font.BOLD, 52);

	// valor que controla o placeholder do TextField.
	private boolean cleared = false;

	public Input(WordleFrame frame, int WORD_LENGTH) {
		this.WORD_LENGTH = WORD_LENGTH;
		this.frame = frame;

		inputField = new JTextField("WORDLE", 10);
		inputField.setHorizontalAlignment(JTextField.CENTER);

		inputField.setBackground(Color.BLACK);
		inputField.setForeground(Color.WHITE);
		inputField.setCaretColor(Color.BLACK);
		inputField.setBorder(BorderFactory.createLineBorder(Color.black, 2));

		inputField.setFont(inputFont);
		inputField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				clearText();
				validateInput(e.getKeyCode());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				clearText();
				inputField.setText(inputField.getText().toUpperCase());
				characterLimit();
			}

			@Override
			public void keyTyped(KeyEvent e) {
				clearText();
			}
		});

		statusLabel = new JLabel();
		statusLabel.setForeground(Color.RED);
		statusLabel.setFont(statusFont);

		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(20, 20, 20, 20));

		add(inputField);
		add(statusLabel);
	}

	private void clearText() {
		if (!cleared && inputField.getText().equals("WORDLE")) {
			inputField.setText("");
			cleared = true;
		}
	}

	private void validateInput(int key) {
		if (key == 10) {
			String input = inputField.getText().replace(" ", "");
			boolean isAlpha = input.matches("[a-zA-Z]+");

			if (input.length() == WORD_LENGTH && isAlpha) {
				frame.handleGuess(Util.strip(input));
				inputField.setText("");
			}

			if (isAlpha) {
				statusLabel.setText("");
			} else {
				statusLabel.setText("Apenas letras são permitidas.");
				inputField.setText("");
			}


		}
	}

	private void characterLimit() {
		String input = inputField.getText().replace(" ","");
		if (input.length() > WORD_LENGTH) {
			inputField.setText(input.substring(0, WORD_LENGTH));
		}
	}

	public void setEnabled(boolean bool) {
		inputField.setEnabled(bool);
	}

}
