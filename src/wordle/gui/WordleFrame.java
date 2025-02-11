package wordle.gui;

import wordle.game.*;
import wordle.stats.*;

import javax.swing.*;
import java.awt.*;

public class WordleFrame extends JFrame {

	// variáveis do jogo
	private final int WORD_LENGTH = 5;
	private final int CHANCES = 6;

	private final GameStats stats;

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

	public WordleFrame(GameStats stats) {
		this.stats = stats;

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

		boolean chancesOver = attempt == CHANCES - 1;
		GuessResult result = new GuessResult(guess, "papel");
		char[] feedback = result.feedback;
		boolean isWin = result.isRight;


		grid.updateGrid(guess, feedback, attempt, isWin);
		keyboard.updateKeys(guess, feedback, attempt, isWin, chancesOver);

		if (attempt < CHANCES - 1 && !isWin) {
			attempt++;
		} else {
			SwingUtilities.invokeLater(() -> {input.setText("WORDLE");});
			input.setEnabled(false);
			showStatsGui(isWin, "A palavra era " + guess.toUpperCase() + "!", stats);

		}
	}

	public void resetGame() {
		attempt = 0;
		grid.resetGrid();
		keyboard.resetKeyboard();
		input.setEnabled(true);
		input.cleared = false;
	}

	private void showStatsGui(boolean isWin, String answer, GameStats stats) {
		stats.updateStats(isWin);

		StatsGui statsGui = new StatsGui(isWin, answer, this, stats);
		statsGui.setVisible(true);
	}
}
