package model;

/**
 * 
 * @author Benjamin Diaz
 *
 */
public class Position {
	int row;
	int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Returns position in terms of row and column. First digit is row, second
	 * is column. E.g: '01', '24', '09', '00', etc.
	 */
	@Override
	public String toString() {
		return row + "" + col;
	}

}
