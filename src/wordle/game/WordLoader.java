package wordle.game;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.List;

import org.json.*;

public class WordLoader {
    private List<Word> words; // alterado para armazenar objetos do tipo Word
    private String filePath; // caminho do arquivo

    public static void main(String[] args) {
        String filePath = "resources/words.json";
        WordLoader wordLoader = new WordLoader(filePath);
        String randomWord = wordLoader.getRandomWord();
        System.out.println("Palavra aleatória: " + randomWord);
    }

    public WordLoader(String filePath) {
        this.filePath = filePath;
        this.words = loadWords(filePath);
    }

    private List<Word> loadWords(String filePath) {

        List<Word> loadedWords = new ArrayList<>();
        try {
            // lê o conteúdo do arquivo JSON como uma String
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(content); // converte a String para um JSONArray

            // itera sobre o JSONArray e adiciona as palavras à lista
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject wordObject = jsonArray.getJSONObject(i);
                String word = wordObject.getString("key").toUpperCase();
                boolean isValid = wordObject.getBoolean("value");

                loadedWords.add(new Word(word, isValid));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedWords;
    }

    public String getRandomWord() {

        if (words.isEmpty()) {
            throw new IllegalStateException("A lista de palavras está vazia. Verifique o arquivo JSON.");
        }

        // verifica a validade das palavras
        List<Word> validWords = new ArrayList<>();
        for (Word word : words) {
            if (word.isValid()) {
                validWords.add(word);
            }
        }

        if (validWords.isEmpty()) {
            WordleGame.ranOutOfWords();
            throw new IllegalStateException("Não há mais palavras válidas disponíveis.");
        }

        Random random = new Random();
        Word selectedWord = validWords.get(random.nextInt(validWords.size()));

        // marca como false se a palavra for usada
        selectedWord.setValid(false);

        // atualiza o arquivo JSON com as modificações
        updateJsonFile();
		String asnwer = selectedWord.getWord();
		System.out.println(asnwer);

        return asnwer;
    }

    // metodo para atualizar o arquivo JSON
    private void updateJsonFile() {

        JSONArray updatedArray = new JSONArray();

        // itera sobre as palavras e as coloca de volta no JSONArray com a nova informação de 'value'
        for (Word word : words) {
            JSONObject wordObject = new JSONObject();
            wordObject.put("key", word.getWord());
            wordObject.put("value", word.isValid());
            updatedArray.put(wordObject);
        }

        try {
            Files.write(Paths.get(filePath), updatedArray.toString().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
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

        public void setValid(boolean valid) {
            isValid = valid;
        }

        // sobrescreve o metodo toString para exibir a palavra
        @Override
        public String toString() {
            return word; 
        }
    }
}
