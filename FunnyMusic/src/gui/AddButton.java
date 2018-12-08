package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import db.DBsheets;
import db.DBsongs;
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
		
		PicChooser chooser = new PicChooser();
		TextInputBar inputBar=new TextInputBar();
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Add")) {
					//TODO
					System.out.println("Add a new favlist! ");
					chooser.showOpenDialog(GlobalVars.getFrame());
					File f=chooser.getSelectedFile();
					if(f!=null) {
					GlobalVars.fav_lists.addItem(f.getAbsolutePath(), f.getName(),System.getenv("USERNAME"), 30, 30);
					GlobalVars.getDBSheets();
					String name=null;
					while (name==null) {
						//TODO : input user name and favlist name
						name=inputBar.showInputDialog(GlobalVars.getFrame(), "INPUT FAVLIST's NAME! ");
					}
					String user=null;
					//TODO : input user name and favlist name
					if (user==null) {
						user=(String)System.getenv("USERNAME");
					}
					DBsheets.insertCol(name,new Date().toString(),user ,f.getAbsolutePath());
					}
				}
			}
		});
		
		
		setOpaque(false);
	}

}
