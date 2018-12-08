package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import other.ConfKit;

public class TitleLogo extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private String default_src="conf/textures/logo.png";
	public TitleLogo() {
		// TODO Auto-generated constructor stub
		
		ImageIcon icon = new ImageIcon(default_src);
		int width = 0,height = 0;
		width=ConfKit.getScreenSize().width/30;
		height=ConfKit.getScreenSize().height/20;
		setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		setPreferredSize(new Dimension(width, height));
		setForeground(Color.CYAN);
		setText("Funny Player");
		setToolTipText("This is a title ");
	}
}
