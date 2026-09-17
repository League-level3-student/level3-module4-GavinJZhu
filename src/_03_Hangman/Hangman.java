package _03_Hangman;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

public class Hangman implements KeyListener{
    //member vars
    Stack<String> stackOfWords = new Stack<>();
    int lives = 10;
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel UI = new JLabel();
    StringBuilder wordStatus = new StringBuilder();
    String word;

    public Hangman (){
        //asks and selects the amount of words the user wants to play
        String numOfWords = JOptionPane.showInputDialog("Enter the number of words to guess (1-100)");
        selectWords(Integer.parseInt(numOfWords));

        //makes the game ui
        panel.add(UI);
        frame.add(panel);
        panel.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel.requestFocusInWindow();

        //game start
        playAllWords();
    }

    static void main() {
        new Hangman();
    }

    void selectWords(int quantity){
        //selects a random word however many times is queried by the user
        //then pushes the words to a stack
        for (int i = 0; i < quantity; i++) {
            String randomWord = Utilities.readRandomLineFromFile("dictionary.txt");
            if (!stackOfWords.contains(randomWord)) {
                stackOfWords.push(randomWord);
            }
            //if word is already in stack, move on
            else{
                i -= 1;
            }
        }
    }

    //this method gets called once, and what happens in it happens the whole time
    public void playAllWords(){
        //calls the code inside for each word in the stack
        for (int i = stackOfWords.size(); i > 0; i--) {
            word = stackOfWords.pop();
            playOneWord(word);
            JOptionPane.showMessageDialog(null, "You guessed the hidden word!");

            //resets game for next word
            wordStatus = new StringBuilder();
            lives = 10;
        }

        //winning message if the user manages to guess all words
        JOptionPane.showMessageDialog(null, "You won the game!");
        System.exit(0);
    }

    public void playOneWord(String word){
        //sets the word status in the game to have underscores for however many letters there are
        wordStatus.repeat("_", word.length());

        //while word isnt finished, set gameplay ui to whatever the status of the game is
        while (!isWordFinished(wordStatus.toString())){
            UI.setText(wordStatus +"  Lives: "+lives);
            frame.pack();
        }
        UI.setText(wordStatus +"  Lives: "+lives);
    }

    //checks if word is guessed or not
    public boolean isWordFinished(String wordStatus){
        return !wordStatus.contains("_");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        //iterates through all characters of word and checks if there are
        //matches with the typed key
        boolean letterPresent = false;
        for (int i = 0; i < wordStatus.length(); i++) {
            if (word.charAt(i) == e.getKeyChar()){
                wordStatus.setCharAt(i, e.getKeyChar());
                letterPresent = true;
            }
        }

        //if guessed letter isn't in word, subtract lives by 1
        if (!letterPresent){
            lives -= 1;
            if (lives == 0){
                //if lives is 0, means the user lost
                JOptionPane.showMessageDialog(null, "Ran out of lives. The hidden word was: "+word);
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
