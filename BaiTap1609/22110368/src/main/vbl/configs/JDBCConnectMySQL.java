package vbl.configs;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectMySQL {
	private static String USERNAME = "root";


	 private static String PASSWORD = "";


	 private static String DRIVER = "com.mysql.cj.jdbc.Driver";


	 private static String URL = "jdbc:mysql://localhost:3306/ltwebst2";



	 public static Connection getConnection() throws SQLException {


	 try {


	 Class.forName(DRIVER);


	 } catch (ClassNotFoundException e) {


	 // TODO Auto-generated catch block


	 e.printStackTrace();


	 }


	 return DriverManager.getConnection(URL, USERNAME, PASSWORD);


	 }



	 //Test chương trình. Kích phải chuột chọn run as->java application


	 public static void main(String[] args) {


	 try {


	 new JDBCConnectMySQL();


	 System.out.println(JDBCConnectMySQL.getConnection());


	 }catch(Exception e) {


	 e.printStackTrace();


	 }


	 }
}
