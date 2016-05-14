package ecomponent;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Holders.Visuals;

public class LED extends ecomp implements MouseMotionListener
{
	int numPins = 2;
	
	boolean[] hasElec = new boolean[2];
	
	int[] pinXDist = { 8, 23 };
	int[] pinYDist = { 88, 88 };
	
	int[] currentX = {-1, -1};
	int[] currentY = {-1, -1};
	
	public LED(Visuals f)
	{
		super(f.br.b);
		
		image = new ImageIcon("C:\\Judo Workspace\\AlgorithmIdeas\\Led_Off.png");
		
		this.setIcon(image);
		this.setSize(image.getIconWidth(), image.getIconHeight());
		this.resize(sr.scale);
		this.setLocation(200, 200);
		this.addMouseMotionListener(this);
		f.add(this);
		
		b = f.br;
		
		f.repaint();
	}
	
	public void hasElectricity()
	{
		//Goes from 0 to the number of pins. Used to spread the electricity.
		for (int i = 0;i < numPins;++i)
		{
			if (!hasElec[i])
			{
				passElectricity(pinXDist[i]);
				hasElec[i] = true;
			}
		}
	}
	
	public void adjustLight(int v)
	{
		
	}
	
	public void attemptFit(int x, int y)
	{
		for (int i = 0;i < numPins;++i)
		{
			//Removes old pins from list.
			if (currentX[i] > -1)
			{
				Queue<ecomp> line = b.lines.get(currentX[i]);
				
				for (int a = 0;a < line.size();++a)
				{
					ecomp polled = line.poll();
					
					if (polled != this)
						line.add(polled);
					else break;
				}
			}
			
			int pinX = b.getPinX(x + pinXDist[i])-21;
			int pinY = b.getPinY(y + pinYDist[i]);
			
			if (pinX > -1 && pinY > -1)
			{
				System.out.println(pinX + " " + pinY);
				currentX[i] = pinX;
				currentY[i] = pinY;
				
				b.lines.get(currentX[i]).add(this);
			}
			else return;
		}
		
		b.getLines();
	}

	public void mouseDragged(MouseEvent e) 
	{
		this.setLocation(e.getXOnScreen() - 20, e.getYOnScreen() - 40);
		
		attemptFit(this.getX(), this.getY());
	}

	public void mouseMoved(MouseEvent e) {}
}
