package Vista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;

public class JMensaje{
	private String styleButton="-fx-background-color: #a6b5c9," +
	"        linear-gradient(#303842 0%, #3e5577 20%, #375074 100%)," +
	"        linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%);" +
	"    -fx-background-insets: 0 0 -1 0,0,1;" +
	"    -fx-background-radius: 5,5,4;" +
	"    -fx-padding: 7 30 7 30;" +
	"    -fx-font-family: \"Jokerman\";" +
	"    -fx-font-size: 18px;" +
	"    -fx-text-fill: black;";
	/**[Codigo de Stylos de Button]
//	private String styleButton="-fx-background-color:" +
//	"        #090a0c," +
//	"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%)," +
//	"        linear-gradient(#20262b, #191d22)," +
//	"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
//	"    -fx-background-radius: 5,4,3,5;" +
//	"    -fx-background-insets: 0,1,2,0;" +
//	"    -fx-text-fill: white;" +
//	"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );" +
//	"    -fx-font-family: \"Arial\";" +
//	"    -fx-text-fill: linear-gradient(white, #d0d0d0);" +
//	"    -fx-font-size: 12px;" +
//	"    -fx-padding: 10 20 10 20;";
//	private String styleButton="-fx-background-color:#ecebe9, rgba(0,0,0,0.05),linear-gradient(#dcca8a, #c7a740)," +
//			"linear-gradient(#f9f2d6 0%, #f4e5bc 20%, #e6c75d 80%, #e2c045 100%)," +
//			"linear-gradient(#f6ebbe, #e6c34d);" +
//			"-fx-background-insets: 0,9 9 8 9,9,10,11;" +
//			"-fx-background-radius: 50;" +
//			"-fx-padding: 15 30 15 30;" +
//			"-fx-font-family: \"Helvetica\";" +
//			"-fx-font-size: 18px;" +
//			"-fx-text-fill: #311c09;" +
//			"-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);";
 **/
	private String smensaje="Habiendo verificado que los datos ingresados sean los correctos.\n" +
			"¿Desea guardar el préstamo de este libro?";
	private Scene scena;
	private Stage stage;
	private Text mensaje=TextBuilder.create()
			.text(smensaje)
			.fill(Color.BLACK)
			.font(Font.font("Matura MT Script Capitals", FontWeight.SEMI_BOLD, 19))
			.build();
	public Button btnGuardar;
	public Button btnCancelar;
	public JMensaje(Stage windows){
		this.stage=windows;
		StackPane cuadro=new StackPane();
		cuadro.setStyle("-fx-background-color: white;" +
				"-fx-border-radius: 10px;" +
				"-fx-border-color: black;" +
				"-fx-border-width: 3px");
		
		StackPane stackPane= new StackPane();
		stackPane.setMaxHeight(40);
		stackPane.setStyle("-fx-background-color: black;");	
		
		cuadro.getChildren().add(stackPane);
		StackPane.setAlignment(stackPane, Pos.TOP_CENTER);
		
		cuadro.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/logoBuscador.png").toString()))
				.fitWidth(320)
				.fitHeight(80)
				.build());
		
		StackPane.setMargin(cuadro.getChildren().get(1), new Insets(0, 0, 145, 30));
		
		btnGuardar=ButtonBuilder.create()
				.text("Guardar")
				.style(styleButton)
				.onMousePressed(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						btnGuardar.setOpacity(.7f);
					}
					
				})
				.onMouseReleased(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						btnGuardar.setOpacity(1);
					}
					
				})
				.build();
		
		btnCancelar=ButtonBuilder.create()
				.text("Cancelar")
				.onMousePressed(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						btnCancelar.setOpacity(.7f);
					}
					
				})
				.onMouseReleased(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						btnCancelar.setOpacity(1);
					}
					
				})
				.onAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						stage.close();
					}
				})
				.style(styleButton)
				.build();
		
		cuadro.getChildren().add(mensaje);		
		StackPane.setMargin(cuadro.getChildren().get(2), new Insets(40, 0, 0, 20));
		
		cuadro.getChildren().add(HBoxBuilder.create()
				.children(btnGuardar,btnCancelar)
				.spacing(35)
				.build());
		StackPane.setMargin(cuadro.getChildren().get(3), new Insets(250, 0, 0, 220));
		
		cuadro.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.ESCAPE){
					stage.close();
				}
			}
		});
		scena=new Scene(cuadro,600, 320);
	}
	
	public void setMensaje(String msj){
		mensaje.setText(msj);
	}
	
	public Scene getView(){
		return scena;
	}
	
}
