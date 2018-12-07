package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import other.ConfKit;

public class SceneBG extends JPanel {
	static private String default_src="conf/bg/bg.jpg";
	
	ImageIcon icon ;
	int width ,height;
	public SceneBG() {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		icon = new ImageIcon(default_src);
//		int width = 0,height = 0;
		width=ConfKit.getScreenSize().width/3;
		height=ConfKit.getScreenSize().height/2;

		JLabel pic=new JLabel(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		pic.setPreferredSize(new Dimension(width, height));
		pic.setForeground(Color.GRAY);
		pic.setToolTipText("background pic");
		add(pic);
	}
	public void set(int w,int h) {
		width=w;
		height=h;
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(icon.getImage(), 0, 0, width, height,null);
	}
}
