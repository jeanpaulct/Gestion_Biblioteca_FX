package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MConeccion {
	private final String driver="org.postgresql.Driver";
	private final String url="jdbc:postgresql://localhost:5432/RosarioDB";
	private final String user="user";
	private final String pwd="userlogin";
	private Connection cone;
	
	public MConeccion() {
		try {
			Class.forName(driver);
			cone=DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			System.out.println("No encuentra driver");
		} catch (SQLException e) {
			System.out.println("Coneccion Incorrecta");
			e.printStackTrace();
		}
	}
	
	public Connection getConeccion(){
		return cone;
	}
	
}
