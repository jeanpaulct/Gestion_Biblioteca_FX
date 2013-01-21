package Modelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MLibro {
	private SimpleStringProperty lcodigo;
	private SimpleStringProperty ltitulo;
	private SimpleStringProperty lautor;
	private SimpleStringProperty leditorial;
	private SimpleIntegerProperty lejemplares;
	private SimpleStringProperty lanioEdicion;
	private SimpleStringProperty lestado;
	
	public MLibro() {
		
		lcodigo=new SimpleStringProperty(null);
		ltitulo=new SimpleStringProperty(null);
		lautor=new SimpleStringProperty(null);
		leditorial=new SimpleStringProperty(null);
		lejemplares=new SimpleIntegerProperty();
		lanioEdicion=new SimpleStringProperty(null);
		lestado=new SimpleStringProperty(null);
	}
	
	public MLibro(String titulo, String autor, int nEjemplares){
		
		ltitulo=new SimpleStringProperty(titulo);
		lautor=new SimpleStringProperty(autor);
		lejemplares=new SimpleIntegerProperty(nEjemplares);
	}
	
	public MLibro(String titulo, String autor, String editorial, String anioEdicion){
		
		ltitulo=new SimpleStringProperty(titulo);
		lautor=new SimpleStringProperty(autor);
		leditorial=new SimpleStringProperty(editorial);		
		lanioEdicion=new SimpleStringProperty(anioEdicion);
	}
	
	public MLibro(String titulo, String autor, String editorial, int nEjemplares,
			String anioEdicion, String estado){
		
		ltitulo=new SimpleStringProperty(titulo);
		lautor=new SimpleStringProperty(autor);
		leditorial=new SimpleStringProperty(editorial);
		lejemplares=new SimpleIntegerProperty(nEjemplares);
		lanioEdicion=new SimpleStringProperty(anioEdicion);
		lestado=new SimpleStringProperty(estado);
	}
	
	
	public String getCodigo() {
		return lcodigo.get();
	}

	public void setCodigo(String codigo) {
		this.lcodigo.setValue(codigo);
	}

	public String getTitulo() {
		return ltitulo.get();
	}

	public void setTitulo(String titulo) {
		this.ltitulo.setValue(titulo);
	}

	public String getAutor() {
		return lautor.get();
	}

	public void setAutor(String autor) {
		this.lautor.setValue(autor);
	}

	public String getEditorial() {
		return leditorial.get();
	}

	public void setEditorial(String editorial) {
		this.leditorial.setValue(editorial);
	}

	public int getEjemplares() {
		return lejemplares.get();
	}

	public void setNEjemplares(int ejemplares) {
		this.lejemplares.setValue(ejemplares);
	}

	public String getAnioEdicion() {
		return lanioEdicion.get();
	}

	public void setLanioEdicion(String anioEdicion) {
		this.lanioEdicion.setValue(anioEdicion);
	}

	public String getEstado() {
		return lestado.get();
	}

	public void setEstado(String estado) {
		this.lestado.setValue(estado);
	}
	
}
