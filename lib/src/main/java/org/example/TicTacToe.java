package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] board;
        boolean playAgain;

        do {
            board = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
            int turns = 0;
            char currentPlayer = 'X';
            String resultMessage = "Draw!";
    
            while (turns < 9) {
                printBoard(board);
                System.out.printf("Current player: %s. Choose a number: ", currentPlayer);
    
                int input = getPlayerInput(scanner, board);
                board[input - 1] = currentPlayer;
    
                if (checkWin(board, currentPlayer)) {
                    resultMessage = "Congratulations, player " + currentPlayer + ", has won!";
                    break;
                } else {
                    turns++;
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
    
            printBoard(board);
            System.out.println(resultMessage);
            playAgain = askToPlayAgain(scanner);
    
        } while (playAgain);
        
        scanner.close();
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        System.out.print("Do you want to play again? (y/n): ");
        String response = scanner.next();
        return response.equalsIgnoreCase("y");
    }

    private static int getPlayerInput(Scanner scanner, char[] board) {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input < 1 || input > 9) {
                    System.out.println("Invalid input. Please enter a number from 1-9.");
                } else if (board[input - 1] == 'X' || board[input - 1] == 'O') {
                    System.out.println("Square " + input + " is already taken. Pick a different square.");
                } else {
                    return input;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number from 1-9.");
                scanner.next(); 
            }
        }
    }

    private static boolean checkWin(char[] board, char player) {
        return (board[0] == player && board[1] == player && board[2] == player) ||
               (board[3] == player && board[4] == player && board[5] == player) ||
               (board[6] == player && board[7] == player && board[8] == player) ||
               (board[0] == player && board[3] == player && board[6] == player) ||
               (board[1] == player && board[4] == player && board[7] == player) ||
               (board[2] == player && board[5] == player && board[8] == player) ||
               (board[0] == player && board[4] == player && board[8] == player) ||
               (board[2] == player && board[4] == player && board[6] == player);
    }

    private static void printBoard(char[] board) {
        System.out.println();
        System.out.println(board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println(" - + - + - ");
        System.out.println(board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println(" - + - + - ");
        System.out.println(board[6] + " | " + board[7] + " | " + board[8]);
        System.out.println();
    }
}
