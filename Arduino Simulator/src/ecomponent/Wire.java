package ecomponent;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Components.WireSelection;

public class Wire extends ecomp implements MouseListener
{
	boolean isViewing = false;
	
	String RED = "Red";
	String BLUE = "Blue";
	String GREEN = "Green";
	String YELLOW = "Yellow";
	
	Breadboard b;
	WireSelection ws;
	
	public Queue<JLabel> wires = new LinkedList<JLabel>();
	
	public Wire(Breadboard br)
	{
		super(br.b);
		
		b = br;
	}
	
	public void addWire(String colour, int x, int y)
	{
		System.out.println("It's being made.");
		image = new ImageIcon("C:\\Judo Workspace\\AlgorithmIdeas\\Wire_" + colour + ".png");
		
		JLabel temp = new JLabel();
		temp.setIcon(new ImageIcon("C:\\Judo Workspace\\AlgorithmIdeas\\Wire_" + colour + ".png"));
		temp.addMouseListener(this);
		this.setSize(4, 4);
		this.resize(temp, sr.scale);
		temp.setLocation(x, y);
		b.b.add(temp);
		
		wires.add(temp);
		
		b.b.repaint();
		b.b.add(b);
	}
	
	public void resize(double scaleFactor)
	{
		if (wires.size() == 0) return;
		
	    Image newimg = image.getImage().getScaledInstance((int)(image.getIconWidth()*scaleFactor),
	    		(int)(image.getIconHeight()*scaleFactor), java.awt.Image.SCALE_SMOOTH);  
	    
	    ImageIcon temp = new ImageIcon(newimg);
				
		for (int i = 0;i < wires.size();++i)
		{	    
			JLabel polled = wires.poll();
			
		    polled.setIcon(temp);
		    polled.setSize(temp.getIconWidth(), temp.getIconHeight());
		    wires.add(polled);
		}
	}
	
	public void resize(JLabel l, double scaleFactor)
	{
	    Image newimg = image.getImage().getScaledInstance((int)(image.getIconWidth()*scaleFactor),
	    		(int)(image.getIconHeight()*scaleFactor), java.awt.Image.SCALE_SMOOTH);  
	    
	    ImageIcon temp = new ImageIcon(newimg);
				
		l.setIcon(temp);
		l.setSize(temp.getIconWidth(), temp.getIconHeight());
	}
	
	public void deleteWire()
	{
		while (!wires.isEmpty())
			b.b.remove(wires.poll());
		
		b.b.repaint();
	}

	public void mouseClicked(MouseEvent e) 
	{
		if (!isViewing)
		{
			ws = new WireSelection(b.b, this);
			isViewing = true;
		}
		else
		{
			ws.delete();
			isViewing = false;
		}
	}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
}
