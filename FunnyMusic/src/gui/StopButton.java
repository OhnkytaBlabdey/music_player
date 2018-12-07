package gui;

import other.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class StopButton extends JButton{

	static private String default_src="conf/textures/stop_b.png";
	public StopButton() {
		// TODO Auto-generated constructor stub
		
		ImageIcon icon = new ImageIcon(default_src);
		int width = 0,height = 0;
		width=ConfKit.getScreenSize().width/40;
		height=ConfKit.getScreenSize().height/30;
		setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		setPreferredSize(new Dimension(width, height));
		setForeground(Color.BLUE);
		setToolTipText("Stop! ");
		setActionCommand("Stop");
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Stop")) {
				if(GlobalVars.music_inited ) {

					if(GlobalVars.music_playing) {
					GlobalVars.play_b.playOrPause();
					}
				
				GlobalVars.music_inited=false;
				GlobalVars.getMusic().Stop();
				}
				
			}
			}
		});
	}
}
