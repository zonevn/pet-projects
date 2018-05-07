package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/app/views/DashboardView.fxml"));
			Scene scene = new Scene(root);			
			scene.getStylesheets().add("/app/theme/DashboardStyle.css");
			stage.setScene(scene);			
			stage.setResizable(false);
			stage.initStyle(StageStyle.UNDECORATED);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
