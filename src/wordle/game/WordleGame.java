package wordle.game;

import wordle.stats.GameStats;

import java.util.*;

public class WordleGame {

    int attempts = 0;
    int chances = 6;
    String rightWord;
    boolean gameOver;
    private List<GuessResult> guessHistory;

    public WordleGame(String rightWord) {
        this.rightWord = rightWord.toUpperCase();
        this.guessHistory = new ArrayList<>();
    }

    public void handleGuess(String guess) {

        boolean chancesOver = attempts == chances - 1;
        GuessResult result = new GuessResult(guess, rightWord);
        guessHistory.add(result);

        boolean isWin = result.isRight;

        if (attempts < chances - 1 && !isWin) {
            attempts++;

        } else {
            gameOver = true;
        }
    }

    private boolean endGame(GameStats stats, boolean isWin) {
        stats.updateStats(isWin);
        return gameOver;
    }
}


