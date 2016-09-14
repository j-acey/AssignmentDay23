package ssa;

import java.sql.*;
import java.io.FileInputStream;
import java.util.Properties;


public class jdbcProject {

	static Connection myConn=null;
	static Statement stmt=null;
	static ResultSet rs=null;	
	
	
	public static void main(String[] args) throws SQLException {
			//inserting new student record
			insert();
			//displaying new student details
			select();
			//updating new student details with different values
			update();
			//displaying updated values
			select();
			//deleting new student from table
			delete();
			//displaying new student no longer exists
			select();
			System.out.println("Unfortunately, this student no longer exists in the table");
	}
	
	
	public static void MyClose(Connection con, Statement stmt, ResultSet rs) throws SQLException{
		
		if(con!=null)
			con.close();
		if(stmt != null)
			stmt.close();
		if(rs !=null)
			rs.close();
		
	}
	
	
	public static void select() throws SQLException {
		
		try{
			//1. Load the properties file
			Properties props = new Properties();
					
			
			props.load(new FileInputStream("jdbcProject.properties"));
			
			
			//2. Read the props
			String theUser = props.getProperty("user");
			String thePassword = props.getProperty("password");
			String theUrl = props.getProperty("url");
			
			// Get Connection
			
			myConn= (Connection)DriverManager.getConnection(theUrl, theUser, thePassword);
			// Create Statement
			stmt= myConn.createStatement();
			
			// Execute query
			
			rs = stmt.executeQuery("select * from student where id = 210");
			
			System.out.println("ID" + "\t" + "First Name" + "\t" + "Last Name" +"\t\t" + "SAT" +"\t \t" + "Major ID" +"\t " + "GPA");
			System.out.println("=================================================================================================");
			while(rs.next()){
				int id=rs.getInt("id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				int sat=rs.getInt("sat");
				int major_id=rs.getInt("major_id");
				double gpa=rs.getDouble("gpa");
				//System.out.println(rs.getInt("id") + " \t" +rs.getString("first_name") + "\t \t" + rs.getString("last_name") + "\t \t " + rs.getInt("sat") + "\t \t \t" + rs.getInt("major_id") + "\t \t" + rs.getDouble("gpa"));
			String msg= String.format("%3d     %-15s %-20s    %-4d            %d                %f", id, first_name, last_name, sat, major_id, gpa, "/n");
				System.out.println(msg);
				System.out.println();
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			
		}finally {
			if(myConn!=null)
				myConn.close();
			if(stmt!=null)
				stmt.close();
		}
		
	}
	
	
	public static void insert() throws SQLException {
		
		try{
			// Load the properties file
			Properties props = new Properties();
					
			
			props.load(new FileInputStream("jdbcProject.properties"));
			
			
			// Read the props
			String theUser = props.getProperty("user");
			String thePassword = props.getProperty("password");
			String theUrl = props.getProperty("url");
			
			// Get Connection
			
			myConn= (Connection)DriverManager.getConnection(theUrl, theUser, thePassword);
			// Create Statement
			stmt= myConn.createStatement();
			
			// Execute query
			String query="insert into student values (210, 'George','Washington', 1600, null, 4.0)";
			int rowAffected = stmt.executeUpdate(query);
				System.out.println("A new student has been added");
				System.out.println();
			
		}catch(Exception ex){
			ex.printStackTrace();
			
		}finally {
			if(myConn!=null)
				myConn.close();
			if(stmt!=null)
				stmt.close();
		}
		
	}
	
	
	public static void update() throws SQLException {
		
		try{
			// Load the properties file
			Properties props = new Properties();
					
			
			props.load(new FileInputStream("jdbcProject.properties"));
			
			
			// Read the props
			String theUser = props.getProperty("user");
			String thePassword = props.getProperty("password");
			String theUrl = props.getProperty("url");
			
			// Get Connection
			
			myConn= (Connection)DriverManager.getConnection(theUrl, theUser, thePassword);
			// Create Statement
			stmt= myConn.createStatement();
			
			// Execute query
			String sql= "update student set gpa=3.5, sat=1450, major_id=1 where id=210";
			int rowAffected= stmt.executeUpdate(sql);
	
			System.out.println("Student data has been updated");
			System.out.println();
			// Process the result set

		}catch(Exception ex){ ex.printStackTrace();}
			finally{
				if(myConn!=null)
					myConn.close();
				if(stmt!=null)
					stmt.close();
			}
		
	}
	

	
	public static void delete() throws SQLException {
		
		try{
			// Load the properties file
			Properties props = new Properties();
					
			
			props.load(new FileInputStream("jdbcProject.properties"));
			
			
			// Read the props
			String theUser = props.getProperty("user");
			String thePassword = props.getProperty("password");
			String theUrl = props.getProperty("url");
			
			// Get Connection
			
			myConn= (Connection)DriverManager.getConnection(theUrl, theUser, thePassword);
			// Create Statement
			stmt= myConn.createStatement();
			
			// Execute query
			String query= "delete from student where id =210";
			int rowAffected= stmt.executeUpdate(query);
			System.out.println("Student record has been deleted");
			System.out.println();
					
	}catch(Exception ex){
		ex.printStackTrace();
		
	}finally{
		if(myConn!=null)
			myConn.close();
		if(stmt !=null)
			stmt.close();
		
	}
	}
	
	
	
}
