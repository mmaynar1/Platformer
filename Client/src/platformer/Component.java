package platformer;

import java.applet.*;
import javax.swing.*;
import java.awt.*;

public class Component extends Applet implements Runnable
{
	private static final long serialVersionUID = 1L;
	private static int pixelSize = 2;
	
	//Other classes can see and use, contains dimensions of window
	public static Dimension size = new Dimension(700,560);
	public static Dimension pixel = new Dimension(size.width/pixelSize, size.height/pixelSize);
	
	public static String name = "Platformer Adventure";
	
	public static boolean isRunning = false;
	
	private Image screen;
	
	public static Level level;
	
	public Component()
	{
		setPreferredSize(size);
	}
	public void start()
	{
		//Defining objects etc.
		level = new Level();
		
		//Starting game loop
		isRunning = true;
		new Thread(this).start();
	}
	public void stop()
	{
		isRunning = false;
	}
	public static void main (String args[])
	{
		Component component = new Component();
		JFrame frame = new JFrame();
		frame.add(component);
		
		//Sets frame to same size as component (size)
		frame.pack();
		
		frame.setTitle(name);
		frame.setResizable(false);
		
		//Center
		frame.setLocationRelativeTo(null);
		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		component.start();
	}
	public void tick()
	{
		
	}
	public void render()
	{
		Graphics g = screen.getGraphics();
		
		//Drawing things
		g.setColor(new Color(75,150,200));
		g.fillRect(0, 0, pixel.width, pixel.height);
		level.render(g);
		
		g = getGraphics();
		g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
		g.dispose();
	}
	public void run()
	{
		screen = createVolatileImage(pixel.width, pixel.height);
		
		while(isRunning)
		{
			//System.out.println("Running!");
			
			tick();
			render();
			
			
			try
			{
				Thread.sleep(5);
			}
			catch(Exception e)
			{
				
			}
		}
	}
}
