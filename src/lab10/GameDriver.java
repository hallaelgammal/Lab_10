/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab10;
import java.util.List;
import java.io.IOException;
/**
 *
 * @author Dell
 */
public class GameDriver {

    private SudokuBoard board;

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

}
