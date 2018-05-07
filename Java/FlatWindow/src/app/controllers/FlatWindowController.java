package app.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FlatWindowController implements Initializable {
	@FXML
	private HBox hbTitlebar;

	@FXML
	private ImageView btClose;

	private double offsetX;
	private double offsetY;

	@FXML
	public void grabWindowFromTitlebar(MouseEvent ev) {
		offsetX = ev.getSceneX();
		offsetY = ev.getSceneY();
	}

	@FXML
	public void moveWindowFromTitlebar(MouseEvent ev) {
		Stage stage = (Stage) hbTitlebar.getScene().getWindow();
		stage.setX(ev.getScreenX() - offsetX);
		stage.setY(ev.getScreenY() - offsetY);
	}

	@FXML
	public void close(MouseEvent ev) {
		Platform.exit();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
