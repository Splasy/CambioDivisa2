package dad.javafx.divisas;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CambioDivisaApp extends Application {
	
	private TextField n;
	private ComboBox<Divisa> divisa1;
	private TextField ns;
	private ComboBox<Divisa> divisa2;
	private Button cambiar;
	Alert error = new Alert(AlertType.ERROR);

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Inicializo las divisas con sus tasas de conversión
		
		Divisa euro = new Divisa("Euro", 1.0);
		Divisa libra = new Divisa("Libra", 0.8873);
		Divisa dolar = new Divisa("Dolar", 1.2007);
		Divisa yen = new Divisa("Yen", 133.59);
		
		
		n = new TextField();
		n.setPromptText("0");
		n.setMaxWidth(150);
		
		ns = new TextField();
		ns.setPromptText("0");
		ns.setMaxWidth(150);
		ns.setDisable(true);
		
		//Se crea el primer combobox al cual hay que darle un tipo,
		//en este caso de tipo divisa, clase creada con los distintos
		//tipos de divisa y sus getters y setters
		
		divisa1 = new ComboBox<Divisa>();
		divisa1.getItems().addAll(euro, libra, dolar, yen);
		divisa1.getSelectionModel().select(1);
		
		divisa2 = new ComboBox<Divisa>();
		divisa2.getItems().addAll(euro, libra, dolar, yen);
		divisa2.getSelectionModel().select(2);
		
		cambiar = new Button("Cambiar");
		cambiar.setDefaultButton(true);
		cambiar.setOnAction(e -> onCambiarAction());
		
		//HBox de los primeros datos
		HBox primera = new HBox();
		primera.setSpacing(5);
		primera.setAlignment(Pos.CENTER);
		primera.getChildren().addAll(n, divisa1);
		
		//HBox de los segundos datos
		HBox segunda = new HBox();
		segunda.setSpacing(5);
		segunda.setAlignment(Pos.CENTER);
		segunda.getChildren().addAll(ns, divisa2);
		
		//VBox donde meto los 2 HBox jnto con el botón
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(primera, segunda, cambiar);
		
		Scene scene = new Scene(root, 320, 200);
		primaryStage.setTitle("Cambio de divisas");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		

	}
	
	public void onCambiarAction() {
		try {
			Divisa divisaEntrada = divisa1.getSelectionModel().getSelectedItem();
			Divisa divisaSalida = divisa2.getSelectionModel().getSelectedItem();
			double cantidad = Double.parseDouble(n.getText());
			Divisa.fromTo(divisaEntrada, divisaSalida,cantidad);
			ns.setText(Divisa.fromTo(divisaEntrada, divisaSalida,cantidad).toString());
			
		}catch(Exception a) {
			error.setTitle("Error.");
			error.setHeaderText("Ha habido un error.");
			error.showAndWait();
		}
		
	}
	public static void main(String[] args) {
		launch(args);

	}

}
