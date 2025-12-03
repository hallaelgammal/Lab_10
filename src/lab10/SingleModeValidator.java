/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab10;

import java.util.List;

/**
 *
 * @author Dell
 */
public class SingleModeValidator implements Validator {

    private final SudokuBoard board;

    public SingleModeValidator(SudokuBoard board) {
        this.board = board;
    }

    @Override
    public List<ValidationError> validate() throws InterruptedException {
        ValidationResult result = new ValidationResult();

        result.addAll(new RowValidator(board.toArrayCopy()).validate());
        result.addAll(new ColValidator(board.toArrayCopy()).validate());
        result.addAll(new BoxValidator(board.toArrayCopy()).validate());
        int zeros = countZeros(board.toArrayCopy());
        return result.getErrors();
    }

    private int countZeros(int[][] b) {
        int cnt = 0;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (b[r][c] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
