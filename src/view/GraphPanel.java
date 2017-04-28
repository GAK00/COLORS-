package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class GraphPanel
{
	private int[] data = {27,5,54,18};
	private int width;
	private Color[] colorModifier;
	ShapePanel panel;
	private Rectangle boundingBox;	
	public GraphPanel(int[] data, ShapePanel panel)
	{
	if(data != null)
	{
		this.data = data;
	}
	this.panel = panel;
	this.width = 10;
	colorModifier = new Color[this.data.length];
	for(int index = 0; index<this.data.length; index++)
	{
		int red = (int)(Math.random()*256);
		int green = (int)(Math.random()*256);
		int blue = (int)(Math.random()*256);
		colorModifier[index] = new Color(red, green,blue);
	}
	int boundWidth = (int)(Math.random()*panel.getWidth())/2;
	int boundHeight = (int)(Math.random()*panel.getHeight())/2;
	boundingBox = new Rectangle((int)(Math.random()*panel.getWidth())-boundWidth,(int)(Math.random()*panel.getHeight())-boundHeight,boundWidth,boundHeight);
	}
	
	protected void paintComponent(Graphics graphics)
	{
		if(this != null);
		width = (int)boundingBox.getWidth()/data.length;
		for(int index = 0; index<data.length; index++)
		{
			int xPoint =(int)( (index * width) + boundingBox.getX());
			int yPoint = (int)((panel.getHeight()-boundingBox.getY())-boundingBox.getHeight() - data[index]);
			Rectangle current = new Rectangle(xPoint, yPoint, width,data[index]);
			Graphics2D drawing = (Graphics2D) graphics;
				drawing.setColor(colorModifier[index]);
				drawing.fill(current);
		}
	}
}
