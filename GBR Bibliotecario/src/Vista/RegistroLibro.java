package Vista;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
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

public class RegistroLibro extends ModeloPagina{

	private BorderPane layoutPrincipal;
	public Node btnSalir;
	public Node btnGuardar;
	public Node btnNuevo;
	public TextField cajaLCodigoDway;
	public TextField cajaLTitulo;
	public TextField cajaLAutor;
	public TextField cajaLEditorial;
	public TextField cajaLAnioEdicion;
	public ChoiceBox<String> chboxlestado;
	public RegistroLibro() {
		
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
				nuevoLibro();
			}
		});
		
	}
	
	private void construyeObjetos() {
		
		btnSalir=buildButton("btnSalir.png", 120 , 120, true);
		btnGuardar=buildButton("btnGuardar.png", 120, 120, true);
		btnNuevo=buildButton("btnNuevo.png", 120, 120, true);
		
		cajaLCodigoDway=TextFieldBuilder.create()
				.editable(false)
				.promptText("Código Dway...")
				.minHeight(35)
				.maxWidth(300)
				.style(estiloCajaDisable)
				.build();
		
		cajaLTitulo=TextFieldBuilder.create()
				.editable(false)
				.promptText("Título...")
				.minHeight(35)
				.maxWidth(300)
				.style(estiloCajaDisable)
				.build();
		
		cajaLAutor=TextFieldBuilder.create()
				.editable(false)
				.promptText("Autor...")
				.minHeight(35)
				.maxWidth(150)
				.style(estiloCajaDisable)
				.build();
		
		cajaLEditorial=TextFieldBuilder.create()
				.editable(false)
				.promptText("Editorial... ")
				.minHeight(35)
				.maxWidth(150)				
				.style(estiloCajaDisable)
				.build();
		
		cajaLAnioEdicion=TextFieldBuilder.create()
				.editable(false)
				.promptText("Año de Edición... ")
				.minHeight(35)
				.maxWidth(150)				
				.style(estiloCajaDisable)
				.build();
		
		chboxlestado=new ChoiceBox<String>();
		chboxlestado.setPrefHeight(35);
		chboxlestado.setDisable(true);
		chboxlestado.setItems(FXCollections.observableArrayList("Nuevo","Regular","Pésimo"));
		
		
	}
	
	private void deleteFormulario(){
		
		cajaLCodigoDway.setText(null);
		chboxlestado.getSelectionModel().clearSelection();
		cajaLTitulo.setText(null);
		cajaLAutor.setText(null);
		cajaLEditorial.setText(null);
		cajaLAnioEdicion.setText(null);
	}

	private void enableControls(boolean active){
		cajaLCodigoDway.setEditable(active);
		chboxlestado.setDisable(!active);
		cajaLTitulo.setEditable(active);
		cajaLAutor.setEditable(active);
		cajaLEditorial.setEditable(active);
		cajaLAnioEdicion.setEditable(active);
	}
	
	private void changeStyleEnable(boolean active){
		if(active){
			cajaLCodigoDway.setStyle(estiloCaja);
			chboxlestado.setStyle(estiloCaja);
			cajaLTitulo.setStyle(estiloCaja);
			cajaLAutor.setStyle(estiloCaja);
			cajaLEditorial.setStyle(estiloCaja);
			cajaLAnioEdicion.setStyle(estiloCaja);
		}else{
			cajaLCodigoDway.setStyle(estiloCajaDisable);
			chboxlestado.setStyle(estiloCajaDisable);
			cajaLTitulo.setStyle(estiloCajaDisable);
			cajaLAutor.setStyle(estiloCajaDisable);
			cajaLEditorial.setStyle(estiloCajaDisable);
			cajaLAnioEdicion.setStyle(estiloCajaDisable);
		}
	}
	
	public void nuevoLibro(){		
		deleteFormulario();
		enableControls(true);
		changeStyleEnable(true);
	}
	
	public void beginNew(){
		deleteFormulario();
		enableControls(false);
		changeStyleEnable(false);
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
										"tituloRegistroLibro.png").toString()))
								.fitWidth(300)
								.fitHeight(100)
								.build())
						.spacing(10)
						.build(),
						ImageViewBuilder.create()
						.image(new Image(getClass().getResource("Imagenes/" +
								"separador.png").toString()))
						.fitWidth(600)
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
						.text("Cód. Dwey		:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build(),
						TextBuilder.create()
						.text("Titulo			:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build(),
						TextBuilder.create()
						.text("Autor			:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build(),
						TextBuilder.create()
						.text("Editorial	   		:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build(),
						TextBuilder.create()
						.text("Año de Edición	:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build(),
						TextBuilder.create()
						.text("Estado			:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build())
				.spacing(20)
				.build());
		StackPane.setMargin(contenedor.getChildren().get(2), new Insets(200, 0, 0, 40));
		
		contenedor.getChildren().add(VBoxBuilder.create()
				.children(cajaLCodigoDway,
						cajaLTitulo,cajaLAutor,cajaLEditorial,
						cajaLAnioEdicion,chboxlestado)
				.spacing(20)
				.build());
		StackPane.setMargin(contenedor.getChildren().get(3), new Insets(202, 0, 0, 330));
		
		return contenedor;
	}
	
	public void goIndex(EventHandler<Event> event) {
		super.goIndex(event);
		btnSalir.setOnMouseClicked(event);
	}
	
	public void escuchaTeclas(EventHandler<KeyEvent> eventHandler){
		layoutPrincipal.setOnKeyPressed(eventHandler);
		cajaLCodigoDway.setOnKeyPressed(eventHandler);
		chboxlestado.setOnKeyPressed(eventHandler);
		cajaLTitulo.setOnKeyPressed(eventHandler);
		cajaLAutor.setOnKeyPressed(eventHandler);
		cajaLEditorial.setOnKeyPressed(eventHandler);
		cajaLAnioEdicion.setOnKeyPressed(eventHandler);
	}
	
	public void onGuardar(EventHandler<Event> eventHandler){
		btnGuardar.setOnMouseClicked(eventHandler);
	}
	
}
