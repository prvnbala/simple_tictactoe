package tictactoe;

import java.util.Scanner;

public class Game {
    private final String initialConfiguration;
    private char[][] gameMatrix;
    private String status = "";

    public Game () {
        initialConfiguration = "_________";
        populateGameMatrix();
    }

    public Game (String initialConfiguration) {
        this.initialConfiguration = initialConfiguration;
        populateGameMatrix();
    }

    private void populateGameMatrix () {
        final int matrixSize = 3;
        gameMatrix = new char[matrixSize][matrixSize];
        char[] input = initialConfiguration.toCharArray();
        int k = 0;
        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix.length; j++) {
                gameMatrix[i][j] = input[k++];
            }
        }
    }

    public void printGameMatrix () {
        int n = gameMatrix.length;
        System.out.println("---------");
        for (int i = 0; i < n; i++) {
            System.out.print("| ");
            for (int j = 0; j < n; j++) {
                System.out.print(gameMatrix[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    private void analyseGameStatus () {
        boolean isXWon = checkWin('X');
        boolean isOWon = checkWin('O');
        int emptyCellCount = getCharCount('_');
        int xCellCount = getCharCount('X');
        int oCellCount = getCharCount('O');

        if (isXWon) {
            status = "X wins";
        }

        if (isOWon) {
            status = "O wins";
        }

        if (!isXWon && !isOWon && emptyCellCount > 0) {
            status = "Game not finished";
        }

        if (!isXWon && !isOWon && emptyCellCount == 0) {
            status = "Draw";
        }

        if ((isOWon && isXWon) || Math.abs(xCellCount - oCellCount) > 1) {
            status = "Impossible";
        }
    }

    private boolean checkWin (char a) {
        return checkRowWin(a) || checkColumnWin(a) || checkDiagonalWin(a);
    }

    private boolean checkRowWin (char a) {
        int n = gameMatrix.length;
        for (int i = 0; i < n; i++) {
            boolean threeInRow = true;
            for (int j = 0; j < n; j++) {
                if (gameMatrix[i][j] != a) {
                    threeInRow = false;
                    break;
                }
            }
            if (threeInRow) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnWin (char a) {
        int n = gameMatrix.length;
        for (int i = 0; i < n; i++) {
            boolean threeInCol = true;
            for (int j = 0; j < n; j++) {
                if (gameMatrix[j][i] != a) {
                    threeInCol = false;
                    break;
                }
            }
            if (threeInCol) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalWin (char a) {
        int n = gameMatrix.length;
        boolean threeInDiagonals = true;
        for (int i = 0; i < n; i++) {
            if (gameMatrix[i][i] != a) {
                threeInDiagonals = false;
            }
        }

        if (threeInDiagonals) {
            return true;
        }

        threeInDiagonals = true;
        for (int i = 0; i < n; i++) {
            if (gameMatrix[i][n - 1 - i] != a) {
                threeInDiagonals = false;
            }
        }

        return threeInDiagonals;
    }

    private int getCharCount (char targetChar) {
        int n = gameMatrix.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (gameMatrix[i][j] == targetChar) {
                    count++;
                }
            }
        }
        return count;
    }

    public String getStatus () {
        analyseGameStatus();
        return status;
    }

    public void updateGameMatrix (int i, int j, char a) {
            gameMatrix[i - 1] [j - 1] = a;
    }

    public void makeMove(char a) {
        String[] inputs = readCoOrdinates();
        boolean isValidInput = validateInputs(inputs);
        while (!isValidInput) {
            inputs = readCoOrdinates();
            isValidInput = validateInputs(inputs);
        }
        int x = Integer.parseInt(inputs[0]);
        int y = Integer.parseInt(inputs[1]);
        updateGameMatrix(x, y, a);
    }

    private boolean validateInputs(String[] inputs) {
        try {
            int i = Integer.parseInt(inputs[0]);
            int j = Integer.parseInt(inputs[1]);

            if (i > 3 || i < 1 || j > 3 || j < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                return isEmptyCell(i, j);
            }
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
        }
        return false;
    }

    private boolean isEmptyCell(int i, int j) {
        if (gameMatrix[i - 1] [j - 1] == '_') {
            return true;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
    }

    private String[] readCoOrdinates() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        String input = scanner.nextLine();
        String[] output = input.split(" ");
        return output;
    }
}
