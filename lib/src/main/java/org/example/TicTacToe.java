package org.example;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    enum Player { X, O }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            int gameMode = showMainMenu(scanner);
            playAgain = false;

            do {
                char[] board = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
                int turns = 0;
                Player currentPlayer = Player.X;
                String resultMessage = "Draw!";

                while (turns < 9) {
                    printBoard(board);
                    if (currentPlayer == Player.X) {
                        System.out.printf("Current player: %s. Choose a number: ", currentPlayer);
                        int input = getPlayerInput(scanner, board);
                        board[input - 1] = currentPlayer.name().charAt(0);
                    } else {
                        int input = getComputerMove(board);
                        System.out.printf("Computer chose square: %d\n", input + 1);
                        board[input] = currentPlayer.name().charAt(0);
                    }

                    if (checkWin(board, currentPlayer)) {
                        resultMessage = "Congratulations, player " + currentPlayer + ", has won!";
                        break;
                    } else {
                        turns++;
                        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
                    }
                }

                printBoard(board);
                System.out.println(resultMessage);
                playAgain = askToPlayAgain(scanner, gameMode);

            } while (playAgain);

        } while (true);
    }

    private static int showMainMenu(Scanner scanner) {
        int choice;
        while (true) {
            System.out.println("Welcome to Tic Tac Toe!");
            System.out.println("Select a game mode:");
            System.out.println("1. Human vs Human");
            System.out.println("2. Human vs Computer");
            System.out.print("Enter your choice (1 or 2): ");
            choice = scanner.nextInt();
            if (choice == 1 || choice == 2) {
                break;
            } else {
                System.out.println("Invalid choice. Please select 1 or 2.");
            }
        }
        return choice;
    }

    private static boolean askToPlayAgain(Scanner scanner, int gameMode) {
        System.out.print("Do you want to play the same game again? (y/n): ");
        String response = scanner.next();
        if (response.equalsIgnoreCase("y")) {
            return true;
        } else {
            System.out.println("Thank you for playing!");
            return false;
        }
    }

    private static int getPlayerInput(Scanner scanner, char[] board) {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input < 1 || input > 9) {
                    System.out.println("Invalid input. Please enter a number from 1-9.");
                } else if (board[input - 1] != ' ') {
                    System.out.println("Square " + input + " is already taken. Pick a different square.");
                } else {
                    return input - 1; // Return index
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number from 1-9.");
                scanner.nextLine(); 
            }
        }
    }

    private static int getComputerMove(char[] board) {
        Random rand = new Random();
        int move;
        do {
            move = rand.nextInt(9);
        } while (board[move] != ' '); // Ensure the move is valid
        return move;
    }

    private static boolean checkWin(char[] board, Player player) {
        char symbol = player.name().charAt(0);
        return (board[0] == symbol && board[1] == symbol && board[2] == symbol) ||
               (board[3] == symbol && board[4] == symbol && board[5] == symbol) ||
               (board[6] == symbol && board[7] == symbol && board[8] == symbol) ||
               (board[0] == symbol && board[3] == symbol && board[6] == symbol) ||
               (board[1] == symbol && board[4] == symbol && board[7] == symbol) ||
               (board[2] == symbol && board[5] == symbol && board[8] == symbol) ||
               (board[0] == symbol && board[4] == symbol && board[8] == symbol) ||
               (board[2] == symbol && board[4] == symbol && board[6] == symbol);
    }

    private static void printBoard(char[] board) {
        System.out.println("\nCurrent Board:");
        System.out.println("1 | 2 | 3");
        System.out.println(" - + - + - ");
        System.out.println("4 | 5 | 6");
        System.out.println(" - + - + - ");
        System.out.println("7 | 8 | 9");
        System.out.println();
        System.out.printf("%c | %c | %c\n", board[0], board[1], board[2]);
        System.out.println(" - + - + - ");
        System.out.printf("%c | %c | %c\n", board[3], board[4], board[5]);
        System.out.println(" - + - + - ");
        System.out.printf("%c | %c | %c\n", board[6], board[7], board[8]);
        System.out.println();
    }
}
