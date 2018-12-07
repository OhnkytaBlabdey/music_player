package gui;

import javax.swing.JFrame;

import db.DBsheets;
import db.DBsongs;
import db.music_info;
import sound.MusicManager;

public abstract class GlobalVars {

	public static JFrame frame;
	public static MusicManager manager;
	public static DBsongs dbSongs;
	public static DBsheets dbSheets;
	public static int x,y;
	
	
	public static MaxBar maxb;
	public static PlayButton play_b;
	public static PlayBar play_bar;
	public static SongsList songs_list;
	/*
	 * music playing status
	 * */
	static public boolean music_playing;
	static public boolean music_inited;
	static public boolean[] music_ended=new boolean[] {false,false};
	
	public static JFrame getFrame() {
		if(frame==null ) {
			frame=new JFrame();
		}
		return frame;
		}
	public static MusicManager getMusic() {
		if(manager==null) {
			manager=new MusicManager(getDBSongs().getSongs());
		}
		return manager;
	}
	public static DBsongs getDBSongs() {
		if(dbSongs==null) {
			dbSongs=new DBsongs();
			DBsongs.init();
		}
		return dbSongs;
	}
	public static DBsheets getDBSheets() {
		if(dbSheets==null) {
			dbSheets=new DBsheets();
			dbSheets.init();
		}
		return dbSheets;
	}
}
