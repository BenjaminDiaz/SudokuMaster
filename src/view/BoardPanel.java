package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Board;
import model.Position;
import model.SudokuGenerator;

/**
 * 
 * @author Benjamin Diaz
 *
 */

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = -3256290564832372809L;

	public Cell[][] cells;
	public SudokuGenerator sg;
	public Board board; // Board with removed spaces
	public Board fullBoard; // Complete board used for comparing results


	public BoardPanel(Window window, int boardSize, int difficulty) {
		setLayout(new GridLayout(9, 9));
		Border blackline = BorderFactory.createLineBorder(Color.black);
		setBorder(blackline);
		cells = new Cell[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				cells[i][j] = new Cell(0);
				add(cells[i][j]);
			}
		}
		sg = new SudokuGenerator();
		sg.generate(difficulty);
		board = sg.diggedBoard;
		fullBoard = sg.fullBoard;
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				Position p = new Position(i, j);
				cells[i][j].value = board.getCell(p);
			}
		}
		repaint();
		revalidate();
	}

	public BoardPanel(BoardPanel sudoku) {
		cells = sudoku.cells;
		board = sudoku.board;
		fullBoard = sudoku.fullBoard;
		repaint();
		revalidate();

	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintChildren(g);
		for (int i = 0; i <= cells.length; i++) {
			g.setColor(Color.BLACK);
			g.drawLine(i * Cell.size, 0, i * Cell.size, cells.length
					* Cell.size);
		}
		for (int i = 0; i <= cells.length; i++) {
			g.setColor(Color.BLACK);
			g.drawLine(0, i * Cell.size, cells.length * Cell.size, i
					* Cell.size);
		}
		for (int i = 0; i <= cells.length; i += 3) {
			g.setColor(Color.BLACK);
			g.drawLine(i * Cell.size + 1, 0, i * Cell.size + 1, cells.length
					* Cell.size);
		}
		for (int i = 0; i <= cells.length; i += 3) {
			g.setColor(Color.BLACK);
			g.drawLine(0, i * Cell.size + 1, cells.length * Cell.size, i
					* Cell.size + 1);
		}
	}

}
