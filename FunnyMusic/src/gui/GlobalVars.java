package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	public static Connection connection;
	public static int x,y;
	
	
	public static MaxBar maxb;
	public static PlayButton play_b;
	public static PlayBar play_bar;
	public static SongsList songs_list;
	public static FavLists fav_lists;
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
	public static Connection getConnection() {
		if(connection==null) {
			
			try {
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection("jdbc:sqlite:"+DBsheets.dbpath);
				connection.setAutoCommit(false);
				System.out.println("Opened database successfully");
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
			
		}
		return connection;
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
