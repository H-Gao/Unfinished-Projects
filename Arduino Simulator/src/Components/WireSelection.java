package Components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import ecomponent.Wire;
import Holders.Breadboard;

public class WireSelection implements ActionListener
{
	PullDownMenu m = new PullDownMenu();
	JButton delete = new JButton("Delete");
	
	Wire w;
	
	Breadboard b;
	
	String[] str = { "Red", "Blue", "Green", "Yellow" };
	
	public WireSelection(Breadboard br, Wire wi)
	{
		b = br;
		w = wi;
		
		for (int i = 0;i < str.length;++i)
			m.addOption(str[i]);
		
		m.setSize(100, 25);
		m.setLocation(0, 0);
		m.add(b);
		
		initButtons();
		setListeners();
	}
	
	public void initButtons()
	{
		delete.addActionListener(this);
		delete.setSize(100, 40);
		delete.setLocation(100, 0);
		b.add(delete);
		
		b.repaint();
	}
	
	public void delete()
	{
		b.remove(delete);
		
		for (int i = 0;!m.options.isEmpty();++i)
		{
			if (i >= m.options.size()) i = 0;
			
			b.remove(m.options.get(i));
			m.options.remove(i);
		}
		
		b.repaint();
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		String sourceText = ((JButton)e.getSource()).getText();
		
		switch (sourceText)
		{
			case "Delete":
			{
				w.deleteWire();
				delete();
				
				return;
			}
		}
		
		for (int i = 0;i < w.wires.size();++i)
		{
			JLabel polled = w.wires.poll();
			System.out.println(polled);
			
			polled.setIcon(new ImageIcon("C:\\Judo Workspace\\AlgorithmIdeas\\Wire_" + sourceText + ".png"));
			w.wires.add(polled);
		}
		
		b.repaint();
	}
	
	public void setListeners()
	{
		ArrayList<JButton> b = m.options;
		
		for (int i = 0;i < b.size();++i)
			b.get(i).addActionListener(this);
	}
}
