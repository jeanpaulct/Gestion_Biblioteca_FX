package Vista;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.effect.GaussianBlurBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;

public class LibrosRecienLlegados extends ModeloPagina {
	
	private BorderPane contenedorPrimario;
	private ImageView imgNuevosTitulo,imglibroView,imgpadre;
	public TextField cajaTotalResultados;
	public TextField cajaIndiceActual;
	public Node btnNext;
	public Node btnBack;
	public Text txtCodigoLibro;
	public Text txtTituloLibro;
	public Text txtAutorLibro;
	public Text txtEditorialLibro;
	public Text txtAnioLibro;
	
	
	public LibrosRecienLlegados() {
		
		creaObjetos();
		
		contenedorPrimario=new BorderPane();
		
		contenedorPrimario.setLeft(buildLeft());
		contenedorPrimario.setCenter(builCenter());
		contenedorPrimario.setTop(buildtoolsFiltroAlumno(true));
		contenedorPrimario.setBottom(buildPiePageAlumno());
		
		super.nodoVista=contenedorPrimario;
	}
	
	private void creaObjetos() {
		//titulo de p�gina
		imgNuevosTitulo=ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/recienLlegadosTitulo.png").toString()))
				.fitHeight(70)
				.fitWidth(450)
				.build();
		
		//Imagen Libro
		imglibroView=ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/libroImagen.png").toString()))
				.fitWidth(694)
				.fitHeight(520)
				.build();
		
		
		
		//Label para mostrar datos de cada libro de busqueda
		txtCodigoLibro=TextBuilder.create()				
				//.text("Código")
				.font(Font.font("Segoe Print", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 26))
				.fill(Color.GREEN)
				.build();
		
		txtTituloLibro=TextBuilder.create()				
				//.text("Título")
				.font(Font.font("Segoe Print", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 26))
				.fill(Color.GREEN)
				.build();
		
		txtAutorLibro=TextBuilder.create()				
				//.text("Autor")
				.font(Font.font("Segoe Print", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 26))
				.fill(Color.GREEN)
				.build();
		
		txtEditorialLibro=TextBuilder.create()
				//.text("Editorial")
				.font(Font.font("Segoe Print", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 26))
				.fill(Color.GREEN)
				.build();
		
		txtAnioLibro=TextBuilder.create()
				//.text("Año")
				.font(Font.font("Segoe Print", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 26))
				.fill(Color.GREEN)
				.build();
		
		//Total de Resultados
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
		
		//imagen de padre
		imgpadre=ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/sanFrancisco.png").toString()))
				.fitWidth(160)
				.fitHeight(200)
				.build();
		
		//Boton anterior
		btnBack=buildButton("btnAtras.png", 50, 50, true);
		
		//Numero de indice actual
		cajaIndiceActual=TextFieldBuilder.create()
				.editable(false)
				.focusTraversable(false)
				.cursor(Cursor.NONE)
				.maxWidth(80)
				.maxHeight(70)
				.minWidth(80)
				.minHeight(70)
				.style(estiloCajaResultados)
				.build();
		
		//Boton siguiente
		btnNext=buildButton("btnSiguiente.png", 50, 50, true);
		
	}

	private Node builCenter() {
		VBox vBox=VBoxBuilder.create()
				.children(imgNuevosTitulo
						,StackPaneBuilder.create()
						.children(imglibroView,
								VBoxBuilder.create()
								.children(txtTituloLibro,txtAutorLibro,txtEditorialLibro
										,txtAnioLibro)
										.padding(new Insets(8, 0, 0, 200))
										.build())
								
								.build()
								)
				.padding(new Insets(10, 0, 0, 2))
				.spacing(10)
				.build();
		VBox.setMargin(imgNuevosTitulo, new Insets(0, 0, 0, 120));
		VBox.setMargin(txtTituloLibro, new Insets(125, 0, 0, 0));
		VBox.setMargin(txtAutorLibro, new Insets(60, 0, 0, 0));
		VBox.setMargin(txtEditorialLibro, new Insets(60, 0, 0, 0));
		VBox.setMargin(txtAnioLibro, new Insets(24, 0, 0, 140));
		return vBox;
	}

	private Node buildLeft() {
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
								.build())
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
				.padding(new Insets(80, 0, 0, 0))
				.spacing(10)
				.build();
		
		HBox.setMargin(btnBack, new Insets(10, 0, 10, 0));
		HBox.setMargin(btnNext, new Insets(10, 0, 10, 0));
		VBox.setMargin(cajaTotalResultados, new Insets(0, 0, 0, 60));
		VBox.setMargin(imgpadre, new Insets(0,0,0,60));
		
		return contenedor;
	}
	
	public void navegacionLibros(EventHandler<KeyEvent> eventHandler){
		contenedorPrimario.setOnKeyPressed(eventHandler);
	}
	
	public void nextLibro(EventHandler<Event> eventHandler){
		btnNext.setOnMouseClicked(eventHandler);
	}
	
	public void backLibro(EventHandler<Event> eventHandler){
		btnBack.setOnMouseClicked(eventHandler);
	}
	
}
