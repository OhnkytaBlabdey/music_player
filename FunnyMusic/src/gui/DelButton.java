package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import db.DBsheets;
import other.ConfKit;

public class DelButton extends JButton {

	static private String default_src="conf/textures/del_b.png";
	public DelButton() {
		ImageIcon icon = new ImageIcon(default_src);
		int width = 0,height = 0;
		width=ConfKit.getScreenSize().width/20;
		height=ConfKit.getScreenSize().height/20;
		setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		setPreferredSize(new Dimension(width, height));
		setForeground(Color.BLUE);
		setToolTipText("Delete this favlist! ");
		setActionCommand("Del");
addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Del")) {
					//TODO
					String delname=((IListItemData)GlobalVars.fav_lists.list.getSelectedValue()).getLabelName();
					
					// gui
					if(GlobalVars.fav_lists.DelItem()==0) {
					// db
					DBsheets.deleteCol(delname);
					// console
					System.out.println("Delete this favlist! "+delname);
					}
				}
			}
		});
		setOpaque(false);
	}
}
