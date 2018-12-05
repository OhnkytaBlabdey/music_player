package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import other.ConfKit;

public class SceneBG extends JLayeredPane {
	static private String default_src="conf/bg/bg.jpg";
	public SceneBG() {
		// TODO Auto-generated constructor stub
		
		ImageIcon icon = new ImageIcon(default_src);
		int width = 0,height = 0;
		width=ConfKit.getScreenSize().width/3;
		height=ConfKit.getScreenSize().height/2;

		JLabel pic=new JLabel(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		pic.setPreferredSize(new Dimension(width, height));
		pic.setForeground(Color.GRAY);
		pic.setToolTipText("background pic");
		add(pic);
	}
}
