package _00_IntroToStacks;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

public class _02_TextUndoRedo implements KeyListener {
    /* 
     * Create a JFrame with a JPanel and a JLabel.
     * 
     * Every time a key is pressed, add that character to the JLabel. It should
     * look like a basic text editor.
     * 
     * Make it so that every time the BACKSPACE key is pressed, the last
     * character is erased from the JLabel.
     * 
     * Save that deleted character onto a Stack of Characters.
     * 
     * Choose a key to be the Undo key. Make it so that when that key is
     * pressed, the top Character is popped  off the Stack and added back to
     * the JLabel.
     */
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    Stack<Character> chars = new Stack<>();

    public static void main(String[] args) {
        _02_TextUndoRedo thing = new _02_TextUndoRedo();
        thing.configureObjects();
    }

     public void configureObjects(){
        panel.add(label);
        frame.add(panel);
        frame.addKeyListener(this);
        frame.pack();
        frame.setVisible(true);
     }

    public void updateLabel(){
        StringBuilder smoothString = new StringBuilder();
        for (Character c : chars ){
            smoothString.append(c);
        }
        label.setText(smoothString.toString());
        System.out.println(chars);
        frame.pack();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
            if (!chars.isEmpty()){
                chars.pop();
            }
            updateLabel();
        }
        else {
            chars.push(e.getKeyChar());
            updateLabel();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
