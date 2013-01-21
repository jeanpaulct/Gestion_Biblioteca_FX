package Vista;

import javafx.event.Event;
import javafx.event.EventHandler;

public abstract class  PaginaAbstracta {
	
	protected static double DefaultAncho=1000;
	protected static double DefaultLargo=700;
	protected static Double maxHeightCabezera=28.0;
	protected static Double maxHeightPie=40.0;
	protected static String estiloCaja="-fx-border-radius: 3px;" +
			"-fx-border-color: rgb(37,143,250);" +
			"-fx-border-width: 2px;";
	protected static String estiloCajaResultados="-fx-border-radius: 8px;" +
			"-fx-border-color: rgb(37,143,250);" +
			"-fx-border-width: 2px;" +
			"-fx-font: oblique 24px bolder;" +
			"-fx-alignment: center;";
	
	public PaginaAbstracta() {
		
	}
	
	public void setnextManejador(EventHandler<Event> eventHandler) {}
	
	
	
	

}
