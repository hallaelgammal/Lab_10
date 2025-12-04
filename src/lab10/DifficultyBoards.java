/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab10;

/**
 *
 * @author Abdallah
 */
public class DifficultyBoards {
    private int [][] easy;
    private int [][] medium;
    private int [][] hard;
    
    public DifficultyBoards(int [][] easy, int [][] medium, int [][] hard) {
        this.easy = easy;
        this.medium = medium;
        this.hard = hard;
    }
    public int [][] getEasy(){
        return easy;
    }
    public int [][] getMedium(){
        return medium;
    }
    public int [][] getHard(){
        return hard;
    }
}
