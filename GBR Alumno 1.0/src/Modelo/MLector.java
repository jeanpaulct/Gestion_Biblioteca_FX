package Modelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MLector {
	
	private SimpleStringProperty cDni;
	private SimpleIntegerProperty cGrado;
	private SimpleStringProperty cSeccion;
	private SimpleStringProperty cTipo;
	
	public MLector() {
		cDni=new SimpleStringProperty(null);
		cGrado=new SimpleIntegerProperty();
		cSeccion=new SimpleStringProperty(null);
		cTipo=new SimpleStringProperty(null);
	}
	
	public MLector(String dni, String tipo) {
		cDni.setValue(dni);
		cTipo.setValue(tipo);
	}
	
	public MLector(String dni,int grado,String seccion, String tipo) {
		cDni.setValue(dni);
		cGrado.set(grado);
		cSeccion.setValue(seccion);
		cTipo.set(tipo);
	}

	public String getDni() {
		return cDni.get();
	}

	public void setDni(String Dni) {
		this.cDni.set(Dni);
	}

	public int getGrado() {
		return cGrado.get();
	}

	public void setGrado(int Grado) {
		this.cGrado.set(Grado);
	}

	public SimpleStringProperty getcSeccion() {
		return cSeccion;
	}

	public void setSeccion(String Seccion) {
		this.cSeccion.set(Seccion);
	}

	public String getTipo() {
		return cTipo.get();
	}

	public void setTipo(String Tipo) {
		this.cTipo.set(Tipo);
	}
	
	

}
