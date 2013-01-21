package Vista;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;

public class ModeloPagina extends PaginaAbstracta{

	protected Node nodoVista;
	public ImageView imgpieImagen;
	public ImageView imgIndex;
	
	public ModeloPagina() {
		buildObjetos();
	}	
	
	private void buildObjetos(){
		
		//imagen goindex
		imgIndex=ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/index.png").toString()))
				.fitWidth(55)
				.fitHeight(55)
				.cursor(Cursor.HAND)
				.onMousePressed(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						imgIndex.setOpacity(.6f);
					}
				})
				.onMouseReleased(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						imgIndex.setOpacity(1);
					}
				})
				.build();
		
		//imagen pie
		imgpieImagen=ImageViewBuilder.create()
				.image(new Image(getClass().getResourceAsStream("Imagenes/imagenPie.png")))
				.preserveRatio(true)
				.fitHeight(40)
				.build();
	}
	
	protected Node buildSuperiorBibliotecario(boolean indexVisible){
		HBox hBox=new HBox();
		
		hBox.setMaxHeight(35);
		hBox.setMinHeight(35);
		hBox.setStyle("-fx-background-color: Black");
		
		if(indexVisible){
			StackPane agrupador=new StackPane();
			agrupador.getChildren().add(hBox);
			agrupador.getChildren().add(imgIndex);
			
			StackPane.setAlignment(hBox, Pos.CENTER);
			StackPane.setAlignment(imgIndex, Pos.BOTTOM_RIGHT);
			StackPane.setMargin(imgIndex, new Insets(0, -5, -20, 0));
			
			return agrupador;
		}else{
			return hBox;
		}
	}
	
	protected Node buildInferiorBibliotecario(){
		HBox hBox=new HBox();
		
		hBox.setMinHeight(40);
		hBox.setMaxHeight(40);
		hBox.setStyle("-fx-background-color: Black");
				
		HBox.setMargin(imgpieImagen, new Insets(0, 0, 0, 10));
		
		hBox.getChildren().addAll(imgpieImagen);		
		return hBox;
	}
	
	public Node getNodoVista(){
		return nodoVista;
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
	
}
