package Main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import Characters.Player;
import Projectiles.Projectile;

/*
 * The player MUST be the first node. It can NEVER be removed.
 */

//Holds all of the characters (so the holder class isn't as clustered).
public class CharacterHolder 
{
	Holder holder;
	
	final int MONSTERS = 0; //A constant, which can be used to access the monsterList.
	final int NPCS = 1; //A constant, which can be used to access the npcList.
	final int PROJECTILES = 2; //A constant, which can be used to access the projectileList.
	
	Player player; //The player.
	Queue<Character> monsterList = new LinkedList<Character>(); //The list of monsters.
	Queue<Character> npcList = new LinkedList<Character>(); //The list of npcs.
	Queue<Projectile> projectileList = new LinkedList<Projectile>(); //The list of projectiles.
	
	Queue lists[] = { monsterList, npcList, projectileList }; //The master list, which contains the other lists.
	
	//Initalizes the variable, and creates the player.
	//Further interaction will occur from the world, or other classes.
	public CharacterHolder(Holder h)
	{
		holder = h;
		createPlayer();
	}
	
	//Creates the player.
	public void createPlayer()
	{
		player = new Player(holder);
	}
	
	//Operations: O(n); sufficent.
	public void delete(Character c, int n) //Deletes a character from a list.
	{
		//Searches entire queue. NOTHING CAN BE ADDED DURING THIS TIME.
		for (int i = 0;i < lists[n].size();++i)
		{
			Character currentChar = (Character)lists[n].poll();
			
			if (currentChar != c) lists[n].add(currentChar);
			else break;
		}
	}
	
	//Operations: O(1); sufficent.
	public void add(Character c, int n)
	{
		lists[n].add(c); //Adds the character to the list.
	}
	
	//HashMap<Character, node> charLoc = new HashMap<Character, node>();
	
	/*//Simulates a Queue, but it can access indexes, making updating much faster.
	public class node
	{
		Character character;
		node connectedTo;
		node connectedFrom;
		
		public node(Character c, node t, node f)
		{
			character = c;
			connectedTo = t;
			connectedFrom = f;
		}
	}*/
}
