package wordle.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;


public class WordLoader {
    private List<String> key;

    public static void main(String[] args) {
        String filePath = "/home/aluno/IdeaProjects/wordle-java/resources/words.json"; // Caminho corrigido
        WordLoader wordLoader = new WordLoader(filePath);
        String randomWord = wordLoader.getRandomWord();
        System.out.println("Palavra aleatória: " + randomWord);
    }

    public WordLoader(String filePath) {
        this.key = loadWords(filePath);
    }

    private List<String> loadWords(String filePath) {
        List<String> loadedWords = new ArrayList<>();
        try {
            // Lê o conteúdo do arquivo JSON como uma String
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(content); // Converte a String para um JSONArray

            // Itera sobre o JSONArray e adiciona as palavras à lista
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject wordObject = jsonArray.getJSONObject(i);
                String word = wordObject.getString("key").toUpperCase();
                boolean isValid = wordObject.getBoolean("value");

                loadedWords.add(String.valueOf(new Word(word, isValid)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedWords;
    }


    public String getRandomWord() {
        if (key.isEmpty()) {
            throw new IllegalStateException("A lista de palavras está vazia. Verifique o arquivo JSON.");
        }

        Random random = new Random();

		String answer = key.get(random.nextInt(key.size()));
		System.out.println(answer);
        return answer;
    }

    private static class Word {
        private String word;
        private boolean isValid;

        public Word(String word, boolean isValid) {
            this.word = word;
            this.isValid = isValid;
        }

        public String getWord() {
            return word;
        }

        public boolean isValid() {
            return isValid;
        }

        // Sobrescreve o método toString para exibir a palavra
        @Override
        public String toString() {
            return word; // Retorna a palavra como string
        }
    }
    }


