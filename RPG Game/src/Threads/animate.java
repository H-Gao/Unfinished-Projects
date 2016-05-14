package Threads;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Characters.Character;

//This method will run as a separate thread, and changes the image of a label, making it look
//like it is being animated.
public class animate extends Thread implements Runnable
{
	boolean loop; //If it is looping, this is true.
	boolean isCharacter;
	boolean wait;
	
	public int i = 0; //Creates a counter.
	int delay; //The amount of times, before the frame changes.
	JFrame fr; //The container which is connected to the monitor (for repainting).
	JLabel frame; //The label that will be animated.
	String path; //The location of the images, before the counter begins.
	
	//The constructor, used to initalize the variables.
	public animate(JFrame fr, JLabel f, int d, String p, boolean l, boolean c)
	{
		this.fr = fr;
		frame = f;
		delay = d;
		path = p;
		
		loop = l;
		isCharacter = c;
	}
	
	//The actual animating gets done here.
	public void run() 
	{
		wait = false;
		
		try //In case an exception appears.
		{
			ImageIcon icon = new ImageIcon(path + "(" + 1 + ").png"); //The first icon.
			frame.setSize(icon.getIconWidth(), icon.getIconHeight()); //Ensures that they are the correct size.
			
			while (true) //Makes it loop at least once, but it can countinue, if it is looping forever.
			{
				while (new File(path + "(" + (++i) + ").png").exists()) //If it is not the end of the animation.
				{
					//Sets the label as the icon, making it appear as if it was moving.
					frame.setIcon(new ImageIcon(path + "(" + i + ").png"));
					fr.repaint(); //Ensures that it is displayed on the monitor.
				
					this.sleep(delay); //Waits for a set amount of time.
				}
				
				if (!loop) break; //If it loops, it will countinue forever. Otherwise, 
				i = 0; //Resets it.
			}
		}
		catch (Exception e) //Catches the exception.
		{
			e.printStackTrace(); //Displays the exception for debugging.
		}
	}
}
