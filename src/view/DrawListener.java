package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;



public class DrawListener implements MouseMotionListener
{
	private int lastX;
	private int lastY;
	private int offset;
	private ShapePanel shapes;
	public DrawListener(ShapePanel shapes, int offset)
	{
		lastX = 0;
		lastY = 0;
		this.offset = offset;
		this.shapes = shapes;
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		if(e.getX()-offset >=0){
		shapes.drawLine(lastX, lastY, e.getX()-offset, e.getY());
		lastX = e.getX() - offset;
		lastY = e.getY();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		if(e.getX() -offset >=0){
		lastX = e.getX() - offset;
		lastY = e.getY();
		}
	}

}
