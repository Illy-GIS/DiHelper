package ru.gis.app.managers;

import ru.gis.app.ApplicationMain;
import ru.gis.app.entities.GenreEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreManager {
    public static List<GenreEntity> getAllRows() throws SQLException {
        try (Connection connection = ApplicationMain.getConnection()) {
            String query = "SELECT * FROM genres;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<GenreEntity> genreList = new ArrayList<>();
            while (resultSet.next()) {
                genreList.add(new GenreEntity(
                        resultSet.getString("Genre"),
                        resultSet.getString("Description")
                ));
            }
            return genreList;
        }
    }
}
