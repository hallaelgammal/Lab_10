/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab10;

/**
 *
 * @author Gehad
 */
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GameLoader {

    private static final Random RANDOM = new Random();

    public static SudokuBoard loadGame(String type) throws IOException {
        File folder;
        switch (type.toLowerCase()) {
            case "easy": folder = new File("easy"); break;
            case "medium": folder = new File("medium"); break;
            case "hard": folder = new File("hard"); break;
            case "incomplete": folder = new File("incomplete"); break;
            default: throw new IllegalArgumentException("Unknown type: " + type);
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));
        if (files == null || files.length == 0) {
            throw new IOException("No game found for type: " + type);
        }

        File selected = files[RANDOM.nextInt(files.length)]; // pick random
        return CSVLoader.load(selected.getAbsolutePath());
    }
}

