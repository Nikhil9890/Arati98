package com.Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
 
public class LoginTests 
{

	
	@Test(dataProvider="DP")
	public void validateLogin(String uname,String pword)
	{
		
		System.out.println("DB Values: " + uname);
		System.out.println("DB Values:"  + pword);
//		WebDriverManager.chromeDriver.setup();
//		WebDriver driver = new ChromeDriver();
//		driver.get(url);
//		driver.findElement(By.id("")).sendKeys(uname);
//		driver.findElement(By.id("")).sendKeys(pword);
//		driver.findElement(By.id("")).click();
		
	}
	
	@DataProvider(name="DP")
	public String[][] feedDP() throws ClassNotFoundException, SQLException
	{
		String data[][] =getDBValues("root","India@11","classscema","127.0.0.1");
		return data;
	}
	public String[][] getDBValues(String uname,String pword,String dbname,String hostip) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		/*
		 * url a database url of the form jdbc:subprotocol:subnameuser 
		 * the database user on whose behalf the connection is being madepassword 
		 * the user's password
		 */
		String url="jdbc:mysql://localhost:3306/classscema";
		String username="root";
		String password="India@11";
		
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		ResultSet rs =  stmt.executeQuery("Select * from classscema.attendence");
		rs.last();
		
		int rows = rs.getRow();
		System.out.println("Number of rows " + rows);
		
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		System.out.println("Number of cols: "+ cols);
		
		String data[][]= new String[rows][cols];
		
		int i=0;
		rs.beforeFirst();
		while(rs.next())
		{
			for(int j=0;j<cols;j++)
			{
				data[i][j]=rs.getString(j+1);
				System.out.print(data[i][j] +" ");
			}
			i++;
		}	
		return data;
	}
}
