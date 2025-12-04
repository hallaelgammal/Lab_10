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
}
