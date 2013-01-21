package Controlador;

import java.sql.Connection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class CReportes {
	
	private  Connection coneccion;
	private JasperReport jasperReport;
	private JasperPrint jasperPrint;
	private JasperViewer visor;
	public CReportes() {
		coneccion=CBussines.getConnection();
	}
	
	@SuppressWarnings("deprecation")
	public void consolidadoPrestamo(){
		
		try {
			jasperReport=(JasperReport)JRLoader.loadObject(
					getClass().getResourceAsStream("/Resources/Reportes/Prestamo.jasper"));
			jasperPrint=JasperFillManager.fillReport(jasperReport, null,coneccion);
			visor=new JasperViewer(jasperPrint,false);
			visor.setTitle("Consolidado de Prestamos");
			visor.show();
			
		} catch (JRException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void inventario(){
		try {
			jasperReport=(JasperReport)JRLoader.loadObject(
					getClass().getResourceAsStream("/Resources/Reportes/Inventario.jasper"));
			jasperPrint=JasperFillManager.fillReport(jasperReport, null,coneccion);
			visor=new JasperViewer(jasperPrint,false);
			visor.setTitle("Libros en Inventario");
			visor.show();
		} catch (JRException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void requerimiento(){
		try {
			jasperReport=(JasperReport)JRLoader.loadObject(
					getClass().getResourceAsStream("/Resources/Reportes/Requerimiento.jasper"));
			jasperPrint=JasperFillManager.fillReport(jasperReport, null,coneccion);
			visor=new JasperViewer(jasperPrint,false);
			visor.setTitle("Libros Requeridos por Maestros");
			visor.show();
		} catch (JRException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void deudores(){
		try {
			jasperReport=(JasperReport)JRLoader.loadObject(
					getClass().getResourceAsStream("/Resources/Reportes/Deudores.jasper"));
			jasperPrint=JasperFillManager.fillReport(jasperReport, null,coneccion);
			visor=new JasperViewer(jasperPrint,false);
			visor.setTitle("Deudores");
			visor.show();
		} catch (JRException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void recienLlegados(){
		try {
			jasperReport=(JasperReport)JRLoader.loadObject(
					getClass().getResourceAsStream("/Resources/Reportes/RecienLlegados.jasper"));
			jasperPrint=JasperFillManager.fillReport(jasperReport, null,coneccion);
			visor=new JasperViewer(jasperPrint,false);
			visor.setTitle("Libros Adquiridos Recientemente");
			visor.show();
		} catch (JRException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void ranking(){
		try {
			jasperReport=(JasperReport)JRLoader.loadObject(
					getClass().getResourceAsStream("/Resources/Reportes/Ranking.jasper"));
			jasperPrint=JasperFillManager.fillReport(jasperReport, null,coneccion);
			visor=new JasperViewer(jasperPrint,false);
			visor.setTitle("Ranking de Lectores");
			visor.show();
		} catch (JRException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
