package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import other.ConfKit;

public class MaxBar extends JButton {

	static private String default_src="conf/textures/maximize.png";
	static private String default_src2="conf/textures/restore_b.png";
	private boolean ismaxed;
	
	public boolean isMax() {
		return ismaxed;
	}
	
	public MaxBar() {
		// TODO Auto-generated constructor stub
		ImageIcon icon1 = new ImageIcon(default_src);
		ImageIcon icon2=new ImageIcon(default_src2);
		final int width = ConfKit.getScreenSize().width/30,height = ConfKit.getScreenSize().height/20;

		setIcon(new ImageIcon(icon1.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		setPreferredSize(new Dimension(width, height));
		setForeground(Color.GRAY);
		setToolTipText("Maximize ");
		setActionCommand("Maximize");
		ismaxed=false;
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getActionCommand().equals("Maximize") && !ismaxed) {
//					GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(GlobalVars.getFrame());
					GlobalVars.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
					ismaxed=true;
					setIcon(new ImageIcon(icon2.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
//					setIcon(icon2);
					setPreferredSize(new Dimension(width, height));
				}
				
				else if(e.getActionCommand().equals("Maximize") && ismaxed)
				{
					setIcon(new ImageIcon(icon1.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
					setPreferredSize(new Dimension(width, height));
					ismaxed=false;
					
					GlobalVars.getFrame().setExtendedState(JFrame.NORMAL);
					//TODO:
//					GlobalVars.getFrame().setSize(400, 300);
//					GlobalVars.getFrame().setLocation(200, 150);
					
				}
			}
		});
	}
}
