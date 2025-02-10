package wordle.game;

//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import org.json.JSONArray;
//import org.json.JSONObject;

public class WordLoader {
//    private List<String> words;
//
//    public WordLoader(String filePath) {
//        this.words = loadWords(filePath);
//    }
//
//    private List<Word> loadWords(String filePath) {
//        List<Word> loadedWords = new ArrayList<>();
//        try {
//            // Lê o conteúdo do arquivo JSON como uma String
//            String content = new String(Files.readAllBytes(Paths.get(filePath)));
//            JSONArray jsonArray = new JSONArray(content); // Converte a String para um JSONArray
//
//            // Itera sobre o JSONArray e adiciona as palavras à lista
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                String word = jsonObject.getString("word");
//                boolean isValid = jsonObject.getBoolean("isValid");
//                loadedWords.add(new Word(word, isValid));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return loadedWords;
//    }
//
//    public String getRandomWord() {
//        if (words.isEmpty()) {
//            throw new IllegalStateException("A lista de palavras está vazia. Verifique o arquivo JSON.");
//        }
//
//        Random random = new Random();
//        while (true) {
//            int index = random.nextInt(words.size()); // Gera um índice aleatório
//            Word selectedWord = words.get(index); // Obtém a palavra correspondente ao índice
//            if (selectedWord.isValid()) { // Verifica se a palavra é válida
//                return selectedWord.getWord(); // Retorna a palavra válida
//            }
//            // Se a palavra não for válida, o loop continua e um novo índice é gerado
//        }
//    }
//
//    private static class Word {
//        private String word;
//        private boolean isValid;
//
//        public Word(String word, boolean isValid) {
//            this.word = word;
//            this.isValid = isValid;
//        }
//
//        public String getWord() {
//            return word;
//        }
//
//        public boolean isValid() {
//            return isValid;
//        }
//    }
//
//    public static void main(String[] args) {
//        String filePath = "wordle-java/resources/words.json"; // Caminho corrigido
//        WordLoader wordLoader = new WordLoader(filePath);
//        String randomWord = wordLoader.getRandomWord();
//        System.out.println("Palavra aleatória: " + randomWord);
//    }
}