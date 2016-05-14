package Icons;

import java.util.Queue;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Characters.Player;

public class Health extends JLabel
{
	Player player;
	public int value;
	
	public Health(Player p, int v)
	{
		player = p;
		value = v;
		
		Stack<Health> q = p.healthIcons;
		int size = q.size();
		
		ImageIcon icon = new ImageIcon("C:\\Judo Workspace\\AlgorithmIdeas\\Animations\\Characters\\"
				+ "Player\\Icons\\Health_" + v + ".png");
		
		this.setIcon(icon);
		this.setSize(icon.getIconWidth(), icon.getIconHeight());
		this.setLocation((size%10)*icon.getIconWidth(), size/10 * icon.getIconHeight());
		q.add(this);
		p.holder.add(this);
	}
}
