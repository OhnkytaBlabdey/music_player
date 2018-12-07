package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Blob;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import db.music_info;

public class SongsList extends IList{
public SongsList() {
	// TODO Auto-generated constructor stub
	super();
	
	list.addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if(e.getValueIsAdjusting()) {
//				System.out.println(e);
				System.out.println("[songlist]");
			if(!GlobalVars.music_inited && !GlobalVars.music_playing) {
				IListItemData data=(IListItemData) list.getSelectedValue();
			GlobalVars.getMusic().Stop();
	
				File[] files = new File[items.size()];
				int i=0;
				for(IListItemData d:items) {
					files[i++]= new File(d.getPath());
				}
			GlobalVars.getMusic().setMusicList(files);
			GlobalVars.getMusic().Change(new File(data.getPath()));
			GlobalVars.play_bar.setSong(data.getLabelName());
			GlobalVars.play_b.playOrPause();
			
				}
			
			
			else if(GlobalVars.music_inited && GlobalVars.music_playing) {
					IListItemData data=(IListItemData) list.getSelectedValue();
					GlobalVars.getMusic().Stop();
						File[] files = new File[items.size()];
						int i=0;
						for(IListItemData d:items) {
							files[i++]= new File(d.getPath());
						}
					GlobalVars.getMusic().setMusicList(files);
					GlobalVars.getMusic().Change(new File(data.getPath()));
					GlobalVars.play_bar.setSong(data.getLabelName());
					GlobalVars.play_b.playOrPause();
				}
			}
		}
	});
	
	JPopupMenu menu=new JPopupMenu();
	JMenuItem item=new JMenuItem("Add Song");
	menu.add(item);
	
	addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			menu.show(listScro, 10, 10);
		}
	});
	
	music_info[] infos = GlobalVars.getDBSongs().queryAll();
	
	for(music_info mInfo:infos) {
			addItem("tmp/5.jpg", mInfo.song, mInfo.path, 30, 30);
	}
	/*
	addItem("tmp/5.jpg","song3", "music/3.mp3", 20, 20);
	addItem("tmp/5.jpg","song3", "music/3.mp3", 20, 20);
	addItem("tmp/5.jpg","song3", "music/3.mp3", 20, 20);
	addItem("tmp/5.jpg","song3", "music/3.mp3", 20, 20);
	addItem("tmp/5.jpg","song3", "music/3.mp3", 20, 20);
	*/
}
}

