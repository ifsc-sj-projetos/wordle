package wordle.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class WordleFrame extends JFrame {

	private final int WORD_LENGTH = 5;
	private final int CHANCES = 6;

	private Grid grid;
	private Keyboard keyboard;
	private JTextField inputField;

	private final int PADDING = 20;
	private final Font input = new Font("Helvetica", Font.BOLD, 44);


	public WordleFrame() {
		setTitle("Wordle");
		setSize(500, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		ImageIcon favicon = new ImageIcon("resources/favicon.png");
		setIconImage(favicon.getImage());

		grid = new Grid(WORD_LENGTH, CHANCES);
		keyboard = new Keyboard(this);

		inputField = new JTextField("WORDLE", 5);
		inputField.setHorizontalAlignment(JTextField.CENTER);
		inputField.setBackground(Color.BLACK);
		inputField.setForeground(Color.WHITE);
		inputField.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		inputField.setCaretColor(Color.BLACK);
		inputField.setFont(input);


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
