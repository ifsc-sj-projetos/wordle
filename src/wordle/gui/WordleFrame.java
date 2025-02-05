package wordle.gui;

import wordle.Util;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class WordleFrame extends JFrame {

	// variáveis do jogo
	private final int WORD_LENGTH = 5;
	private final int CHANCES = 6;

	// variáveis de conteúdo
	private final Grid grid;
	private final Keyboard keyboard;
	private final JTextField inputField;
	private final JLabel statusLabel;

	// variáveis de estilo
	private final int PADDING = 20;
	private final Font inputFont = new Font("Helvetica", Font.BOLD, 52);
	private final Font statusFont = new Font("Helvetica", Font.PLAIN, 16);

	// valor que controla o placeholder do TextField.
	private boolean cleared = false;

	// TESTE
	public int attempt = 0;

	public WordleFrame() {

		ImageIcon favicon = new ImageIcon("resources/favicon.png");
		setIconImage(favicon.getImage());

		setTitle("Wordle");
		setSize(500, 870);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		grid = new Grid(WORD_LENGTH, CHANCES);
		keyboard = new Keyboard();

		inputField = new JTextField("WORDLE", 10);
		inputField.setHorizontalAlignment(JTextField.CENTER);
		inputField.setBackground(Color.BLACK);
		inputField.setForeground(Color.WHITE);
		inputField.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		inputField.setCaretColor(Color.BLACK);
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

		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.BLACK);
		inputPanel.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
		inputPanel.add(inputField);
		inputPanel.add(statusLabel);

		add(grid, BorderLayout.CENTER);
		add(keyboard, BorderLayout.SOUTH);
		add(inputPanel, BorderLayout.NORTH);

	}

	// limpa o placeholder
	private void clearText() {
		if (!cleared && inputField.getText().equals("WORDLE")) {
			inputField.setText("");
			cleared = true;
		}
	}

	// checa o input do usuário quando o "Enter" é pressionado
	private void validateInput(int key) {
		if (key == 10) {
			String input = inputField.getText().replace(" ", "");
			boolean isAlpha = input.matches("[a-zA-Z]+");

			if (input.length() == WORD_LENGTH && isAlpha) {
				handleGuess(Util.strip(input));
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

	// impede que o usuário insira mais que 5 letras
	private void characterLimit() {
		String input = inputField.getText().replace(" ","");
		if (input.length() > WORD_LENGTH) {
			inputField.setText(input.substring(0,WORD_LENGTH));
		}
	}

	private void handleGuess(String guess) {
				Random random = new Random();
				char[] color = {'x', '%', 'o', 'x', 'x'};
				char[] feedback = {
						color[random.nextInt(color.length)],
						color[random.nextInt(color.length)],
						color[random.nextInt(color.length)],
						color[random.nextInt(color.length)],
						color[random.nextInt(color.length)],
				};

		grid.updateGrid(guess, feedback, attempt);
		keyboard.updateKeys(guess, feedback);

				if (attempt < CHANCES - 1) {
					attempt++;
				} else {
					inputField.setEnabled(false);
				}
	}
	public static void main(String[] args) {
		WordleFrame wordleGame = new WordleFrame();
		wordleGame.setVisible(true);
	}
}
