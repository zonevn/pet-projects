package app;

import app.controllers.ArduinoController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/views/ArduinoView.fxml"));
			Parent root = loader.load();
			ArduinoController controller = loader.getController();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setOnHidden((ev) -> {
				controller.shutdown();
				Platform.exit();				
			});
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
