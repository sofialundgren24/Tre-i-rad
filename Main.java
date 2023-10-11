import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int yourWins = 0;
        int computerWins = 0;
        int otherPlayerWins = 0;
        boolean playAgain = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your name? ");
        String input_name = scanner.nextLine();


        while(playAgain) {
            char[][] board = {{' ', ' ', ' '},
                              {' ', ' ', ' '},
                              {' ', ' ', ' '}};

            boolean opponent = chooseOpponent(scanner);

            if(opponent){//If opponent = true, then player wants to play against other player
                System.out.println("What is the other player's name? ");
                String other_name = scanner.nextLine();
                System.out.println("Hello to the other player " + other_name);

                playAgainstAnotherPlayer(board, scanner, yourWins, otherPlayerWins, input_name, other_name);
            }else{
                playAgainstComputer(board, scanner, yourWins, computerWins, input_name);
            }

            System.out.println("Want to play again? y/n");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                System.out.println("Lets do another round!");
            } else if (answer.equalsIgnoreCase("n")) {
                System.out.println("Computer won " + " times, and you won " +  " times!");
                playAgain = false;
            }else{
                System.out.println("Invalid input");
            }
        }

    }

    private static void playAgainstComputer(char[][] board, Scanner scanner, int yourWins, int computerWins, String input_name) {
        String quickFix = "";
        while (true) {
            yourTurn(board, scanner, input_name);//sending in scanner object and the board
            if (isGameFinished(board, input_name, quickFix)) {
                break;
            }
            printBoard(board);
            computerTurn(board);

            if (isGameFinished(board, input_name, quickFix)) {
                break;
            }
            printBoard(board);
        }
    }

    private static void playAgainstAnotherPlayer(char[][] board, Scanner scanner, int yourWins, int otherPlayerWins, String input_name, String other_name) {


        while (true) {
            yourTurn(board, scanner, input_name);//sending in scanner object and the board
            if (isGameFinished(board, input_name, other_name)) {
                break;
            }
            printBoard(board);
            otherPlayerTurn(board, scanner, other_name);

            if (isGameFinished(board, input_name, other_name)) {
                break;
            }
            printBoard(board);
        }
    }

    private static void otherPlayerTurn(char[][] board, Scanner scanner, String other_name) {
        System.out.println(other_name + "s turn!");
        String userInput;
        while(true){
            System.out.println("Where do you want to put your piece (1-9)");
            userInput = scanner.nextLine();

            if (isSpaceAvailable(board, userInput)) {
                break;
            } else {
                System.out.println(userInput + "; this is not a valid move! ");
            }
        }
        placingPiece(board, userInput, 'Z');
    }

    private static boolean chooseOpponent(Scanner scanner) {
        System.out.println("Choose your opponent by pressing 1 or 2: \n" + "1. Another player\n" + "2. Computer");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1:
                System.out.println("You chose another player!");
                return true; //True for player
            case 2:
                System.out.println("You choose computer player!");
                return false; //False for computer
        }
        return false;
    }


    private static boolean isGameFinished(char[][] board, String input_name, String other_name) {
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


    private static boolean threeInRow(char[][] board, char symbol) {
        if ((board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
                (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
                (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) || //checking rows

                (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
                (board[1][0] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
                (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) || //checking columns

                (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) { //checking diagonal

            return true; //if one of these conditions is true, someone has won
        }
        return false;
    }


    private static void computerTurn(char[][] board) {
        Random rand = new Random();
        int computerMovePos;

        while(true) {
            computerMovePos = rand.nextInt(9) + 1;  //random int between 0-8, then we add + 1
            if (isSpaceAvailable(board, Integer.toString(computerMovePos))) {
                break;
            }
        }
        System.out.println("Computer choose " + computerMovePos);
        placingPiece(board, Integer.toString(computerMovePos), 'O');
    }


    private static boolean isSpaceAvailable(char[][] board, String position) {
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

    private static void yourTurn(char[][] board, Scanner scanner, String input_name) {
        System.out.println(input_name + "s turn!");
        String userInput;
        while(true){
            System.out.println("Where do you want to put your piece (1-9)");
            userInput = scanner.nextLine();

            if (isSpaceAvailable(board, userInput)) {
                break;
            } else {
                System.out.println(userInput + "; this is not a valid move! ");
            }
        }
        placingPiece(board, userInput, 'X');
    }


    private static void placingPiece(char[][] board, String position, char piece) {

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




    private static void printBoard(char[][] board) {
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("-+-+-");
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("-+-+-");
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }
}