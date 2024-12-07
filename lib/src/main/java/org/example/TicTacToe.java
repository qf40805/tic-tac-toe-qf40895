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
            char playerOneMark = getCustomMark(scanner, "Player 1");
            char playerTwoMark = (gameMode == 1) ? getCustomMark(scanner, "Player 2") : 'O';

            playAgain = false;

            do {
                char[] board = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
                int turns = 0;
                char currentPlayerMark = playerOneMark;
                String resultMessage = "Draw!";

                while (turns < 9) {
                    printBoard(board);
                    if (gameMode == 1 || currentPlayerMark == playerOneMark) {
                        // Player 1 or Player 2's turn (for player vs player mode)
                        System.out.printf("Current player: %s. Choose a number: ", currentPlayerMark);
                        int input = getPlayerInput(scanner, board);
                        board[input] = currentPlayerMark;
                    } else {
                        // Computer's turn (for player vs computer mode)
                        int input = getComputerMove(board);
                        System.out.printf("Computer chose square: %d\n", input + 1);
                        board[input] = currentPlayerMark;
                    }

                    if (checkWin(board, currentPlayerMark)) {
                        resultMessage = "Congratulations, player " + currentPlayerMark + ", has won!";
                        break;
                    } else {
                        turns++;
                        currentPlayerMark = (currentPlayerMark == playerOneMark) ? playerTwoMark : playerOneMark;
                    }
                }

                printBoard(board);
                System.out.println(resultMessage);
                playAgain = askToPlayAgain(scanner);

            } while (playAgain);

        } while (true);
    }

    private static int showMainMenu(Scanner scanner) {
        int choice;
        while (true) {
            System.out.println("Welcome to Tic-Tac-Toe!");
            System.out.println("1. Play against another player");
            System.out.println("2. Play against the computer");
            System.out.print("Choose your game mode (1 or 2): ");
            try {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please select 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static char getCustomMark(Scanner scanner, String playerName) {
        char mark;
        while (true) {
            System.out.printf("%s, please choose your mark (1 character): ", playerName);
            String input = scanner.next();
            if (input.length() == 1 && !input.trim().isEmpty()) {
                mark = input.charAt(0);
                break;
            } else {
                System.out.println("Invalid mark. Please enter a single character.");
            }
        }
        return mark;
    }

    private static void printBoard(char[] board) {
        System.out.println("\n" + 
            (board[0] == ' ' ? "1" : board[0]) + " | " + 
            (board[1] == ' ' ? "2" : board[1]) + " | " + 
            (board[2] == ' ' ? "3" : board[2]));
        System.out.println("--+---+--");
        System.out.println(
            (board[3] == ' ' ? "4" : board[3]) + " | " + 
            (board[4] == ' ' ? "5" : board[4]) + " | " + 
            (board[5] == ' ' ? "6" : board[5]));
        System.out.println("--+---+--");
        System.out.println(
            (board[6] == ' ' ? "7" : board[6]) + " | " + 
            (board[7] == ' ' ? "8" : board[7]) + " | " + 
            (board[8] == ' ' ? "9" : board[8]) + "\n");
    }

    private static int getPlayerInput(Scanner scanner, char[] board) {
        int input;
        while (true) {
            try {
                input = scanner.nextInt() - 1;
                if (input >= 0 && input < 9 && board[input] == ' ') {
                    return input;
                } else {
                    System.out.println("Invalid move. The spot is already taken or out of range. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                scanner.nextLine();
            }
        }
    }

    private static int getComputerMove(char[] board) {
        Random rand = new Random();
        int input;
        do {
            input = rand.nextInt(9);
        } while (board[input] != ' ');
        return input;
    }

    private static boolean checkWin(char[] board, char playerMark) {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] pattern : winPatterns) {
            if (board[pattern[0]] == playerMark && board[pattern[1]] == playerMark && board[pattern[2]] == playerMark) {
                return true;
            }
        }
        return false;
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        System.out.print("Do you want to play again? (Y/N): ");
        String response = scanner.next().toUpperCase();
        return response.equals("Y");
    }
}
