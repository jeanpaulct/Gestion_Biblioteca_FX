package Vista;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.effect.BlendBuilder;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.effect.ReflectionBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Popup;

public class Buscador extends ModeloPagina {
	
	private BorderPane layoutPrincipal;
	private ImageView fondoLogoColegio,imgLogoBuscador;
	public TextField cajaBusqueda;
	public Node btnBuscar;	
	public Text txtTipoFiltro;
	public Popup popUpBusqueda;
	public ListView<String> listaAutocompletado;
	
	public Buscador() {
		
		creaObjetos();
		
		layoutPrincipal=new BorderPane();
		
		layoutPrincipal.setCenter(buildBusqueda());
		layoutPrincipal.setBottom(buildPiePageAlumno());		
		layoutPrincipal.setTop(buildtoolsFiltroAlumno(false));
		////IMPORTANTE PARA EXPORTAR Y LLAMAR
		super.nodoVista=layoutPrincipal;
	}
	
	@SuppressWarnings("deprecation")
	private void creaObjetos() {
		//fondoLogoColegio
		fondoLogoColegio=ImageViewBuilder.create()				
				.fitWidth(300)
				.fitHeight(380)
				.image(new Image(getClass().getResource("Imagenes/logoColegio.png").toString()))
				.opacity(.2f)
				.build();
		
		//LogoBuscador
		imgLogoBuscador=ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/logoBuscador.png").toString()))
				.smooth(true)
				.cache(true)				
				.fitWidth(600)
				.fitHeight(150)
				.build();
		
		//Caja de Busqueda
		cajaBusqueda=TextFieldBuilder.create()
				.id("Buscador")
				.prefWidth(600)
				.promptText("Escriba su busqueda")
				.prefHeight(45)
				.style(estiloCaja)
				.build();
		
		//Label de Tipo de FiltroPresentado
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
		
		//Boton buscar
		btnBuscar=buildButton("btnBuscar.png", 110, 45, true);
		
		//PopUp de Autocompletado
		popUpBusqueda=new Popup();
		popUpBusqueda.setX(352);
		popUpBusqueda.setY(438);
		popUpBusqueda.setAutoHide(true);
		popUpBusqueda.setFocused(false);
		
		listaAutocompletado=new ListView<String>();
		listaAutocompletado.setPrefWidth(600);
		listaAutocompletado.setMaxHeight(147);
		
		popUpBusqueda.getContent().addAll(listaAutocompletado);
		
	}

	private Node buildBusqueda(){
		
		StackPane agrupador=new StackPane();
		
		agrupador.setMaxWidth(DefaultAncho);
		agrupador.setMaxHeight(DefaultLargo-maxHeightPie-maxHeightCabezera);
		
		agrupador.getChildren().add(fondoLogoColegio);
		StackPane.setAlignment(fondoLogoColegio, Pos.TOP_CENTER);
		StackPane.setMargin(fondoLogoColegio, new Insets(50, 0, 0, 0));
		
		HBox hBox1=new HBox(10);
		hBox1.getChildren().addAll(imgLogoBuscador,txtTipoFiltro);
		HBox.setMargin(txtTipoFiltro, new Insets(100, 0, 0, 0));
		
		HBox hBox2=new HBox(4);
		hBox2.getChildren().addAll(cajaBusqueda,btnBuscar);
		
		agrupador.getChildren().addAll(hBox1,hBox2);
		
		StackPane.setMargin(hBox1, new Insets(170, 0, 0, 170));
		StackPane.setMargin(hBox2, new Insets(320, 0, 0, 170));
		return agrupador;
	}
	
	@Override
	public void setnextManejador(EventHandler<Event> eventHandler) {
		btnBuscar.setOnMouseClicked(eventHandler);
		
	}
	
	public void onKeyCajaTexto(EventHandler<KeyEvent> eventHandler){
		cajaBusqueda.setOnKeyPressed(eventHandler);
	}

}

