package wordle.game;

import wordle.gui.*;
import wordle.stats.*;

import javax.swing.*;

public class WordleGame {

    public int attempt = 0;
    private final int CHANCES = 6;
    private String answer;
    private final WordleFrame wordleFrame;
    private final GameStats stats;
    private final Input input;

    public WordleGame(String answer, WordleFrame wordleFrame, GameStats stats, Input input) {
        this.answer = answer;
        this.wordleFrame = wordleFrame;
        this.stats = stats;
        this.input = input;
    }

    public boolean isGameOver(boolean isWin) {
        if (attempt < CHANCES - 1 && !isWin) {
            attempt++;
        } else {
            return true;
        }
        return false;
    }

    public void endGame(boolean isWin) {
        SwingUtilities.invokeLater(() -> {input.setText("WORDLE");});
        input.setEnabled(false);

        stats.updateStats(isWin);
        String textStatus = "A palavra era " + answer.toUpperCase() + "!";

        StatsGui statsGui = new StatsGui(isWin, textStatus, wordleFrame, stats);
        statsGui.setVisible(true);
    }

    public boolean getChancesOver() {
        return attempt == CHANCES - 1;
    }

}


