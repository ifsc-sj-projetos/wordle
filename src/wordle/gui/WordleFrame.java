package wordle.gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WordleFrame extends JFrame {

	private final int WORD_LENGTH = 5;
	private final int CHANCES = 6;

	private Grid grid;
	private Keyboard keyboard;
	private JTextField inputField;

	private final int PADDING = 20;
	private final Font inputFont = new Font("Helvetica", Font.BOLD, 44);

	private boolean cleared = false;


	public WordleFrame() {
		setTitle("Wordle");
		setSize(500, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		ImageIcon favicon = new ImageIcon("resources/favicon.png");
		setIconImage(favicon.getImage());

		grid = new Grid(WORD_LENGTH, CHANCES);
		keyboard = new Keyboard(this);

		inputField = new JTextField("WORDLE", 10);
		inputField.setHorizontalAlignment(JTextField.CENTER);
		inputField.setBackground(Color.BLACK);
		inputField.setForeground(Color.WHITE);
		inputField.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		inputField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				clearTextOnce();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				clearTextOnce();
				inputField.setText(inputField.getText().toUpperCase());
			}

			@Override
			public void keyTyped(KeyEvent e) {
				clearTextOnce();
			}

			// Helper method to clear text once
			private void clearTextOnce() {
				if (!cleared && inputField.getText().equals("WORDLE")) {
					inputField.setText("");
					cleared = true;
				}
			}
		});

		inputField.setCaretColor(Color.BLACK);
		inputField.setFont(inputFont);

		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.BLACK);
		inputPanel.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
		inputPanel.add(inputField);


		add(grid, BorderLayout.CENTER);
		add(keyboard, BorderLayout.SOUTH);
		add(inputPanel, BorderLayout.NORTH);

	}

	public static void main(String[] args) {
		WordleFrame wordleGame = new WordleFrame();
		wordleGame.setVisible(true);

	}
}
