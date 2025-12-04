/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab10;

/**
 *
 * @author Gehad
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVLoader {

    public static SudokuBoard load(String path) throws IOException {
        int[][] data = new int[9][9];

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int row = 0;

            while ((line = br.readLine()) != null && row < 9) {
                String[] parts = line.trim().split("\\s*,\\s*|\\s+");

                if (parts.length < 9) {
                    throw new IOException("CSV row " + (row + 1)
                            + " has fewer than 9 columns.");
                }

                for (int c = 0; c < 9; c++) {
                    try {
                        data[row][c] = Integer.parseInt(parts[c].trim());
                    } catch (NumberFormatException ex) {
                        throw new IOException("Invalid number at row " + (row + 1) + " col " + (c + 1), ex);
                    }

                }
                row++;
            }
            if (row < 9) {
                throw new IOException("CSV file has fewer than 9 rows.");
            }
        }
         return new SudokuBoard(data);
    }
}
