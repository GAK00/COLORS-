package view;

import javax.swing.JFrame;

import controller.Controller;

public class ColorFrame extends JFrame
{

	public ColorFrame(Controller baseController)
	{
		super();
		this.setSize(this.getMaximumSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(new DrawPanel(baseController));
		this.setVisible(true);
	}
}
