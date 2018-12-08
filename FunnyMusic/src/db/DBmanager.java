package db;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class DBmanager {
	
	public boolean dbExists(String dbpath) {
		return new File(dbpath).exists();
	}
	
	public boolean tableExists(String table,Connection connection) {
		DatabaseMetaData meta;
		ResultSet rs0;
		try {
			meta = connection.getMetaData();
			rs0 = meta.getTables(null, null, table, null);
		return rs0.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public void createTable() {
		//TODO:
	}
	
	public void createDB() {
		//TODO
	}
	
	public void insertCol() {
		
	}
	
	public void updateCol() {
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
	Connection conn = null;
	Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:test.db");
      conn.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = conn.createStatement();
      String createtable = "CREATE TABLE COMPANY " +
                   "(ID INT PRIMARY KEY     NOT NULL," +
                   " NAME           TEXT    NOT NULL, " + 
                   " AGE            INT     NOT NULL, " + 
                   " ADDRESS        CHAR(50), " + 
                   " SALARY         REAL)"; 
      //stmt.executeUpdate(createtable);
      Random random=new Random();
      String sql_insert = "INSERT INTO COMPANY (id,name,age,salary) VALUES ("+random.nextInt()+",\""+"blabla"+"\","+random.nextInt()+","+random.nextGaussian()+")";
      
      stmt.executeUpdate(sql_insert);
      stmt.close();
      Statement stmt3 = conn.createStatement();
      String sql3 = "UPDATE COMPANY set SALARY = "+random.nextGaussian()+" where ID>1;";
      stmt3.executeUpdate(sql3);
      conn.commit();
      stmt = conn.createStatement();
      String sql4 = "DELETE from COMPANY where ID%5=3;";
      stmt.executeUpdate(sql4);
      conn.commit();
      Statement stmt2 = conn.createStatement();
      ResultSet rs = stmt2.executeQuery( "SELECT * FROM COMPANY;" );
      while(rs.next()) {
    	  int id = rs.getInt("id");
          String  name = rs.getString("name");
          int age  = rs.getInt("age");
          String  address = rs.getString("address");
          float salary = rs.getFloat("salary");
          System.out.println( "ID = " + id );
          System.out.println( "NAME = " + name );
          System.out.println( "AGE = " + age );
          System.out.println( "ADDRESS = " + address );
          System.out.println( "SALARY = " + salary );
          System.out.println();
      }
      stmt2.close();
      conn.commit();
      conn.close();
      System.out.println("DB closed");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
	}
}
