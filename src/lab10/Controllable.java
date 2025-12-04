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
public interface Controllable {
    void loadGame(DifficultyEnum level);
    void generateFromSolved();
    void VerifyCurrent();
    void solveCurrent();
    void handleUserAction(String action ) throws IOException;
}
