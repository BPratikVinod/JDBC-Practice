package com.qsp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StoredProcedures {
	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/qsp";
		String user = "postgres";
		String pass = "root";

		try {
			// 1st step
			Class.forName("org.postgresql.Driver");
			// 2nd step
			Connection con = DriverManager.getConnection(url, user, pass);
			// 3rd step
			CallableStatement cs = con.prepareCall("call create_student(30, 'Rani Mukharji', 9332493420)");
			// 4th step
			cs.execute();
			System.out.println("inserted");
			// 5th step
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}