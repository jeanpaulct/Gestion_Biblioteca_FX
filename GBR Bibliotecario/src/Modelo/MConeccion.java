package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MConeccion {
	private final String driver="org.postgresql.Driver";
	private final String url="jdbc:postgresql://localhost:5432/RosarioDB";
	private Connection cone;
	
	public MConeccion(String user, String password) {
		try {
			Class.forName(driver);
			cone=DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("No encuentra driver");
		} catch (SQLException e) {
			System.out.println("Coneccion Incorrecta");
		}
	}
	
	public Connection getConeccion(){
		return cone;
	}
	
}
