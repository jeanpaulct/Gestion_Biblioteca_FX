package Controlador;

import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import Modelo.MLibro;
import Vista.Buscador;
import Vista.LibrosRecienLlegados;
import Vista.Ranking;
import Vista.ResultadosBusqueda;



public class Principal extends Application {
	private char tipoFiltro='N';
	public StackPane nodoRaiz;
	
	public Parent buscadorView;
	public Parent resultadosBusquedaView;
	public Parent librosRecienLlegadosView;
	public Parent rankigView;
	
	private Buscador buscadorClass;
	private ResultadosBusqueda resultadosBusquedaClass;
	private LibrosRecienLlegados librosRecienLLegadosClass;
	private Ranking rankingClass;
	
	public static Scene escena;
	
	//datos
	private CBussiness cBussiness;
	private Integer currentIndex;
	private Integer currentIndexNuevos=0;
	
	private ObservableList<MLibro> listaLibros;
	private ObservableList<MLibro> listaNuevosLibros;
	private ObservableList<String> autoAll;
	
	public Principal() {
		cBussiness=new CBussiness();
		listaLibros=FXCollections.observableArrayList();
		listaNuevosLibros=cBussiness.getNuevosLibros();
		autoAll=cBussiness.getAuto();
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		nodoRaiz=new StackPane();
		escena=new Scene(nodoRaiz,1000, 700);
		// para el windows message
			
		
		buscadorView=creaBuscador(primaryStage);
		resultadosBusquedaView=creaResultadosBuscador();
		librosRecienLlegadosView=creaLibrosLlegados();
		rankigView=creaRanking();
		
		nodoRaiz.getChildren().add(buscadorView);
		
		
		primaryStage.setTitle("Buscador Inteligente \"Biblioteca Rosarina\"");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("ico.png")));
		primaryStage.setMaxWidth(1016);
		primaryStage.setMaxHeight(738);
		primaryStage.setX(175);
		primaryStage.setY(0);
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				cBussiness.close();
			}
		});
		
		primaryStage.setScene(escena);
		primaryStage.show();
	}
	
	private Parent creaBuscador(final Stage stage) {
		
		buscadorClass=new Buscador();
		
		stage.xProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number antiguaUbicacion, Number nuevaUbicacion) {
				if(!Double.isNaN(antiguaUbicacion.doubleValue())){
					double aux=buscadorClass.popUpBusqueda.xProperty().get();
					double varia=nuevaUbicacion.doubleValue()-antiguaUbicacion.doubleValue();
					buscadorClass.popUpBusqueda.setX(aux+varia);
					//System.out.println("Variacion"+varia+", nuevo valor:"+buscadorClass.popUpBusqueda.getX());
				}
//				else{
//					System.out.println("null");
//				}
			}
		});
		
		stage.yProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number antiguaUbicacion, Number nuevaUbicacion) {
				if(!Double.isNaN(antiguaUbicacion.doubleValue())){
					double aux=buscadorClass.popUpBusqueda.yProperty().get();
					double varia=nuevaUbicacion.doubleValue()-antiguaUbicacion.doubleValue();
					buscadorClass.popUpBusqueda.setY(aux+varia);
					//System.out.println("Variacion"+varia+", nuevo valor:"+buscadorClass.popUpBusqueda.getX());
				}
//				else{
//					System.out.println("null");
//				}
			}
		});
		
		buscadorClass.setnextManejador(new EventHandler<Event>() {
			
			public void handle(Event event) {
				nodoRaiz.setStyle("-fx-background-color: white;");
				Search(buscadorClass.cajaBusqueda.getText());
				nodoRaiz.getChildren().setAll(resultadosBusquedaView);
			}
		});
		
		buscadorClass.listaAutocompletado.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getClickCount()==2){
					buscadorClass.cajaBusqueda.setText(
							buscadorClass.listaAutocompletado.getSelectionModel().getSelectedItem());
					buscadorClass.popUpBusqueda.hide();
				}
			}
		});
		
		buscadorClass.listaAutocompletado.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case ESCAPE:
					buscadorClass.popUpBusqueda.hide();
					break;
				case ENTER:		
					if(buscadorClass.listaAutocompletado.getSelectionModel().getSelectedItem()!=null &&
					buscadorClass.popUpBusqueda.isShowing()){
						buscadorClass.cajaBusqueda.setText(
								buscadorClass.listaAutocompletado.getSelectionModel().getSelectedItem());
						Search(buscadorClass.cajaBusqueda.getText());
						nodoRaiz.getChildren().setAll(resultadosBusquedaView);
					}else{
						Search(buscadorClass.cajaBusqueda.getText());
						nodoRaiz.getChildren().setAll(resultadosBusquedaView);
						buscadorClass.popUpBusqueda.hide();
					}
					break;
				default:
					break;
				}
			}
			
		});
		
		buscadorClass.cajaBusqueda.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getClickCount()==2){
					buscadorClass.popUpBusqueda.show(stage);
				}
			}
		});
		
		buscadorClass.goLibrosRecienLLegados(new EventHandler<Event>() {
			
			public void handle(Event event) {
				nodoRaiz.getChildren().setAll(librosRecienLlegadosView);
				actualizaResultadoNuevosLibros();
			}
		});
		
		buscadorClass.goRanking(new EventHandler<Event>() {
			
			public void handle(Event event) {
				nodoRaiz.getChildren().setAll(rankigView);
			}
		});
		
		buscadorClass.onKeyCajaTexto(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				
				if(!(event.getCode()==KeyCode.SHIFT) || !(event.getCode()==KeyCode.ALT) ||
						!(event.getCode()==KeyCode.HOME) ||
						!(event.getCode()==KeyCode.CONTROL) || !(event.getCode()==KeyCode.CAPS) ||
						!(event.getCode()==KeyCode.F1) || !(event.getCode()==KeyCode.F2) ||
						!(event.getCode()==KeyCode.F3) || !(event.getCode()==KeyCode.F4) ||
						!(event.getCode()==KeyCode.F5) || !(event.getCode()==KeyCode.F6) ||
						!(event.getCode()==KeyCode.F7) ||!(event.getCode()==KeyCode.F8) ||
						!(event.getCode()==KeyCode.F9) || !(event.getCode()==KeyCode.F10) ||
						!(event.getCode()==KeyCode.F11) || !(event.getCode()==KeyCode.F12) ||
						!(event.getCode()==KeyCode.NUM_LOCK) || !(event.getCode()==KeyCode.PAUSE) ||
						!(event.getCode()==KeyCode.INSERT) || !(event.getCode()==KeyCode.END)){
					AutoCompleted(buscadorClass.cajaBusqueda.getText(), stage);					
				}else{
					buscadorClass.popUpBusqueda.hide();
				}
			}
		});
		
		buscadorClass.goIndex(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				buscadorClass.txtTipoFiltro.setText("");
				resultadosBusquedaClass.txtTipoFiltro.setText("");
				reiniciaValores();
			}
		});
		
		//Eventos filtro
		buscadorClass.setFiltroAutor(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				if(tipoFiltro!='A'){
					tipoFiltro='A';
					
					buscadorClass.txtTipoFiltro.setText("Por Autor");
					resultadosBusquedaClass.txtTipoFiltro.setText("Por Autor");
					
					Search(buscadorClass.cajaBusqueda.getText());
					if(!listaLibros.isEmpty()){
						nodoRaiz.getChildren().setAll(resultadosBusquedaView);
					}
				}
			}
		});
		
		buscadorClass.setFiltroTitulo(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				if(tipoFiltro!='T'){
					
					tipoFiltro='T';
					
					buscadorClass.txtTipoFiltro.setText("Por Título");
					resultadosBusquedaClass.txtTipoFiltro.setText("Por Título");
					
					Search(buscadorClass.cajaBusqueda.getText());
					if(!listaLibros.isEmpty()){
						nodoRaiz.getChildren().setAll(resultadosBusquedaView);
					}
				}
			}
		});
		
		buscadorClass.setFiltroEditorial(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				if(tipoFiltro!='E'){
					tipoFiltro='E';
					
					buscadorClass.txtTipoFiltro.setText("Por Editorial");
					resultadosBusquedaClass.txtTipoFiltro.setText("Por Editorial");
					
					Search(buscadorClass.cajaBusqueda.getText());
					
					if(!listaLibros.isEmpty()){
						nodoRaiz.getChildren().setAll(resultadosBusquedaView);
					}
				}
			}
		});

		StackPane stackPane=new StackPane();
		stackPane.getChildren().add(buscadorClass.getNodoVista());
		return stackPane;
	}
	
	private Parent creaResultadosBuscador() {
		resultadosBusquedaClass=new ResultadosBusqueda();
		
		//para ir a inicio
		resultadosBusquedaClass.goIndex(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				
				buscadorClass.txtTipoFiltro.setText("");
				resultadosBusquedaClass.txtTipoFiltro.setText("");
				reiniciaValores();
				nodoRaiz.getChildren().setAll(buscadorView);
			}
		});

		resultadosBusquedaClass.goLibrosRecienLLegados(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				nodoRaiz.getChildren().setAll(librosRecienLlegadosView);
				actualizaResultadoNuevosLibros();
			}
		});
		
		resultadosBusquedaClass.goRanking(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				nodoRaiz.getChildren().setAll(rankigView);
			}
		});
		
		resultadosBusquedaClass.navegacionBusqueda(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				if(!listaLibros.isEmpty()){
					if(event.getCode()==KeyCode.RIGHT){
						//System.out.println("Derecha");
						if(currentIndex!=listaLibros.size()-1){
							currentIndex+=1;
							actualizaResultado();
						}
					}else{
						if(event.getCode()==KeyCode.LEFT){
							//System.out.println("Izquierda");
							if(currentIndex!=0){
								currentIndex-=1;
								actualizaResultado();
							}
						}
					}
				}
				if(event.getCode()==KeyCode.ESCAPE){
					resultadosBusquedaClass.txtTipoFiltro.setText("");
					tipoFiltro='N';
					reiniciaValores();
					Search(resultadosBusquedaClass.cajaTextoBusqueda.getText());
				}
			}
		});
		
		resultadosBusquedaClass.setNext(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				if(currentIndex!=listaLibros.size()-1 && !listaLibros.isEmpty()){
					currentIndex+=1;
					actualizaResultado();
				}
			}
		});
		
		resultadosBusquedaClass.setBack(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				if(currentIndex!=0 && !listaLibros.isEmpty()){
					currentIndex-=1;
					actualizaResultado();
				}
			}
		});
		
		resultadosBusquedaClass.setBusqueda(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				Search(resultadosBusquedaClass.cajaTextoBusqueda.getText());
			}
		});
		
		resultadosBusquedaClass.setNuevaBusqueda(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.ENTER){
					Search(resultadosBusquedaClass.cajaTextoBusqueda.getText());
				}
			}
		});
		
		//Crea filtros
		
		resultadosBusquedaClass.setFiltroTitulo(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				if(tipoFiltro!='T'){
					tipoFiltro='T';
					
					resultadosBusquedaClass.txtTipoFiltro.setText("Por Título");
					reiniciaValores();
					Search(resultadosBusquedaClass.cajaTextoBusqueda.getText());
				}
			}
		});
		
		resultadosBusquedaClass.setFiltroAutor(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				if(tipoFiltro!='A'){
					tipoFiltro='A';
					
					resultadosBusquedaClass.txtTipoFiltro.setText("Por Autor");
					reiniciaValores();
					Search(resultadosBusquedaClass.cajaTextoBusqueda.getText());
				}
			}
		});
		
		resultadosBusquedaClass.setFiltroEditorial(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				if(tipoFiltro!='E'){
					tipoFiltro='E';
					
					resultadosBusquedaClass.txtTipoFiltro.setText("Por Editorial");
					reiniciaValores();
					Search(resultadosBusquedaClass.cajaTextoBusqueda.getText());
				}
			}
		});

		
		StackPane stackPane=new StackPane();
		stackPane.getChildren().add(resultadosBusquedaClass.getNodoVista());
		return stackPane;
	}
	
	private Parent creaRanking() {
		rankingClass=new Ranking();
		
		rankingClass.goIndex(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				tipoFiltro='T';
				buscadorClass.txtTipoFiltro.setText("");
				resultadosBusquedaClass.txtTipoFiltro.setText("");
				nodoRaiz.getChildren().setAll(buscadorView);
			}
		});
		
		rankingClass.goLibrosRecienLLegados(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				nodoRaiz.getChildren().setAll(librosRecienLlegadosView);
				actualizaResultadoNuevosLibros();
			}
		});
		
		rankingClass.setFiltroAutor(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				buscadorClass.txtTipoFiltro.setText("Por Autor");
				resultadosBusquedaClass.txtTipoFiltro.setText("Por Autor");
				tipoFiltro='A';
				nodoRaiz.getChildren().setAll(buscadorView);
			}
		});
		
		rankingClass.setFiltroEditorial(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				buscadorClass.txtTipoFiltro.setText("Por Editorial");
				resultadosBusquedaClass.txtTipoFiltro.setText("Por Editorial");
				tipoFiltro='E';
				nodoRaiz.getChildren().setAll(buscadorView);
			}
		});
		
		rankingClass.setFiltroTitulo(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				buscadorClass.txtTipoFiltro.setText("Por Título");
				resultadosBusquedaClass.txtTipoFiltro.setText("Por Título");
				tipoFiltro='T';
				nodoRaiz.getChildren().setAll(buscadorView);
			}
		});
		
		setRanking();
		
		StackPane stackPane=new StackPane();
		stackPane.getChildren().add(rankingClass.getNodoVista());
		return stackPane;
	}

	private Parent creaLibrosLlegados() {
		
		librosRecienLLegadosClass=new LibrosRecienLlegados();
		
		librosRecienLLegadosClass.goIndex(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				nodoRaiz.getChildren().setAll(buscadorView);
			}
		});
		
		librosRecienLLegadosClass.goRanking(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				nodoRaiz.getChildren().setAll(rankigView);
			}
		});
		
		librosRecienLLegadosClass.nextLibro(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				if(currentIndexNuevos+1 != listaNuevosLibros.size()){
					currentIndexNuevos++;
					actualizaResultadoNuevosLibros();
				}
			}
		});
		
		librosRecienLLegadosClass.backLibro(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				if(currentIndexNuevos !=0){
					currentIndexNuevos--;
					actualizaResultadoNuevosLibros();
				}
			}
		});
		
		librosRecienLLegadosClass.navegacionLibros(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case RIGHT:
					if(currentIndexNuevos+1 != listaNuevosLibros.size()){
						currentIndexNuevos++;
						actualizaResultadoNuevosLibros();
					}
					break;
				case LEFT:
					if(currentIndexNuevos !=0){
						currentIndexNuevos--;
						actualizaResultadoNuevosLibros();
					}
					break;
				case ESCAPE:
					nodoRaiz.getChildren().setAll(buscadorView);
					break;
				}
			}
		});
		StackPane stackPane=new StackPane();
		stackPane.getChildren().add(librosRecienLLegadosClass.getNodoVista());
		return stackPane;
	}
	
	private void setRanking(){
		List<String> values=cBussiness.ranking();
		
		for(int i=0;i<10;i++){
			if(i<5){
				rankingClass.rankingAlumnos.get(i).setText(values.get(i));
			}
			else{
				rankingClass.rankingDocentes.get(i-5).setText(values.get(i));
			}
		}
	}
	
	private void actualizaResultadoNuevosLibros() {
		
		librosRecienLLegadosClass.cajaTotalResultados.setText(String.valueOf(listaNuevosLibros.size()));
		librosRecienLLegadosClass.cajaIndiceActual.setText(String.valueOf(currentIndexNuevos+1));
		librosRecienLLegadosClass.txtTituloLibro.setText(changeResult(listaNuevosLibros.get(currentIndexNuevos).getTitulo()));
		librosRecienLLegadosClass.txtAutorLibro.setText(changeResult(listaNuevosLibros.get(currentIndexNuevos).getAutor()));
		librosRecienLLegadosClass.txtEditorialLibro.setText(changeResult(listaNuevosLibros.get(currentIndexNuevos).getEditorial()));
		librosRecienLLegadosClass.txtAnioLibro.setText(changeResult(listaNuevosLibros.get(currentIndexNuevos).getAnioEdicion()));
	}
	
	private void actualizaResultado(){
		
		resultadosBusquedaClass.cajaTotalResultados.setText(String.valueOf(listaLibros.size()));
		resultadosBusquedaClass.cajaIndiceActual.setText(String.valueOf(currentIndex+1));
		resultadosBusquedaClass.txtTituloLibro.setText(changeResult(listaLibros.get(currentIndex).getTitulo()));
		resultadosBusquedaClass.txtAutorLibro.setText(changeResult(listaLibros.get(currentIndex).getAutor()));
		resultadosBusquedaClass.txtEditorialLibro.setText(listaLibros.get(currentIndex).getEditorial());
		resultadosBusquedaClass.txtAnioLibro.setText(listaLibros.get(currentIndex).getAnioEdicion());
	}
	
	private void reiniciaValores(){
		listaLibros.clear();
		currentIndex=0;
		resultadosBusquedaClass.cajaTotalResultados.setText(null);
		resultadosBusquedaClass.cajaIndiceActual.setText(null);
		resultadosBusquedaClass.txtTituloLibro.setText(null);
		resultadosBusquedaClass.txtAutorLibro.setText(null);
		resultadosBusquedaClass.txtEditorialLibro.setText(null);
		resultadosBusquedaClass.txtAnioLibro.setText(null);
	}
	
	private String changeResult(String cadena){
		if(cadena.length()>50){
			return cadena.substring(0, 50);
			
		}
		else{
			return cadena;
		}
	}
	
	private String trataBusqueda(String busqueda){
		String []arrays=new String[busqueda.length()];
		boolean space=true;
		for(int i=0;i<busqueda.length();i++){
			arrays[i]=busqueda.substring(i, i+1);
			if(arrays[i].equals(" ") && space){
				arrays[i]="";
			}else{
				space=false;
			}
		}
		
		busqueda="";
		for(int i=0;i<arrays.length;i++){
			busqueda+=arrays[i];
		}
		
		return busqueda;
	}

	private void Search(String cadenaBusqueda){
		cadenaBusqueda=trataBusqueda(cadenaBusqueda);
		if(cadenaBusqueda.matches("((\\w+)(\\W*))+")){
			
			buscadorClass.cajaBusqueda.setText(cadenaBusqueda);
			resultadosBusquedaClass.cajaTextoBusqueda.setText(cadenaBusqueda);
			
			reiniciaValores();
			
			listaLibros=cBussiness.getConsulta(resultadosBusquedaClass.cajaTextoBusqueda.getText(), tipoFiltro);
			
			if(!listaLibros.isEmpty()){
				actualizaResultado();
			}else{
				reiniciaValores();
				resultadosBusquedaClass.cajaTotalResultados.setText("0 Resultados");
			}
		}
	}
	
	private void AutoCompleted(String cadenaEscritura,Stage stage){
		ObservableList<String> filtro=FXCollections.observableArrayList();
		cadenaEscritura=cadenaEscritura.toUpperCase();
		int indexOcurrencia;
		for (String data : autoAll) {
			indexOcurrencia=data.indexOf(cadenaEscritura);
			if(indexOcurrencia!=-1){
				filtro.add(data);
			}
		}
		
		if(filtro.isEmpty()){
			buscadorClass.popUpBusqueda.hide();
		}else{
			buscadorClass.listaAutocompletado.setItems(filtro);
			buscadorClass.popUpBusqueda.show(stage);
		}
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
