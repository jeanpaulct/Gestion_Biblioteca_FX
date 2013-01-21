package Vista;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlurBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.TilePaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextBuilder;
import Modelo.MLibro;

public class Acciones extends ModeloPagina{
	
	private StackPane center=new StackPane();
	@SuppressWarnings("unused")
	private String estiloBorde="-fx-background-color: rgb(242,242,238);" +
			"-fx-border-color: rgb(175,177,190);" +
			"-fx-border-radius: 4px;" +
			"-fx-border-width: 1px;";
	private Node busquedaClickView;
	private Node registroClickView;
	private Node reportesClickView;
	
	public BorderPane contenedorPrimario;	
	public Node btnEntornoBusqueda;
	public Node btnRegistroAccionesLibros;
	public Node btnReportes;
	public Node btnRegistroPrestamo;
	public Node btnRegistroLibro;
	public Node btnRequerimientoLibro;
	public Node btnVerConsolidadoPrestamo;
	public Node btnVerDeudoresLibros;
	public Node btnVerRanking;
	public Node btnVerInventario;
	public Node btnVerRequerimientoLibro;
	public Node btnVerNuevoLibros;
	public TextField cajabusqueda;
	public Button btnBuscar;
	public TableView<MLibro> tblresultadosBusqueda;
	public TableColumn<MLibro, String> coltituloLibro;
	public TableColumn<MLibro, String> colautorLibro;
	public TableColumn<MLibro, Integer> colejmLibro;
	
