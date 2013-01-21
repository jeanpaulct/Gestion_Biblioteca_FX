package Vista;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BloomBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;

public class ModeloPagina extends PaginaAbstracta{
	
	protected Node nodoVista;
	public ImageView imgpieImagen;
	public Node imgIndex;
	protected Text txtFiltroTitulo;
	protected Text txtFiltroAutor;
	protected Text txtFiltroEditorial;
	protected Text txtRanking;
	protected Text txtRecienLlegados;
	
	public ModeloPagina() {
		buildObjetos();
	}
	
	private void buildObjetos(){
		
		//imagen goindex
		imgIndex=buildButton("index.png", 60, 60, true);
		
		//filtro autor
		txtFiltroAutor=TextBuilder.create()
				.text("Por Autor")
				.cursor(Cursor.HAND)
				.fill(Color.WHITE)								
				.font(Font.font("MS UI Gothic", FontWeight.BOLD, 28))
				.build();
		
		//filtro titulo
		txtFiltroTitulo=TextBuilder.create()
				.text("Por Título")
				.cursor(Cursor.HAND)
				.fill(Color.WHITE)					
				.font(Font.font("MS UI Gothic", FontWeight.BOLD, 28))
				.build();
		
		//filtro materia
		txtFiltroEditorial=TextBuilder.create()
				.text("Por Editorial")
				.cursor(Cursor.HAND)
				.fill(Color.WHITE)					
				.font(Font.font("MS UI Gothic", FontWeight.BOLD, 28))
				.build();
		
		//ver Ranking
		txtRanking=TextBuilder.create()
				.text("Ranking")
				.cursor(Cursor.HAND)
				.fill(Color.WHITE)					
				.font(Font.font("MS UI Gothic", FontWeight.BOLD, 28))
				.build();
		
		//pie de p�gina
		imgpieImagen=ImageViewBuilder.create()
				.image(new Image(getClass().getResourceAsStream("Imagenes/imagenPie.png")))
				.preserveRatio(true)
				.fitHeight(40)
				.build();
		//label de recien LLegados
		txtRecienLlegados=TextBuilder.create()
				.text("Recien Llegados")
				.font(Font.font("MS UI Gothic", FontWeight.BOLD, 20))
				.fill(Color.WHITE)
				.cursor(Cursor.HAND)
				.effect(BloomBuilder.create().threshold(0f).build())
				.build();
	}
	
	protected Node buildtoolsFiltroAlumno(boolean index){
		
		HBox hBox=new HBox(10);
		hBox.setMaxHeight(maxHeightCabezera);
		hBox.setMaxWidth(DefaultAncho);
		hBox.setMinWidth(DefaultAncho);
		hBox.setPadding(new Insets(10,10,5,10));
		hBox.setStyle("-fx-background-color:black");
		
		hBox.getChildren().add(txtFiltroTitulo);
		hBox.getChildren().add(TextBuilder.create()
				.text("|")
				.fill(Color.WHITE)
				.font(Font.font("MS UI Gothic", FontWeight.BOLD, 28))					
				.build());
		hBox.getChildren().add(txtFiltroAutor);
		hBox.getChildren().add(TextBuilder.create()
				.text("|")
				.fill(Color.WHITE)
				.font(Font.font("MS UI Gothic", FontWeight.BOLD, 28))					
				.build());
		hBox.getChildren().add(txtFiltroEditorial);
		hBox.getChildren().add(TextBuilder.create()
				.text("|")
				.fill(Color.WHITE)
				.font(Font.font("MS UI Gothic", FontWeight.BOLD, 28))					
				.build());
		hBox.getChildren().add(txtRanking);
		hBox.getChildren().add(TextBuilder.create()
				.text("|")
				.fill(Color.WHITE)
				.font(Font.font("MS UI Gothic", FontWeight.BOLD, 28))					
				.build());
		//Agregando el Efecto Bloom
		Bloom bloom=BloomBuilder.create().threshold(0f).build();
		hBox.setEffect(bloom);
		
		if(index){
			StackPane agrupador=new StackPane();
			agrupador.getChildren().add(hBox);
			agrupador.getChildren().add(imgIndex);
			StackPane.setAlignment(imgIndex, Pos.BOTTOM_RIGHT);
			StackPane.setMargin(imgIndex, new Insets(0, -6, -17, 0));
			return agrupador;
		}
		else{
			return hBox;
		}
		
	}
	
	protected HBox buildPiePageAlumno(){
		
		HBox hBox=new HBox();
		hBox.setMaxHeight(maxHeightPie);
		hBox.setMaxWidth(DefaultAncho);
		hBox.setStyle("-fx-background-color: Black");
		
		HBox agrupacion=new HBox();
		
		agrupacion.getChildren().addAll(
				TextBuilder.create()
				.text("|   ")
				.font(Font.font("MS UI Gothic", FontWeight.BOLD, FontPosture.REGULAR, 20))
				.fill(Color.WHITE)				
				.build(),
				
				txtRecienLlegados,
				
				TextBuilder.create()
				.text("   |")
				.font(Font.font("MS UI Gothic", FontWeight.BOLD, FontPosture.REGULAR, 20))
				.fill(Color.WHITE)
				.build());
		
		hBox.getChildren().addAll(imgpieImagen,agrupacion);
		HBox.setMargin(imgpieImagen, new Insets(0, 0, 0, 10));
		HBox.setMargin(agrupacion, new Insets(10, 0, 10, 770));
		return hBox;
	}
	
	public Node getNodoVista(){
		return nodoVista;
	}
	
	public void goLibrosRecienLLegados(EventHandler<Event> event){
		txtRecienLlegados.setOnMouseClicked(event);
	}
	
	public void goRanking(EventHandler<Event> event){
		txtRanking.setOnMouseClicked(event);
	}
	
	public void goIndex(EventHandler<Event> event){
		imgIndex.setOnMouseClicked(event);
	}
	
	protected Node buildButton(String url, double ancho, double largo, boolean focus) {
		
		String cadena="Imagenes/Botones/"+url;
		final StackPane boton=new StackPane(); 
		boton.setMaxWidth(ancho);
		boton.setMaxHeight(largo);
		
		StackPane stackPane=StackPaneBuilder.create()
				.children(ImageViewBuilder.create()
						.cursor(Cursor.HAND)
						.image(new Image(getClass().getResource(cadena).toString()))
						.fitWidth(ancho)
						.fitHeight(largo)
						.build())
				.focusTraversable(focus)
				.onMousePressed(new EventHandler<Event>() {

					public void handle(Event event) {
						boton.setOpacity(.6f);
					}
				})
				.onMouseReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						boton.setOpacity(1);
					}
				})
				.build();
		boton.getChildren().add(stackPane);
		return boton;
		
	}

	public void setFiltroAutor(EventHandler<Event> event){
		txtFiltroAutor.setOnMouseClicked(event);
	}
	
	public void setFiltroTitulo(EventHandler<Event> event){
		txtFiltroTitulo.setOnMouseClicked(event);
	}
	
	public void setFiltroEditorial(EventHandler<Event> event){
		txtFiltroEditorial.setOnMouseClicked(event);
	}
	
}
