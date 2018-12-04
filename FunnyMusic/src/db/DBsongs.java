package db;
//import other.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import other.hashMD5;

public class DBsongs {

	public static String dbpath="./db/user.db";
	public static Connection conn;
//	public static hashMD5 encoder=new hashMD5();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * test function
		 * 
		 * */
		init();
//		deleteCol("song1", 0, "", "");
//		insertCol("song1", new Random().nextInt(), "a1a1a1a1a11a1ddff", "../../b/b/a/a/c__d/abc.mp3");
//		insertCol("song2", 6, "../pracSound/23.mp3");
		insertCol(6, "../pracSound/23.mp3");
		music_info[] infos=queryAll();
		for(music_info info:infos) {
			System.out.println(info);
		}

	}
	public static boolean dbExists() {
		File dir=new File("./db");
		if(!(dir.exists())) {
			dir.mkdir();
		}
		return (new File(dbpath).exists());
	}
	
	public static boolean tableExists(String table) {
		DatabaseMetaData meta;
		ResultSet rs0;
		try {
			meta = conn.getMetaData();
			rs0 = meta.getTables(null, null, table, null);
		return rs0.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public static void createTable() {
		//TODO:
		if(!tableExists("music")) {
			String sql_create="CREATE TABLE music (\r\n" + 
					"    id   INTEGER PRIMARY KEY AUTOINCREMENT\n" + 
					"    NOT NULL,\n" + 
					"    song TEXT    NOT NULL,\n" + 
					"    sheet_id   INT     NOT NULL,\n" + 
					"    md5_val       TEXT    NOT NULL,\n" + 
					"    dir      TEXT    NOT NULL\n" + 
					");";

			try {
				Statement stmt_create_table=conn.createStatement();
				stmt_create_table.executeUpdate(sql_create);
				conn.commit();
				stmt_create_table.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static void createDB() {
		//TODO
	}
	
	public static void init() {
		  try {
			  dbExists();
			  
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbpath);
			conn.setAutoCommit(false);
			System.out.println("Opened database successfully");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  if(!tableExists("music")) createTable();
	}
	public static void insertCol(String song,int sheet_id,String md5,String path) {
		String sql_insert="INSERT INTO music (song,sheet_id,md5_val,dir) VALUES('"+song+"',"+sheet_id+", '"+md5+"', '"+path+"' )";
		try {
			Statement stmt_insert=conn.createStatement();
			stmt_insert.executeUpdate(sql_insert);
			
			conn.commit();
			stmt_insert.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void insertCol(String song,int sheet_id,String path) {
		insertCol(song, sheet_id,hashMD5.encodeFile(path), path);
	}
	public static void insertCol(int sheet_id,String path) {
		insertCol(new File(path).getName(),sheet_id, path);
	}
	
	public static void updateCol(String song,int sheet_id,String md5,String path) {
		//TODO
	}
	
	public static music_info[] queryAll() {
		String sql_query="SELECT * FROM music";
		music_info[] res=new music_info[] {};
		
		try {
			Statement stmt_query=conn.createStatement();

			ResultSet result = stmt_query.executeQuery(sql_query);

			ArrayList<music_info> list = new ArrayList<music_info>();

			while(result.next()) {
				String song;int sheet_id;String md5;String path;
				song=result.getString("song");
				sheet_id=result.getInt("sheet_id");
				md5=result.getString("md5_val");
				path=result.getString("dir");

				list.add(new music_info(song,sheet_id,md5,path));

			}
			
			res=list.toArray(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static void deleteCol(String song,int sheet_id,String md5,String path) {
		String sql_delete="DELETE FROM music WHERE song LIKE '"+song+"'";
		try {
			Statement stmt_del=conn.createStatement();
			stmt_del.executeUpdate(sql_delete);
			
			conn.commit();
			stmt_del.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
