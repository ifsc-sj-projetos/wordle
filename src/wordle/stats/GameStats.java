package wordle.stats;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org .json.*;

import java.util.Random;

public class GameStats {

    protected int gamesPlayed;
    protected int gamesWon;
    protected int victoryPercent;
    protected int streak;
    protected int bestStreak;

    //Cria o caminho para o arquivo stats.json
    private static final String FILE_PATH = "resources/stats.json";

    public GameStats() {
        loadStats();
    }

    private void loadStats() {

        //Acesso de objetos JSON para os parâmetros (Jogos jogados, Jogos vencidos, Sequência de vitórias e Melhor sequência)
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONObject json = new JSONObject(content);

            gamesPlayed = json.getInt("gamesPlayed");
            gamesWon = json.getInt("gamesWon");

            //Calcula a porcentagem de vitória do jogador
            float percent = ((float) gamesWon / gamesPlayed) * 100;
            victoryPercent = (int) percent;

            streak = json.getInt("streak");
            bestStreak = json.getInt("bestStreak");

        //Zera as variáveis em caso de exceção
        } catch (IOException e) {
            gamesPlayed = gamesWon = streak = bestStreak = 0;
        }
    }

    //Atualiza os status de quantidade de jogos jogados e vencidos, sequência e melhor sequência caso se aplique
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
            System.out.println("Erro na obtenção de status");
        }
    }

}

