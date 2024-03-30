package org.example.sudoku.model;

public class SudokuGrid {
    private int[][] grid;
    private static final int SIZE = 9; // Size of the Sudoku grid

    public SudokuGrid(int[][] grid) {
        if (grid.length != SIZE || !isValidGrid(grid)) {
            throw new IllegalArgumentException("Invalid Sudoku grid");
        }
        this.grid = grid;
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getValueAt(int row, int col) {
        return grid[row][col];
    }

    public void setValueAt(int row, int col, int value) {
        grid[row][col] = value;
    }

    private boolean isValidGrid(int[][] grid) {
        // Check if the grid is a 9x9 grid and contains valid values (1-9)
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] < 1 || grid[i][j] > 9) {
                    return false;
                }
            }
        }
        return true;
    }
}
