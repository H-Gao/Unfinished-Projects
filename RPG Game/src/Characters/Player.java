package Characters;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

import Icons.Health;
import Main.Holder;
import Projectiles.Projectile;
import Threads.DoAfter;
import Threads.WaitUntil;
import Threads.animate;

public class Player extends Character
{
	public Holder holder;
	boolean inVulnerable = false;
	
	//The keys that will be used to perform a specific action. Refer to index on bottom.
	char[] keys = {'a', 'd', 'w', 's'};
	
	int strength;
	int intelligence;
	int dexterity;
	
	byte step = 0;
	public Stack<Health> healthIcons = new Stack<Health>();
	
	public Player(Holder h) 
	{
		super(h);
		name = "Player";
		holder = h;
		
		init();
		
		loadDetails("C:\\Judo Workspace\\.RPGGame\\Resources\\CharacterDetails\\Player.txt");
		startDefaultAnim();
		keybinding();
		createHealth();
	}
	
	public void init()
	{
		System.out.println("Created.");
		this.setLocation(0, 0);
		holder.add(this);
		
		holder.repaint();
	}
	
	public void createHealth()
	{
		for (int i = 0;i < (health/4);++i)
		{
			Health h = new Health(this, 4);
		}
		
		if (health%4 != 0)
		{
			Health h = new Health(this, health%4);
		}
	}
	
	public void startDefaultAnim()
	{
		def = new animate(holder, this, 1000, "C:\\Judo Workspace\\AlgorithmIdeas\\Animations\\Characters"
				+ "\\Player\\Normal\\Left\\Player_NormalLeft ", true, true);
		
		def.start();
	}
	
	public void onHit(Projectile p)
	{
		if (inVulnerable) return;
		
		int damage = p.damage; //The damages that the projectile does.
		int totalValue = 0; //The total amount of damage that is taken off the hearts.
		p.onCollided(this); //Handles all effects or other special items.
		
		//Waits until it is empty or until it has taken enough hearts.
		while (!healthIcons.isEmpty() && totalValue < damage)
		{
			Health popped = healthIcons.pop(); //The icon.
			totalValue += popped.value;
			holder.remove(popped);
		}
		
		int difference = totalValue - damage;
		
		if (difference != 0)
		{
			Health h = new Health(this, difference);
		}
		
		health -= damage;
		inVulnerable = true;
		
		//Makes sure the player cannot be hit for a fifth of a second.
		WaitUntil wait = new WaitUntil(200, new DoAfter() {
			public void performs(){
				inVulnerable = false; }});
	}
	
	public void shiftPlayer(int x, int y)
	{
		this.setLocation(this.getX() + x, this.getY() + y);
	}
	
	public void loadDetails(String path)
	{
		try 
		{
			Scanner in = new Scanner(new BufferedReader(new FileReader(path)));
			
			name = in.nextLine();
			health = Integer.parseInt(in.nextLine());
			movementSpeed = Integer.parseInt(in.nextLine());
			strength = Integer.parseInt(in.nextLine());
			intelligence = Integer.parseInt(in.nextLine());
			dexterity = Integer.parseInt(in.nextLine());
			
			in.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void keybinding()
	{
		//Does the keybinding for all keys.
		for (int i = 0;i < keys.length;++i)
		{
			//Creates an input map, matching the keystroke to the action.
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke
					(KeyEvent.getExtendedKeyCodeForChar(keys[i]), 0), keys[i]);
			
			//Does the action here.
			this.getActionMap().put(keys[i], new whenKeyDown(keys[i]));
		}
	}
	
	public class whenKeyDown extends AbstractAction
	{
		char key;
		
		public whenKeyDown(char k)
		{
			key = k;
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			if (key == keys[0]) shiftPlayer(-movementSpeed, 0);
			
			if (key == keys[1]) shiftPlayer(movementSpeed, 0);
			
			if (key == keys[2]) shiftPlayer(0, -movementSpeed);
			
			if (key == keys[3]) shiftPlayer(0, movementSpeed);
			
			holder.repaint();
			playAnimation("C:\\Judo Workspace\\AlgorithmIdeas\\Animations\\Characters"
					+ "\\Player\\Walking\\Down\\Player_WalkingDown" + step + "_", 500, false);
			step = (byte)(++step%2);
		}
	}
}

/*
 * Key 1: Move left.
 * Key 2: Move right.
 * Key 3: Move up.
 * Key 4: Move down.
*/