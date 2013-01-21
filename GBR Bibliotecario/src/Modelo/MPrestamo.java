package Modelo;

import java.sql.Date;

import javafx.beans.property.SimpleStringProperty;

public class MPrestamo {
	
	private SimpleStringProperty pcodigo;
	private SimpleStringProperty plibro;
	private SimpleStringProperty pdni;
	private Date pFecha;
	private SimpleStringProperty pestado;
	
	public MPrestamo(String codigo,String libro,String dni, Date fecha, String estado) {
		pcodigo=new SimpleStringProperty(codigo);
		plibro=new SimpleStringProperty(libro);
		pdni=new SimpleStringProperty(dni);
		pFecha=fecha;
		pestado=new SimpleStringProperty(estado);
	}
/*[Objetos]
 * 
//	public MLibro getLibro() {
//		return pLibro;
//	}
//
//	public void setLibro(MLibro libro) {
//		this.pLibro = libro;
//	}
//
//	public MLector getLector() {
//		return pLector;
//	}
//
//	public void setLector(MLector lector) {
//		this.pLector = lector;
//	}
 * 
 */

	public String getCodigo() {
		return pcodigo.get();
	}

	public void setCodigo(String codigo) {
		this.pcodigo.setValue(codigo);
	}

	public String getLibro() {
		return plibro.get();
	}

	public void setLibro(String libro) {
		this.plibro.set(libro);
	}

	public String getDni() {
		return pdni.get();
	}

	public void setDni(String dni) {
		this.pdni.set(dni);
	}

	public Date getFecha() {
		return pFecha;
	}

	public void setFecha(Date fecha) {
		this.pFecha=fecha;
	}
	
	public String getEstado(){
		return pestado.get();
	}
	
	public void setEstado(String estado){
		pestado.set(estado);
	}

}
