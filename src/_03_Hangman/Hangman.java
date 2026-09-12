package _03_Hangman;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

public class Hangman {
    Stack<String> stackOfWords = new Stack<>();
    int lives = 10;
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel UI = new JLabel();

    public Hangman (){
        String numOfWords = JOptionPane.showInputDialog("Enter the number of words to guess (1-100)");

        selectWords(Integer.parseInt(numOfWords));
        //System.out.println(stackOfWords);

        panel.add(UI);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        playAllWords();

    }

    static void main() {
        new Hangman();
    }

    void selectWords(int quantity){
        for (int i = 0; i < quantity; i++) {
            String randomWord = Utilities.readRandomLineFromFile("dictionary.txt");
            if (!stackOfWords.contains(randomWord)) {
                stackOfWords.push(randomWord);
            }
            else{
                i -= 1;
            }
        }
    }

    public void playAllWords(){
        for (int i = 0; i < stackOfWords.size(); i++) {
            playOneWord(stackOfWords.pop());
        }
    }

    public void playOneWord(String word){
        StringBuilder wordStatus = new StringBuilder();
        wordStatus.repeat("_", word.length());
        UI.setText(wordStatus +"  Lives: "+lives);

        while (!isWordFinished(wordStatus.toString())){
            wordStatus = new StringBuilder(updateWordStatus(wordStatus, word));
            UI.setText(wordStatus +"  Lives: "+lives);
        }

    }

    public boolean isWordFinished(String wordStatus){
        return wordStatus.contains("_");
    }
    public String updateWordStatus(StringBuilder wordStatus, String word){
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //iterates through all characters of word and checks if there are
                //matches with the typed key
                for (int i = 0; i < wordStatus.length(); i++) {
                    if (word.charAt(i) == e.getKeyChar()){
                        wordStatus.setCharAt(i, e.getKeyChar());
                    }
                }
                System.out.println(e);
            }
        });
        return wordStatus.toString();
    }
}
