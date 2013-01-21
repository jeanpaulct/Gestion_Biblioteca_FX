package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Modelo.MConeccion;
import Modelo.MLibro;

public class CBussiness {
	
	private MConeccion nc;
	private Connection conexion;
	private PreparedStatement busquedaDefault;
	private PreparedStatement busquedaTitulo;
	private PreparedStatement busquedaAutor;
	private PreparedStatement busquedaEditorial;
	private PreparedStatement autocompletado;
	private PreparedStatement nuevosLibros;
	private PreparedStatement ranking;
	public CBussiness() {
		
		nc=new MConeccion();
		conexion=nc.getConeccion();
		
		try {
			autocompletado=conexion.prepareStatement("SELECT * FROM sp_autocomplete()");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			busquedaDefault=conexion.prepareStatement("SELECT * FROM sp_busquedaMejorada(?)");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Consulta preparada no completada- Default");
		}
		
		try {
			busquedaTitulo=conexion.prepareStatement("SELECT * FROM sp_busquedaMejorada(?) ORDER BY titulo");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Consulta preparada no completada- Titulo");
			e.printStackTrace();
		}
		
		try {
			busquedaAutor=conexion.prepareStatement("SELECT * FROM sp_busquedaMejorada(?) ORDER BY autor");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Consulta preparada no completada- Titulo");
			e.printStackTrace();
		}
		
		try {
			busquedaEditorial=conexion.prepareStatement("SELECT * FROM sp_busquedaMejorada(?) ORDER BY editorial");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Consulta preparada no completada- Editorial");
			e.printStackTrace();
		}
		
		try{
			nuevosLibros=conexion.prepareStatement("SELECT * FROM sp_nuevosLibros()");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Consulta preparada no completada- nuevos libros");
			e.printStackTrace();
		}
		
		try{
			ranking=conexion.prepareStatement("SELECT * FROM sp_ranking(?)");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Consulta preparada no completada- ranking");
			e.printStackTrace();
		}
		
	}
	
	public ObservableList<MLibro> getConsulta(String busqueda,char tipoFiltro){
		
		ObservableList<MLibro> lista=FXCollections.observableArrayList();
		ResultSet resultados;
		
		busqueda=busqueda.toUpperCase();
		switch (tipoFiltro) {
			case 'T':
			try {
				busquedaTitulo.setString(1, busqueda);
				
				resultados=busquedaTitulo.executeQuery();
				
				while(resultados.next()){
					lista.add(new MLibro(resultados.getString(1),
							resultados.getString(2), resultados.getString(3),
							resultados.getString(4)));
				}
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
				break;
			case 'A':
				try {
					busquedaAutor.setString(1, busqueda);
					
					resultados=busquedaAutor.executeQuery();
					
					while(resultados.next()){						
						lista.add(new MLibro(resultados.getString(1),
								resultados.getString(2), resultados.getString(3),
								resultados.getString(4)));
					}
					
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				break;
			case 'E':
				try {
					busquedaEditorial.setString(1, busqueda);
					resultados=busquedaEditorial.executeQuery();
					
					while(resultados.next()){
						lista.add(new MLibro(resultados.getString(1),
								resultados.getString(2), resultados.getString(3),
								resultados.getString(4)));
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				break;
			default:
				try {					
					busquedaDefault.setString(1, busqueda);
										
					resultados=busquedaDefault.executeQuery();
					
					while(resultados.next()) {
						lista.add(new MLibro(resultados.getString(1),
								resultados.getString(2), resultados.getString(3),
								resultados.getString(4)));
					}
					
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				break;
		}
		return lista;
	}
	
	public ObservableList<String> getAuto(){
		ObservableList<String> lista=FXCollections.observableArrayList();
		ResultSet resultados;
		
		try {
			resultados=autocompletado.executeQuery();
			while (resultados.next()) {
				lista.add(resultados.getString(1));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
	public ObservableList<MLibro> getNuevosLibros(){
		ObservableList<MLibro> lista=FXCollections.observableArrayList();
		ResultSet rs;
		try {
			rs=nuevosLibros.executeQuery();
			while (rs.next()) {
				lista.add(new MLibro(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Problemas al agregar a la lista los libros");
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<String> ranking(){
		List<String> valores=new ArrayList<String>();
		ResultSet rs;
		int i=0;
		
		try {
			ranking.setString(1, "A");
			rs=ranking.executeQuery();
			while(rs.next()){
				valores.add(rs.getString(1));
				i++;
			}
			for(int j=i;j<5;j++){
				valores.add("  ");
				i++;
			}
			
			
			//rs.close();
			ranking.setString(1, "D");
			rs=ranking.executeQuery();
			
			while(rs.next()){
				valores.add(rs.getString(1));
				i++;
			}
			
			for(int j=i;j<10;j++){
				valores.add("  ");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Problemas al agregar a la lista ranking");
			e.printStackTrace();
		}
		return valores;
	}

	public void close() {
		try {
			conexion.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}