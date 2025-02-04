package wordle.gui;

import javax.swing.*;
import java.awt.*;

public class WordleFrame extends JFrame {

	private final int WORD_LENGTH = 5;
	private final int CHANCES = 6;

	private Grid grid;
	private Keyboard keyboard;
	private JTextField inputField;

	public WordleFrame() {
		setTitle("Wordle");
		setSize(500, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		ImageIcon favicon = new ImageIcon("resources/favicon.png");
		setIconImage(favicon.getImage());

		grid = new Grid(WORD_LENGTH, CHANCES);
		keyboard = new Keyboard(this);

		inputField = new JTextField(5);
		inputField.setHorizontalAlignment(JTextField.CENTER);
		JButton submitButton = new JButton("Enter");

		JPanel inputPanel = new JPanel();
		inputPanel.add(inputField);
		inputPanel.add(submitButton);


		add(grid, BorderLayout.CENTER);
		add(keyboard, BorderLayout.SOUTH);
		add(inputPanel, BorderLayout.NORTH);

//		pack();

	}

	public static void main(String[] args) {
		WordleFrame wordleGame = new WordleFrame();
		wordleGame.setVisible(true);

	}
}
