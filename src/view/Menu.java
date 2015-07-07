package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.IOManager;
import model.Sudoku;

public class Menu extends JMenuBar {

	private static final long serialVersionUID = 7901387255695465700L;
	private JMenu sudokuMenu;
	private JMenuItem newSudoku, exitSudoku, saveSudoku, loadSudoku;
	private Window window;
	
	private void createSudoku(int difficulty) {
		window.reCreateSudoku(difficulty);
	}
	
	private void save() {
		IOManager io = new IOManager();
		io.saveToFile(window.getSudoku());
	}
	
	private void load() {
		IOManager io = new IOManager();
		Sudoku sudoku = io.readFromFile();
		window.setSudoku(sudoku);
		window.reset();		
	}
	private void setLabel(String string) {
		window.setLabel(string);
		
	}
	public Menu(Window window) {
		this.window = window;
		sudokuMenu = new JMenu("Sudoku");
		newSudoku = new JMenuItem("Nuevo Sudoku...");
		newSudoku.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] selectionValues = { "Fácil", "Medio", "Difícil",
						"Muy Difícil", "Endemoniado" };
				String initialSelection = "Fácil";
				String selected = "";
				Object selection = JOptionPane.showInputDialog(null,
						"Elige la dificultad:", "Nuevo Sudoku",
						JOptionPane.QUESTION_MESSAGE, null, selectionValues,
						initialSelection);
				if(selection != null) {
					selected = selection.toString();
				}
				else {
					return;
				}
				
				switch (selected) {
				case "Fácil":
					createSudoku(1);
					break;
				case "Medio":
					createSudoku(2);
					break;
				case "Difícil":
					createSudoku(3);
					break;
				case "Muy Difícil":
					createSudoku(4);
					break;
				case "Endemoniado":
					createSudoku(5);
					break;
				}
				setLabel("Buena Suerte!");
			}


		});
		
		saveSudoku = new JMenuItem("Guardar");
		saveSudoku.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
				
			}
		});
		loadSudoku = new JMenuItem("Cargar");
		loadSudoku.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				load();
				
			}
		});
		
		exitSudoku = new JMenuItem("Salir");
		exitSudoku.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);;

			}
		});

		sudokuMenu.add(newSudoku);
		sudokuMenu.add(saveSudoku);
		sudokuMenu.add(loadSudoku);
		sudokuMenu.add(exitSudoku);
		JMenu helpMenu = new JMenu("Ayuda");
		JMenuItem aboutSudoku = new JMenuItem("Acerca de SudokuMaster");
		aboutSudoku.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "www.github.com/BenjaminDiaz");
				
			}
		});
		helpMenu.add(aboutSudoku);
		add(sudokuMenu);
		add(helpMenu);
	}
}
