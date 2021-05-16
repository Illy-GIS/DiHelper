package ru.gis.app.managers;

import ru.gis.app.ApplicationMain;
import ru.gis.app.entities.ConceptEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConceptManager {
    public static List<ConceptEntity> getAllRowsByUserOwner(Integer userOwnerID) throws SQLException {
        try (Connection connection = ApplicationMain.getConnection()) {
            String query = "SELECT * FROM concept WHERE UserOwnerID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userOwnerID);
            ResultSet resultSet = statement.executeQuery();
            List<ConceptEntity> conceptList = new ArrayList<>();
            while (resultSet.next()) {
                conceptList.add(new ConceptEntity(
                        resultSet.getInt("ID"),
                        resultSet.getInt("UserOwnerID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Budget"),
                        resultSet.getString("Genres"),
                        resultSet.getString("AgeCertificate"),
                        resultSet.getInt("Runtime"),
                        resultSet.getString("Locations"),
                        resultSet.getString("Camera"),
                        resultSet.getString("Cast")
                ));
            }
            return conceptList;
        }
    }

    public static ConceptEntity getRowByID(Integer ID) throws SQLException {
        try (Connection connection = ApplicationMain.getConnection()) {
            String query = "SELECT * FROM concept WHERE ID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new ConceptEntity(
                        resultSet.getInt("ID"),
                        resultSet.getInt("UserOwnerID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Budget"),
                        resultSet.getString("Genres"),
                        resultSet.getString("AgeCertificate"),
                        resultSet.getInt("Runtime"),
                        resultSet.getString("Locations"),
                        resultSet.getString("Camera"),
                        resultSet.getString("Cast")
                );
            } else {
                return null;
            }
        }
    }

    public static void addRow(ConceptEntity conceptEntity) throws SQLException {
        try (Connection connection = ApplicationMain.getConnection()) {
            String query = "INSERT INTO concept (UserOwnerID, Title, Budget, Genres, AgeCertificate, Runtime, Locations, Camera, Cast) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, conceptEntity.getUserOwnerID());
            statement.setString(2, conceptEntity.getTitle());
            statement.setString(3, conceptEntity.getBudget());
            statement.setString(4, conceptEntity.getGenres());
            statement.setString(5, conceptEntity.getAgeCertificate());
            if (conceptEntity.getRuntime() == null) {
                statement.setNull(6, Types.INTEGER);
            } else {
                statement.setInt(6, conceptEntity.getRuntime());
            }
            statement.setString(7, conceptEntity.getLocations());
            statement.setString(8, conceptEntity.getCamera());
            statement.setString(9, conceptEntity.getCast());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                conceptEntity.setID(resultSet.getInt(1));
            }
        }
    }

    public static void updateRow(ConceptEntity conceptEntity) throws SQLException {
        try (Connection connection = ApplicationMain.getConnection()) {
            String query = "UPDATE concept SET UserOwnerID = ?, Title = ?, Budget = ?, Genres = ?, AgeCertificate = ?, Runtime = ?, Locations = ?, Camera = ?, Cast = ? WHERE ID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, conceptEntity.getUserOwnerID());
            statement.setString(2, conceptEntity.getTitle());
            statement.setString(3, conceptEntity.getBudget());
            statement.setString(4, conceptEntity.getGenres());
            statement.setString(5, conceptEntity.getAgeCertificate());
            if (conceptEntity.getRuntime() == null) {
                statement.setNull(6, Types.INTEGER);
            } else {
                statement.setInt(6, conceptEntity.getRuntime());
            }
            statement.setString(7, conceptEntity.getLocations());
            statement.setString(8, conceptEntity.getCamera());
            statement.setString(9, conceptEntity.getCast());
            statement.setInt(10, conceptEntity.getID());
            statement.executeUpdate();
        }
    }

    public static void deleteRowByID(Integer ID) throws SQLException {
        try (Connection connection = ApplicationMain.getConnection()) {
            String query = "DELETE FROM concept WHERE ID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ID);
            statement.executeUpdate();
        }
    }
}
