package com.qsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CRUDOperations {
	static Scanner sc = new Scanner(System.in);
	static Connection con;
	static String url = "jdbc:postgresql://localhost:5432/qsp";
	static String user = "postgres";
	static String pass = "root";

	static {
		try {
			// 1st step
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver loaded");
			// 2nd step
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection is created");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// findAll();
		// findProductById();
		// insertProduct();
		// updateProductPrice();
		// deleteProductById();
		try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void findAll() {
		try {
			PreparedStatement stm = con.prepareStatement("select * from product");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getInt("id") + " ");
				System.out.print(rs.getString("name") + " ");
				System.out.print(rs.getInt("price"));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void findProductById() {
		System.out.println("Enter the Id:");
		int id = sc.nextInt();

		try {
			PreparedStatement stm = con.prepareStatement("select * from product where id = ?");
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();

			if (!rs.isBeforeFirst()) {
				System.out.println("Product not found!");
				return;
			}
			;

			while (rs.next()) {
				System.out.println(rs.getInt("id"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getInt("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteProductById() {
		System.out.println("Enter the Id:");
		int id = sc.nextInt();

		try {
			PreparedStatement stm = con.prepareStatement("delete from product where id = ?");
			stm.setInt(1, id);
			stm.execute();
			System.out.println("query executed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateProductPrice() {
		System.out.println("Enter the Id:");
		int id = sc.nextInt();
		System.out.println("Enter the Price:");
		int price = sc.nextInt();

		try {
			PreparedStatement stm = con.prepareStatement("update product set price = ? where id = ?");
			stm.setInt(1, price);
			stm.setInt(2, id);
			stm.execute();
			System.out.println("query executed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertProduct() {
		System.out.println("Enter the id: ");
		int id = sc.nextInt();
		System.out.println("Enter the product name: ");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.println("Enter the price: ");
		int price = sc.nextInt();

		try {
			PreparedStatement stm = con.prepareStatement("INSERT INTO PRODUCT VALUES(?, ?, ?)");
			stm.setString(2, name);
			stm.setInt(1, id);
			stm.setInt(3, price);
			stm.execute();
			System.out.println("query executed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
