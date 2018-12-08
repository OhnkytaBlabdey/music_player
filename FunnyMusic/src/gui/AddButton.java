package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import other.ConfKit;

public class AddButton extends JButton{
	
	static private String default_src="conf/textures/add_b.png";
	public AddButton() {
		ImageIcon icon = new ImageIcon(default_src);
		int width = 0,height = 0;
		width=ConfKit.getScreenSize().width/20;
		height=ConfKit.getScreenSize().height/20;
		setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		setPreferredSize(new Dimension(width, height));
		setForeground(Color.BLUE);
		setToolTipText("Add a new favlist! ");
		setActionCommand("Add");
addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Add")) {
					//TODO
					System.out.println("Add a new favlist! ");
				}
			}
		});
		setOpaque(false);
	}

}
