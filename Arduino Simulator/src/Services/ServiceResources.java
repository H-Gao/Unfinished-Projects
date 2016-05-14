package Services;

import java.util.ArrayList;

import ecomponent.ecomp;

public class ServiceResources 
{
	ArrayList<ecomp> allComp = new ArrayList<ecomp>(); //All of the ecomponents
	
	public double scale = 1;
	
	public void addComp(ecomp c)
	{
		allComp.add(c);
	}
	
	public void resize(double s)
	{
		scale = s;
		
		for (int i = 0;i < allComp.size();++i)
			allComp.get(i).resize(scale);
	}
}
