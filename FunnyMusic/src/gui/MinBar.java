package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import other.ConfKit;

public class MinBar extends JButton {

	static private String default_src="conf/textures/minimize_b.png";
	public MinBar() {
		// TODO Auto-generated constructor stub
		ImageIcon icon = new ImageIcon(default_src);
		int width = 0,height = 0;
		width=ConfKit.getScreenSize().width/30;
		height=ConfKit.getScreenSize().height/20;
		setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		setPreferredSize(new Dimension(width, height));
		setForeground(Color.GRAY);
		setToolTipText("Minimize ");
		setActionCommand("Minimize");
	}
}
