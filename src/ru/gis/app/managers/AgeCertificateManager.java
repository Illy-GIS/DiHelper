package ru.gis.app.managers;

import ru.gis.app.ApplicationMain;
import ru.gis.app.entities.AgeCertificateEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgeCertificateManager {
    public static AgeCertificateEntity getRowByOriginal(String original) throws SQLException {
        try (Connection connection = ApplicationMain.getConnection()) {
            String query = "SELECT * FROM certificate WHERE Original = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, original);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new AgeCertificateEntity(
                        resultSet.getString("Original"),
                        resultSet.getString("Analogue"),
                        resultSet.getString("Description")
                );
            } else {
                return null;
            }
        }
    }
}
