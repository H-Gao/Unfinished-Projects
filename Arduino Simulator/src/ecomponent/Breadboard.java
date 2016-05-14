package ecomponent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Services.ServiceResources;

public class Breadboard extends ecomp implements MouseListener, MouseMotionListener
{
	//Contains the lines, and the components withn them.
	HashMap<Integer, Queue<ecomp>> lines = new HashMap<Integer, Queue<ecomp>>();
	
	//Contains the different paths, so it can run when electricity is passed through them.
	HashMap<Integer, Queue<ecomp>> paths = new HashMap<Integer, Queue<ecomp>>();
	
	Wire w;
	
	Holders.Breadboard b;
	
	public Breadboard(Holders.Breadboard br)
	{
		super(br);
		
		b = br;
		
		image = new ImageIcon("C:\\Judo Workspace\\ArduinoSimulator\\Resources\\ecomp\\Breadboard.png");
		this.setIcon(image);
		this.setSize(731, 312);
		this.resize(sr.scale);
		this.setLocation(400, 0);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		b.add(this);
		
		for (int i = 0;i < 40;++i)
			lines.put(i, new LinkedList<ecomp>());
	}
	
	public int getPinX(int x)
	{
		for (int i = 0;i < 10;++i) if (x >= 15+70*i && x <= 70+70*i) 
			return (x - (15+70*i))/15 + i*4;
		//return (x - (16 + (x/71)*21))/14;
		
		return -1;
	}
	
	public int getPinY(int y)
	{
		if (y >= 22 && y <= 47) return (y - 22)/14;
		else if (y >= 67 && y <= 137) return (y - 65)/14 + 2;
		else if (y >= 176 && y <= 246) return (y - 176)/14 + 7;
		else if (y >= 266 && y <= 291) return (y - 266)/14 + 12;
			
		return -1; //It isn't on any pin.
	}
	
	public void setElectricity(int l)
	{
		Queue<ecomp> line = lines.get(l);
		
		for (int i = 0;i < line.size();++i)
		{
			ecomp polled = line.poll();
			
			polled.hasElectricity();
			line.add(polled);
		}
	}
	
	public void getLines()
	{
		for (int a = 0;a < lines.size();++a)
		{
			Queue<ecomp> line = lines.get(a);
			
			for (int b = 0;b < line.size();++b)
			{
				ecomp polled = line.poll();
				
				System.out.println("Line: " + a + " " + polled);
				line.add(polled);
			}
		}
	}
	
	public void mouseDragged(MouseEvent e) 
	{
		if (w != null) w.addWire(w.RED, e.getXOnScreen()-10, e.getYOnScreen()-36);
	}
	
	public void mouseMoved(MouseEvent e) 
	{
		System.out.println(getPinX(e.getX()) + " " + getPinY(e.getY()));
	}

	public void mousePressed(MouseEvent e) 
	{
		if (getPinX(e.getXOnScreen()-10) > -1 && getPinY(e.getYOnScreen()-36) > -1)
		{
			w = new Wire(this);
			sr.addComp(w);
		}
	}
	
	public void mouseReleased(MouseEvent e) 
	{
		if (getPinX(e.getXOnScreen()-10) < 0 || getPinY(e.getYOnScreen()-36) < 0)
			w.deleteWire();
	}
	
	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
}
