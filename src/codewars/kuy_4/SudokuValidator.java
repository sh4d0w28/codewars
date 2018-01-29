package kuy_4;

/*
Sudoku Solution Validator
Write a function validSolution/ValidateSolution/valid_solution() that accepts a 2D array
representing a Sudoku board, and returns true if it is a valid solution, or false otherwise.

The cells of the sudoku board may also contain 0's, which will represent empty cells.
Boards containing one or more zeroes are considered to be invalid solutions.

The board is always 9 cells by 9 cells, and every cell only contains integers from 0 to 9.

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SudokuValidator {

    private static int[][] board;

    private static boolean check(int[][] sudoku) {
        try {
            board = sudoku;
            for(int i = 0; i<9; i++) {
                checkHorizontal(i);
                checkVertical(i);
            }
            for(int i = 1; i<9; i+=3) {
                for (int j=1; j < 9; j+=3) {
                    checkBox(i, j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void checkBox(int ic, int jc) throws Exception {
        List<Integer> carr = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        for(int i = ic-1; i<= ic+1; i++) {
            for(int j = jc-1; j<=jc+1; j++) {
                if (board[i][j] == 0) {
                    throw new Exception("NOTVALID");
                }
                carr.remove((Object)board[i][j]);
            }
        }
        if (carr.size() > 0) {
            throw new Exception("NOTVALID");
        }
    }

    private static void checkVertical(int hNum) throws Exception {
        List<Integer> carr = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        for(int i = 0; i< 9; i++) {
            if (board[i][hNum] == 0) {
                throw new Exception("NOTVALID");
            }
            carr.remove((Object)board[i][hNum]);
        }
        if (carr.size() > 0) {
            throw new Exception("NOTVALID");
        }
    }

    private static void checkHorizontal(int hNum) throws Exception {
        List<Integer> carr = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        for(int i = 0; i< 9; i++) {
            if (board[hNum][i] == 0) {
                throw new Exception("NOTVALID");
            }
            carr.remove((Object)board[hNum][i]);
        }
        if (carr.size() > 0) {
            throw new Exception("NOTVALID");
        }
    }

    public static void main(String[] args) {
        int[][] sudoku = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        System.out.println(check(sudoku));

    }
}