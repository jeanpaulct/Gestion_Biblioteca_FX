package Controlador;
import java.util.Date;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import Modelo.MLibro;
import Modelo.MPrestamo;
import Vista.Acciones;
import Vista.JMensaje;
import Vista.Login;
import Vista.RegistroLibro;
import Vista.RegistroPrestamo;
import Vista.RegistroRequerimiento;


public class Principal extends Application {
	
	private ObservableList<MLibro> listaLibros;
	private CBussines busines;
	private CReportes reportes;
	private Stage  mmsj;
	private JMensaje viewMsj;
	
	public StackPane nodoRaiz;
	
	private Login loginClass;
	public Parent loginView;
	private Acciones accionesClass;
	public Parent accionesView;
	private RegistroRequerimiento registroRequerimientoClass;
	public Parent registroRequerimientosView;
	private RegistroPrestamo registroPrestamoClass;
	public Parent registroPrestamoView;
	private RegistroLibro registroLibroClass;
	public Parent registroLibroView;
	
	public static Scene escena;
	
	public Principal() {
		listaLibros=FXCollections.observableArrayList();
	}
	
	public void start(Stage primaryStage) throws Exception {
		nodoRaiz=new StackPane();
		escena=new Scene(nodoRaiz,1000, 700);
		
		mmsj=new Stage(StageStyle.TRANSPARENT);
		viewMsj=new JMensaje(mmsj);
		mmsj.setScene(viewMsj.getView());
		mmsj.initModality(Modality.WINDOW_MODAL);
		mmsj.initOwner(primaryStage);	
		//eventos para el mensaje de confirmacion
		mmsj.setOnShown(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				nodoRaiz.setOpacity(.3f);
			}
		});
		
		mmsj.setOnHidden(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				nodoRaiz.setOpacity(1f);
			}
		});
		
		
		loginView=creaLogin();
		accionesView=creaAcciones();
		registroRequerimientosView=creaRequerimientos();
		registroPrestamoView=creaRegistroPrestamo();
		registroLibroView=creaRegistroLibro();
		
		nodoRaiz.getChildren().add(loginView);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			public void handle(WindowEvent event) {
				if(busines!=null){
					busines.cierra();
				}
			}
		});
		
		primaryStage.setTitle("Sistema \"Biblioteca Rosarina\"");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("ico.png")));
		primaryStage.setMaxWidth(1016);
		primaryStage.setMaxHeight(738);
		primaryStage.setX(175);
		primaryStage.setY(0);
		primaryStage.setScene(escena);
		primaryStage.show();
	}
	
	private Parent creaRequerimientos() {
		registroRequerimientoClass=new RegistroRequerimiento();
		
		registroRequerimientoClass.goIndex(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				registroRequerimientoClass.beginNew();
				nodoRaiz.getChildren().setAll(accionesView);
			}
		});
		
		registroRequerimientoClass.escuchaTeclas(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case ESCAPE:
					nodoRaiz.getChildren().setAll(accionesView);
					break;
				case F5:
					viewMsj.setMensaje("Habiendo verificado que los datos ingresados son los correctos.\n" +
							"¿Desea guardar el requerimiento de este libro?");
					viewMsj.btnGuardar.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							registraRequerimiento();
							mmsj.close();
						}
					});
					mmsj.show();
					break;
				case F6:
					registroRequerimientoClass.nuevoRequerimiento();
					break;
				}
			}
		});
		
		registroRequerimientoClass.onGuardar(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				viewMsj.setMensaje("Habiendo verificado que los datos ingresados son los correctos.\n" +
						"¿Desea guardar el requerimiento de este libro?");
				
				viewMsj.btnGuardar.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						registraRequerimiento();
						mmsj.close();
					}
				});
				mmsj.show();
			}
		});
		
		return StackPaneBuilder.create()
				.children(registroRequerimientoClass.getNodoVista())
				.build();
	}
	
	private Parent creaRegistroLibro(){
		
		registroLibroClass=new RegistroLibro();
		
		registroLibroClass.goIndex(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				registroLibroClass.beginNew();
				nodoRaiz.getChildren().setAll(accionesView);
			}
		});
		
		registroLibroClass.escuchaTeclas(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case ESCAPE:
					nodoRaiz.getChildren().setAll(accionesView);
					break;
				case F5:
					viewMsj.setMensaje("Habiendo verificado que los datos ingresados son los correctos.\n" +
							"¿Desea guardar el ingreso de este nuevo libro?");
					
					viewMsj.btnGuardar.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							registraRequerimiento();
							mmsj.close();
						}
					});
					mmsj.show();
					break;
				case F6:
					registroLibroClass.nuevoLibro();
					break;
				}
			}
		});
		
		registroLibroClass.onGuardar(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				viewMsj.setMensaje("Habiendo verificado que los datos ingresados son los correctos.\n" +
						"¿Desea guardar el ingreso de este nuevo libro?");
				
				viewMsj.btnGuardar.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						registraLibro();
						mmsj.close();
					}
				});				
				mmsj.show();
			}
		});
		
		return StackPaneBuilder.create()
				.children(registroLibroClass.getNodoVista())
				.build();
	}
	
	private Parent creaRegistroPrestamo() {
		
		registroPrestamoClass=new RegistroPrestamo();
		
		registroPrestamoClass.goIndex(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				registroPrestamoClass.beginNew();
				nodoRaiz.getChildren().setAll(accionesView);
			}
		});
		
		registroPrestamoClass.escuchaTeclas(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case ESCAPE:
					nodoRaiz.getChildren().setAll(accionesView);
					break;
				case F5:
					viewMsj.setMensaje("Habiendo verificado que los datos ingresados son los correctos.\n" +
							"¿Desea guardar el requerimiento de este libro?");
					
					viewMsj.btnGuardar.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							registraRequerimiento();
							mmsj.close();
						}
					});
					mmsj.show();
					break;
				case F6:
					registroPrestamoClass.NuevoPrestamo();
					break;
				}
			}
		});
		
		registroPrestamoClass.onGuardar(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				viewMsj.setMensaje("Habiendo verificado que los datos ingresados son los correctos.\n" +
						"¿Desea guardar el préstamo de este libro?");
				
				viewMsj.btnGuardar.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						registraPrestamo();
						mmsj.close();
						muestraPrestamos();
					}
				});
				
				mmsj.show();
			}
		});
		
		registroPrestamoClass.onClickTable(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				if(event.getClickCount()==2){
					final ObservableList<MPrestamo> data=registroPrestamoClass.tblvistaPrestamos.getSelectionModel().getSelectedItems();
					viewMsj.setMensaje("" +
							"¿Desea guardar la devolucion de este libro?");
					
					viewMsj.btnGuardar.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							busines.registraDevolucion(Integer.parseInt(data.get(0).getCodigo()));
							mmsj.close();
							muestraPrestamos();
						}
					});
					mmsj.show();
				}
			}
		});
		
		return StackPaneBuilder.create()
				.children(registroPrestamoClass.getNodoVista())
				.build();
	}

	private Parent creaAcciones() {
		
		accionesClass=new Acciones();
		
		//vistas
		
		accionesClass.goRegistroLibro(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				nodoRaiz.getChildren().setAll(registroLibroView);
			}
		});
		
		accionesClass.goNuevoRequerimiento(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				nodoRaiz.getChildren().setAll(registroRequerimientosView);
			}
		});
		
		accionesClass.goRegistroPrestamo(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				muestraPrestamos();
				nodoRaiz.getChildren().setAll(registroPrestamoView);
			}
		});
		
		//acciones
		
		accionesClass.accionBusquedaCaja(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.ENTER){
					Search(accionesClass.cajabusqueda.getText());
				}
			}
		});
		
		accionesClass.accionBusquedaBoton(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Search(accionesClass.cajabusqueda.getText());
			}
		});
		
		//reportes 
		accionesClass.reportPrestamo(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				reportes.consolidadoPrestamo();
			}
		});
		
		accionesClass.reportInventario(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				reportes.inventario();				
			}
		});
		
		accionesClass.reportRequerimientos(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				reportes.requerimiento();
			}
		});
		
		accionesClass.reportDeudores(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				reportes.deudores();
			}
		});
		
		accionesClass.reportNuevosLibros(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				reportes.recienLlegados();
			}
		});
		
		accionesClass.reportRanking(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				reportes.ranking();
			}
		});
		
		StackPane stackPane=new StackPane();
		stackPane.getChildren().add(accionesClass.getNodoVista());
		return stackPane;
	}

	private Parent creaLogin() {
		loginClass=new Login();
		loginClass.setnextManejador(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				try {
					busines=new CBussines(loginClass.txtUserName.getText(),loginClass.txtUserPwd.getText());
					reportes=new CReportes();
					nodoRaiz.getChildren().setAll(accionesView);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return;
				}
				
			}
		});
		
		loginClass.onkeyEnter(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.ENTER){
					try {
						busines=new CBussines(loginClass.txtUserName.getText(),loginClass.txtUserPwd.getText());
						reportes=new CReportes();
						nodoRaiz.getChildren().setAll(accionesView);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}
				}
			}
		});
		StackPane stackPane=new StackPane();
		stackPane.getChildren().add(loginClass.getNodoVista());
		return stackPane;
	}
	
	private String trataBusqueda(String busqueda){
		String []arrays=new String[busqueda.length()];
		boolean space=true;
		for(int i=0;i<busqueda.length();i++){
			arrays[i]=busqueda.substring(i, i+1);
			if(arrays[i].equals(" ") && space){
				arrays[i]="";
			}else{
				space=false;
			}
		}
		
		busqueda="";
		for(int i=0;i<arrays.length;i++){
			busqueda+=arrays[i];
		}
		
		return busqueda;
	}

	private void Search(String cadenaBusqueda){
		cadenaBusqueda=trataBusqueda(cadenaBusqueda);
		if(cadenaBusqueda.matches("((\\w+)(\\W*))+")){			
			listaLibros=busines.getBusqueda(cadenaBusqueda);
			accionesClass.coltituloLibro.setCellValueFactory(new PropertyValueFactory<MLibro, String>("Titulo"));
			accionesClass.colautorLibro.setCellValueFactory(new PropertyValueFactory<MLibro, String>("Autor"));
			accionesClass.colejmLibro.setCellValueFactory(new PropertyValueFactory<MLibro, Integer>("Ejemplares"));
			accionesClass.tblresultadosBusqueda.setItems(listaLibros);
		}
	}
	
	private void registraPrestamo(){
		
		busines.registerPrestamo(registroPrestamoClass.cajaLCodigo.getText().toString(),
				registroPrestamoClass.cajaADNI.getText().toString(),
				registroPrestamoClass.chbTipoPrestamo.getSelectionModel().getSelectedItem().substring(0,1),
				registroPrestamoClass.chbAGrado.getSelectionModel().getSelectedItem(),
				registroPrestamoClass.chbASeccion.getSelectionModel().getSelectedItem());
	}
	
	private void registraLibro(){
		try {
			busines.registerLibro(registroLibroClass.cajaLCodigoDway.getText(),
					registroLibroClass.cajaLTitulo.getText(),
					registroLibroClass.cajaLAutor.getText(),
					registroLibroClass.cajaLEditorial.getText(),
					registroLibroClass.cajaLAnioEdicion.getText(),
					registroLibroClass.chboxlestado.getSelectionModel().getSelectedItem().substring(0,1));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}
		
		registroLibroClass.beginNew();
	}
	
	private void registraRequerimiento(){
		try {
			busines.registerRequerimiento(registroRequerimientoClass.cajaLTitulo.getText(),
					registroRequerimientoClass.cajaLAutor.getText(),
					registroRequerimientoClass.cajaLEditorial.getText(),
					registroRequerimientoClass.cajaLAnioEdicion.getText(),
					registroRequerimientoClass.areaPedido.getSelectionModel().getSelectedItem(),
					registroRequerimientoClass.cajaDNISolicitante.getText());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}
		registroRequerimientoClass.beginNew();
	}
	
	private void muestraPrestamos(){
		try {
			ObservableList<MPrestamo> lista=busines.viewPrestamo();
			registroPrestamoClass.colcodigoPrestamo.setCellValueFactory(new PropertyValueFactory<MPrestamo, String>("Codigo"));
			registroPrestamoClass.colcodigoLibro.setCellValueFactory(new PropertyValueFactory<MPrestamo, String>("Libro"));
			registroPrestamoClass.coldniLector.setCellValueFactory(new PropertyValueFactory<MPrestamo, String>("Dni"));
			registroPrestamoClass.colfechaPrestamo.setCellValueFactory(new PropertyValueFactory<MPrestamo, Date>("Fecha"));
			registroPrestamoClass.colestadoPrestamo.setCellValueFactory(new PropertyValueFactory<MPrestamo, String>("Estado"));
			registroPrestamoClass.tblvistaPrestamos.setItems(lista);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}
		registroPrestamoClass.beginNew();
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
