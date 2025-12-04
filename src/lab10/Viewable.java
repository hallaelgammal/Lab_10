/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab10;

import java.io.IOException;

/**
 *
 * @author Abdallah
 */
public interface Viewable {
    GameCatalogue getGameCatalogue();
    SudokuBoard getGame(DifficultyEnum level) throws InvalidSudokuException;
    SudokuBoard driveGames(SudokuBoard solved) throws InvalidSudokuException;
    ValidationResult verifyGame(SudokuBoard game);
    int [][] solveGame(SudokuBoard game) throws InvalidSudokuException;
    void logUserAction(String userAction) throws IOException;
}
