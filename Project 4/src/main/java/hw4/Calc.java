package hw4;

import java.util.Scanner;


/**
 * A program for an RPN calculator that uses a stack.
 */
public final class Calc {

  // A variable that represents whether the user has decided to terminate the program
  private static boolean endProgram;

  // The stack in which the user's numerical input is stored
  private static LinkedStack<Integer> stack;

  /**
   * The main function.
   *
   * @param args Not used.
   */
  public static void main(String[] args) {
    startProgram();
  }

  /**
   * Starts the program by instantiating endProgram and stack and taking in user input.
   */
  private static void startProgram() {
    // The user has not entered '!' to end the program
    endProgram = false;
    // Creates a new stack to store the user data
    stack = new LinkedStack<Integer>();
    // Creates a scanner to read the user input
    Scanner scanner = new Scanner(System.in);
    // Takes in user input until the user decides to end the program
    while (!endProgram) {
      // Adds user input integers to the stack to be stored
      if (scanner.hasNextInt()) {
        stack.push(scanner.nextInt());
      } else {
        // Determines whether other input is an operation, command, or invalid
        checkInput(scanner.next());
      }
    }
  }

  /**
   * Determines whether non-integer user input is an operation, command, or invalid.
   *
   * @param input the user input which is not an integer
   */
  private static void checkInput(String input) {
    if ("+".equals(input) || "-".equals(input) || "*".equals(input) || "/".equals(input) || "%".equals(input)) {
      // Valid operations are performed
      checkValidOperation(input);
    } else if ("?".equals(input)) {
      // The stack is printed out
      System.out.println(stack.toString());
    } else if (".".equals(input)) {
      if (!stack.empty()) {
        // The top of the stack is printed if it exits
        System.out.println(stack.top());
      } else {
        // An empty stack triggers an error message
        System.out.println("ERROR: empty stack");
      }
    } else if ("!".equals(input)) {
      // Ends the program
      endProgram = true;
    } else {
      // Invalid input triggers an error message
      System.out.println("ERROR: bad token");
    }
  }

  /**
   * Checks that there are at least 2 integers in the stack so an operation can be performed.
   *
   * @param operation the user input operation already checked to be a valid input
   */
  private static void checkValidOperation(String operation) {
    // Checks if there is data at the top of the stack
    if (!stack.empty()) {
      // Stores the data in order to pass it to the operate method
      int b = stack.top();
      // Pops the data because it will be replaced by the resultant operation
      stack.pop();
      // Checks if there is a second integer at the new top of the stack
      if (!stack.empty()) {
        // Stores the data in order to pass it to the operate method
        int a = stack.top();
        // Pops the data because it will be replaced by the resultant operation
        stack.pop();
        // Performs the operation
        operate(operation, a, b);
      } else {
        // If there is no second integer the first integer is added back to the stack
        stack.push(b);
        // An error message is triggered
        System.out.println("ERROR: only one operand");
      }
    } else {
      // No data in the stack triggers an error message
      System.out.println("ERROR: no operands");
    }
  }

  /**
   * Performs the operations between numbers popped off from the stack and adds the result to the stack.
   *
   * @param operation the valid operation
   * @param a the first operand
   * @param b the second operand
   */
  private static void operate(String operation, int a, int b) {
    if ("+".equals(operation)) {
      // Addition
      stack.push(a + b);
    } else if ("-".equals(operation)) {
      // Subtraction
      stack.push(a - b);
    } else if ("*".equals(operation)) {
      // Multiplication
      stack.push(a * b);
    } else if (b == 0) {
      // Division by zero can result in an arithmetic error so this triggers an error message
      System.out.println("ERROR: cannot divide by zero");
      // The operands are put back on the stack in order
      stack.push(a);
      stack.push(b);
    } else if ("/".equals(operation)) {
      // Integer division
      stack.push(a / b);
    } else if ("%".equals(operation)) {
      // Remainder division
      stack.push(a % b);
    }
  }
}
