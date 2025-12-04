/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab10;
import java.util.List;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
/**
 *
 * @author Dell
 */
public class GameDriver {

    private SudokuBoard board;
        // Initialize storage
GameStorage storage = new GameStorage();

    /*
    * Loads and verifies a Sudoku board from a CSV file.
     * @param csvFile Path to the CSV file.
     * @throws InvalidSudokuException If the board is invalid or incomplete.
     */
    public GameDriver(String csvFile) throws InvalidSudokuException {
        SudokuBoard boardFromCSV;
        try {
            boardFromCSV = CSVLoader.load(csvFile);  // call static method from CSVLoader
        } catch (IOException e) {
            throw new InvalidSudokuException("Failed to load CSV: " + e.getMessage());
        }

        this.board=boardFromCSV;
        verifySourceSolution();//validate immeaditely
    }

    public void verifySourceSolution() throws InvalidSudokuException {
        try {
            SingleModeValidator validator = new SingleModeValidator(board);
            List<ValidationError> errors = validator.validate();
            ValidationResult result = new ValidationResult();
            result.addAll(errors);
            if (!result.isValid()) {
                throw new InvalidSudokuException("Source solution is not valid:\n" + result.toString());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new InvalidSudokuException("Validation interrupted.");
        }
    }

    public SudokuBoard getBoard() {
        return board;
    }
    
    private int[][] deepCopy(int[][] src) {   //
    int[][] copy = new int[9][9];
    for (int i = 0; i < 9; i++) {
        copy[i] = src[i].clone();
    }
    return copy;
}
    //++
   public String getDifficulty(){
       int empty=board.countEmptyCells();
       if(empty<=30)
           return "Easy";
       if(empty<=45)
           return "Medium";
       return "Hard";
   }
    public DifficultyBoards generateDifficultyBoards() {
        
        int [][] solved = board.toArrayCopy(); //original solved board
        
        int [][] easy = deepCopy(solved);
        int [][] medium = deepCopy(solved);
        int [][] hard = deepCopy(solved);
        
         // --- EASY: remove 10 cells ---
    removeCells(easy,10);

    // --- MEDIUM: remove 20 cells ---
    removeCells(medium,20);

    // --- HARD: remove 25 cells ---
    removeCells(hard,25);
         
         return new DifficultyBoards(easy, medium, hard);
        
       
        
    }
    private void removeCells(int[][] board, int n) {
    Set<Integer> used = new HashSet<>();
    Random rand = new Random();
    while (used.size() < n) {
        int idx = rand.nextInt(81); // 0..80
        if (!used.contains(idx)) {
            used.add(idx);
            board[idx / 9][idx % 9] = 0;
        }
    }
}
    // habiba donia
public void generateAndSaveBoards() {
        try {
            DifficultyBoards boards = generateDifficultyBoards();

            // Save boards by difficulty
            storage.saveBoard(new SudokuBoard(boards.getEasy()), DifficultyEnum.EASY);
            storage.saveBoard(new SudokuBoard(boards.getMedium()), DifficultyEnum.MEDIUM);
            storage.saveBoard(new SudokuBoard(boards.getHard()), DifficultyEnum.HARD);

            // Save the currently played game
            storage.saveCurrentGame(board);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
