package wordle.stats;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;

import java.util.Random;

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

            float percent = ((float) gamesWon / gamesPlayed) * 100;
            victoryPercent = (int) percent;

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

    /*private void loadStats() {

        Random random = new Random();

        gamesPlayed = random.nextInt(50);
        gamesWon = random.nextInt(gamesPlayed);
        float percent = ((float) gamesWon / gamesPlayed) * 100;
        victoryPercent = (int) percent;

        int a = random.nextInt(10);
        int b = random.nextInt(10);

        streak = Math.min(a, b);
        bestStreak = Math.max(a, b);

    }*/

    }

