package core;

import java.awt.EventQueue;

import view.Window;

/**
 * Based on the work of team FUXIA:
 * https://github.com/mihneagiurgea/fuxia
 * 
 * Digging algorithm developed and described by ZHANGroup: 
 * http://zhangroup.aporc.org/images/files/Paper_3485.pdf
 * 
 * @author Benjamin Diaz
 *
 */

public class SudokuMain {

	public static void main(String[] args){
	    EventQueue.invokeLater(new Runnable(){
	          public void run(){    
	        	Window window = new Window();
	            window.setVisible(true);
	         }
	     });
	}
}
