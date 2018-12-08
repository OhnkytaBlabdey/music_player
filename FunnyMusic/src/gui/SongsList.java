package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import db.DBsongs;
import db.music_info;

public class SongsList extends IList{
	
	
	
public SongsList() {
	// TODO Auto-generated constructor stub
	super();
	GlobalVars.songs_list=this;
	GlobalVars.getDBSongs();
	music_info[] infos = DBsongs.queryAll();
	
	for(music_info mInfo:infos) {
			addItem("conf/textures/song.png", mInfo.song, mInfo.path, 30, 30);
	}
	list.setSelectedIndex(0);
	
	list.addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub

			if(!e.getValueIsAdjusting()) {
//				System.out.println(e);
				IListItemData data=(IListItemData) list.getSelectedValue();
				if(data==null) {
//					list.setSelectedIndex(0);
					setList();
					list.setValueIsAdjusting(true);
					//TODO
					return;
				}
				if(data.getPath()==null) {
					// empty list hint only
//					items.remove(data);
//					Clear();
					if(items.size()>1) {
						// change to new favlist
					setList();
					list.setValueIsAdjusting(true);
					}
					return;
				}
				
				GlobalVars.play_bar.setSong(data.getLabelName());
				GlobalVars.getMusic().setCurrent(new File(data.getPath()));
				System.out.println("[songlist] next playing : "+data.getPath());
				
				/*
//			if(!GlobalVars.music_inited && !GlobalVars.music_playing) {
//				IListItemData data=(IListItemData) list.getSelectedValue();
//			GlobalVars.getMusic().Stop();
//	
//				File[] files = new File[items.size()];
//				int i=0;
//				for(IListItemData d:items) {
//					files[i++]= new File(d.getPath());
//				}
//			GlobalVars.getMusic().setMusicList(files);
//			GlobalVars.getMusic().Change(new File(data.getPath()));
//			GlobalVars.play_bar.setSong(data.getLabelName());
//			GlobalVars.play_b.playOrPause();
//			
//				}
//			
//			
//			else if(GlobalVars.music_inited && GlobalVars.music_playing) {
//					IListItemData data=(IListItemData) list.getSelectedValue();
//					GlobalVars.getMusic().Stop();
//						File[] files = new File[items.size()];
//						int i=0;
//						for(IListItemData d:items) {
//							files[i++]= new File(d.getPath());
//						}
//					GlobalVars.getMusic().setMusicList(files);
//					GlobalVars.getMusic().Change(new File(data.getPath()));
//					GlobalVars.play_bar.setSong(data.getLabelName());
//					GlobalVars.play_b.playOrPause();
//				}
 * */
			}
		}
	});
	
	JPopupMenu menu=new JPopupMenu();
	JMenuItem item_add=new JMenuItem("Add Song");
	JMenuItem item_del=new JMenuItem("Delete Song");
	
	Mp3Chooser chooser=new Mp3Chooser();
	item_add.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			chooser.showOpenDialog(GlobalVars.getFrame());
			File f=chooser.getSelectedFile();
			if(f!=null) {
			addItem("", f.getName(), f.getAbsolutePath(), 30, 30);
			GlobalVars.getDBSongs();
			DBsongs.insertCol(GlobalVars.fav_lists.getCurrentSheetID(), f.getAbsolutePath());
			}
		}
	});
	menu.add(item_add);
	
	item_del.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			GlobalVars.getMusic().Stop();
			System.out.println("[songlist] delete "+GlobalVars.fav_lists.getCurrentSheetID()+" - "+((IListItemData)list.getSelectedValue()).getLabelName());
			GlobalVars.getDBSongs();
			DBsongs.deleteCol(((IListItemData)list.getSelectedValue()).getLabelName(), GlobalVars.fav_lists.getCurrentSheetID(), null, ((IListItemData)list.getSelectedValue()).getPath());
		}
	});
	menu.add(item_del);
	
	list.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.isPopupTrigger()) {
				menu.show(e.getComponent(), e.getX(), e.getY());	
			}
		}
	});
//	add(menu);
	
	
	/*
	addItem("tmp/5.jpg","song3", "music/3.mp3", 20, 20);
	addItem("tmp/5.jpg","song3", "music/3.mp3", 20, 20);
	addItem("tmp/5.jpg","song3", "music/3.mp3", 20, 20);
	addItem("tmp/5.jpg","song3", "music/3.mp3", 20, 20);
	addItem("tmp/5.jpg","song3", "music/3.mp3", 20, 20);
	*/
}
	public void setList() {
		File[] files = new File[items.size()];
		int i=0;
		for(IListItemData d:items) {
			if(d==null) {
//				items.remove(d);
				continue;
			}
//			if(d.getPath()==null) {
//				// ??
//				continue;
//			}
			files[i++]= new File(d.getPath());
		}
	GlobalVars.getMusic().setMusicList(files);
	}
	public void NextSong() {
		if(list.getLastVisibleIndex()<1) {
			return;
		}
		list.setSelectedIndex((list.getSelectedIndex()+1)%(list.getLastVisibleIndex()+1));
		IListItemData data=(IListItemData) list.getSelectedValue();
		GlobalVars.play_bar.setSong(data.getLabelName());
		GlobalVars.getMusic().setCurrent(new File(data.getPath()));
		System.out.println("[songlist] to be played : "+data.getPath());
	}
	

}

