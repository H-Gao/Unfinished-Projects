package Holders;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JFrame;

import Services.ServiceResources;
import ecomponent.LED;

public class Breadboard extends Visuals implements MouseWheelListener
{
	public ServiceResources sr = new ServiceResources();
	
	public Breadboard(String name)
	{
		this.setName(name + " Breadboard View");
		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.addMouseWheelListener(this);
		
		br = new ecomponent.Breadboard(this);
		sr.addComp(br);
		repaint();
		
		LED l = new LED(this);
		sr.addComp(l);
		
		this.add(l);
		this.add(br);
		
		System.out.println(br);
		
		repaint();
	}

	public void mouseWheelMoved(MouseWheelEvent e) 
	{
		double s = sr.scale + 0.1*e.getPreciseWheelRotation();
		
		if (s != 0) sr.resize(s);
	}
}
