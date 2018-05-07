package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class DashboardController implements Initializable {
	@FXML
	private HBox hbTitlebar;

	@FXML
	private ImageView btClose;

	@FXML
	private BorderPane borderpane;

	private double offsetX;
	private double offsetY;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initComponents();
		loadGUI("Portal");
	}

	@FXML
	private void handleHome(ActionEvent event) {
		loadGUI("Home");
	}

	@FXML
	private void handleMessages(ActionEvent event) {
		loadGUI("Messages");
	}

	@FXML
	private void handleReports(ActionEvent event) {
		loadGUI("Reports");
	}

	@FXML
	private void handleLogout(ActionEvent event) {
		loadGUI("Portal");
	}

	private void initComponents() {
		btClose.setOnMouseClicked((ev) -> {
			Platform.exit();
		});

		// grab window from title bar
		hbTitlebar.setOnMousePressed((ev) -> {
			offsetX = ev.getSceneX();
			offsetY = ev.getSceneY();
		});

		// move window from title bar
		hbTitlebar.setOnMouseDragged((ev) -> {
			Stage stage = (Stage) hbTitlebar.getScene().getWindow();
			stage.setX(ev.getScreenX() - offsetX);
			stage.setY(ev.getScreenY() - offsetY);
		});
	}

	private void loadGUI(String gui) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/app/views/" + gui + "SubView.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		borderpane.setCenter(root);
	}
}
