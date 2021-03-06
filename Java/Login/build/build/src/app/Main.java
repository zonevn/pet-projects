package app;

import app.dao.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private UserDAO dao;

	@Override
	public void start(Stage stage) throws Exception {
		try {
			dao = new UserDAO();			
			Parent root = FXMLLoader.load(getClass().getResource("/app/views/LoginView.fxml"));
			stage.setTitle("MVC Pattern");
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
