package wordle.game;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GuessResult {
    private String guess;
    private String answer;
    public char[] feedback;
    public boolean isRight;

    // construtor
    public GuessResult(String guess, String answer) {
        this.guess = guess;
        this.answer = answer;
        this.feedback = setFeedback(); // Gera o feedback
        this.isRight = isRightAnswer(); // Verifica se a resposta está correta

        System.out.println(Arrays.toString(feedback));
        System.out.println(isRight);
    }

    // Método privado para gerar o feedback
    private char[] setFeedback() {

        char[] feedback = new char[5]; // Array para armazenar o feedback
        for (int i = 0; i < 5; i++) {
            char guessChar = guess.charAt(i);
            char answerChar = answer.charAt(i);

            if (guessChar == answerChar) {
                feedback[i] = 'o'; // Letra correta na posição correta
            } else if (answer.contains(String.valueOf(guessChar))) {
                feedback[i] = '%'; // Letra correta na posição errada
            } else {
                feedback[i] = 'x'; // Letra não está na palavra correta
            }
        }
        return feedback;
    }

    //  verifica se todos os valores do array feedback indicam acertos 'z'.
    private Boolean isRightAnswer() {
        for (char v : feedback) {
            if (v != 'o') {
                return false; // se algum feedback não for 'z', a resposta está errada
            }
        }
        return true; // todos os feedbacks são 'z', a resposta está correta
    }

    // Método getter para isRight
    public Boolean getIsRightAnswer() {
        return isRight;
    }

    // método getter para feedback
    public char[] getFeedback() {
        return feedback;
    }
}
