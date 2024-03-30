package org.example.sudoku.service;

public class SudokuValidator {
    private static final int SIZE = 9;
    private static final int SUBGRID_SIZE = 3;

    public static boolean validate(int[][] grid, int row, int col, int num) {
        // Check if the number is valid in the given row, column, and subgrid
        return !isInRow(grid, row, num) &&
                !isInColumn(grid, col, num) &&
                !isInSubgrid(grid, row - row % SUBGRID_SIZE, col - col % SUBGRID_SIZE, num);
    }

    private static boolean isInRow(int[][] grid, int row, int num) {
        for (int col = 0; col < SIZE; col++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInColumn(int[][] grid, int col, int num) {
        for (int row = 0; row < SIZE; row++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInSubgrid(int[][] grid, int startRow, int startCol, int num) {
        for (int row = 0; row < SUBGRID_SIZE; row++) {
            for (int col = 0; col < SUBGRID_SIZE; col++) {
                if (grid[row + startRow][col + startCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }
}

