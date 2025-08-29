import java.util.Scanner;

public class Sudoku {
    private static final int SIZE = 9;
    private static int[][] board = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printBoard();
            System.out.print("Digite linha (1-9), coluna (1-9) e número (1-9), separados por espaço: ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            int num = scanner.nextInt();

            if (board[row][col] != 0) {
                System.out.println("Essa célula já foi preenchida.");
                continue;
            }

            if (isValidMove(row, col, num)) {
                board[row][col] = num;
                if (isBoardFull()) {
                    printBoard();
                    System.out.println("Parabéns! Você completou o Sudoku.");
                    break;
                }
            } else {
                System.out.println("Movimento inválido.");
            }
        }
        scanner.close();
    }

    private static void printBoard() {
        System.out.println("-------------------------");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0) System.out.print("| ");
                System.out.print(board[i][j] == 0 ? ". " : board[i][j] + " ");
            }
            System.out.println("|");
            if ((i + 1) % 3 == 0) System.out.println("-------------------------");
        }
    }

    private static boolean isValidMove(int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
        }

        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;

        for (int i = boxRowStart; i < boxRowStart + 3; i++) {
            for (int j = boxColStart; j < boxColStart + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }

        return true;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }
}
