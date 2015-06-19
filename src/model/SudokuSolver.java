package model;

public class SudokuSolver {

	int[][] solution;

	int numSolutions;

	public SudokuSolver() {

	}

	public SudokuSolver(int[][] board) {
		setSolution(board);
	}

	public void setSolution(int[][] board) {
		this.solution = board.clone();
	}

	public Position[] getPositions(int[][] board) {
		int size = board.length;
		Position[] positions = new Position[size * size];
		int k = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				positions[k] = new Position(i, j);
				k++;
			}
		}
		return positions;
	}

	/**
	 * Copies array, gets position array of board and then calls backtracking
	 * sudoku solver with the copy of the original board.
	 * 
	 * @param board
	 * @return True if solvable, else false
	 */
	public boolean solve(int[][] board) {
		setSolution(board);
		Position[] positions = getPositions(board);
		return solve(positions, 0, solution);
	}

	/**
	 * Backtracking sudoku solver. Returns true if board has, at least, one
	 * solution. It takes as parameters a row and column of the board, then it
	 * tries a number, check if it is valid, then recursively calls the function
	 * on the next available board cell. If it can not find a solution, it moves
	 * on to the next digit and tries again, until it finds a solution or it
	 * tries all possible valid combinations.
	 * 
	 * @param row
	 *            current row of board
	 * @param col
	 *            current column of board
	 * @return boolean that corresponds to true if it finds a solution, else
	 *         false.
	 * 
	 */
	private boolean solve(Position[] positions, int index, int[][] board) {
		if (index >= positions.length) {
			return true;
		}
		Position p = positions[index];
		if (isEmpty(p.row, p.col, board)) {
			for (int i = 1; i < 10; i++) {
				if (isValid(p.row, p.col, i, board)) {
					board[p.row][p.col] = i;
					if (solve(positions, index + 1, board))
						return true;
					board[p.row][p.col] = 0;
				}

			}
		} else {
			return solve(positions, index + 1, board);
		}
		return false;
	}

	public boolean hasUniqueSolution(int[][] board) {
		Position[] positions = getPositions(board);
		setSolution(board);
		numSolutions = 0;
		int n = getNumSolutions(positions, 0, solution);
		return (n > 0) && (n < 2);
	}

	private int getNumSolutions(Position[] positions, int index, int[][] board) {
		if (numSolutions > 2) {
			return 2;
		}
		if (index >= positions.length) {
			numSolutions++;
			return numSolutions;
		}
		Position p = positions[index];
		if (isEmpty(p.row, p.col, board)) {
			for (int i = 1; i < 10; i++) {
				if (isValid(p.row, p.col, i, board)) {
					board[p.row][p.col] = i;
					getNumSolutions(positions, index + 1, board);
					board[p.row][p.col] = 0;
				}

			}
		} else {
			return getNumSolutions(positions, index + 1, board);
		}
		return numSolutions;
	}

	/**
	 * Checks if the cell is empty
	 * 
	 * @param row
	 *            current row of board
	 * @param col
	 *            current column of board
	 * @return true if it is empty, else false.
	 */
	public boolean isEmpty(int row, int col, int[][] board) {
		return (board[row][col] == 0);
	}

	/**
	 * Checks if the number i in the defined row and column is a valid move, or
	 * in another words, if it breaks the sudoku property. It returns a boolean
	 * depending if it is valid or not.
	 * 
	 * @param row
	 *            current row of board
	 * @param col
	 *            current column of board
	 * @param i
	 *            current value of cell
	 * @return true if it is a valid move, else false.
	 * 
	 */
	public boolean isValid(int row, int col, int i, int[][] board) {
		// Check row
		for (int j = 0; j < board.length; j++) {
			if (j != col) {
				if (board[row][j] == i)
					return false;
			}
		}
		// Check column
		for (int j = 0; j < board.length; j++) {
			if (j != row) {
				if (board[j][col] == i)
					return false;
			}
		}
		// Check Box
		int startRow = 3 * (row / 3);
		int startCol = 3 * (col / 3);

		for (int j = startRow; j < startRow + 3; j++) {
			for (int k = startCol; k < startCol + 3; k++) {
				if (j != row || k != col) {
					if (board[j][k] == i) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Checks if the number i in the defined row and column is a valid move, or
	 * in another words, if it breaks the sudoku property.
	 * 
	 * @param row
	 *            current row of board
	 * @param col
	 *            current column of board
	 * @param i
	 *            current value of cell
	 * @return null if valid move, else a string that references where it
	 *         conflicts: "row", "col" or "box"
	 * 
	 */
	public String getConflict(int row, int col, int i, int[][] board) {
		// Check row
		for (int j = 0; j < board.length; j++) {
			if (j != col) {
				if (board[row][j] == i)
					return "row";
			}
		}
		// Check column
		for (int j = 0; j < board.length; j++) {
			if (j != row) {
				if (board[j][col] == i)
					return "col";
			}
		}
		// Check Box
		int startRow = 3 * (row / 3);
		int startCol = 3 * (col / 3);

		for (int j = startRow; j < startRow + 3; j++) {
			for (int k = startCol; k < startCol + 3; k++) {
				if (j != row || k != col) {
					if (board[j][k] == i) {
						return "box";
					}
				}
			}
		}
		return null;
	}

}
