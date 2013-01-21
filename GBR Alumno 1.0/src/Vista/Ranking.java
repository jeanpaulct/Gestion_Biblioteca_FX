package Vista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;

public class Ranking extends ModeloPagina {

	private BorderPane layoutPrincipal;
	public ObservableList<Text> rankingAlumnos;
	public ObservableList<Text> rankingDocentes;
	public Ranking() {
		
		creaObjetos();
		
		layoutPrincipal=new BorderPane();
		
		layoutPrincipal.setCenter(buildCenter());
		
		layoutPrincipal.setTop(buildtoolsFiltroAlumno(true));
		layoutPrincipal.setBottom(buildPiePageAlumno());
		
		super.nodoVista=layoutPrincipal;
	}
	
	private void creaObjetos() {
		
		rankingAlumnos=FXCollections.observableArrayList();
		rankingAlumnos.addAll(TextBuilder.create().fill(Color.GREEN).font(Font.font("Arial", FontWeight.BOLD, 20)).text("Azul").build(),
				TextBuilder.create().fill(Color.GREEN).font(Font.font("Arial", FontWeight.BOLD, 20)).build(),
				TextBuilder.create().fill(Color.GREEN).font(Font.font("Arial", FontWeight.BOLD, 20)).build(),
				TextBuilder.create().fill(Color.GREEN).font(Font.font("Arial", FontWeight.BOLD, 20)).build(),
				TextBuilder.create().fill(Color.GREEN).font(Font.font("Arial", FontWeight.BOLD, 20)).build());
		
		rankingDocentes=FXCollections.observableArrayList();
		rankingDocentes.addAll(TextBuilder.create().fill(Color.GREEN).font(Font.font("Arial", FontWeight.BOLD, 20)).build(),
				TextBuilder.create().fill(Color.GREEN).font(Font.font("Arial", FontWeight.BOLD, 20)).build(),
				TextBuilder.create().fill(Color.GREEN).font(Font.font("Arial", FontWeight.BOLD, 20)).build(),
				TextBuilder.create().fill(Color.GREEN).font(Font.font("Arial", FontWeight.BOLD, 20)).build(),
				TextBuilder.create().fill(Color.GREEN).font(Font.font("Arial", FontWeight.BOLD, 20)).build());
	}
	
	private Node buildCenter() {
		StackPane contenedor=new StackPane();
		contenedor.getChildren().add(ImageViewBuilder.create()
				.image(new Image(getClass().getResource("Imagenes/logoColegio.png").toString()))
				.preserveRatio(true)
				.fitHeight(400)
				.opacity(.2f)
				.build());
		
		contenedor.getChildren().add(VBoxBuilder.create()
				.children(
						HBoxBuilder.create()
						.children(TextBuilder.create()
								.fill(Color.BLUE)
								.font(Font.font("Arial",FontWeight.BOLD,23))
								.text("Alumnos:")
								.build(),
								VBoxBuilder.create()
								.children(TextBuilder.create().fill(Color.BLACK).font(Font.font("Arial",FontWeight.BOLD,20)).text("1º Puesto: ").build(),
									TextBuilder.create().fill(Color.BLACK).font(Font.font("Arial",FontWeight.BOLD,20)).text("2º Puesto: ").build(),
									TextBuilder.create().fill(Color.BLACK).font(Font.font("Arial",FontWeight.BOLD,20)).text("3º Puesto: ").build(),
									TextBuilder.create().fill(Color.BLACK).font(Font.font("Arial",FontWeight.BOLD,20)).text("4º Puesto: ").build(),
									TextBuilder.create().fill(Color.BLACK).font(Font.font("Arial",FontWeight.BOLD,20)).text("5º Puesto: ").build())
									.padding(new Insets(35,0,0,0))
								.spacing(20)
								.build(),
								VBoxBuilder.create()
								.children(rankingAlumnos.get(0),
										rankingAlumnos.get(1),
										rankingAlumnos.get(2),
										rankingAlumnos.get(3),
										rankingAlumnos.get(4))
								.padding(new Insets(35,0,0,0))
								.spacing(20)
								.build())
						.spacing(10)
						.build()
					,
						HBoxBuilder.create()
						.children(TextBuilder.create()
								.fill(Color.BLUE)
								.font(Font.font("Arial",FontWeight.BOLD,23))
								.text("Docentes:")
								.build(),
								VBoxBuilder.create()
								.children(TextBuilder.create().fill(Color.BLACK).font(Font.font("Arial",FontWeight.BOLD,20)).text("1º Puesto: ").build(),
										TextBuilder.create().fill(Color.BLACK).font(Font.font("Arial",FontWeight.BOLD,20)).text("2º Puesto: ").build(),
										TextBuilder.create().fill(Color.BLACK).font(Font.font("Arial",FontWeight.BOLD,20)).text("3º Puesto: ").build(),
										TextBuilder.create().fill(Color.BLACK).font(Font.font("Arial",FontWeight.BOLD,20)).text("4º Puesto: ").build(),
										TextBuilder.create().fill(Color.BLACK).font(Font.font("Arial",FontWeight.BOLD,20)).text("5º Puesto: ").build())
								.padding(new Insets(35,0,0,0))
								.spacing(20)
								.build(),
								VBoxBuilder.create()
								.children(rankingDocentes.get(0),
										rankingDocentes.get(1),
										rankingDocentes.get(2),
										rankingDocentes.get(3),
										rankingDocentes.get(4))
								.padding(new Insets(35,0,0,0))
								.spacing(20)
								.build())
						.spacing(10)
						.build()
					)
					.padding(new Insets(80,0,0,200))
					.spacing(50)
					.build()
					);
		return contenedor;
		
	}

}
