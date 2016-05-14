package ecomponent;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Services.ServiceResources;

/*
 * A regular components.
 * This is a class, where all other components are extended from.
 * It holds what devices it is connected to, and what is connected to it.
 */

public class ecomp extends JLabel
{
	public ArrayList<Integer> connectedTo = new ArrayList<Integer>(); //What components it is connected to.
	
	public Breadboard b;
	
	public int[] pinXDist; //The x distance that the pins are from pin 0.
	public int[] pinYDist; //The y distance that the pins are from pin 0.
	
	public int inputVolt = 0;
	
	public ImageIcon image;
	
	public ServiceResources sr;
	
	public ecomp(Holders.Breadboard b)
	{
		sr = b.sr;
	}
	
	public void resize(double scaleFactor)
	{
	    Image newimg = image.getImage().getScaledInstance((int)(image.getIconWidth()*scaleFactor),
	    		(int)(image.getIconHeight()*scaleFactor), java.awt.Image.SCALE_SMOOTH);  
	    
	    ImageIcon temp = new ImageIcon(newimg);
	    
	    this.setIcon(temp);
	    this.setSize(temp.getIconWidth(), temp.getIconHeight());
	}
	
	public void hasElectricity()
	{
	}
	
	public void passElectricity(int toPin)
	{
		b.setElectricity(b.getPinX(toPin));
	}
	
	public void changeOutputVoltage()
	{
		
	}
}
