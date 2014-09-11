package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Baza {

	public static void main(String[] args) {
		
		
		 Connection connection = null;
	        ResultSet resultSet = null;
	        Statement statement = null;
	 
	        try {
	            Class.forName("org.h2.Driver");
	            connection = DriverManager.getConnection(
	                    "jdbc:h2:tcp://localhost/~/simulator", "shrubec", "shrubec");
	            statement = connection.createStatement();
	            resultSet = statement
	                    .executeQuery("select * from kontakt");
	            while (resultSet.next()) {
	                System.out.println("EMPLOYEE NAME:"
	                        + resultSet.getString("EMPNAME"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                resultSet.close();
	                statement.close();
	                connection.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

	

}
