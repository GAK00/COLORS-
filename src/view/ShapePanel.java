package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import controller.Controller;

public class ShapePanel extends JPanel
{
	private Controller baseController;
	private List<Rectangle> rectangleList;
	private Color inverted;
	public ShapePanel(Controller baseController)
	{
		super();
		inverted = Color.black;
		this.baseController = baseController;
		this.rectangleList = new ArrayList<Rectangle>();
		this.setMinimumSize(new Dimension(100,500));
	}
	
	
	public void addRectangles()
	{
		for(int index = 0; index < 500; index++)
		{
			int width = (int)(Math.random()*120)+1;
			int height = (int)(Math.random()*150)+15;
			int xCorner = (int)(Math.random()*(getWidth()-width));
			int yCorner = (int)(Math.random()*(getHeight()-height));
			Rectangle currentRectangle = new Rectangle(xCorner,yCorner,width,height);
			rectangleList.add(currentRectangle);
		}
		this.repaint();
	}
	@Override
	protected void paintComponent(Graphics graphics)
	{
		Graphics2D drawingGraphics = (Graphics2D) graphics;
		for(Rectangle currentRect : rectangleList)
		{
			drawingGraphics.setColor(inverted);
			int strokeWidth = (int)(Math.random() * 10)+1;
			drawingGraphics.setStroke(new BasicStroke(strokeWidth));
			int rand = (int)(Math.random()*35);
			if(rand % 5 ==0 || rand % 7 ==0)
			{
				drawingGraphics.fill(currentRect);
			}
			else
			{
				drawingGraphics.draw(currentRect);
			}
		}
	}
	public void setBackground(Color color)
	{
		super.setBackground(color);
		int red = 255 - color.getRed();
		int green = 255 - color.getGreen();
		int blue = 255 - color.getBlue();
		inverted = new Color(red,green,blue);
	}
}
