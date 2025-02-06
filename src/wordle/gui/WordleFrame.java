package wordle.gui;

import wordle.stats.StatsGui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class WordleFrame extends JFrame {

	// variáveis do jogo
	private final int WORD_LENGTH = 5;
	private final int CHANCES = 6;

	// variáveis de conteúdo
	private final Grid grid;
	private final Keyboard keyboard;
	private final Input input;

	// variáveis de cor
	private final Color GREEN = new Color(1,154,1);
	private final Color YELLOW = new Color(255,196,37);
	private final Color DARK_GRAY = new Color(75,75,75);
	private final Color WINNER_GRAY = new Color(30,30,30);


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

		grid = new Grid(WORD_LENGTH, CHANCES, 5);
		grid.setColors(GREEN, YELLOW, DARK_GRAY, WINNER_GRAY);

		keyboard = new Keyboard();
		keyboard.setColors(GREEN, YELLOW, DARK_GRAY, WINNER_GRAY);

		input = new Input(this, WORD_LENGTH);

		add(grid, BorderLayout.CENTER);
		add(keyboard, BorderLayout.SOUTH);
		add(input, BorderLayout.NORTH);

	}

	public void handleGuess(String guess) {
				Random random = new Random();
				char[] color = {'x', '%', 'o', 'x', 'x'};
				char[] feedback = {
						color[random.nextInt(color.length)],
						color[random.nextInt(color.length)],
						color[random.nextInt(color.length)],
						color[random.nextInt(color.length)],
						color[random.nextInt(color.length)],
				};
				boolean[] bools = {true, false, false, false, false};
				boolean isWin = bools[random.nextInt(bools.length)];
				boolean chancesOver = attempt == CHANCES - 1;

		grid.updateGrid(guess, feedback, attempt, isWin);
		keyboard.updateKeys(guess, feedback, attempt, isWin, chancesOver);

				if (attempt < CHANCES - 1 && !isWin) {
					attempt++;
				} else {
					input.setEnabled(false);
					showStatsGui(isWin, "A palavra era " + guess.toUpperCase() + "!");
				}
	}

	public void resetGame() {
		attempt = 0;
		grid.resetGrid();
		keyboard.resetKeyboard();
		input.setEnabled(true);
	}

	private void showStatsGui(boolean isWin, String answer) {
		StatsGui statsGui = new StatsGui(isWin, answer, this);
		statsGui.setVisible(true);
	}

	public static void main(String[] args) {
		WordleFrame wordleGame = new WordleFrame();
		wordleGame.setVisible(true);
	}
}
