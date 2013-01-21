package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Modelo.MConeccion;
import Modelo.MLibro;
import Modelo.MPrestamo;

public class CBussines {
	
	private MConeccion cn;
	private static Connection conexion;
	//private PreparedStatement login;
	private PreparedStatement busquedaLibros;
	private PreparedStatement guardaPrestamo;
	private PreparedStatement guardaDevolucion;
	private PreparedStatement viewPrestamos;
	private PreparedStatement guardaLibro;
	private PreparedStatement guardaRequerimiento;
	
	public CBussines(String user, String password) {
		try {
			cn=new MConeccion(user, password);
			conexion=cn.getConeccion();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
//		try {
//			login=conexion.prepareStatement("SELECT * " +
//					"FROM tblusuario " +
//					"WHERE usname=? AND uspassword=?");
//		} catch (SQLException e) {
//			System.out.println("Consulta de login no Completada");
//			e.printStackTrace();
//		}
//		
		try {
			busquedaLibros=conexion.prepareStatement("SELECT * FROM busquedaBibliotecario WHERE lista SIMILAR TO ?");
		} catch (SQLException e) {
			System.out.println("Consulta de Busqueda no Completada");
			e.printStackTrace();
		}
		
		try{
			guardaPrestamo=conexion.prepareStatement("SELECT sp_ingresaPrestamo(?,?,?,?,?)");
		}catch (SQLException e) {
			System.out.println("Query ingreso prestamo no Completada");
			e.printStackTrace();
		}
		
		try {
			guardaDevolucion=conexion.prepareStatement("SELECT sp_devolucion(?)");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			viewPrestamos=conexion.prepareStatement("SELECT * FROM sp_viewPrestamos()");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			guardaLibro=conexion.prepareStatement("SELECT sp_ingresaLibro(?,?,?,?,?,?)");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			guardaRequerimiento=conexion.prepareStatement("SELECT sp_ingresaRequerimiento(?,?,?,?,?,?)");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ObservableList<MLibro> getBusqueda(String busqueda){
		
		busqueda=ReplaceBusqueda(busqueda);
		
		ObservableList<MLibro> lista=FXCollections.observableArrayList();
		ResultSet resultados;
		
		try {
			busquedaLibros.setString(1, busqueda);
			
			resultados=busquedaLibros.executeQuery();
			
			while (resultados.next()) {
				lista.add(new MLibro(resultados.getString("ltitulo"), resultados.getString("lautor"), resultados.getInt("cantidad")));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return lista;
	}
	
	private String ReplaceBusqueda(String busqueda) {	
		busqueda=busqueda.toUpperCase();
		busqueda=busqueda.replace(' ', '|');
		busqueda="%("+busqueda+")%";
		
		return busqueda;
	}
	
	public void registerPrestamo(String codLibro,String codLector,String tipoUser,
			String grado,String seccion){
		try {
			guardaPrestamo.setString(1, codLibro);
			guardaPrestamo.setString(2, codLector);
			guardaPrestamo.setString(3, tipoUser);
			guardaPrestamo.setString(4, grado);
			guardaPrestamo.setString(5, seccion);
			
			guardaPrestamo.execute();
			
		} catch (SQLException e) {
			System.out.println("Prestamo no registrado "+ e.getCause());
			e.printStackTrace();
		}
		
	}
	
	public void registraDevolucion(int codigo){
		try {
			guardaDevolucion.setInt(1, codigo);
			guardaDevolucion.execute();
		} catch (SQLException e) {
			
			System.out.println("Devolución no registrado "+ e.getMessage());
		}
	}
	
	public ObservableList<MPrestamo> viewPrestamo(){
		ObservableList<MPrestamo> listaPrestamo=FXCollections.observableArrayList();
		ResultSet resultados;
		try {
			resultados=viewPrestamos.executeQuery();
			
			while(resultados.next()){
				listaPrestamo.add(new MPrestamo(resultados.getString(1),
						resultados.getString(3), resultados.getString(2), resultados.getDate(4), resultados.getString(5)));
			}			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return listaPrestamo;
	}
	
	public void registerLibro(String codDwey,
			String title, String autor, String editorial,String anioEdicion,String estado){
		try {
			guardaLibro.setString(1, codDwey);
			guardaLibro.setString(2, title);
			guardaLibro.setString(3, autor);
			guardaLibro.setString(4, editorial);
			guardaLibro.setString(5, anioEdicion);
			guardaLibro.setString(6, estado);
			guardaLibro.execute();
		} catch (Exception e) {
			System.out.println("libro no registrado"+e.getMessage());
		}
	}
	
	public void registerRequerimiento(String titulo, String autor, String editorial,
			String anioEdicion, String area, String dni){
		try {
			guardaRequerimiento.setString(1, titulo);
			guardaRequerimiento.setString(2, autor);
			guardaRequerimiento.setString(3, editorial);
			guardaRequerimiento.setString(4, anioEdicion);
			guardaRequerimiento.setString(5, area);
			guardaRequerimiento.setString(6, dni);
			guardaRequerimiento.execute();
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return conexion;
	}

	public void cierra() {
		try {
			conexion.close();
		} catch (SQLException e) {
			System.out.println("No elimina"+e.getCause());
			e.printStackTrace();
		}
	}
	
}
