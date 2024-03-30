package org.example.sudoku;

import org.example.sudoku.model.SudokuGrid;
import org.example.sudoku.service.SudokuGenerator;
import org.example.sudoku.service.SudokuValidator;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class SudokuMicroservice {
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        port(8080);

        // Replace "http://localhost:63342" with the actual origin of your frontend application
        String allowedOrigin = "http://localhost:63342";

        // Enable CORS for all routes
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", allowedOrigin);
            response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
            response.header("Access-Control-Allow-Credentials", "true");
        });

        // Endpoint to generate Sudoku grid
        get("/generate", SudokuMicroservice::generateSudoku, gson::toJson);

        // Define the '/validate' endpoint
        post("/validate", (request, response) -> {
            response.type("application/json");
            try {
                // Extract row, column, and number from the request parameters
                int row = Integer.parseInt(request.queryParams("row"));
                int col = Integer.parseInt(request.queryParams("col"));
                int num = Integer.parseInt(request.queryParams("num"));

                // Sample Sudoku grid, you'll need to replace this with your own grid
                int[][] grid = {
                        {5, 3, 0, 0, 7, 0, 0, 0, 0},
                        {6, 0, 0, 1, 9, 5, 0, 0, 0},
                        {0, 9, 8, 0, 0, 0, 0, 6, 0},
                        {8, 0, 0, 0, 6, 0, 0, 0, 3},
                        {4, 0, 0, 8, 0, 3, 0, 0, 1},
                        {7, 0, 0, 0, 2, 0, 0, 0, 6},
                        {0, 6, 0, 0, 0, 0, 2, 8, 0},
                        {0, 0, 0, 4, 1, 9, 0, 0, 5},
                        {0, 0, 0, 0, 8, 0, 0, 7, 9}
                };

                // Validate the number placement
                boolean isValid = SudokuValidator.validate(grid, row, col, num);

                // Create a response object indicating the validation result
                return new Gson().toJson(new ValidationResult(isValid));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                // Handle invalid input parameters
                response.status(400); // Bad request
                return new Gson().toJson(new ValidationResult(false, "Invalid input parameters"));
            }
        });

        // Handle exceptions
        exception(Exception.class, (exception, request, response) -> {
            response.status(500); // Internal Server Error
            response.body("Internal Server Error");
        });
    }

    // Class representing the result of the validation
    private static class ValidationResult {
        private boolean isValid;
        private String errorMessage;

        public ValidationResult(boolean isValid) {
            this.isValid = isValid;
        }

        public ValidationResult(boolean isValid, String errorMessage) {
            this.isValid = isValid;
            this.errorMessage = errorMessage;
        }

        public boolean isValid() {
            return isValid;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    private static SudokuGrid generateSudoku(Request request, Response response) {
        SudokuGrid grid = SudokuGenerator.generateSudokuGrid();
        response.status(200);
        return grid;
    }

    private static boolean validateNumber(Request request, Response response) {
        SudokuGrid sudokuGrid = gson.fromJson(request.body(), SudokuGrid.class);
        int[][] grid = sudokuGrid.getGrid();

        int row = Integer.parseInt(request.queryParams("row"));
        int col = Integer.parseInt(request.queryParams("col"));
        int num = Integer.parseInt(request.queryParams("num"));

        if (SudokuValidator.validate(grid, row, col, num)) {
            response.status(200);
            return true;
        } else {
            response.status(400);
            return false;
        }
    }
}

