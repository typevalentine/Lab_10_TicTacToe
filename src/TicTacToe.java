import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String player = "X";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            clearBoard();
            display();

            while (true) {
                int row = SafeInput.getRangedInt(in, "Enter the row (1-3): ", 1, 3) - 1;
                int col = SafeInput.getRangedInt(in, "Enter the column (1-3): ", 1, 3) - 1;

                if (isValidMove(row, col)) {
                    board[row][col] = player;
                    if (isWin(player)) {
                        display();
                        System.out.println("Player " + player + " wins!");
                        break;
                    } else if (isTie()) {
                        display();
                        System.out.println("It's a tie!");
                        break;
                    }
                    player = (player.equals("X")) ? "O" : "X";
                    display();
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            }

            playAgain = SafeInput.getYNConfirm(in, "Do you want to play again? (y/n): ");
            player = "X";
        }
        in.close();
    }

    private static void clearBoard()
    {
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                board[i][j] = " ";
            }
        }
    }


    private static void display()
    {
        System.out.println("  1 2 3");
        for (int i = 0; i < ROW; i++)
        {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < COL; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


    private static boolean isValidMove(int row, int col)
    {
        return board[row][col].equals(" ");
    }


    private static boolean isWin(String player)
    {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player)
    {
        for (int i = 0; i < COL; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player)
    {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }


    private static boolean isDiagonalWin(String player)
    {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie()
    {
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                if (board[i][j].equals(" "))
                {
                    return false;
                }
            }
        }
        return true;
    }
}