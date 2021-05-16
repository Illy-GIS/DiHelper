package sample;

import ru.gis.app.ApplicationMain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmManager {
    public static List<FilmEntity> getAll() throws SQLException {
        try (Connection connection = ApplicationMain.getConnection()) {
            String query = "SELECT * FROM movie LIMIT 100;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<FilmEntity> filmList = new ArrayList<>();
            while (resultSet.next()) {
                filmList.add(new FilmEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getInt("ReleaseYear"),
                        resultSet.getDouble("UserScore"),
                        resultSet.getString("MajorPoster"),
                        resultSet.getString("Genres"),
                        resultSet.getString("AgeCertificate"),
                        resultSet.getInt("Runtime"),
                        resultSet.getString("Budget"),
                        resultSet.getString("GrossWorld")
                ));
            }
            return filmList;
        }
    }
}
