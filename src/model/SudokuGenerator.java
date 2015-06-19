package model;

import java.util.Random;

/**
 * 
 * @author Benjamin Diaz
 *
 */
public class SudokuGenerator {

	public SudokuSolver solver;
	public int[][] readyBoard;
	public int[][] fullBoard;

	public SudokuGenerator() {
		solver = new SudokuSolver();
	}

	/**
	 * Generates board array and initializes it with zeroes.
	 * 
	 * @param size
	 * @return 2D array board filled with zeroes
	 */
	public int[][] getEmptyBoard(int size) {
		int[][] board = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = 0;
			}
		}
		return board;
	}

	/**
	 * Generates random Sudoku board and sets the full board and the ready to
	 * play board (the one with the blank spaces). First, it creates a new 2D
	 * array for the board and an array for the board positions. Then it creates
	 * an array that contains numbers from 1 to 9 and shuffles it. Finally, it
	 * calls the recursive function that creates the full board and then it
	 * removes a defined amount of random spaces from it, in a symmetric way.
	 * 
	 * @param size
	 *            Size of the Sudoku board
	 * @param blankCells
	 *            Number of cells to leave empty
	 *
	 */
	public void generateBoard(int size, int blankCells) {
		int[][] board = getEmptyBoard(size);

		readyBoard = new int[size][size];
		Position[] positions = solver.getPositions(board);
		int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		shuffle(numbers);
		generateRandomBoard(positions, 0, numbers, board);
		setFullBoard(board);
		while (!removeRandomCells(positions, board, blankCells, 0)) {
			board = getEmptyBoard(size);
			generateRandomBoard(positions, 0, numbers, board);
			setFullBoard(board);
		}
		setReadyBoard(board);
	}

	public void setFullBoard(int[][] board) {
		fullBoard = new int[board.length][board.length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				fullBoard[i][j] = board[i][j];
			}
		}
	}

	public void setReadyBoard(int[][] board) {
		readyBoard = new int[board.length][board.length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				readyBoard[i][j] = board[i][j];
			}
		}
	}

	/**
	 * Removes defined number of spaces of Sudoku board, leaving them blank.
	 * Each loop it removes two random spaces symetrically positioned.
	 * 
	 * @param positions
	 *            Array of board positions
	 * @param board
	 *            Current Sudoku board
	 * @param amount
	 *            Amount of spaces to remove
	 * @param a
	 *            Number of times the function has been called.
	 *
	 * @return True if it worked correctly, false if the function is called too
	 *         many times, preventing buffer overflow
	 */
	private boolean removeRandomCells(Position[] positions, int[][] board,
			int amount, int a) {
		if (a > 100){
			System.out.println("Reached max calls!");
			return false;
	}
		if (amount <= 0) {
			return true;
		}
		Random r = new Random();
		int l = positions.length;
		int rIndex = r.nextInt(l);
		Position p = positions[rIndex];
		Position invP = positions[l - rIndex - 1];
		if (!solver.isEmpty(p.row, p.col, board)) {
			int aux = board[p.row][p.col];
			int aux2 = board[invP.row][invP.col];
			board[p.row][p.col] = 0;
			board[invP.row][invP.col] = 0;
			if (solver.hasUniqueSolution(board)) {
				return removeRandomCells(positions, board, amount - 2, a+1);
			} else {
				System.out.println("No unique solution!");
				board[p.row][p.col] = aux;
				board[invP.row][invP.col] = aux2;
				return removeRandomCells(positions, board, amount, a+1);
			}
		} else {
			return removeRandomCells(positions, board, amount, a);
		}

	}

	/**
	 * Shuffles an array using Fisher-Yates algorithm
	 * 
	 * @param array
	 *            Array to be shuffled
	 */
	public void shuffle(int[] array) {
		Random r = new Random();
		int arrayLength = array.length;
		for (int i = arrayLength - 1; i > 0; i--) {
			int index = r.nextInt(i + 1);
			int aux = array[index];
			array[index] = array[i];
			array[i] = aux;
		}
	}

	/**
	 * Generates full Sudoku board and returns it.
	 * 
	 * @param positions
	 *            Array of board positions
	 * @param index
	 *            Current index of positions array
	 * @param numbers
	 *            Array of available numbers for each board cell.
	 * @param board
	 * @return Full Sudoku board
	 */
	private boolean generateRandomBoard(Position[] positions, int index,
			int[] numbers, int[][] board) {
		if (index >= positions.length) {
			return true;
		}
		shuffle(numbers);
		Position p = positions[index];
		for (int i = 0; i < 9; i++) {

			if (solver.isValid(p.row, p.col, numbers[i], board)) {
				board[p.row][p.col] = numbers[i];
				if (generateRandomBoard(positions, index + 1, numbers, board)) {
					return true;
				}
				board[p.row][p.col] = 0;
			}
		}

		return false;
	}

	/**
	 * Prints board in console
	 * 
	 * @param board
	 */
	public void printBoard(int[][] board) {
		System.out.println();
		// for (int[] a : board) {
		// System.out.println(Arrays.toString(a));
		// }
		for (int i = 0; i < board.length; i++) {
			System.out.print("[ ");
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == 0)
					System.out.print("  ");
				else
					System.out.print(board[i][j] + " ");
			}
			System.out.print("]");
			System.out.println();
		}
		System.out.println();
	}

}
