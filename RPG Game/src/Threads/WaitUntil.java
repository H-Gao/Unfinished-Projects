package Threads;

public class WaitUntil extends Thread implements Runnable
{
	int waitTime;
	DoAfter doAfter;
	
	public WaitUntil(int w, DoAfter d)
	{
		waitTime = w;
		doAfter = d;
		
		this.start();
	}

	public void run() 
	{
		try
		{
			Thread.sleep(waitTime);
			doAfter.performs();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
