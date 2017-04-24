package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import controller.Controller;

public class DrawPanel extends JPanel
{
	private Color current;
	private Color next;
	private int colorNumb;
	private int currentNumb;
	private int deltaRed;
	private int deltaGreen;
	private int deltaBlue;
	private double leftOverRed;
	private double leftOverGreen;
	private double leftOverBlue;
	private ShapePanel shapes;
	private JButton button;
	private SpringLayout layout;
	private Controller baseController;
	private double LR;
	private double LG;
	private double LB;
	public DrawPanel(Controller baseController){
super();
this.baseController = baseController;
shapes = new ShapePanel(baseController);
button = new JButton("button");
layout = new SpringLayout();
current = Color.white;
currentNumb = 0;
colorNumb = 120;
NextColor();
TimerTask colorChange = new TimerTask()
{
	
	public void run()
	{
		if(currentNumb<colorNumb){
//		deltaRed = (next.getRed()-current.getRed())/colorNumb;
//		deltaGreen = (next.getGreen() - current.getGreen())/colorNumb;
//		deltaBlue = (next.getBlue() - current.getBlue())/colorNumb;
		int red = getBackground().getRed();
		int green = getBackground().getGreen();
		int blue = getBackground().getBlue();
		if(currentNumb ==0)
		{
			LR = leftOverRed;
			LG = leftOverGreen;
			LB = leftOverBlue;
		}
		if((int) LR == 1)
		{
			red +=1;
			LR = LR -1;
		}
		else if((int)LR == -1)
		{
			red -=1;
			LR = LR - -1;
		}
		else
		{
			LR += leftOverRed;
		}
		if((int) LG == 1)
		{
			green +=1;
			LG = LG -1;
		}
		else if((int)LG == -1)
		{
			green -=1;
			LG = LG +1;
		}
		else
		{
			LG += leftOverGreen;
		}
		if((int) LB == 1)
		{
			blue +=1;
			LB = LB -1;
		}
		else if((int)LB == -1)
		{
			blue -=1;
			LB = LB +1;
		}
		else
		{
			LB += leftOverBlue;
		}

		setBackground(new Color(red+deltaRed,green+deltaGreen, blue + deltaBlue));
		shapes.setBackground(new Color(red+deltaRed,green+deltaGreen, blue + deltaBlue));
		currentNumb++;
		}
		
		else
		{
			currentNumb =0;
			NextColor();
		}
		
		
	}
};
Timer timer = new Timer();
timer.schedule(colorChange, 10,10);
setup();
setupLayout();
setupListeners();
	}

private void NextColor()
{
	current = this.getBackground();
//	int red = ((int)(Math.random()*40)+11)*PosNeg() + current.getRed();
//	int green = ((int)(Math.random()*40)+11)*PosNeg() + current.getGreen();
//	int blue = ((int)(Math.random()*40)+11)*PosNeg() + current.getBlue();
//	if(red>255)
//	{
//		red = 255;
//	}
//	if(red<0)
//	{
//		red = 0;
//	}
//	if(green>255)
//	{
//		green = 255;
//	}
//	if(green<0)
//	{
//		green = 0;
//	}
//	if(blue>255)
//	{
//		blue = 255;
//	}
//	if(blue<0)
//	{
//		blue = 0;
//	}
	int red = (int)(Math.random()*256);
	int green = (int)(Math.random()*256);
    int blue = (int)(Math.random()*256);
	next = new Color(red,green,blue);
	deltaRed = (next.getRed()-current.getRed())/colorNumb;
	deltaGreen = (next.getGreen() - current.getGreen())/colorNumb;
	deltaBlue = (next.getBlue() - current.getBlue())/colorNumb;
	leftOverRed = ((double)(next.getRed()-current.getRed())/(double)colorNumb) -(double)deltaRed;
	leftOverGreen = ((double)(next.getGreen()-current.getGreen())/(double)colorNumb) -(double)deltaGreen;
	leftOverBlue = ((double)(next.getBlue()-current.getBlue())/(double)colorNumb) -(double)deltaBlue;
}

private int PosNeg()
{
	int rand = (int)(Math.random()*2);
	if(rand == 0)
	{
		return -1;
	}
	return 1;
}
private void setup()
{
	this.setLayout(layout);
	this.add(button);
	this.add(shapes);
	}
private void setupLayout()
{

layout.putConstraint(SpringLayout.WEST, shapes, 700, SpringLayout.WEST, this);
layout.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, shapes);
layout.putConstraint(SpringLayout.EAST, button, -15, SpringLayout.WEST, shapes);
layout.putConstraint(SpringLayout.NORTH, shapes, 0, SpringLayout.NORTH, this);
layout.putConstraint(SpringLayout.SOUTH, shapes, 0, SpringLayout.SOUTH, this);
layout.putConstraint(SpringLayout.EAST, shapes, 0, SpringLayout.EAST, this);
}
private void setupListeners()
{
	button.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			shapes.addRectangles();
		}
		
	});
}

}
