/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab10;

public class Lab10 {

    public static void main(String[] args) {

        String validFile = "valid.csv";
        String invalidFile = "invalid.csv";
        String incompleteFile = "incomplete.csv";

        System.out.println("===== TEST 1: VALID BOARD =====");
        testGameDriver(validFile);

        System.out.println("\n===== TEST 2: INVALID BOARD =====");
        testGameDriver(invalidFile);

        System.out.println("\n===== TEST 3: INCOMPLETE BOARD =====");
        testGameDriver(incompleteFile);
    }

    private static void testGameDriver(String csvFile) {
        
        
        try {
            // Load and validate the board
            GameDriver driver = new GameDriver(csvFile);

            // Check for incomplete cells
            SudokuBoard board = driver.getBoard();
            if (board.hasEmptyCells()) {  // You need to implement this in SudokuBoard
                System.out.println("Board is incomplete: " + csvFile);
            } else {
                System.out.println("Board is valid and complete: " + csvFile);
            }
            System.out.println("\n--- Generating difficulty boards ---");

            DifficultyBoards diff = driver.generateDifficultyBoards();

            // Print + verify difficulty levels
            printDifficulty("EASY (10 removed)", diff.getEasy(), 10);
            printDifficulty("MEDIUM (20 removed)", diff.getMedium(), 20);
            printDifficulty("HARD (25 removed)", diff.getHard(), 25); 

        } catch (InvalidSudokuException e) {
            // Board is invalid or failed to load
            System.out.println("Board validation failed for " + csvFile + ":");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // Other unexpected errors
            System.out.println("Unexpected error for " + csvFile + ":");
            e.printStackTrace();
        }
    }
    private static void printDifficulty(String title, int[][] board, int expectedZeros) {
        System.out.println("\n===== " + title + " =====");
        printBoard(board);

        int zeroCount = countZeros(board);
        System.out.println("Zeros found: " + zeroCount 
                + " (expected: " + expectedZeros + ")");
    }

    private static void printBoard(int[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    private static int countZeros(int[][] board) {
        int count = 0;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == 0)
                    count++;
            }
        }
        return count;
    }
    
}
