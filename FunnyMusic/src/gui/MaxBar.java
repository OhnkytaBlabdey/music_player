package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import other.ConfKit;

public class MaxBar extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private String default_src="conf/textures/maximize_b.png";
	static private String default_src2="conf/textures/restore_b.png";
	private boolean ismaxed;
	
	ImageIcon icon1 ;
	ImageIcon icon2 ;
	final int width,height;
	
	public boolean isMax() {
		return ismaxed;
	}
	
	public MaxBar() {
		// TODO Auto-generated constructor stub
		GlobalVars.maxb=this;
		icon1 = new ImageIcon(default_src);
		icon2 =new ImageIcon(default_src2);
		width = ConfKit.getScreenSize().width/30;
		height = ConfKit.getScreenSize().height/20;

		setIcon(new ImageIcon(icon1.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		setPreferredSize(new Dimension(width, height));
		setForeground(Color.GRAY);
		setToolTipText("Maximize ");
		setActionCommand("Maximize");
		ismaxed=false;
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getActionCommand().equals("Maximize") ) {
					MaximizeOrRestore();
				}
				
//				else if(e.getActionCommand().equals("Maximize") && )
				
			}
		});
	}
	
	public void MaximizeOrRestore() {
		if(ismaxed){
			setIcon(new ImageIcon(icon1.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
			setPreferredSize(new Dimension(width, height));
			ismaxed=false;
			
			GlobalVars.getFrame().setExtendedState(JFrame.NORMAL);
			//TODO:
//			GlobalVars.getFrame().setSize(400, 300);
//			GlobalVars.getFrame().setLocation(200, 150);
			
		}else 
			{
					GlobalVars.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
					ismaxed=true;
					setIcon(new ImageIcon(icon2.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
					setPreferredSize(new Dimension(width, height));
				}
		
	}
}
