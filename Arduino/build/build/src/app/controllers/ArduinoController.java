package app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class ArduinoController implements Initializable {

	@FXML
	public void handleTurnOn(ActionEvent event) {
		System.out.println("Turn On");
	}

	@FXML
	public void handleBlink(ActionEvent event) {
		System.out.println("Blink");
	}

	@FXML
	public void handleTurnOff(ActionEvent event) {
		System.out.println("Turn Off");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("initialized");
	}
}
