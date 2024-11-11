package minesweeper;

public class Minesweeper {
    private char[][] mineField;
    private boolean[][] closedField;
    int columnL = 0;
    int rowL = 0;
    String str;

    /**
     * Empty constructor
     */
    public Minesweeper() {
        // todo implement           
        // Igor ty mozhesh eto prochitat' ? ---> jawohl
    }

    /**
     * Initializes the mine field from given multiline string representation
     * A line represents a row of the field
     * All lines must have the same number of characters, which is the number of columns of the field
     * A character can be either '*' for a mine cell, or '.' for an empty cell
     * The number of rows must be smaller than or equal to the number of columns
     * Rows are indexed top-down with the top row index equal to zero.
     * @param s the string
     * @return true if initialization succeeds, i.e., field is valid, or false otherwise.
     */
    public boolean setMinefield(String s) {
        if (s.isEmpty()) {
            return false;
        }
        String[] convert = s.split(System.lineSeparator());
        s = String.join("X", convert);
        //s = s.replace("\n", "X");
        str = s;
        int rowLength = 0;
        int columnLength = 0;
        int check = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '*' && s.charAt(i) != '.' && s.charAt(i) != 'X') {
                return false;
            }
            if (s.charAt(i) == 'X') {
                columnLength++;
                if (columnLength > 1 && check != rowLength) {
                    return false;
                }
                check = rowLength;
                rowLength = 0;
            }
            else {
                rowLength++;
            }
        }
        if (columnLength > 0 && check != rowLength) {
            return false;
        }
        columnLength++;
        if (columnLength > rowLength) {
            return false;
        }
        columnL = columnLength;
        rowL = rowLength;
        mineField = new char[columnLength][rowLength];
        closedField = new boolean[columnLength][rowLength];
        int i = 0;
        int j = 0;
        for (int x = 0; x < s.length(); x++) {
            if (s.charAt(x) == 'X') {
                i++;
                j = 0;
            }
            else {
                mineField[i][j] = s.charAt(x);
                closedField[i][j] = false;
                j++;
            }
        }
        return true;
    }

    /**
     *
     * @return the number of bombs in the field, or -1 if the field is invalid
     */
    public int getNumberOfBombs() {
        int count = 0;
        if (setMinefield(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '*') {
                    count++;
                }
            }
            return count;
        }
        return -1;
    }

    /**
     * Returns the hint at coordinate (row, column)
     * @param row
     * @param col
     * @return the hint (>=0) if successful and cell has no bomb, -1 otherwise
     */
    public int getHintAt(int row, int col) {
        if (row >= columnL || row < 0 || col >= rowL || col < 0) {
            return -1;
        }
        
        int count_bombs = 0;
        if (mineField[row][col] != '*') {
            if (columnL == 1 && row == 0 && col == 0) {
                if (mineField[row][col + 1] == '*') {
                    count_bombs++;
                }
                return count_bombs;
            }
            if (columnL == 1 && row == 0 && col == rowL - 1) {
                if (mineField[row][col - 1] == '*') {
                    count_bombs++;
                }
                return count_bombs;
            }
            if (columnL == 1 && row == 0 && col != rowL - 1 && col != 0) {
                if (mineField[row][col + 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row][col - 1] == '*') {
                    count_bombs++;
                }
                return count_bombs;
            }
            else if (row == 0 && col == 0) {
                if (mineField[row][col + 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row + 1][col] == '*') {
                    count_bombs++;
                }
                if (mineField[row + 1][col + 1] == '*') {
                    count_bombs++;
                }
                return count_bombs;
            }
            else if (row == 0 && col == rowL - 1 ) {
                if (mineField[row][col - 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row + 1][col] == '*') {
                    count_bombs++;
                }
                if (mineField[row + 1][col - 1] == '*') {
                    count_bombs++;
                }
                return count_bombs;
            }
            else if (row == 0 && (col != rowL - 1 && col != 0)) {
                if (mineField[row][col - 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row][col + 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row + 1][col] == '*') {
                    count_bombs++;
                }
                if (mineField[row + 1][col - 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row + 1][col + 1] == '*') {
                    count_bombs++;
                }
                return count_bombs;
            }
            else if (row == columnL - 1 && col == 0) {
                if (mineField[row][col + 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row - 1][col] == '*') {
                    count_bombs++;
                }
                if (mineField[row - 1][col + 1] == '*') {
                    count_bombs++;
                }
                return count_bombs;
            }
            else if (row == columnL - 1 && col == rowL - 1) {
                if (mineField[row][col - 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row - 1][col] == '*') {
                    count_bombs++;
                }
                if (mineField[row - 1][col - 1] == '*') {
                    count_bombs++;
                }
                return count_bombs;
            }
            else if (row == columnL - 1 && (col != rowL - 1 && col != 0)) {
                if (mineField[row][col - 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row][col + 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row - 1][col] == '*') {
                    count_bombs++;
                }
                if (mineField[row - 1][col - 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row - 1][col + 1] == '*') {
                    count_bombs++;
                }
                return count_bombs;
            }
            else if ((row != columnL - 1 && row != 0) && col == 0) {
                if (mineField[row - 1][col] == '*') {
                    count_bombs++;
                }
                if (mineField[row - 1][col + 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row][col + 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row + 1][col + 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row + 1][col] == '*') {
                    count_bombs++;
                }
                return count_bombs;
            }
            else if ((row != columnL - 1 && row != 0) && col == rowL - 1) {
                if (mineField[row - 1][col] == '*') {
                    count_bombs++;
                }
                if (mineField[row - 1][col - 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row][col - 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row + 1][col - 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row + 1][col] == '*') {
                    count_bombs++;
                }
                return count_bombs;
            }
            else {
                if (mineField[row - 1][col] == '*') {
                    count_bombs++;
                }
                if (mineField[row - 1][col - 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row - 1][col + 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row][col - 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row ][col + 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row + 1][col] == '*') {
                    count_bombs++;
                }
                if (mineField[row + 1][col - 1] == '*') {
                    count_bombs++;
                }
                if (mineField[row + 1][col + 1] == '*') {
                    count_bombs++;
                }
                return count_bombs;
            }

        }
        return -1; 
    }

    /**
     * Checks the visibility of a cell.
     * @param row
     * @param col
     * @return true if the cell (row,column) is visible, false otherwise
     */
    public boolean isVisible(int row, int col) {
        return closedField[row][col]; 
    }

    /**
     * Simulates a user click on a cell. If this is a bomb cell, all the bomb cells in the entire field become visible.
     * Otherwise only this cell becomes visible.
     * @param row
     * @param col
     * @return true if the cell has no bomb, false if it's a bomb cell
     */
    public boolean clickOn(int row, int col) {
        if (mineField[row][col] == '*') {
            for (int i = 0; i < columnL; i++) {
                for (int j = 0; j < rowL; j++) {
                    if (mineField[i][j] == '*') {
                        closedField[i][j] = true;
                    }
                }
            }
            return false;
        }
        else {
            closedField[row][col] = true;
            return true;
        }
    }

    /**
     *
     * @return the number of rows of the field
     */
    public int getHeight() {
        return columnL;
    }

    /**
     *
     * @return the number of columns of the field
     */
    public int getWidth() {
        return rowL;
    }

    public String showField() {
        String s = "";
        for (int i = 0; i < columnL; i++) {
            for (int j = 0; j < rowL; j++) {
                s += mineField[i][j];
            }
            s += "\n";
        }
        return s;
    }
	
}
