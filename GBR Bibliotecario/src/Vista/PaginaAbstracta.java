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
	protected static String estiloCajaDisable="-fx-background-color: rgb(207, 203, 205);" +
			"-fx-border-radius: 3px;" +
			"-fx-border-color: rgb(37,143,250);" +
			"-fx-border-width: 2px;" +
			"-fx-text-fill: white;";
	protected static String botonLogin="-fx-background-color:blue," +
					"linear-gradient(#fcfcfc, #f3f3f3)," +
					"linear-gradient(#f2f2f2 0%, #ebebeb 49%, #dddddd 50%, #cfcfcf 100%);" +
			"-fx-background-insets: 1, 2, 3;" +
			"-fx-background-radius: 0 0 9 0, 0 0 8 0, 0 0 8 0;" +
			"-fx-padding: 3 30 3 30;" +
			"-fx-text-fill: black;" +
			"-fx-font-size: 15px;";
	
	public void setnextManejador(EventHandler<Event> eventHandler) {}
	
}
