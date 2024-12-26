package com.qsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/qsp";
		String user = "postgres";
		String pass = "root";
		String URL = "jdbc:postgresql://localhost:5432/qsp?user=postgres&password=root";

		try {
			// 1st step
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver class is loaded");

			// 2nd step
			// Connection con = DriverManager.getConnection(url, user, pass);
			Connection con = DriverManager.getConnection(URL);
			System.out.println("Connection is created");

			// 3rd step
			Statement stm = con.createStatement();

			// 4th step
			// boolean res = stm.execute("INSERT INTO STUDENTS VALUES(50, 'RAM',
			// 8293845225)");
			// boolean res = stm.execute("DELETE FROM STUDENTS WHERE id=40");

			stm.execute("select * from emp");
			ResultSet rs = stm.getResultSet();
			while (rs.next()) {
				System.out.println(rs.getInt("id"));
			}

			// 5th step
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
