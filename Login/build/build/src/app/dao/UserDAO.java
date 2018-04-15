package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.models.User;
import utils.MySQLConnector;

public class UserDAO {
	private Connection connection = null;

	public UserDAO() {
		connection = MySQLConnector.getConnection();
	}

	public void addUser(User user) {
		String sqlStr = "insert into users(userlogin,password)values(?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
			preparedStatement.setString(1, user.getUserLogin());
			preparedStatement.setString(2, user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUser(int userId) {
		String sqlStr = "delete from users where userid=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateUser(User user) {
		String sqlStr = "update users set userlogin=?, password=? where userid=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
			preparedStatement.setString(1, user.getUserLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setInt(3, user.getUserID());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User getUser(String userLogin) {
		User user = new User();
		String sqlStr = "select * from users where userLogin=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
			preparedStatement.setString(1, userLogin);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				user.setUserID(rs.getInt("userid"));
				user.setUserLogin(rs.getString("userlogin"));
				user.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User getUserById(int userId) {
		User user = new User();
		String sqlStr = "select * from users where userid=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				user.setUserID(rs.getInt("userid"));
				user.setUserLogin(rs.getString("userlogin"));
				user.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		String sqlStr = "select * from users";
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sqlStr);
			while (rs.next()) {
				User user = new User();
				user.setUserID(rs.getInt("userid"));
				user.setUserLogin(rs.getString("userlogin"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
