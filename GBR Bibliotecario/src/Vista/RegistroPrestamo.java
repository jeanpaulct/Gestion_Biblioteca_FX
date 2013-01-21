package Vista;

import java.util.Date;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.effect.GaussianBlurBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextBuilder;
import Modelo.MPrestamo;

public class RegistroPrestamo extends ModeloPagina {

	private BorderPane layoutPrincipal;
	public Node btnSalir;
	public Node btnGuardar;
	public Node btnNuevo;
	public TextField cajaLCodigo;
	public TextField cajaADNI;
	public ChoiceBox<String>  chbAGrado;
	public ChoiceBox<String>  chbASeccion;
	public ChoiceBox<String>  chbTipoPrestamo;
	public TableView<MPrestamo> tblvistaPrestamos;
	public TableColumn<MPrestamo, String> colcodigoPrestamo;
	public TableColumn<MPrestamo, String> colcodigoLibro;
	public TableColumn<MPrestamo, String> coldniLector;
	public TableColumn<MPrestamo, Date> colfechaPrestamo;
	public TableColumn<MPrestamo, String> colestadoPrestamo;
	public RegistroPrestamo() {
		
		construyeObjetos();
		contruyeEventos();
		layoutPrincipal=new BorderPane();
		
		layoutPrincipal.setCenter(buildCenter());
		layoutPrincipal.setTop(buildSuperiorBibliotecario(true));
		layoutPrincipal.setBottom(buildInferiorBibliotecario());
		
		this.nodoVista=layoutPrincipal;
	}
	
	
	private void contruyeEventos() {
		
		btnNuevo.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				NuevoPrestamo();
			}
		});
		
	}

	@SuppressWarnings("unchecked")
	private void construyeObjetos() {
		
		btnSalir=buildButton("btnSalir.png", 120 , 120, true);
		btnGuardar=buildButton("btnGuardar.png", 120, 120, true);
		btnNuevo=buildButton("btnNuevo.png", 120, 120, true);
		
		cajaLCodigo=TextFieldBuilder.create()
				.editable(false)
				.minHeight(35)
				.maxWidth(300)
				.promptText("Codigo del Libro...")
				.style(estiloCajaDisable)
				.build();
		
		cajaADNI=TextFieldBuilder.create()
				.editable(false)
				.promptText("Dni...")
				.minHeight(35)
				.maxWidth(300)
				.style(estiloCajaDisable)
				.build();
		
		chbAGrado=new ChoiceBox<String>();
		chbAGrado.setItems(FXCollections.observableArrayList("1","2","3","4","5"));
		chbAGrado.setMinHeight(35);
		chbAGrado.setDisable(true);
		chbAGrado.setStyle(estiloCajaDisable);
		
		chbASeccion=new ChoiceBox<String>();
		chbASeccion.setItems(FXCollections.observableArrayList("A","B","C","D","E","F","G"));
		chbASeccion.setMinHeight(35);
		chbASeccion.setDisable(true);
		chbASeccion.setStyle(estiloCajaDisable);
		
		chbTipoPrestamo=new ChoiceBox<String>();
		chbTipoPrestamo.setItems(FXCollections.observableArrayList("Alumno","Docente","Padre de Familia"));
		chbTipoPrestamo.setMinHeight(35);
		chbTipoPrestamo.setDisable(true);
		chbTipoPrestamo.setStyle(estiloCajaDisable);
		
		tblvistaPrestamos=new TableView<MPrestamo>();
		tblvistaPrestamos.setMaxHeight(130);
		tblvistaPrestamos.setMaxWidth(612);
		tblvistaPrestamos.setEditable(true);
		
		colcodigoPrestamo=new TableColumn<MPrestamo, String>();
		colcodigoPrestamo.setText("Número Prestamo");
		colcodigoPrestamo.setMinWidth(140);
		
		colcodigoLibro=new TableColumn<MPrestamo, String>();
		colcodigoLibro.setText("Código Libro");
		colcodigoLibro.setMinWidth(140);
		
		coldniLector=new TableColumn<MPrestamo, String>();
		coldniLector.setText("DNI Lector");
		coldniLector.setMinWidth(140);
		
		colfechaPrestamo=new TableColumn<MPrestamo, Date>();
		colfechaPrestamo.setText("Fecha Préstamo");
		colfechaPrestamo.setMinWidth(140);
		
		colestadoPrestamo=new TableColumn<MPrestamo, String>();
		colestadoPrestamo.setText("Estado");
		colestadoPrestamo.setMinWidth(50);
		
		tblvistaPrestamos.getColumns().addAll(colcodigoPrestamo,colcodigoLibro,coldniLector,
				colfechaPrestamo,colestadoPrestamo);
		
	}
	
	private void deleteFormulario(){
		
		cajaLCodigo.setText(null);
		cajaADNI.setText(null);
		chbAGrado.getSelectionModel().clearSelection();
		chbASeccion.getSelectionModel().clearSelection();
		chbTipoPrestamo.getSelectionModel().clearSelection();
	}
	
	private void enableControls(boolean active){
		
		cajaLCodigo.setEditable(active);
		cajaADNI.setEditable(active);
		chbAGrado.setDisable(!active);
		chbASeccion.setDisable(!active);
		chbTipoPrestamo.setDisable(!active);
	}
	
	private void changeStyleEnable(boolean active){
		
		if(active){
			cajaLCodigo.setStyle(estiloCaja);
			cajaADNI.setStyle(estiloCaja);
			chbAGrado.setStyle(estiloCaja);
			chbASeccion.setStyle(estiloCaja);
			chbTipoPrestamo.setStyle(estiloCaja);
		}else{
			cajaLCodigo.setStyle(estiloCajaDisable);
			cajaADNI.setStyle(estiloCajaDisable);
			chbAGrado.setStyle(estiloCajaDisable);
			chbASeccion.setStyle(estiloCajaDisable);
			chbTipoPrestamo.setStyle(estiloCajaDisable);
		}
	}
	
	public void beginNew(){
		deleteFormulario();
		enableControls(false);
		changeStyleEnable(false);
	}
	
	public void NuevoPrestamo(){
		deleteFormulario();
		enableControls(true);
		changeStyleEnable(true);
	}
	
	private Node buildCenter() {
		StackPane contenedor=new StackPane();
		
		contenedor.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/logoColegio.png").toString()))
				.fitWidth(350)
				.fitHeight(400)
				.opacity(.3f)
				.build());
		StackPane.setMargin(contenedor.getChildren().get(0), new Insets(100, 40, 0, 500));
		
		contenedor.getChildren().add(VBoxBuilder.create()
				.children(HBoxBuilder.create()
						.children(btnSalir,btnGuardar,btnNuevo,
								ImageViewBuilder.create()
								.image(new Image(getClass().getResource("Imagenes/" +
										"tituloRegistroPrestamo.png").toString()))
								.fitWidth(300)
								.fitHeight(100)
								.build())
						.spacing(3)
						.build(),
						ImageViewBuilder.create()
						.image(new Image(getClass().getResource("Imagenes/" +
								"separador.png").toString()))
						.fitWidth(350)
						.fitHeight(1)
						.effect(GaussianBlurBuilder.create().radius(3).build())
						.translateX(30)
						.rotate(180)
						.build())
				.padding(new Insets(20, 0, 0, 10))
				.spacing(30)
				.build());
		StackPane.setAlignment(contenedor.getChildren().get(1), Pos.TOP_LEFT);
		
		contenedor.getChildren().add(VBoxBuilder.create()
				.children(TextBuilder.create()
						.text("Cód. del Libro		:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build(),
						TextBuilder.create()
						.text("Nº DNI del Lector	:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build(),
						TextBuilder.create()
						.text("Usuario			:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build(),
						TextBuilder.create()
						.text("Grado			:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build(),
						TextBuilder.create()
						.text("Sección	   		:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build())
				.spacing(20)
				.build());
		StackPane.setMargin(contenedor.getChildren().get(2), new Insets(200, 0, 0, 40));
		
		contenedor.getChildren().add(VBoxBuilder.create()
				.children(cajaLCodigo,cajaADNI,chbTipoPrestamo,chbAGrado,chbASeccion)
				.spacing(20)
				.build());
		StackPane.setMargin(contenedor.getChildren().get(3), new Insets(202, 0, 0, 330));
		
		contenedor.getChildren().add(ImageViewBuilder.create()
						.image(new Image(getClass().getResource("Imagenes/" +
								"separador.png").toString()))
						.fitWidth(650)
						.fitHeight(1)
						.effect(GaussianBlurBuilder.create().radius(3).build())
						.rotate(180)
						.build());
		
		StackPane.setAlignment(contenedor.getChildren().get(4), Pos.CENTER_LEFT);
		StackPane.setMargin(contenedor.getChildren().get(4), new Insets(350, 0, 20, 10));
		
		contenedor.getChildren().add(tblvistaPrestamos);
		StackPane.setAlignment(contenedor.getChildren().get(5), Pos.BOTTOM_LEFT);
		StackPane.setMargin(contenedor.getChildren().get(5), new Insets(0, 0, 10, 80));
		return contenedor;
	}
	
	@Override
	public void goIndex(EventHandler<Event> event) {		
		btnSalir.setOnMouseClicked(event);
	}
	
	public void escuchaTeclas(EventHandler<KeyEvent> eventHandler){
		layoutPrincipal.setOnKeyPressed(eventHandler);
		cajaLCodigo.setOnKeyPressed(eventHandler);
		cajaADNI.setOnKeyPressed(eventHandler);
		chbAGrado.setOnKeyPressed(eventHandler);
		chbASeccion.setOnKeyPressed(eventHandler);
		chbTipoPrestamo.setOnKeyPressed(eventHandler);
	}
	
	public void onGuardar(EventHandler<Event> eventHandler){
		btnGuardar.setOnMouseClicked(eventHandler);
	}
	
	public void onClickTable(EventHandler<MouseEvent> eventHandler){
		tblvistaPrestamos.setOnMouseClicked(eventHandler);
	}
	
}
