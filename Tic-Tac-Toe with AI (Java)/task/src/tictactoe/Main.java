package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Read initial cells
        System.out.print("Enter the cells: ");
        String cells = scanner.nextLine();

        // 2. Build the 3x3 board
        char[][] board = new char[3][3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = cells.charAt(index++);
                board[i][j] = (c == '_') ? ' ' : c;
            }
        }

        // 3. Print the initial board
        printBoard(board);

        // 4. Ask for coordinates until the user enters a valid empty cell
        while (true) {
            System.out.print("Enter the coordinates: ");
            String line = scanner.nextLine();

            String[] parts = line.trim().split("\\s+");
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

            int r = row - 1; // 0-based indices
            int c = col - 1;

            if (board[r][c] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            // 5. Decide whose move it is (X if counts equal, otherwise O)
            int xCount = 0;
            int oCount = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 'X') xCount++;
                    else if (board[i][j] == 'O') oCount++;
                }
            }
            char toMove = (xCount == oCount) ? 'X' : 'O';
            board[r][c] = toMove;
            break;
        }

        // 6. Print updated board
        printBoard(board);

        // 7. Print game state
        System.out.println(getGameState(board));
    }

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
