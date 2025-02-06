package wordle.stats;

import java.util.Random;

public class GameStats {

    protected int gamesPlayed;
    protected int gamesWon;
    protected int victoryPercent;
    protected int streak;
    protected int bestStreak;

    public GameStats() {
        loadStats();
    }

    private void loadStats() {

        Random random = new Random();

        gamesPlayed = random.nextInt(50);
        gamesWon = random.nextInt(gamesPlayed);
        victoryPercent = (gamesWon / gamesPlayed) * 100;

        int a = random.nextInt(10);
        int b = random.nextInt(10);

        streak = Math.min(a, b);
        bestStreak = Math.max(a, b);

    }
}
