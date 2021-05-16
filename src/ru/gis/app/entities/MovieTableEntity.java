package ru.gis.app.entities;

import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class MovieTableEntity {
    /*private final ImageView minorPoster;
    private final ImageView majorPoster;*/
    private final TextArea movieInfo = new TextArea();
    private final MovieEntity movieEntity;

    public MovieTableEntity(MovieEntity movieEntity) {
        /*minorPoster = movieEntity.getMinorPosterImageView();
        majorPoster = movieEntity.getMajorPosterImageView();*/
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
        /*sb.append("minorPoster=").append(minorPoster);
        sb.append(", majorPoster=").append(majorPoster);*/
        sb.append(", movieInfo=").append(movieInfo);
        sb.append(", movieEntity=").append(movieEntity);
        sb.append('}');
        return sb.toString();
    }

    /*public ImageView getMinorPoster() {
        return minorPoster;
    }

    public ImageView getMajorPoster() {
        return majorPoster;
    }*/

    public TextArea getMovieInfo() {
        return movieInfo;
    }

    public MovieEntity getMovieEntity() {
        return movieEntity;
    }
}
