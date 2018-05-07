package app.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import app.dao.UserDAO;
import app.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utils.Sha1;

public class LoginController implements Initializable {
	@FXML
	private JFXButton btLogin;

	@FXML
	private JFXPasswordField tfPassword;

	@FXML
	private JFXTextField tfUser;

	@FXML
	private JFXButton btSignup;

	@FXML
	void doLogin(ActionEvent event) {
		System.out.println("Nothing");
	}

	@FXML
	void doSignup(ActionEvent event) {
		String usr = tfUser.getText();
		String pwd = tfPassword.getText();
		if (usr.isEmpty() || pwd.isEmpty()) {
			System.out.println("User or Password is empty.");
		} else {
			pwd = Sha1.getHash(pwd);
			System.out.println("Password=" + pwd);
			UserDAO dao = new UserDAO();			
			if (dao.getUser(usr).getUserLogin().isEmpty()) {
				System.out.println("UserLogin is existing.");
			} else {
				User user = new User();
				user.setUserLogin(usr);
				user.setPassword(pwd);
				dao.addUser(user);
				System.out.println("Congratulation! Registration is successful.");
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
