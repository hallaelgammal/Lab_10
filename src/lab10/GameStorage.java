/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab10;

/**
 *
 * @author Gehad
 */
import java.io.*;
import java.nio.file.*;
import java.util.UUID;
import static lab10.DifficultyEnum.EASY;
import static lab10.DifficultyEnum.HARD;
import static lab10.DifficultyEnum.MEDIUM;


public class GameStorage {

    private static final String CURRENT_GAME_DIR = "incomplete"; // your manually created folder
    private static final String EASY_DIR = "easy";
    private static final String MEDIUM_DIR = "medium";
    private static final String HARD_DIR = "hard";

    // Save a board to a folder based on difficulty
    public void saveBoard(SudokuBoard board, DifficultyEnum difficulty) throws IOException {
        String filename = UUID.randomUUID().toString() + ".csv";
        Path folder;

        switch (difficulty) {
            case EASY -> folder = Paths.get(EASY_DIR);
            case MEDIUM -> folder = Paths.get(MEDIUM_DIR);
            case HARD -> folder = Paths.get(HARD_DIR);
            default -> throw new IllegalArgumentException("Unknown difficulty");
        }

        saveBoardToFile(board, folder.resolve(filename));
    }

    // Save the currently played game (overwrite previous current game)
    public void saveCurrentGame(SudokuBoard board) throws IOException {
        // Clear previous current game
        clearFolder(CURRENT_GAME_DIR);
        Path file = Paths.get(CURRENT_GAME_DIR, "incomplete.csv");
        saveBoardToFile(board, file);
    }

    // Delete a completed and valid game
    public void deleteGame(Path file) throws IOException {
        Files.deleteIfExists(file);
    }

    // Helper: write board to CSV file
    private void saveBoardToFile(SudokuBoard board, Path file) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(file)) {
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    bw.write(String.valueOf(board.get(r, c)));
                    if (c < 8) bw.write(",");
                }
                bw.newLine();
            }
        }
    }

    // Helper: clear a folder
    private void clearFolder(String folderPath) throws IOException {
        Files.list(Paths.get(folderPath)).forEach(path -> {
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

   // Load the current game if exists
public SudokuBoard loadCurrentGame() throws IOException {
    Path file = Paths.get(CURRENT_GAME_DIR, "incomplete.csv"); // use exact file name
    if (Files.exists(file)) {
        return CSVLoader.load(file.toString());
    }
    return null; // no current game
}

}
