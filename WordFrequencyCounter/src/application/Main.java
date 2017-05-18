package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	private static Stage primaryStage; //Declare static Stage

    private void setPrimaryStage(Stage stage) {
        Main.primaryStage = stage;
    }

    static public Stage getPrimaryStage() {
        return Main.primaryStage;
    }
	

	@Override
	public void start(Stage primaryStage) {
		try {
			setPrimaryStage(primaryStage);
			Parent root=FXMLLoader.load(getClass().getResource("/application/scene.fxml"));
			Scene scene = new Scene(root,400,400);
			primaryStage.setTitle("Word Frequency Counter");
			primaryStage.setMinWidth(640);
			primaryStage.setMinHeight(480);
			primaryStage.setWidth(800);
			primaryStage.setHeight(600);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
