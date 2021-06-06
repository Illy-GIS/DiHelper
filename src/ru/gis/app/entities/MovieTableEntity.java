package ru.gis.app.entities;

import javafx.scene.control.TextArea;

public class MovieTableEntity {
    private final TextArea movieInfo = new TextArea();
    private final MovieEntity movieEntity;

    public MovieTableEntity(MovieEntity movieEntity) {
        movieInfo.setWrapText(true);
        movieInfo.getStyleClass().add("movie-info-area");
        StringBuilder infoBuilder = new StringBuilder();
        String[] genres = movieEntity.getGenres().split(",");
        infoBuilder.append(movieEntity.getTitle());
        infoBuilder.append("\n\nГод выхода: ").append(movieEntity.getReleaseYear());
        infoBuilder.append("\nСредняя оценка пользователей: ").append(movieEntity.getUserScore());
        infoBuilder.append("\nЖанры: ");
        for (String genre : genres) {
            infoBuilder.append(genre).append(", ");
        }
        infoBuilder.delete(infoBuilder.length() - 2, infoBuilder.length() - 1);
        if (movieEntity.getAgeCertificate() != null) {
            infoBuilder.append("\nВозрастной рейтинг: ").append(movieEntity.getAgeCertificate());
        }
        if (movieEntity.getRuntime() != null && movieEntity.getRuntime() != 0) {
            infoBuilder.append("\nДлительность: ").append(movieEntity.getRuntime()).append(" мин.");
        }
        movieInfo.setText(infoBuilder.toString());
        movieInfo.setEditable(false);
        movieInfo.setMouseTransparent(true);
        this.movieEntity = movieEntity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MovieTableEntity{");
        sb.append(", movieInfo=").append(movieInfo);
        sb.append(", movieEntity=").append(movieEntity);
        sb.append('}');
        return sb.toString();
    }

    public TextArea getMovieInfo() {
        return movieInfo;
    }

    public MovieEntity getMovieEntity() {
        return movieEntity;
    }
}
