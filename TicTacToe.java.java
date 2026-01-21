import java.util.Scanner;

public class TicTacToe {

    static char[][] board = new char[3][3];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Game started between Player X and Player O");
        System.out.println("Enter row and column (0-2)");

        boolean playAgain;
        do {
            initializeBoard();
            startGame();
            System.out.print("\nDo you want to play again? (yes/no): ");
            playAgain = sc.next().equalsIgnoreCase("yes");
        } while (playAgain);

        System.out.println("Game Over. Thanks for playing!");
    }

    // Initialize board
    static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Game loop
    static void startGame() {
        char currentPlayer = 'X';
        boolean gameFinished = false;

        while (!gameFinished) {
            displayBoard();
            System.out.println("Player " + currentPlayer + "'s turn to play");
            makeMove(currentPlayer);

            if (checkWinner(currentPlayer)) {
                displayBoard();
                System.out.println("🎉 Player " + currentPlayer + " wins!");
                gameFinished = true;
            } else if (isBoardFull()) {
                displayBoard();
                System.out.println("😐 Match Draw!");
                gameFinished = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    // Display board like sample image
    static void displayBoard() {
        System.out.println("\nCurrent Board:");
        System.out.println("    0   1   2");
        System.out.println("  +---+---+---+");

        for (int i = 0; i < 3; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println();
            System.out.println("  +---+---+---+");
        }
    }

    // Player move with validation
    static void makeMove(char player) {
        int row, col;

        while (true) {
            System.out.print("Enter row and column: ");
            row = sc.nextInt();
            col = sc.nextInt();

            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("❌ Invalid position. Try again.");
            } else if (board[row][col] != ' ') {
                System.out.println("❌ Cell already filled. Try again.");
            } else {
                board[row][col] = player;
                break;
            }
        }
    }

    // Winner check
    static boolean checkWinner(char player) {

        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        // Diagonals
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    // Check draw
    static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}