package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //System.out.print("Enter cells: ");
        //String state = scanner.nextLine();

        //Game game = new Game(state);
        Game game = new Game();
        game.printGameMatrix();
        

        boolean xMoves = true;
        boolean oMoves = false;
        while (game.getStatus().equals("Game not finished")) {
            if (xMoves) {
                game.makeMove('X');
            }

            if (oMoves) {
                game.makeMove('O');
            }

            xMoves = !xMoves;
            oMoves = !oMoves;
            game.printGameMatrix();
        }

        System.out.println(game.getStatus());



    }

}
