package controllerFlexi;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/viewFlexi/mainView.fxml"));
			Scene scene = new Scene(root,900,900);
			scene.getStylesheets().add(getClass().getResource("/viewFlexi/application.css").toExternalForm());
			primaryStage.setTitle("Welcome to Flexi rent Properties!!");
			primaryStage.setScene(scene);
			primaryStage.show();
			//System.out.println(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
