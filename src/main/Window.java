package main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	private static final long serialVersionUID = -4810618286807932601L;

//Constructor 
	public Window(int width, int height, String title, Game game){
		JFrame frame = new JFrame(title);  //frame of the window
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //basically the "X" button
		frame.setResizable(false); //can't resize the window
		frame.setLocationRelativeTo(null);//window would start in top left otherwise(not needed but nice)
		frame.add(game); //adds game class into the frame
		frame.setVisible(true); 
		game.start(); //running start method we created in Game
	}
}
