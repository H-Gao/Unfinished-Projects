package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;

import Startup.PlayAnimation;

/*
 * The holder class is used to hold essential variables; characters, entities, etc.
 * It can also detect basic mouse movements, and keyboard strokes.
 * It is the central section of the game.
 */
public class Holder extends JFrame
{
	CharacterHolder ch;
	
	public Holder()
	{
		init(); //Initalizes the variables, and creates the basic options.
		ch = new CharacterHolder(this);
	}
	
	//Creates the default settings for the holder.
	public void init()
	{
		this.setTitle("RPG");
		this.setSize(800, 600);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		PlayAnimation p = new PlayAnimation(this);
	}
	
	public static void main(String[] args) { Holder h = new Holder(); }
}
