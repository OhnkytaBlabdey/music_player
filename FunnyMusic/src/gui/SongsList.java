package gui;

import java.io.File;

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
				System.out.println(e);
			IListItemData data=(IListItemData) list.getSelectedValue();
			GlobalVars.getMusic().Stop();
//			GlobalVars.getMusic().Change(new File(data.getPath()));
//			GlobalVars.play_b.setPauseIcon();
			File[] files = new File[items.size()];
			int i=0;
			for(IListItemData d:items) {
				files[i++]= new File(d.getPath());
			}
			GlobalVars.getMusic().setMusicList(files);
			GlobalVars.getMusic().setCurrent(new File(data.getPath()));
			GlobalVars.play_b.playOrPause();
			}
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
