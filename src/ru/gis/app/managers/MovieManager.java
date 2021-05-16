package ru.gis.app.managers;

import ru.gis.app.ApplicationMain;
import ru.gis.app.entities.MovieEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    public static List<MovieEntity> getAllRows() throws SQLException {
        try (Connection connection = ApplicationMain.getConnection()) {
            String query = "SELECT * FROM movie;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<MovieEntity> movieList = new ArrayList<>();
            while (resultSet.next()) {
                movieList.add(new MovieEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getInt("ReleaseYear"),
                        resultSet.getDouble("UserScore"),
                        resultSet.getString("Genres"),
                        resultSet.getString("AgeCertificate"),
                        resultSet.getInt("Runtime"),
                        resultSet.getString("Budget"),
                        resultSet.getString("GrossWorld"),
                        resultSet.getString("Locations"),
                        resultSet.getString("Camera"),
                        resultSet.getString("Cast"),
                        resultSet.getString("MinorPoster"),
                        resultSet.getString("MajorPoster")
                ));
            }
            return movieList;
        }
    }

    public static List<MovieEntity> getAllRowsWithLimits(int startLimit, int endLimit) throws SQLException {
        try (Connection connection = ApplicationMain.getConnection()) {
            String query = "SELECT * FROM movie WHERE ID >= ? AND ID < ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, startLimit);
            statement.setInt(2, endLimit);
            ResultSet resultSet = statement.executeQuery();
            List<MovieEntity> movieList = new ArrayList<>();
            while (resultSet.next()) {
                movieList.add(new MovieEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getInt("ReleaseYear"),
                        resultSet.getDouble("UserScore"),
                        resultSet.getString("Genres"),
                        resultSet.getString("AgeCertificate"),
                        resultSet.getInt("Runtime"),
                        resultSet.getString("Budget"),
                        resultSet.getString("GrossWorld"),
                        resultSet.getString("Locations"),
                        resultSet.getString("Camera"),
                        resultSet.getString("Cast"),
                        resultSet.getString("MinorPoster"),
                        resultSet.getString("MajorPoster")
                ));
            }
            return movieList;
        }
    }
}
