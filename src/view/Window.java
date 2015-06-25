package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.SudokuGenerator;

public class Window extends JFrame {

	/**
	 * @author Benjamin Diaz
	 */
	private static final long serialVersionUID = -3739008754324139579L;

	public BoardPanel boardPanel;
	public JPanel buttonPanel;
	public JPanel topPanel;
	public JLabel topLabel;
	public JButton checkButton, optionsButton;
	public SudokuGenerator sg;
	public int boardSize = 9;
	private final int EASY = 1, MEDIUM = 2, HARD = 3, VERY_HARD = 4, EVIL = 5;

	public Window() {
		super("SudokuMaster");
		setLayout(new BorderLayout(20, 20));
		addMenu(this);
		addBoardPanel(this);
		addButtonPanel(this);
		addTopPanel(this);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		boardPanel.createSudoku(boardSize, EASY);
	}

	private void addMenu(final Window window) {
		JMenuBar menu = new JMenuBar();
		JMenu sudokuMenu = new JMenu("Sudoku");
		JMenuItem newSudoku = new JMenuItem("Nuevo Sudoku...");
		newSudoku.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				topLabel.setText("Cargando Sudoku...");
				window.remove(boardPanel);
				boardPanel = new BoardPanel();
				window.add(boardPanel);
				Object[] selectionValues = { "Fácil", "Medio", "Difícil", "Muy Difícil", "Endemoniado" };
				String initialSelection = "Fácil";
				String selected = "";
				Object selection = JOptionPane.showInputDialog(null,
						"Elige la dificultad:", "Nuevo Sudoku",
						JOptionPane.QUESTION_MESSAGE, null, selectionValues,
						initialSelection);
				selected = selection.toString();
				
				switch (selected) {
				case "Fácil":
					boardPanel.createSudoku(boardSize, EASY);
					break;
				case "Medio":
					boardPanel.createSudoku(boardSize, MEDIUM);
					break;
				case "Difícil":
					boardPanel.createSudoku(boardSize, HARD);
					break;
				case "Muy Difícil":
					boardPanel.createSudoku(boardSize, VERY_HARD);
					break;
				case "Endemoniado":
					boardPanel.createSudoku(boardSize, EVIL);
					break;
				}
				topLabel.setText("Buena Suerte!");
			}
		});
		JMenuItem exitSudoku = new JMenuItem("Salir");
		exitSudoku.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(EXIT_ON_CLOSE);

			}
		});
		sudokuMenu.add(newSudoku);
		sudokuMenu.add(exitSudoku);
		JMenu helpMenu = new JMenu("Ayuda");
		JMenuItem aboutSudoku = new JMenuItem("Acerca de SudokuMaster");
		helpMenu.add(aboutSudoku);
		menu.add(sudokuMenu);
		menu.add(helpMenu);
		window.setJMenuBar(menu);
	}

	private void addBoardPanel(Window window) {
		boardPanel = new BoardPanel();
		window.add(boardPanel, BorderLayout.CENTER);
		boardPanel.repaint();
	}

	private void addTopPanel(Window window) {
		topPanel = new JPanel();
		topLabel = new JLabel("Buena Suerte!");
		topLabel.setFont(new Font("Arial Bold", Font.PLAIN, 20));
		topPanel.add(topLabel);
		window.add(topPanel, BorderLayout.PAGE_START);

	}

	private void addButtonPanel(Window window) {
		buttonPanel = new JPanel();
		checkButton = new JButton("Revisar");
		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boardPanel.repaint();
				int errors = 0;
				int blankSpaces = 0;
				for (int i = 0; i < boardSize; i++) {
					for (int j = 0; j < boardSize; j++) {
						int cellValue = boardPanel.cells[i][j].getValue();
						if (cellValue != -1) {
							if (cellValue != 0
									&& cellValue != boardPanel.fullBoard.board[i][j]) {
								++errors;
							} else if (cellValue == 0) {
								++blankSpaces;
							}
						} else {
							System.out
									.println("Por favor ingrese solo numeros entre 1 y 9!");
							topLabel.setText("Por favor ingrese solo numeros entre 1 y 9!");
							return;
						}
					}
				}
				if (errors != 0) {
					if (errors == 1) {
						System.out.println("Hay " + errors + " error!");
						topLabel.setText("Hay " + errors + " error!");
					} else {
						System.out.println("Hay " + errors + " errores!");
						topLabel.setText("Hay " + errors + " errores!");
					}

				} else if (blankSpaces != 0) {
					System.out.println("Faltan " + blankSpaces
							+ " espacios. Ánimo!");
					topLabel.setText("Faltan " + blankSpaces
							+ " espacios. Ánimo!");
				} else {
					System.out.println("Lo lograste! Felicitaciones!");
					topLabel.setText("Lo lograste! Felicitaciones!");
				}

			}
		});
		buttonPanel.add(checkButton);
		optionsButton = new JButton("Opciones");
		buttonPanel.add(optionsButton);
		window.add(buttonPanel, BorderLayout.PAGE_END);
	}
}
