package gui;

import other.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PlayButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private String default_src="conf/textures/play_b.png";
	static private String default_src2="conf/textures/pause_b.png";
//	static private boolean playing;
//	static private boolean inited;
	ImageIcon icon ;
	ImageIcon icon2 ;
	int width,height;
	
	public PlayButton() {
		// TODO Auto-generated constructor stub
		GlobalVars.play_b=this;
		icon = new ImageIcon(default_src);
		icon2 = new ImageIcon(default_src2);
		width = 0;
		height = 0;
		width=ConfKit.getScreenSize().width/20;
		height=ConfKit.getScreenSize().height/20;
		setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		setPreferredSize(new Dimension(width, height));
		setForeground(Color.BLUE);
		setToolTipText("Play! ");
		setActionCommand("Play");
		GlobalVars.music_playing=false;
		GlobalVars.music_inited=false;
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(GlobalVars.getMusic().getCurrentMusic()==null) {
					return;
				}
				if(e.getActionCommand().equals("Play")) {
					playOrPause();
				}
			}
		});
	}
	public void playOrPause() {
		if(!GlobalVars.music_playing) {
			setIcon(new ImageIcon(icon2.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
			setPreferredSize(new Dimension(width, height));
			if(GlobalVars.music_inited) {
				GlobalVars.getMusic().Resume();
			}else {
			GlobalVars.getMusic().Play();
			GlobalVars.music_inited=true;
			}
			GlobalVars.music_playing=true;
		}else {
			setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
			setPreferredSize(new Dimension(width, height));
			GlobalVars.getMusic().Pause();
//			GlobalVars.getMusic().Stop();
			GlobalVars.music_playing=false;
		}
	}
	public void setPauseIcon() {
		setIcon(new ImageIcon(icon2.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		setPreferredSize(new Dimension(width, height));
		if(GlobalVars.music_inited) {
		}else {
		GlobalVars.music_inited=true;
		}
		GlobalVars.music_playing=true;
	}
}
