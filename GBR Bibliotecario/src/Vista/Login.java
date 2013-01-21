package Vista;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextBuilder;

public class Login extends ModeloPagina{
	public TextField txtUserName;
	public PasswordField txtUserPwd;
	public Button btnIngresar;
	public Button btnCancelar;
	public BorderPane principal;
	
	public Login() {
		
		construyeObjetos();
		principal=new BorderPane();		
		principal.setTop(buildSuperiorBibliotecario(false));
		principal.setBottom(buildInferiorBibliotecario());
		
		principal.setCenter(buildCenter());
		BorderPane.setMargin(principal.getCenter(), new Insets(180, 250, 160, 250));
		super.nodoVista=principal;
	}

	private void construyeObjetos() {
		
		//caja para ingresar nombre
		txtUserName=new TextField();
		txtUserName.setPromptText("Nombre");
		txtUserName.setPrefWidth(250);
		txtUserName.setPrefHeight(30);
		txtUserName.setStyle("-fx-border-radius: 3px;" +
				"-fx-border-color: rgb(37,143,250);" +
				"-fx-border-width: 2px");
		
		//caja para ingresar contraseña
		txtUserPwd=new PasswordField();
		txtUserPwd.setPromptText("*******");
		txtUserPwd.setPrefWidth(250);
		txtUserPwd.setPrefHeight(30);
		txtUserPwd.setStyle("-fx-border-radius: 3px;" +
				"-fx-border-color: rgb(37,143,250);" +
				"-fx-border-width: 2px");
		
		//construccion de botones
		btnIngresar=new Button("Ingresar");
		btnIngresar.setPrefWidth(110);
		btnIngresar.setPrefHeight(30);
		btnIngresar.setFont(Font.font("Serif",FontWeight.LIGHT, 17));
		btnIngresar.setTextFill(Color.BLACK);
		//btnIngresar.setStyle(botonLogin);
		
		btnCancelar=new Button("Cancelar");
		btnCancelar.setPrefWidth(115);
		btnCancelar.setPrefHeight(30);
		btnCancelar.setFont(Font.font("Serif",FontWeight.LIGHT, 17));
		btnCancelar.setTextFill(Color.BLACK);
		//btnCancelar.setStyle(botonLogin);
		btnCancelar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});
	}

	private Node buildCenter() {
				
		StackPane cuadro=new StackPane();
		cuadro.setStyle("-fx-background-color: white;" +
				"-fx-border-radius: 10px;" +
				"-fx-border-color: black;" +
				"-fx-border-width: 3px");
		
		StackPane stackPane= new StackPane();
		stackPane.setMaxHeight(40);
		stackPane.setStyle("-fx-background-color: black;");
		stackPane.getChildren().add(TextBuilder.create()
				.text("BIENVENIDO")
				.font(Font.font("Serif", 23))
				.fill(Color.WHITE)
				.build());		
		cuadro.getChildren().add(stackPane);
		StackPane.setAlignment(stackPane, Pos.TOP_CENTER);
		
		cuadro.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/logoBuscador.png").toString()))
				.fitWidth(220)
				.fitHeight(55)
				.build());		
		StackPane.setMargin(cuadro.getChildren().get(1),new Insets(20, 50, 200, 50));
		
		
		cuadro.getChildren().add(HBoxBuilder.create()
				.spacing(45)
				.children(TextBuilder.create()
						.text("Usuario: ")
						.font(Font.font("Leelawadee", FontWeight.MEDIUM, 20))						
						.build()
						,
						txtUserName)
				.build());
		
		StackPane.setMargin(cuadro.getChildren().get(2), new Insets(120, 10, 90, 56));
		
		cuadro.getChildren().add(HBoxBuilder.create()
				.spacing(14)
				.children(TextBuilder.create()
						.text("Contraseña: ")
						.font(Font.font("Leelawadee", FontWeight.MEDIUM, 20))
						.build()
						,
						txtUserPwd)
				.build());
		
		StackPane.setMargin(cuadro.getChildren().get(3), new Insets(180, 10, 0, 56));
		
		
		
		cuadro.getChildren().add(HBoxBuilder.create()
				.spacing(20)
				.children(btnIngresar,btnCancelar)
				.build());
		
		StackPane.setMargin(cuadro.getChildren().get(4), new Insets(250, 0, 0, 200));
		
		
		return cuadro;
	}
	
	@Override
	public void setnextManejador(EventHandler<Event> eventHandler) {
		// TODO Auto-generated method stub
		super.setnextManejador(eventHandler);
		btnIngresar.setOnMouseClicked(eventHandler);
	}
	
	public void onkeyEnter(EventHandler<KeyEvent> eventHandler){
		principal.setOnKeyPressed(eventHandler);
	}
}
