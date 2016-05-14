package Startup;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Main.Holder;
import Threads.animate;

public class PlayAnimation 
{
	public PlayAnimation(Holder h)
	{
		String animPath = "C:\\Judo Workspace\\AlgorithmIdeas\\Animations\\Startup\\Startup_ ";
		
		JLabel anim = new JLabel();
		anim.setSize(h.getWidth(), h.getHeight());
		h.add(anim);
		
		animate a = new animate(h, anim, 100, animPath, false, false);
		a.start();
		
		while (a.isAlive());
		h.remove(anim);
		h.repaint();
	}
}
