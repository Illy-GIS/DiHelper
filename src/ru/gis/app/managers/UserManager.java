package ru.gis.app.managers;

import ru.gis.app.ApplicationMain;
import ru.gis.app.entities.UserEntity;

import java.sql.*;

public class UserManager {
    public static void addRow(UserEntity userEntity) throws SQLException {
        try (Connection connection = ApplicationMain.getConnection()) {
            String query = "INSERT INTO moviedb.user(Login, Name, Password) VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userEntity.getEmail());
            statement.setString(2, userEntity.getName());
            statement.setString(3, userEntity.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                userEntity.setId(resultSet.getInt(1));
            }
        }
    }

    public static UserEntity getRowByEmail(String email) throws SQLException {
        try (Connection connection = ApplicationMain.getConnection()) {
            String query = "SELECT * FROM moviedb.user WHERE Login = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new UserEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Login"),
                        resultSet.getString("Name"),
                        resultSet.getString("Password")
                );
            } else {
                return null;
            }
        }
    }
}
