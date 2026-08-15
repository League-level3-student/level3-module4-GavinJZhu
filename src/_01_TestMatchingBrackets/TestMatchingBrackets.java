package _01_TestMatchingBrackets;

import java.util.Stack;

public class TestMatchingBrackets {
    /*
     * Use a Stack to complete the method for checking if every opening bracket
     * has a matching closing bracket
     */
    public static boolean doBracketsMatch(String b) {
        Stack<Character> chars = new Stack<>();
        char[] charsOfString = b.toCharArray();
        if (charsOfString[0] == '}'){
            return false;
        }
        for (char character : charsOfString){
            if (character == '{') {
                chars.push('a');
            }
            else if (character == '}') {
                chars.pop();
            }
        }
        return chars.isEmpty();
    }
}