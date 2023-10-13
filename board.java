public class board {
    private static char[][] board;


    public board() {
        board = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
    }

    public static char[][] getBoard() {
        return board;
    }

    public static void printBoard(char[][] board) {
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);//Printing the board
        System.out.println("-+-+-");
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("-+-+-");
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }

    public static boolean isSpaceAvailable(char[][] board, String position) { //Checks if the space in current playing board is blank ' '.
        switch (position) {
            case "1":
                return (board[0][0] == ' '); //if the board at place [][] is empty (' '), return true, else false
            case "2":
                return (board[0][1] == ' ');
            case "3":
                return (board[0][2] == ' ');
            case "4":
                return (board[1][0] == ' ');
            case "5":
                return (board[1][1] == ' ');
            case "6":
                return (board[1][2] == ' ');
            case "7":
                return (board[2][0] == ' ');
            case "8":
                return (board[2][1] == ' ');
            case "9":
                return (board[2][2] == ' ');
            default:
                return false;

        }
    }
    public static void placingPiece(char[][] board, String position, char piece) {//Putting the actual piece on the board.

        switch(position) {
            case "1":
                board[0][0] = piece;
                break;
            case "2":
                board[0][1] = piece;
                break;
            case "3":
                board[0][2] = piece;
                break;
            case "4":
                board[1][0] = piece;
                break;
            case "5":
                board[1][1] = piece;
                break;
            case "6":
                board[1][2] = piece;
                break;
            case "7":
                board[2][0] = piece;
                break;
            case "8":
                board[2][1] = piece;
                break;
            case "9":
                board[2][2] = piece;
                break;
            default:
                System.out.println("You entered wrong value");
        }
    }

    public static boolean isGameFinished(char[][] board, String input_name, String other_name) { //Checks if three in a row has occured
        if (threeInRow(board, 'X')){
            printBoard(board);
            System.out.println(input_name + " has won!");
            return true;
        }

        if (threeInRow(board, 'O')){
            printBoard(board);
            System.out.println("Computer has won!");
            return true;
        }

        if (threeInRow(board, 'Z')) { // Check if 'Z' has won
            printBoard(board);
            System.out.println(other_name + " has won!");
            return true;
        }


        for (int i = 0; i < board.length; i++){
            for (int j= 0; j < board[i].length; j++){
                if (board[i][j] == ' '){
                    return false;
                }
            }
        }
        printBoard(board);
        System.out.println("The game ended in tie!");
        return true;
    }



    private static boolean threeInRow(char[][] board, char symbol) { //Checks all the possible solutions for three in a row
        if ((board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
                (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
                (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) || //checking rows

                (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
                (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
                (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) || //checking columns

                (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) { //checking diagonal

            return true; //if one of these conditions is true, someone has won
        }
        return false;
    }

}
