/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab10;

import javax.swing.JTable;

/**
 *
 * @author Gehad
 */
public class SudokuBoard {

    private final int[][] board = new int[9][9];

    public SudokuBoard(int[][] data) {
        for (int r = 0; r < 9; r++) {
            System.arraycopy(data[r], 0, board[r], 0, 9);
        }
    }

    public int get(int r, int c) {
        return board[r][c];
    }
    

    public int[][] toArrayCopy() {
        int[][] copy = new int[9][9];
        for (int r = 0; r < 9; r++) {
            System.arraycopy(board[r], 0, copy[r], 0, 9);
        }
        return copy;
    }
    public boolean hasEmptyCells() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                sb.append(board[r][c]);
                if (c < 8) sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
    
    //new
    public void set(int r, int c, int value) {
    if (r < 0 || r > 8 || c < 0 || c > 8) 
        throw new IllegalArgumentException("Row and column must be 0-8");
    if (value < 0 || value > 9) 
        throw new IllegalArgumentException("Value must be 0-9");
    board[r][c] = value;
}
    
    public void loadIntoTable(JTable table) {
    for (int r = 0; r < 9; r++) {
        for (int c = 0; c < 9; c++) {
            int value = get(r, c);
            table.setValueAt(value == 0 ? "" : value, r, c);
        }
    }
}

  public void updateFromTable(JTable table) {
    for (int r = 0; r < 9; r++) {
        for (int c = 0; c < 9; c++) {
            Object val = table.getValueAt(r, c);
            if (val == null || val.toString().trim().isEmpty()) {
                set(r, c, 0);
            } else {
                set(r, c, Integer.parseInt(val.toString()));
            }
        }
    }
}


}

