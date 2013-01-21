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

public class RegistroRequerimiento extends ModeloPagina{
	private BorderPane layoutPrincipal;
	public Node btnSalir;
	public Node btnGuardar;
	public Node btnNuevo;
	public TextField cajaLTitulo;
	public TextField cajaLAutor;
	public TextField cajaLEditorial;
	public TextField cajaLAnioEdicion;
	public ChoiceBox<String> areaPedido;
	public TextField cajaDNISolicitante;
	
	public RegistroRequerimiento() {
		
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
				nuevoRequerimiento();
			}
		});
		
		btnSalir.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				beginNew();
			}
		});
		
	}

	private void construyeObjetos() {
		
		btnSalir=buildButton("btnSalir.png", 120 , 120, true);
		btnGuardar=buildButton("btnGuardar.png", 120, 120, true);
		btnNuevo=buildButton("btnNuevo.png", 120, 120, true);
		
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
				.maxWidth(300)
				.style(estiloCajaDisable)
				.build();
		
		cajaLEditorial=TextFieldBuilder.create()
				.editable(false)
				.promptText("Editorial...")
				.minHeight(35)
				.maxWidth(300)
				.style(estiloCajaDisable)
				.build();
		
		cajaLAnioEdicion=TextFieldBuilder.create()
				.editable(false)
				.promptText("Año de edición... ")
				.minHeight(35)
				.maxWidth(80)
				.style(estiloCajaDisable)
				.build();
		
		areaPedido=new ChoiceBox<String>();
		areaPedido.setItems(FXCollections.observableArrayList("Matematica","Comunicación",
				"Inglés","Arte","Historia, Geografia y Economía","Formación Ciudadana y Cívica",
				"Persona, Familia y RRHH","Educación Fisica","Educación Religiosa",
				"Ciencia, Tecnologia y Ambiente","Educación para el Trabajo","Metodología",
				"Tutoría"));
		areaPedido.setStyle(estiloCajaDisable);
		areaPedido.setMinHeight(35);
		areaPedido.setDisable(true);
		
		cajaDNISolicitante=TextFieldBuilder.create()
				.editable(false)
				.promptText("Dni Solicitante... ")
				.minHeight(35)
				.maxWidth(80)
				.style(estiloCajaDisable)
				.build();
		
	}

	private void deleteFormulario(){
		cajaLTitulo.setText(null);
		cajaLAutor.setText(null);
		cajaLEditorial.setText(null);
		cajaLAnioEdicion.setText(null);
		areaPedido.getSelectionModel().clearSelection();
		cajaDNISolicitante.setText(null);
	}
	
	private void enableControls(boolean active){
		
		cajaLTitulo.setEditable(active);
		cajaLAutor.setEditable(active);
		cajaLEditorial.setEditable(active);
		cajaLAnioEdicion.setEditable(active);
		areaPedido.setDisable(!active);
		cajaDNISolicitante.setEditable(active);
	}
	
	private void changeStyleEnable(boolean active){
		if(active){
			cajaLTitulo.setStyle(estiloCaja);
			cajaLAutor.setStyle(estiloCaja);
			cajaLEditorial.setStyle(estiloCaja);
			cajaLAnioEdicion.setStyle(estiloCaja);
			areaPedido.setStyle(estiloCaja);
			cajaDNISolicitante.setStyle(estiloCaja);
		}else{
			cajaLTitulo.setStyle(estiloCajaDisable);
			cajaLAutor.setStyle(estiloCajaDisable);
			cajaLEditorial.setStyle(estiloCajaDisable);
			cajaLAnioEdicion.setStyle(estiloCajaDisable);
			areaPedido.setStyle(estiloCajaDisable);
			cajaDNISolicitante.setStyle(estiloCajaDisable);
		}
		
	}

	public void beginNew(){
		deleteFormulario();
		enableControls(false);
		changeStyleEnable(false);
	}
	
	public void nuevoRequerimiento(){
		deleteFormulario();
		enableControls(true);
		changeStyleEnable(true);
	}
	
	private Node buildCenter() {
		StackPane contenedor=new StackPane();
		
		contenedor.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/logoColegio.png").toString()))
				.fitWidth(300)
				.fitHeight(350)
				.opacity(.3f)
				.build());
		StackPane.setAlignment(contenedor.getChildren().get(0), Pos.BOTTOM_RIGHT);
		
		contenedor.getChildren().add(VBoxBuilder.create()
				.children(HBoxBuilder.create()
						.children(btnSalir,btnGuardar,btnNuevo,
								ImageViewBuilder.create()
								.image(new Image(getClass().getResource("Imagenes/" +
										"tituloRequerimientoNuevo.png").toString()))
								.fitWidth(450)
								.fitHeight(100)
								.build())
						.spacing(3)
						.build(),
						ImageViewBuilder.create()
						.image(new Image(getClass().getResource("Imagenes/" +
								"separador.png").toString()))
						.fitWidth(660)
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
						.text("Título			:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build(),
						TextBuilder.create()
						.text("Autor			:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build(),
						TextBuilder.create()
						.text("Editorial			:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build(),
						TextBuilder.create()
						.text("Año de Edición	:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build(),
						TextBuilder.create()
						.text("Area de Pedido	:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build(),
						TextBuilder.create()
						.text("Dni del Solicitante	:")
						.font(Font.font("Arial", 30))
						.fill(Color.BLACK)
						.build())
				.spacing(40)
				.build());
		StackPane.setMargin(contenedor.getChildren().get(2), new Insets(200, 0, 0, 40));
		
		contenedor.getChildren().add(VBoxBuilder.create()
				.children(cajaLTitulo,cajaLAutor,cajaLEditorial,cajaLAnioEdicion,areaPedido,cajaDNISolicitante)
				.spacing(40)
				.build());
		StackPane.setMargin(contenedor.getChildren().get(3), new Insets(202, 0, 0, 330));
		return contenedor;
	}
	
	@Override
	public void goIndex(EventHandler<Event> event) {
		// TODO Auto-generated method stub
		super.goIndex(event);
		btnSalir.setOnMouseClicked(event);
	}
	
	public void escuchaTeclas(EventHandler<KeyEvent> eventHandler){
		layoutPrincipal.setOnKeyPressed(eventHandler);
		cajaLTitulo.setOnKeyPressed(eventHandler);
		cajaLAutor.setOnKeyPressed(eventHandler);
		cajaLEditorial.setOnKeyPressed(eventHandler);
		areaPedido.setOnKeyPressed(eventHandler);
	}
	
	public void onGuardar(EventHandler<Event> eventHandler){
		btnGuardar.setOnMouseClicked(eventHandler);
	}
	
}
