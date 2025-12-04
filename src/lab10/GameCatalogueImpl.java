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

public class GameCatalogueImpl implements GameCatalogue {

    private static final String CURRENT = "current";
    private static final String EASY = "easy";
    private static final String MEDIUM = "medium";
    private static final String HARD = "hard";

    @Override
    public boolean hasUnfinishedGame() {
        File currentFolder = new File(CURRENT);
        File[] files = currentFolder.listFiles((dir, name) -> name.endsWith(".csv"));
        return files != null && files.length > 0;
    }

    @Override
    public boolean hasGamesPerDifficulty() {
        return hasGameInFolder(EASY) && hasGameInFolder(MEDIUM) && hasGameInFolder(HARD);
    }

    private boolean hasGameInFolder(String folder) {
        File f = new File(folder);
        File[] files = f.listFiles((dir, name) -> name.endsWith(".csv"));
        return files != null && files.length > 0;
    }
}

