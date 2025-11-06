package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input command: ");
            String line = scanner.nextLine().trim();

            if ("exit".equals(line)) {
                break;
            }

            String[] parts = line.split("\\s+");
            if (parts.length != 3 || !"start".equals(parts[0])) {
                System.out.println("Bad parameters!");
                continue;
            }

            String xType = parts[1];
            String oType = parts[2];

            if (!isValidPlayerType(xType) || !isValidPlayerType(oType)) {
                System.out.println("Bad parameters!");
                continue;
            }

            playGame(scanner, xType, oType);
        }
    }

    private static boolean isValidPlayerType(String type) {
        return "user".equals(type) || "easy".equals(type);
    }

    // ---------------- game loop ----------------

    private static void playGame(Scanner scanner, String xType, String oType) {
        // empty board
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        printBoard(board);

        char current = 'X';

        while (true) {
            String currentType = (current == 'X') ? xType : oType;

            if ("user".equals(currentType)) {
                makeUserMove(scanner, board, current);
            } else { // easy AI
                System.out.println("Making move level \"easy\"");
                makeEasyMove(board, current);
            }

            printBoard(board);

            String state = getGameState(board);
            if (!"Game not finished".equals(state)) {
                System.out.println(state);
                break;
            }

            current = (current == 'X') ? 'O' : 'X';
        }
    }

    // ---------------- moves ----------------

    private static void makeUserMove(Scanner scanner, char[][] board, char symbol) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");

            if (parts.length != 2) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int row, col;
            try {
                row = Integer.parseInt(parts[0]);
                col = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (row < 1 || row > 3 || col < 1 || col > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            int r = row - 1;
            int c = col - 1;

            if (board[r][c] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            board[r][c] = symbol;
            break;
        }
    }

    private static void makeEasyMove(char[][] board, char symbol) {
        int r, c;
        do {
            r = random.nextInt(3);
            c = random.nextInt(3);
        } while (board[r][c] != ' ');
        board[r][c] = symbol;
    }

    // ---------------- helpers ----------------

    private static void printBoard(char[][] board) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static String getGameState(char[][] board) {
        boolean xWins = hasWon(board, 'X');
        boolean oWins = hasWon(board, 'O');
        boolean empty = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    empty = true;
                }
            }
        }

        if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (empty) {
            return "Game not finished";
        } else {
            return "Draw";
        }
    }

    private static boolean hasWon(char[][] board, char player) {
        // rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player &&
                    board[i][1] == player &&
                    board[i][2] == player) {
                return true;
            }
        }
        // columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player &&
                    board[1][j] == player &&
                    board[2][j] == player) {
                return true;
            }
        }
        // diagonals
        if (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player) {
            return true;
        }

        return false;
    }
}
