package Vista;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.effect.BlendBuilder;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.effect.GaussianBlurBuilder;
import javafx.scene.effect.ReflectionBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;

public class ResultadosBusqueda extends ModeloPagina{
	
	private BorderPane layoutPadre;
	private ImageView imglibroView,imgpadre,logoBuscadorResultados;
	public TextField cajaTextoBusqueda;
	public TextField cajaTotalResultados;
	public TextField cajaIndiceActual;
	public Node btnBuscar;
	public Node btnNext;
	public Node btnBack;
	public Text txtTipoFiltro;
	public Text txtTituloLibro;
	public Text txtAutorLibro;
	public Text txtEditorialLibro;
	public Text txtAnioLibro;
	
	
	public ResultadosBusqueda() {
		
		creaObjetos();
		
		layoutPadre=new BorderPane();
		layoutPadre.setFocusTraversable(true);
		
		layoutPadre.setCenter(construyeCenter());
		layoutPadre.setTop(buildtoolsFiltroAlumno(true));
		layoutPadre.setBottom(buildPiePageAlumno());
		
		super.nodoVista=layoutPadre;
	}
	
	private void creaObjetos() {
		
		//Libro Imagen
		imglibroView=ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/libroImagen.png").toString()))
				.fitHeight(520)
				.fitWidth(685)
				.build();
		
		//filtro
		txtTipoFiltro=TextBuilder.create()
				.text("")
				.font(Font.font("Dialog", FontWeight.BOLD, FontPosture.ITALIC, 20))
				.fill(Color.BLACK)
				.effect(BlendBuilder.create()
						.mode(BlendMode.MULTIPLY)
						.bottomInput(ReflectionBuilder.create().fraction(.8f).build())
						.topInput(DropShadowBuilder.create().color(Color.BLACK).radius(20).spread(.2f).build())
						.build())
				.build();
		
		//Labels para mostrar Resultados de cada Libro
//		txtCodigoLibro=TextBuilder.create()				
//				.text("Código")
//				.font(Font.font(null, FontWeight.SEMI_BOLD, FontPosture.ITALIC, 20))
//				.fill(Color.GREEN)
//				.build();
		
		txtTituloLibro=TextBuilder.create()				
				.text("")
				.font(Font.font(null, FontWeight.SEMI_BOLD, FontPosture.ITALIC, 18))
				.fill(Color.GREEN)
				.build();
		
		txtAutorLibro=TextBuilder.create()				
				.text("")
				.font(Font.font(null, FontWeight.SEMI_BOLD, FontPosture.ITALIC, 18))
				.fill(Color.GREEN)
				.build();
		
		txtEditorialLibro=TextBuilder.create()
				.text("")
				.font(Font.font(null, FontWeight.SEMI_BOLD, FontPosture.ITALIC, 18))
				.fill(Color.GREEN)
				.build();
		
		txtAnioLibro=TextBuilder.create()
				.text("")
				.font(Font.font(null, FontWeight.SEMI_BOLD, FontPosture.ITALIC, 18))
				.fill(Color.GREEN)
				.build();
		
		//Caja resultados numero de Resultados
		cajaTotalResultados=TextFieldBuilder.create()
				.cursor(Cursor.NONE)
				.focusTraversable(false)
				.maxWidth(150)
				.maxHeight(50)
				.minWidth(150)
				.minHeight(50)
				.editable(false)
				.style(estiloCajaResultados)
				.build();
		
		//Boton Atras
		btnBack=buildButton("btnAtras.png", 50, 50, true);				
		
		//Imagen padre
		imgpadre=ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/sanFrancisco.png").toString()))
				.fitWidth(180)
				.fitHeight(200)
				.build();
		
		//Caja Indice Actual
		cajaIndiceActual=TextFieldBuilder.create()
				.editable(false)
				.focusTraversable(true)
				.cursor(Cursor.NONE)
				.maxWidth(80)
				.maxHeight(70)
				.minWidth(80)
				.minHeight(70)
				.style(estiloCajaResultados)
				.build();
		
		//Boton Siguiente
		btnNext=buildButton("btnSiguiente.png", 50, 50, true);
		
		//imagen logoBuscador
		logoBuscadorResultados=ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/logoResultadoBusqueda.png").toString()))
				.onMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						txtTipoFiltro.setText("");
					}
				})
				.cursor(Cursor.OPEN_HAND)
				.fitWidth(180)
				.fitHeight(70)
				.build();
		
		
		//Caja para ingreso de Busqueda
		cajaTextoBusqueda=TextFieldBuilder.create()
				.promptText("Ingrese su Busqueda del libro")
				.prefWidth(550)
				.prefHeight(50)
				.style(estiloCaja)
				.focusTraversable(true)
				.build();
		
		//Boton Buscar
		btnBuscar=buildButton("btnBuscar.png", 140, 50, true);
		
		//eventos
		
		btnBuscar.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				btnBuscar.requestFocus();
				layoutPadre.requestFocus();
			}
		});
				
	}

	private Node construyeCenter() {
		
		BorderPane borderPane=new BorderPane();
		borderPane.setMaxHeight(DefaultLargo-maxHeightCabezera-maxHeightPie);
		
		borderPane.setTop(getTop());		
		borderPane.setLeft(getLeft());
		borderPane.setCenter(getCenter());

		
		return borderPane;
	}

	private Node getCenter() {
		StackPane agrupacion=new StackPane();
		
		agrupacion.getChildren().add(imglibroView);
		StackPane.setAlignment(imglibroView, Pos.CENTER);
		
		agrupacion.getChildren().add(HBoxBuilder.create()
				.children(VBoxBuilder.create()
						.children(txtTituloLibro,txtAutorLibro,txtEditorialLibro,txtAnioLibro)
						.build())
				.build());
		//HBox.setMargin(txtCodigoLibro, new Insets(60, 210, 0, 30));
		VBox.setMargin(txtTituloLibro, new Insets(148, 0, 0, 200));
		VBox.setMargin(txtAutorLibro, new Insets(85, 0, 0, 200));
		VBox.setMargin(txtEditorialLibro, new Insets(80, 0, 0, 200));
		VBox.setMargin(txtAnioLibro, new Insets(46, 0, 0, 400));
		
		return agrupacion;
	}

	private Node getLeft() {
		
		HBox contenedor=HBoxBuilder.create()
				.children(VBoxBuilder.create()
						.children(TextBuilder.create()
								.font(Font.font(null, FontWeight.MEDIUM, 16))
								.text("    Total de Resultados Encontrados")
								.fill(Color.BLACK)
								.build()
								,cajaTotalResultados,imgpadre,
								HBoxBuilder.create()
								.children(btnBack,cajaIndiceActual,btnNext)
								.padding(new Insets(0, 20, 0, 20))
								.spacing(25)
								.build()
								,txtTipoFiltro)
						.padding(new Insets(15, 0, 0, 20))
						.spacing(15)
						.build(),
						ImageViewBuilder.create()
						.image(new Image(getClass().getResource("Imagenes/separador.png").toString()))
						.rotate(180)
						.fitWidth(1)
						.effect(GaussianBlurBuilder.create().radius(3).build())
						.fitHeight(500)
						.build())
				.padding(new Insets(5, 5, 0, 0))
				.spacing(10)
				.build();
		
		HBox.setMargin(btnBack, new Insets(10, 0, 10, 0));
		HBox.setMargin(btnNext, new Insets(10, 0, 10, 0));
		VBox.setMargin(cajaTotalResultados, new Insets(0, 0, 0, 60));
		VBox.setMargin(imgpadre, new Insets(0, 0, 0, 60));
		
		return contenedor;
		
	}

	private Node getTop() {
		HBox hBox=new HBox();
		hBox.setSpacing(10);
		hBox.setPadding(new Insets(5, 0, 0, 10));
		
		hBox.getChildren().addAll(logoBuscadorResultados,ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/separador.png").toString()))
				.rotate(180)
				.fitWidth(1)
				.effect(GaussianBlurBuilder.create().radius(3).build())
				.fitHeight(60)
				.build(),
				cajaTextoBusqueda,btnBuscar);
		HBox.setMargin(hBox.getChildren().get(1), new Insets(5, 0, 5, 0));
		HBox.setMargin(cajaTextoBusqueda, new Insets(10, 0, 10, 0));
		HBox.setMargin(btnBuscar, new Insets(10, 0, 10, 0));
		
		VBox agrupacion =new VBox();
		agrupacion.setSpacing(5);
		agrupacion.setPadding(new Insets(0, 0, 4, 0));
		
		agrupacion.getChildren().add(hBox);
		agrupacion.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/separador.png").toString()))				
				.fitWidth(970)
				.effect(GaussianBlurBuilder.create().radius(3).build())
				.fitHeight(1)
				.build());
		VBox.setMargin(agrupacion.getChildren().get(1), new Insets(5, 0, 0, 10));
		
		return agrupacion;
	}
	
	public void navegacionBusqueda(EventHandler<KeyEvent> eventHandler){
		layoutPadre.setOnKeyPressed(eventHandler);
	}
	
	public void setNext(EventHandler<Event> event){
		btnNext.setOnMouseClicked(event);
	}
	
	public void setBack(EventHandler<Event> event){
		btnBack.setOnMouseClicked(event);
	}
	
	public void setBusqueda(EventHandler<Event> event){
		btnBuscar.setOnMouseClicked(event);
	}
	
	public void setNuevaBusqueda(EventHandler<KeyEvent> eventHandler ){
		cajaTextoBusqueda.setOnKeyPressed(eventHandler);
	}
	
}