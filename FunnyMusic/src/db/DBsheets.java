package db;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DBsheets {
	public static String dbpath="./db/user.db";
	public static Connection conn;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * test function
		 * 
		 * */
		init();
//		deleteCol("song1", "66665555", "me", ".");
//		insertCol("song1", new Date().toString(), "a1a1a1a1a11a1ddff", "../../b/b/a/a/c__d/abc.mp3");
		System.out.println("id is "+getIDByName("song1"));
		System.out.println("name is "+getNameByID(0));
		sheet_info[] infos=queryAll();
		for(sheet_info info:infos) {
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
		if(!tableExists("sheet")) {
			String sql_create="CREATE TABLE sheet (\n" + 
					"    id    INTEGER NOT NULL\n" + 
					"    PRIMARY KEY AUTOINCREMENT,\n" + 
					"    sheet_name  TEXT    NOT NULL,\n" + 
					"    create_date TEXT    NOT NULL,\n" + 
					"    sheet_user  TEXT    NOT NULL,\n" + 
					"    path_cover  TEXT    NOT NULL\n" + 
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
		  if(!tableExists("sheet")) createTable();
		  if(getNameByID(0)==null) {
			  insertCol(0,"MyFavlist", new Date().toString(), System.getenv().get("USERNAME"), "./conf/textures/song.png");
		  }
	}
	private static void insertCol(int i, String name,String date,String user,String path) {
		// TODO Auto-generated method stub
		String sql_insert="INSERT INTO sheet (id,sheet_name,create_date,sheet_user,path_cover) VALUES("+i+",'"+name+"', '"+date+"', '"+user+"', '"+path+"' )";
		try {
			Statement stmt_insert=conn.createStatement();
			stmt_insert.executeUpdate(sql_insert);
			
			conn.commit();
			stmt_insert.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void insertCol(String name,String date,String user,String path) {
		String sql_insert1="INSERT INTO sheet (sheet_name,create_date,sheet_user,path_cover) VALUES('"+name+"', '"+date+"', '"+user+"', '"+path+"' )";
		try {
			Statement stmt_insert=conn.createStatement();
			stmt_insert.executeUpdate(sql_insert1);
			
			conn.commit();
			stmt_insert.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int getIDByName(String name) {
		int id=0;
		String sql_query="SELECT id FROM sheet WHERE sheet_name LIKE '"+name+"'";
		try {
			Statement stmt_query=conn.createStatement();
			
			ResultSet result = stmt_query.executeQuery(sql_query);
			if(result.next()) {
				id=result.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		if(id>0) return id;
		return -1;
	}
	
	public static String getNameByID(int id) {
		String name = null;
		String sql_query="SELECT sheet_name FROM sheet WHERE id="+id;
		try {
			Statement stmt_query=conn.createStatement();
			
			ResultSet result = stmt_query.executeQuery(sql_query);
			if(result.next()) {
				name=result.getString("sheet_name");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	public static void updateCol(String name,String date,String user,String path) {
		//TODO
	}
	
	public static sheet_info[] queryAll() {
		String sql_query="SELECT * FROM sheet";
		sheet_info[] res=new sheet_info[] {};
		
		try {
			Statement stmt_query=conn.createStatement();
			
			ResultSet result = stmt_query.executeQuery(sql_query);
			
			ArrayList<sheet_info> list = new ArrayList<sheet_info>();

			while(result.next()) {
				String name, date, user, path;int id;
				id=result.getInt("id");
				name=result.getString("sheet_name");
				date=result.getString("create_date");
				user=result.getString("sheet_user");
				path=result.getString("path_cover");

				list.add(new sheet_info(id,name, date, user, path));

			}
			
			res=list.toArray(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static void deleteCol(String name,String date,String user,String path) {
		String sql_delete="DELETE FROM sheet WHERE sheet_name like '"+name+"'";
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
