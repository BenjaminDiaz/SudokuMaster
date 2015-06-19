package core;

import java.awt.EventQueue;

import view.Window;

/**
 * Main class
 * 
 * @author Benjamin Diaz
 *
 */

public class SudokuMain {

//	public static void main(String[] args) {
//		SudokuGenerator sg = new SudokuGenerator();
//		int[][] sudoku = sg.generateBoard(9,10);
//		sg.printBoard(sudoku);
//	}
	public static void main(String[] args){
	    EventQueue.invokeLater(new Runnable(){
	          public void run(){    
	        	Window window = new Window();
	            window.setVisible(true);
	         }
	     });
	}
}
