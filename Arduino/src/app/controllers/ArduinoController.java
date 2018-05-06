package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import utils.SerialComm;
import utils.SerialComm.Status;

public class ArduinoController implements Initializable {
	private static final int TURN_OFF = 0;
	private static final int TURN_ON = 1;
	private static final int BLINK = 2;
	private SerialComm serialComm;

	@FXML
	private Label msgbox;

	@FXML
	private void handleTurnOn(ActionEvent event) {
		try {
			serialComm.send(TURN_ON);
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}

	@FXML
	private void handleBlink(ActionEvent event) {
		try {
			serialComm.send(BLINK);
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}

	@FXML
	private void handleTurnOff(ActionEvent event) {
		try {
			serialComm.send(TURN_OFF);
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}

	@FXML
	void handleConnect(ActionEvent event) {
		Thread backThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					serialComm.init();
					Thread.sleep(1000);
					if (serialComm.getStatus() == Status.CONNECTED) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								msgbox.setText("Connecting");
							}
						});
						System.out.println("Arduino is connected successfully.");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
		backThread.setDaemon(true);
		backThread.start();
	}

	@FXML
	void handleDisconnect(ActionEvent event) {
		if (serialComm.getStatus() == Status.CONNECTED) {
			shutdown();
			msgbox.setText("Not Connect");
		}
	}

	@FXML
	void handleWelcome(ActionEvent event) {
		System.out.println("Nothing to do");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		msgbox.setText("Unknown");
		serialComm = new SerialComm() {
			@Override
			public void onReceive(String res) {
				System.out.println(res);
			}
		};
	}

	public void shutdown() {
		serialComm.close();
		if (serialComm.getStatus() == Status.DISCONNECTED)
			System.out.println("Arduino disconnected.");
	}

}
