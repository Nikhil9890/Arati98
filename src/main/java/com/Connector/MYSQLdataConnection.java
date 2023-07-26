package com.Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.ResultsetRow;
import com.mysql.cj.xdevapi.Result;

public class MYSQLdataConnection {

	public static void main(String[] args) throws ClassNotFoundException {
		// localhost or 127.0.0.1 and "//" is compulsary
		// mysql-connector-java-8.0.28 is necessary
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classscema", "root", "India@11");
			// here "classscema" is database name, root is "username" and "India@11"
			// password
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from attendence");
			
			ArrayList<String> Name = new ArrayList<String>();
			while (rs.next()) {
				for (int i = 0; i < 5;) {
					Name.add(i, rs.getString(2));
					rs.next();
					i++;
				}
				System.out.println(Name.size());
				System.out.println(Name);
				System.out.println(Name.get(0));
				//System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
				con.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
