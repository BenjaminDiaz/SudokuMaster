package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import model.Sudoku;
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
	private Sudoku sudoku;

	public Window() {
		super("SudokuMaster");
		setLayout(new BorderLayout(20, 20));
		addMenu();
		addBoardPanel(1);
		addButtonPanel();
		addTopPanel();
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

	}

	public void setLabel(String message) {
		topLabel.setText(message);
	}

	private void addMenu() {
		JMenuBar menu = new Menu(this);
		setJMenuBar(menu);
	}

	private void addBoardPanel(int difficulty) {
		sg = new SudokuGenerator();
		sudoku = sg.generate(difficulty);
		boardPanel = new BoardPanel(this);
		add(boardPanel, BorderLayout.CENTER);
		boardPanel.repaint();
	}
	
	private void addBoardPanel() {
		boardPanel = new BoardPanel(this);
		add(boardPanel, BorderLayout.CENTER);
		boardPanel.repaint();
	}
	
	public BoardPanel getBoardPanel() {
		return boardPanel;
	}
	
	public void reCreateSudoku(int difficulty) {
		getContentPane().removeAll();
		addTopPanel();
		addBoardPanel(difficulty);		
		addButtonPanel();
		revalidate();
	}
	public void reset() {
		getContentPane().removeAll();
		addTopPanel();
		addBoardPanel();		
		addButtonPanel();
		revalidate();
	}

	private void addTopPanel() {
		topPanel = new JPanel();
		topLabel = new JLabel("Buena Suerte!");
		topLabel.setFont(new Font("Arial Bold", Font.PLAIN, 20));
		topPanel.add(topLabel);
		add(topPanel, BorderLayout.PAGE_START);

	}

	private void addButtonPanel() {
		buttonPanel = new JPanel();
		checkButton = new JButton("Revisar");
		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boardPanel.repaint();
				int errors = boardPanel.sudoku.getErrors();
				int blankSpaces = boardPanel.sudoku.getBlanks();

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
		add(buttonPanel, BorderLayout.PAGE_END);
	}

	public void setBoardPanel(BoardPanel bp) {
		getContentPane().removeAll();
		addTopPanel();
		boardPanel = bp;
		add(boardPanel);
		addButtonPanel();
		revalidate();
	}

	public Sudoku getSudoku() {
		return sudoku;
	}

	public void setSudoku(Sudoku s) {
		sudoku = s;		
	}
	

}
