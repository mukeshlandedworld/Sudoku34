package org.example.sudoku.service;

import org.example.sudoku.model.SudokuGrid;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuGenerator {
    private static final int SIZE = 9;
    private static final int SUBGRID_SIZE = 3;

    public static SudokuGrid generateSudokuGrid() {
        int[][] grid = new int[SIZE][SIZE];
        generatePartialSudoku(grid);
        return new SudokuGrid(grid);
    }

    private static boolean generatePartialSudoku(int[][] grid) {
        List<Integer> values = IntStream.rangeClosed(1, SIZE).boxed().collect(Collectors.toList());
        Collections.shuffle(values);

        return solveSudoku(grid, values);
    }

    private static boolean solveSudoku(int[][] grid, List<Integer> values) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == 0) {
                    for (int num : values) {
                        if (isValidPlacement(grid, row, col, num)) {
                            grid[row][col] = num;
                            if (solveSudoku(grid, values)) {
                                return true;
                            }
                            grid[row][col] = 0; // Backtrack
                        }
                    }
                    return false;
                }
            }
        }
        return true; // Sudoku solved
    }

    private static boolean isValidPlacement(int[][] grid, int row, int col, int num) {
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


//import org.example.sudoku.model.SudokuGrid;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//public class SudokuGenerator {
//    private static final int SIZE = 9;
//    private static final int SUBGRID_SIZE = 3;
//
//    public static SudokuGrid generateSudokuGrid() {
//        int[][] grid = new int[SIZE][SIZE];
//        generatePartialSudoku();
//        return new SudokuGrid(grid);
//    }
//
//    private static boolean generatePartialSudoku() {
//        List<Integer> values = IntStream.rangeClosed(1, SIZE).boxed().collect(Collectors.toList());
//        Collections.shuffle(values);
//
//        int[][] grid = new int[0][];
//        return solveSudoku(grid, values);
//    }
//
//    private static boolean solveSudoku(int[][] grid, List<Integer> values) {
//        for (int row = 0; row < SIZE; row++) {
//            for (int col = 0; col < SIZE; col++) {
//                if (grid[row][col] == 0) {
//                    for (int num : values) {
//                        if (isValidPlacement(grid, row, col, num)) {
//                            grid[row][col] = num;
//                            if (solveSudoku(grid, values)) {
//                                return true;
//                            }
//                            grid[row][col] = 0; // Backtrack
//                        }
//                    }
//                    return false;
//                }
//            }
//        }
//        return true; // Sudoku solved
//    }
//
//    private static boolean isValidPlacement(int[][] grid, int row, int col, int num) {
//        return !isInRow(grid, row, num) &&
//                !isInColumn(grid, col, num) &&
//                !isInSubgrid(grid, row - row % SUBGRID_SIZE, col - col % SUBGRID_SIZE, num);
//    }
//
//    private static boolean isInRow(int[][] grid, int row, int num) {
//        for (int col = 0; col < SIZE; col++) {
//            if (grid[row][col] == num) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean isInColumn(int[][] grid, int col, int num) {
//        for (int row = 0; row < SIZE; row++) {
//            if (grid[row][col] == num) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean isInSubgrid(int[][] grid, int startRow, int startCol, int num) {
//        for (int row = 0; row < SUBGRID_SIZE; row++) {
//            for (int col = 0; col < SUBGRID_SIZE; col++) {
//                if (grid[row + startRow][col + startCol] == num) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//}
