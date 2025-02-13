import wordle.game.WordLoader;
import wordle.gui.WordleFrame;
import wordle.stats.GameStats;

public class Main {
	public static void main(String[] args) {

		String filePath = "resources/words.json";
		WordLoader wordLoader = new WordLoader(filePath);
		GameStats stats = new GameStats();

		WordleFrame wordleGame = new WordleFrame(stats, wordLoader);
		wordleGame.setVisible(true);

	}
}