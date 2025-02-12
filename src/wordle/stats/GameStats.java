package wordle.stats;

import java.io.IOException;
import java.nio.file.*;
import org .json.*;

public class GameStats {

    protected int gamesPlayed;
    protected int gamesWon;
    protected int victoryPercent;
    protected int streak;
    protected int bestStreak;

    private static final String FILE_PATH = "resources/stats.json";

    public GameStats() {
        loadStats();
    }

    private void loadStats() {

        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONObject json = new JSONObject(content);

            gamesPlayed = json.getInt("gamesPlayed");
            gamesWon = json.getInt("gamesWon");
			victoryPercent = getVictoryPercent();
            streak = json.getInt("streak");
            bestStreak = json.getInt("bestStreak");

        } catch (IOException e) {
            gamesPlayed = gamesWon = streak = bestStreak = 0;
        }
    }
    
    public void updateStats(boolean won) {
        gamesPlayed++;
        if (won) {
            gamesWon++;
            streak++;
            if (streak > bestStreak) {
                bestStreak = streak;
            }

        } else {
            streak = 0;
        }

		victoryPercent = getVictoryPercent();
        saveStats();
    }

    private void saveStats() {
        try {
            JSONObject json = new JSONObject();
            json.put("gamesPlayed", gamesPlayed);
            json.put("gamesWon", gamesWon);
            json.put("streak", streak);
            json.put("bestStreak", bestStreak);
            Files.write(Paths.get(FILE_PATH), json.toString().getBytes());

        } catch (IOException e) {
            System.out.println("teste");
        }
    }

	private int getVictoryPercent() {
		float percent = ((float) gamesWon / gamesPlayed) * 100;
		return  (int) percent;
	}

}

