import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int yourWins = 0;
    private static int computerWins = 0;
    private static int otherPlayerWins = 0;
    ;


    public static void main(String[] args) {

        boolean playAgain = true;
        String other_name;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your name? ");
        String input_name = scanner.nextLine();


        while (playAgain) {

            board board_object = new board();
            char[][] array_board = board_object.getBoard();
            //Instance board
            boolean opponent = chooseOpponent(scanner);

            if (opponent) {//If opponent = true, then player wants to play against other player
                System.out.println("What is the other player's name? ");
                other_name = scanner.nextLine();
                System.out.println("Hello to the other player " + other_name);
                playAgainstAnotherPlayer(array_board, scanner, input_name, other_name);
            } else {
                playAgainstComputer(array_board, scanner, input_name);
            }

            System.out.println("Want to play again? y/n");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                System.out.println("Lets do another round!");
            } else if (answer.equalsIgnoreCase("n")) {
                if (opponent) {
                    System.out.println(input_name + " won " + yourWins + " times and the other player(s) won " + otherPlayerWins + " times.");
                } else {
                    System.out.println("Computer won " + computerWins + " times, and " + input_name + " won " + yourWins + " times.");
                }

                playAgain = false;
            } else {
                System.out.println("Invalid input");
            }
        }

    }

    private static void playAgainstComputer(char[][] array_board, Scanner scanner, String input_name) {
        String quickFix = "";
        while (true) {
            yourTurn(array_board, scanner, input_name);//sending in scanner object and the board
            if (board.isGameFinished(array_board, input_name, quickFix)) {
                yourWins++;
                break;
            }
            board.printBoard(array_board);
            computerTurn(array_board);

            if (board.isGameFinished(array_board, input_name, quickFix)) {
                computerWins++;
                break;
            }
            board.printBoard(array_board);
        }
    }

    private static void playAgainstAnotherPlayer(char[][] array_board, Scanner scanner, String input_name, String other_name) {


        while (true) {
            yourTurn(array_board, scanner, input_name);//sending in scanner object and the board
            if (board.isGameFinished(array_board, input_name, other_name)) {
                yourWins++;
                break;
            }
            board.printBoard(array_board);
            otherPlayerTurn(array_board, scanner, other_name);

            if (board.isGameFinished(array_board, input_name, other_name)) {
                otherPlayerWins++;
                break;
            }
            board.printBoard(array_board);
        }
    }

    private static void otherPlayerTurn(char[][] array_board, Scanner scanner, String other_name) { //lets the other player move their piece
        System.out.println("It is " + other_name + "s turn.");
        String userInput;
        while (true) {
            System.out.println("Where do you want to put your piece (1-9)");
            userInput = scanner.nextLine();

            if (board.isSpaceAvailable(array_board, userInput)) {
                break;
            } else {
                System.out.println(userInput + "; this is not a valid move! ");
            }
        }
        board.placingPiece(array_board, userInput, 'Z');
    }

    private static boolean chooseOpponent(Scanner scanner) {
        System.out.println("Choose your opponent by pressing 1 or 2: \n" + "1. Another player\n" + "2. Computer");
        String choice = scanner.nextLine();


        switch (choice) {
            case "1":
                System.out.println("You chose another player!");
                return true; //True for player
            case "2":
                System.out.println("You choose computer player!");
                return false; //False for computer
            default:
                System.out.println("Wrong input, you will now play against computer.");
        }
        return false;
    }


    private static void computerTurn(char[][] array_board) { //Handles computeras turn
        Random rand = new Random();
        int computerMovePos;

        while (true) {
            computerMovePos = rand.nextInt(9) + 1;  //random int between 0-8, then we add + 1
            if (board.isSpaceAvailable(array_board, Integer.toString(computerMovePos))) {//Breaks out of loop when valid place is found
                break;
            }
        }
        System.out.println("Computer choose " + computerMovePos);
        board.placingPiece(array_board, Integer.toString(computerMovePos), 'O');
    }


    private static void yourTurn(char[][] array_board, Scanner scanner, String input_name) {//Handles my turn
        System.out.println("It is " + input_name + "s turn.");
        String userInput;
        while (true) {
            System.out.println("Where do you want to put your piece (1-9)");
            userInput = scanner.nextLine();

            if (board.isSpaceAvailable(array_board, userInput)) {
                break;
            } else {
                System.out.println(userInput + "; this is not a valid move! ");
            }
        }
        board.placingPiece(array_board, userInput, 'X');
    }
}

