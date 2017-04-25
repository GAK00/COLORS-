package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import controller.Controller;

public class ShapePanel extends JPanel
{
	private Controller baseController;
	private List<ShapeData> rectangleList;
	private List<ShapeData> circleList;
	private List<ShapeData> ellipseList;
	private List<ShapeData> triangleList;
	private List<ShapeData> polyList;
	private List<List<ShapeData>> shapes;
	private Color inverted;
	private int slowDown;

	public ShapePanel(Controller baseController)
	{
		super();
		slowDown = 0;
		inverted = Color.black;
		this.baseController = baseController;
		this.rectangleList = new ArrayList<ShapeData>();
		this.circleList = new ArrayList<ShapeData>();
		this.ellipseList = new ArrayList<ShapeData>();
		this.triangleList = new ArrayList<ShapeData>();
		this.polyList =new ArrayList<ShapeData>();
		this.setMinimumSize(new Dimension(100, 500));
		shapes = new ArrayList<List<ShapeData>>();
		shapes.add(rectangleList);
		shapes.add(circleList);
		shapes.add(ellipseList);
		shapes.add(triangleList);
		shapes.add(polyList);
	}

	public void addRectangles()
	{
		for (int index = 0; index < 30; index++)
		{
			int width = (int) (Math.random() * 180) + 1;
			int height = (int) (Math.random() * 180) + 1;
			int xCorner = (int) (Math.random() * (getWidth() - width));
			int yCorner = (int) (Math.random() * (getHeight() - height));
			int rand = (int) (Math.random() * 35);
			ShapeData currentRectangle = new ShapeData(new Rectangle(xCorner, yCorner, width, height), rand % 30 == 0, (int) (Math.random() * 5) + 1);
			currentRectangle.setAlpha(100);
			rectangleList.add(currentRectangle);
		}
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics graphics)
	{
		Graphics2D drawingGraphics = (Graphics2D) graphics;
		for(List<ShapeData> currentShapes : shapes)
		{
			drawShapes(currentShapes,drawingGraphics);
		}
	}
	public void addCircles()
	{
		if(circleList.size() > 100)
		{
			circleList.clear();
		}
		for(int index = 0; index< 30; index++)
		{
			int radius = (int)(Math.random()*50)+20;
			int x = (int)(Math.random()*getWidth()-radius);
			int y = (int)(Math.random()*getHeight()-radius);
			Ellipse2D.Double circle = new Ellipse2D.Double(x,y,radius,radius);
			int rand = (int) (Math.random() * 35);
			circleList.add(new ShapeData(circle,rand % 30 == 0, (int) (Math.random() * 5) + 1));
		}
	}
	public void addEllipse()
	{
		if(circleList.size() > 100)
		{
			circleList.clear();
		}
		for(int index = 0; index< 30; index++)
		{
			int radiusX = (int)(Math.random()*50)+20;
			int radiusY = (int)(Math.random()*50)+20;
			int x = (int)(Math.random()*getWidth()-radiusX);
			int y = (int)(Math.random()*getHeight()-radiusY);
			Ellipse2D.Double circle = new Ellipse2D.Double(x,y,radiusX,radiusY);
			int rand = (int) (Math.random() * 35);
			circleList.add(new ShapeData(circle,rand % 30 == 0, (int) (Math.random() * 5) + 1));
		}
	}
	public void addTriangles()
	{
		if(triangleList.size() > 500){
			triangleList.clear();
		}
		for(int index = 0; index < 30; index++)
		{
			int vertexCount = 3;
			int [] xVertices = new int [vertexCount];
			int [] yVertices = new int [vertexCount];
			for(int vertex =0; vertex < vertexCount; vertex++)
			{
				int xCorner = (int)(Math.random() * getWidth());
				int yCorner = (int)(Math.random() * getHeight());
				xVertices[vertex] = xCorner;
				yVertices[vertex] = yCorner;
			}
			Polygon current = new Polygon(xVertices, yVertices, vertexCount);
			int rand = (int) (Math.random() * 35);
			triangleList.add(new ShapeData(current,rand % 30 == 0, (int) (Math.random() * 5) + 1));
		}
	}
	public void addPolygons()
	{
		if(polyList.size() > 500){
			polyList.clear();
		}
		for(int index = 0; index < 30; index++)
		{
			int vertexCount = (int)(Math.random()*7)+4;
			int [] xVertices = new int [vertexCount];
			int [] yVertices = new int [vertexCount];
			for(int vertex =0; vertex < vertexCount; vertex++)
			{
				int xCorner = (int)(Math.random() * getWidth());
				int yCorner = (int)(Math.random() * getHeight());
				xVertices[vertex] = xCorner;
				yVertices[vertex] = yCorner;
			}
			Polygon current = new Polygon(xVertices, yVertices, vertexCount);
			int rand = (int) (Math.random() * 35);
			polyList.add(new ShapeData(current,rand % 30 == 0, (int) (Math.random() * 5) + 1));
		}
	}
	public void drawShapes(List<ShapeData> shapes, Graphics2D drawingGraphics)
	{

		drawingGraphics.setColor(inverted);
		for (ShapeData shape : shapes)
		{
			if (slowDown == 70)
			{
				int strokeWidth = (int) (Math.random() * 5) + 1;
				drawingGraphics.setStroke(new BasicStroke(strokeWidth));
				shape.setStrokeWidth(strokeWidth);
				int rand = (int) (Math.random() * 35);
				if (rand % 30 == 0)
				{
					drawingGraphics.setColor(randomizeAlpha(inverted));
					shape.setAlpha(drawingGraphics.getColor().getAlpha());
					drawingGraphics.fill(shape.getShape());
					shape.setFilled(true);
					drawingGraphics.setColor(inverted);
				} else
				{
					drawingGraphics.draw(shape.getShape());
					shape.setFilled(false);
				}

				slowDown = 0;
			}

			else 
			{
				if(slowDown % 20 == 0){
					shape.setStrokeWidth((int)(Math.random() * 5) + 1);}
					drawingGraphics.setStroke(new BasicStroke(shape.getStrokeWidth()));
					if (shape.isFilled())
					{
						drawingGraphics.setColor(new Color(inverted.getRed(), inverted.getGreen(), inverted.getBlue(), shape.getAlpha()));
						drawingGraphics.fill(shape.getShape());
						drawingGraphics.setColor(inverted);
					} else
					{
						drawingGraphics.draw(shape.getShape());
					}
				
				slowDown++;
			}
			
		}
	}

	private Color randomizeAlpha(Color color)
	{
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (Math.random() * 256));
	}

	public void setBackground(Color color)
	{
		super.setBackground(color);
		int red = 255 - color.getRed();
		int green = 255 - color.getGreen();
		int blue = 255 - color.getBlue();
		inverted = new Color(red, green, blue);
	}
}
