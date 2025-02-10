import wordle.gui.WordleFrame;
import wordle.stats.GameStats;

public class Main {
	public static void main(String[] args) {
		GameStats stats = new GameStats();

		WordleFrame wordleGame = new WordleFrame(stats);
		wordleGame.setVisible(true);

	}
}