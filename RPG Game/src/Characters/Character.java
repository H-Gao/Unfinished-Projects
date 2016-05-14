package Characters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Main.Holder;
import Projectiles.Projectile;
import Threads.animate;

public class Character extends JLabel
{
	Holder holder;
	
	public animate def; //The default animation.
	public animate oth; //The other animation.
	
	static String name;
	int health;
	int movementSpeed;
	int direction;
	
	public Character(Holder h)
	{
		holder = h;
	}
	
	public void goTo(int x, int y)
	{
		
	}
	
	public void onHit(Projectile p)
	{
		int damage = p.damage; //The damages that the projectile does.
		p.onCollided(this); //Handles all effects or other special items.
	}
	
	public void playAnimation(String path, int delay, boolean loop)
	{
		if (oth == null || !oth.isAlive())
		{
			oth = new animate(holder, this, delay, path, loop, true);
			oth.start();
		}
	}
	
	public void startDefaultAnim() {}
	
	public void loadDetails(String path)
	{
		try 
		{
			Scanner in = new Scanner(new FileReader(path));
			Object[] lookingFor = { health, movementSpeed, direction };
			
			String input;
			for (int i = 0;(input = in.nextLine()) != null;++i) lookingFor[i] = Integer.parseInt(input);
			in.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
