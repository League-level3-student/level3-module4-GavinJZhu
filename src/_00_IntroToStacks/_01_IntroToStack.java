package _00_IntroToStacks;

import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

public class _01_IntroToStack {

    public static void main(String[] args) {

        // 1. Create a Stack of Doubles
        //    Don't forget to import the Stack class
        Stack<Double> doubles = new Stack<>();
        // 2. Use a loop to push 100 random doubles between 0 and 100 to the Stack.
        for (int i = 0; i<100; i++){
            Random ran = new Random();
            doubles.push(ran.nextDouble(100));
        }
        // 3. Ask the user to enter in two numbers between 0 and 100, inclusive. 
        String one = JOptionPane.showInputDialog("Enter in a number between 0-100");
        Double firstNumber = Double.valueOf(one);

        String two = JOptionPane.showInputDialog("Enter in another number between 0-100");
        Double secondNumber = Double.valueOf(two);

        // 4. Pop all the elements off of the Stack. Every time a double is popped that is
        //    between the two numbers entered by the user, print it to the screen.
        Double upperBound;
        Double lowerBound;
        if (firstNumber<secondNumber){
            upperBound = secondNumber;
            lowerBound = firstNumber;
        }
        else {
            upperBound = firstNumber;
            lowerBound = secondNumber;
        }
        System.out.println("Popping elements off stack... \nElements between 65 and 75:");
        while (!doubles.isEmpty()){
            Double poppedDouble = doubles.pop();
            if (lowerBound < poppedDouble && poppedDouble < upperBound){
                System.out.println(poppedDouble);
            }
        }

        // EXAMPLE:
        // NUM 1: 65
        // NUM 2: 75

        // Popping elements off stack...
        // Elements between 65 and 75:
        // 66.66876846
        // 74.51651681
        // 70.05110654
        // 69.21350456
        // 71.54506465
        // 66.47984807
        // 74.12121224
    }
}