	public Acciones() {
		contenedorPrimario=new BorderPane();
		
		construyeObjetos();
		
		
		center.getChildren().setAll(buildCenter0());
		
		contenedorPrimario.setLeft(buildLeft());
		contenedorPrimario.setCenter(center);
		contenedorPrimario.setTop(buildSuperiorBibliotecario(true));
		contenedorPrimario.setBottom(buildInferiorBibliotecario());
		
		goIndex(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				center.getChildren().setAll(buildCenter0());
			}
		});
		
		super.nodoVista=contenedorPrimario;
	}
	
	
	@SuppressWarnings("unchecked")
	private void construyeObjetos() {
		
		btnEntornoBusqueda=buildButton("btnBusqueda.png", 300, 100, true);
		btnRegistroAccionesLibros=buildButton("btnAcciones.png", 300, 100, true);
		btnReportes=buildButton("btnReportes.png", 300, 100, true);
		
		btnRegistroPrestamo=buildButton("btnRegistrarPrestamo.png", 150, 150, true);
		btnRegistroLibro=buildButton("btnRegistroLibro.png", 150, 150, true);
		btnRequerimientoLibro=buildButton("btnNuevoRequerimiento.png", 150, 150, true);
		
		btnVerConsolidadoPrestamo=buildButton("btnVerConsolidadoPrestamos.png", 150, 150, true);
		btnVerDeudoresLibros=buildButton("btnVerDeudoresLibros.png", 150, 150, true);
		btnVerRanking=buildButton("btnVerRanking.png", 150, 150, true);
		btnVerInventario=buildButton("btnVerInventario.png", 150, 150, true);
		btnVerRequerimientoLibro=buildButton("btnVerRequerimiento.png", 150, 150, true);
		btnVerNuevoLibros=buildButton("btnVerRecienLlegados.png", 150, 150, true);
		
		cajabusqueda=new TextField();
		cajabusqueda.setPromptText("Escriba el título del libro o su autor...");
		cajabusqueda.setPrefWidth(530);
		cajabusqueda.setPrefHeight(30);
		cajabusqueda.setFocusTraversable(false);
		cajabusqueda.setStyle("-fx-border-radius: 3px;" +
				"-fx-border-color: rgb(37,143,250);" +
				"-fx-border-width: 2px;");
		
		btnBuscar=new Button("Buscar");
		btnBuscar.setPrefWidth(110);
		btnBuscar.setPrefHeight(30);
		btnBuscar.setFocusTraversable(false);
		btnBuscar.setFont(Font.font("Serif",FontWeight.LIGHT, 17));
		btnBuscar.setTextFill(Color.BLACK);
		
		tblresultadosBusqueda=new TableView<MLibro>();
		tblresultadosBusqueda.setMaxWidth(616);
		tblresultadosBusqueda.setMinHeight(450);
		tblresultadosBusqueda.setEditable(false);
		
		coltituloLibro=new TableColumn<MLibro, String>("Título");
		coltituloLibro.setMinWidth(340);
		colautorLibro=new TableColumn<MLibro, String>("Autor");
		colautorLibro.setMinWidth(185);
		colejmLibro=new TableColumn<MLibro, Integer>("Nº Ejemplares");
		colejmLibro.setMinWidth(90);
		
		tblresultadosBusqueda.getColumns().addAll(coltituloLibro,colautorLibro,colejmLibro);
		
		//Acciones de interfaz
		buildAccionesEscuchaEventos();
		
		busquedaClickView=buildCenter1();
		registroClickView=buildCenter2();
		reportesClickView=buildCenter3();
		
	}
	
	private void buildAccionesEscuchaEventos() {
		
		btnEntornoBusqueda.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				center.getChildren().setAll(busquedaClickView);
			}
		});
		
		btnRegistroAccionesLibros.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				center.getChildren().setAll(registroClickView);
			}
		});
		
		btnReportes.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				center.getChildren().setAll(reportesClickView);
			}
		});
		
	}
	
	private Node buildLeft() {
		VBox agrupador=new VBox(10);
		agrupador.setPadding(new Insets(20));
		
		agrupador.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/" +
						"logoColegioShadow.png").toString()))
				.fitWidth(175)
				.fitHeight(175)
				.build());
		VBox.setMargin(agrupador.getChildren().get(0),new Insets(0, 50, 10, 70));
		
		agrupador.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/separador.png").toString()))
				.fitWidth(300)
				.effect(GaussianBlurBuilder.create().radius(3).build())
				.opacity(.7f)
				.fitHeight(1)
				.build());
		
		agrupador.getChildren().add(btnEntornoBusqueda);
		
		
		agrupador.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/separador.png").toString()))
				.fitWidth(300)
				.effect(GaussianBlurBuilder.create().radius(3).build())
				.opacity(.7f)
				.fitHeight(1)
				.build());
		
		agrupador.getChildren().add(btnRegistroAccionesLibros);
		
		agrupador.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/separador.png").toString()))
				.fitWidth(300)
				.effect(GaussianBlurBuilder.create().radius(3).build())
				.fitHeight(1)
				.opacity(.7f)
				.build());
		
		agrupador.getChildren().add(btnReportes);
		
		HBox contenedor=new HBox();
		
		contenedor.getChildren().add(agrupador);
		
		contenedor.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/separador.png").toString()))
				.rotate(180)
				.fitWidth(2)
				.effect(GaussianBlurBuilder.create().radius(3).build())
				.opacity(.7f)
				.fitHeight(600)
				.build());
		
		HBox.setMargin(contenedor.getChildren().get(1),new Insets(10));
		return contenedor;
	}
	
	private Node buildCenter0(){
		StackPane contenedor=new StackPane();
		
		contenedor.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/" +
						"logoColegio.png").toString()))
				.fitWidth(300)
				.fitHeight(400)
				.opacity(.2f)
				.build());
		
		contenedor.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/" +
						"bienvenidostitle.png").toString()))
				.fitWidth(350)
				.fitHeight(80)
				.build());
		StackPane.setAlignment(contenedor.getChildren().get(1),Pos.TOP_CENTER);
		
		contenedor.getChildren().add(TextBuilder.create()
				.text("Visión:")
				.font(Font.font("Arial",FontWeight.BLACK, 24))
				.build());
		StackPane.setMargin(contenedor.getChildren().get(2), new Insets(-350, 0, 0, -500));
		
		contenedor.getChildren().add(StackPaneBuilder.create()
				.children(StackPaneBuilder.create()						
						.style("-fx-background-color: rgb(104,102,102);")
						.build())
				.style("-fx-border-color:rgb(104,102,102);" +
						"-fx-border-radius: 10px;" +
						"-fx-border-width: 3px;")
				.opacity(.1f)
				.build());
		StackPane.setMargin(contenedor.getChildren().get(3), new Insets(170, 60, 300, 100));
		
		contenedor.getChildren().add(TextBuilder.create()
				.text("Al término del 2015, la comunidad rosarina mantendrá\n" +
						"   su liderazgo como una institución innovadora y\n" +
						" líder en la formación integral y práctica de valores\n" +
						"                  ético-cristiano y cívico,\n" +
						" haciendo uso de la tecnología e instrumentos modernos,\n" +
						"con docentes actualizados y comprometidos, que vivencien\n" +
						"  el evangelio al estilo Mariano-Franciscano para\n" +
						"      construir una sociedad más justa y humana.")
				.fill(Color.GREEN)
				.font(Font.font(null, 14))
				.build());
		StackPane.setMargin(contenedor.getChildren().get(4), new Insets(170, 60, 300, 100));								
		
		contenedor.getChildren().add(TextBuilder.create()
				.text("Misión:")
				.font(Font.font("Arial",FontWeight.BLACK, 24))
				.build());
		
		StackPane.setMargin(contenedor.getChildren().get(5), new Insets(100, 0, 0, -500));
		contenedor.getChildren().add(StackPaneBuilder.create()
				.children(StackPaneBuilder.create()						
						.style("-fx-background-color: rgb(104,102,102);")
						.build())
				.style("-fx-border-color:rgb(104,102,102);" +
						"-fx-border-radius: 10px;" +
						"-fx-border-width: 3px;")
				.opacity(.1f)
				.build());
		StackPane.setMargin(contenedor.getChildren().get(6), new Insets(400, 60, 70, 100));
		
		contenedor.getChildren().add(TextBuilder.create()
				.text("  Somos una institución educativa Mariana Franciscana,\n" +
						"        que promueve en las estudiantes rosarinas\n" +
						"      el desarrollo de habilidades intelectuales, sociales,\n" +
						"volitivas, motrices y axiológicas, con actitud emprendedora\n" +
						"            y responsabilidad ecológica para que\n" +
						"          enfrenten y asuman proactivamente retos y\n" +
						"              desafíos del mundo actual.")
				.fill(Color.GREEN)
				.font(Font.font(null, 14))
				.build());
		StackPane.setMargin(contenedor.getChildren().get(7), new Insets(400, 60, 70, 100));
		
		return contenedor;
	}
	
	private Node buildCenter1() {
		
		StackPane contenedor=new StackPane();
		
		contenedor.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/" +
						"bienvenidostitle.png").toString()))
				.fitWidth(350)
				.fitHeight(80)
				.build());
		StackPane.setAlignment(contenedor.getChildren().get(0),Pos.TOP_CENTER);
				
		VBox agrupador=new VBox(10);
		
		
		HBox controles=new HBox(10);
		
		controles.getChildren().addAll(cajabusqueda,btnBuscar);
		
		agrupador.getChildren().addAll(controles,tblresultadosBusqueda);
	
		contenedor.getChildren().add(agrupador);
		StackPane.setMargin(contenedor.getChildren().get(1), new Insets(100, 20, 0, 0));
		return contenedor;
	}
	
	private Node buildCenter2(){
		StackPane contenedor=new StackPane();
		
		contenedor.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/" +
						"logoColegio.png").toString()))
				.fitWidth(300)
				.fitHeight(400)
				.opacity(.2f)
				.build());
		
		contenedor.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/" +
						"bienvenidostitle.png").toString()))
				.fitWidth(350)
				.fitHeight(80)
				.build());
		StackPane.setAlignment(contenedor.getChildren().get(1),Pos.TOP_CENTER);
		
		TilePane botones=TilePaneBuilder.create()
				.children(btnRegistroPrestamo,btnRegistroLibro,btnRequerimientoLibro)
				.hgap(50)
				.prefColumns(1)
				.prefRows(1)
				.build();
		
		contenedor.getChildren().add(botones);
		StackPane.setMargin(botones, new Insets(100,0,0,40));
		return contenedor;
	}
	
	private Node buildCenter3(){
		StackPane contenedor=new StackPane();
		
		contenedor.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/" +
						"logoColegio.png").toString()))
				.fitWidth(300)
				.fitHeight(400)
				.opacity(.2f)
				.build());
		
		contenedor.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/" +
						"bienvenidostitle.png").toString()))
				.fitWidth(350)
				.fitHeight(80)
				.build());
		StackPane.setAlignment(contenedor.getChildren().get(1),Pos.TOP_CENTER);
		
		TilePane botonespanel=new TilePane();
		botonespanel.setPrefColumns(3);
		botonespanel.setPrefRows(2);
		botonespanel.setHgap(20);
		botonespanel.setVgap(30);
		
		
		botonespanel.getChildren().add(btnVerConsolidadoPrestamo);
		botonespanel.getChildren().add(btnVerDeudoresLibros);
		botonespanel.getChildren().add(btnVerRanking);
		botonespanel.getChildren().add(btnVerInventario);
		botonespanel.getChildren().add(btnVerRequerimientoLibro);
		botonespanel.getChildren().add(btnVerNuevoLibros);
		
		contenedor.getChildren().add(botonespanel);
		
		StackPane.setMargin(contenedor.getChildren().get(2), new Insets(120,0,0,70));
		StackPane.setAlignment(contenedor.getChildren().get(2), Pos.BOTTOM_CENTER);
		
		return contenedor;
	}
	
	public void accionBusquedaCaja(EventHandler<KeyEvent> eventHandler){
		cajabusqueda.setOnKeyPressed(eventHandler);
	}
	
	public void accionBusquedaBoton(EventHandler<ActionEvent> eventHandler){
		btnBuscar.setOnAction(eventHandler);
	}
	
	public void goRegistroPrestamo(EventHandler<Event> eventHandler){
		btnRegistroPrestamo.setOnMouseClicked(eventHandler);
	}
	
	public void goNuevoRequerimiento(EventHandler<Event> eventHandler){
		btnRequerimientoLibro.setOnMouseClicked(eventHandler);
	}
	
	public void goRegistroLibro(EventHandler<Event> eventHandler){
		btnRegistroLibro.setOnMouseClicked(eventHandler);
	}
	
	public void reportPrestamo(EventHandler<Event> eventHandler){
		btnVerConsolidadoPrestamo.setOnMouseClicked(eventHandler);
	}
	
	public void reportDeudores(EventHandler<Event> eventHandler){
		btnVerDeudoresLibros.setOnMouseClicked(eventHandler);
	}

	public void reportRanking(EventHandler<Event> eventHandler){
		btnVerRanking.setOnMouseClicked(eventHandler);
	}
	
	public void reportInventario(EventHandler<Event> eventHandler){
		btnVerInventario.setOnMouseClicked(eventHandler);
	}
	
	public void reportRequerimientos(EventHandler<Event> eventHandler){
		btnVerRequerimientoLibro.setOnMouseClicked(eventHandler);
	}
	
	public void reportNuevosLibros(EventHandler<Event> eventHandler){
		btnVerNuevoLibros.setOnMouseClicked(eventHandler);
	}
	
}
