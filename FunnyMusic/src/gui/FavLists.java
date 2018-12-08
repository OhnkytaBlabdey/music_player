package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import db.DBsheets;
import db.DBsongs;
import db.music_info;
import db.sheet_info;
import other.ConfKit;

public class FavLists extends IList {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FavLists() {
		// TODO Auto-generated constructor stub
		super();
		GlobalVars.fav_lists=this;
		setLayout(new GridLayout(2, 1));
		
		GlobalVars.getDBSheets();
		sheet_info[] infos=DBsheets.queryAll();
		for(sheet_info info:infos) {
//			addItem(info.path, info.user+"\t-\t"+info.name, null, 30, 30);
			addItem(info.path, info.name, null, 30, 40);
		}
		list.setSelectedIndex(0);
		
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!e.getValueIsAdjusting()) {
//					System.out.println(e);
					GlobalVars.songs_list.Clear();
					
					
					IListItemData data=(IListItemData) list.getSelectedValue();
					GlobalVars.getDBSongs();
					
					if(data==null) {
						
						return;
					}
					
					music_info[] infos = DBsongs.querySheet(data.getLabelName());
					
					for(music_info mInfo:infos) {
						GlobalVars.songs_list.addItem("conf/textures/song.png", mInfo.song, mInfo.path, 30, 30);
					}
					System.out.println("[favlist] sheet : "+data.getLabelName());
					System.out.println("[favlist] current sheet id : "+getCurrentSheetID());
					if(GlobalVars.songs_list.items.size()<1) {
						GlobalVars.songs_list.addItem("conf/textures/no_song.png", "当前歌单中没有歌曲", null, 30, 30);
					}
				}
			}
		});
		
		JPanel panel=new JPanel();
//		panel.setLayout(new GridLayout(1, 2));
//		panel.setLayout(new FlowLayout(FlowLayout.LEADING));
		panel.setOpaque(false);
		panel.add(new AddButton());
		panel.add(new DelButton());
//		panel.setPreferredSize(new Dimension(ConfKit.getScreenSize().width/10, ConfKit.getScreenSize().height/20));

		add(panel);
		

		//TODO add button and del button 
		
		/*
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
		*
		*/
		
	}
	
	@Override
	public void addItem(String picpath, String text, String path, int w, int h) {
		// TODO Auto-generated method stub
		if(picpath==null || new File(picpath)==null) picpath="./conf/textures/favlist.png";
		super.addItem(picpath, text, path, w, h);
	}
	
	public int DelItem() {
		if((list.getSelectedIndex()<1&& items.size()<2) || getCurrentSheetID()==0 ){
			System.err.println("You can't delete this favlist! ");
			return -1;
		}
		items.remove(list.getSelectedIndex());
		return 0;
	}
	
	public int getCurrentSheetID() {
		GlobalVars.getDBSheets();
		return DBsheets.getIDByName(((IListItemData)list.getSelectedValue()).getLabelName());
	}
	
}
