package Components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class PullDownMenu
{
	int x = 0;
	int y = 0;
	
	int w = 0;
	int h = 0;
	
	ArrayList<JButton> options = new ArrayList<JButton>();
	
	public PullDownMenu()
	{
		
	}
	
	public void setSize(int w, int h)
	{
		this.w = w;
		this.h = h;
		
		for (int i = 0;i < options.size();++i)
			options.get(i).setBounds(x, y + h*i, w, h);
	}
	
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		for (int i = 0;i < options.size();++i)
			options.get(i).setBounds(x, y + h*i, w, h);
	}
	
	public void add(Holders.Breadboard b)
	{
		for (int i = 0;i < options.size();++i)
			b.add(options.get(i));
	}
	
	public void addOption(String s)
	{
		JButton temp = new JButton(s);
		temp.setSize(w, h);
		temp.setLocation(x, y);
		options.add(temp);
	}
	
	public void addOption(JButton b)
	{
		options.add(b);
	}
}
