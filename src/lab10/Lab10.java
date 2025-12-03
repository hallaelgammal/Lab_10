/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab10;

/**
 *
 * @author Abdallah
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Lab10 {

    public static void main(String[] args) throws Exception {

        String validFile = "valid.csv";
        String invalidFile = "invalid.csv";
        String incompleteFile ="incomplete.csv";

        // ===== TEST 1: VALID BOARD =====
        System.out.println("===== TEST 1: VALID BOARD =====");
        testBoard(validFile);

        // ===== TEST 2: INVALID BOARD =====
        System.out.println("\n===== TEST 2: INVALID BOARD =====");
        testBoard(invalidFile);

        // ===== TEST 3: INCOMPLETE BOARD =====
        System.out.println("\n===== TEST 3: INCOMPLETE BOARD =====");
        testBoard(incompleteFile);
    }

    private static void testBoard(String csvFile) throws InterruptedException {
        int[][] board = readBoardFromCSV(csvFile);
        if (board == null) {
            System.out.println("Failed to read board from CSV: " + csvFile);
            return;
        }

        SudokuBoard b = new SudokuBoard(board);
        SingleModeValidator v = new SingleModeValidator(b);
        System.out.println(v.validate());
    }

    private static int[][] readBoardFromCSV(String filePath) {
        List<int[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length != 9) {
                    System.out.println("Invalid row length: " + line);
                    return null;
                }
                int[] row = new int[9];
                for (int i = 0; i < 9; i++) {
                    row[i] = Integer.parseInt(tokens[i].trim());
                }
                rows.add(row);
            }
            if (rows.size() != 9) {
                System.out.println("CSV must have exactly 9 rows.");
                return null;
            }
            return rows.toArray(new int[9][9]);
        } catch (Exception e) {
            System.out.println("Error reading CSV: " + e.getMessage());
            return null;
        }
    }
}
