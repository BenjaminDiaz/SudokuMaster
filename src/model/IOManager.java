package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import view.BoardPanel;

public class IOManager {
	
	public void saveToFile(BoardPanel sudoku) {
		try(FileOutputStream fos = new FileOutputStream("sudoku.bin")) {
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(sudoku);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BoardPanel readFromFile() {
		try(FileInputStream fis = new FileInputStream("sudoku.bin")){
			ObjectInputStream ois = new ObjectInputStream(fis);
			BoardPanel sudoku = (BoardPanel) ois.readObject();
			ois.close();
			return sudoku;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
