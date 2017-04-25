package view;

import java.awt.Shape;

public class ShapeData
{
	private int strokeWidth;
	private boolean isFilled;
	private int alpha;
	Shape shape;
	public ShapeData(Shape shape, boolean isFilled, int strokeWidth)
	{
		this.isFilled = isFilled;
		this.strokeWidth = strokeWidth;
		this.shape = shape;
	}
	public Shape getShape()
	{
		return shape;
	}
	public void setShape(Shape shape)
	{
		this.shape = shape;
	}
	public int getStrokeWidth()
	{
		return strokeWidth;
	}
	public void setStrokeWidth(int strokeWidth)
	{
		this.strokeWidth = strokeWidth;
	}
	public boolean isFilled()
	{
		return isFilled;
	}
	public void setFilled(boolean isFilled)
	{
		this.isFilled = isFilled;
	}
	public int getAlpha()
	{
		return alpha;
	}
	public void setAlpha(int color)
	{
		this.alpha = color;
	}
	

}
