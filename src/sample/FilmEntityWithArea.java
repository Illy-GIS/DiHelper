package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class FilmEntityWithArea {
    private ImageView poster;
    private TextArea filmInfo = new TextArea();
    private Label title = new Label();
    private VBox vContainer;

    public FilmEntityWithArea(FilmEntity filmEntity) {
        poster = filmEntity.getPosterView();
        title.setText(filmEntity.getTitle());
        title.setStyle("-fx-font-family: \"Roboto Medium\"; -fx-font-size: 36px; -fx-text-fill: #3F34C7");
        title.setWrapText(true);
        filmInfo.setText("Год выхода: " + filmEntity.getReleaseYear().toString() + "\nОценка пользователей: " + filmEntity.getUserScore().toString() +
                "\nЖанры: " + filmEntity.getGenres() + "\nВозрастной рейтинг: " + filmEntity.getAgeCertificate() + "\nДлительность: " + filmEntity.getRuntime().toString() +
                "\nБюджет: " + filmEntity.getBudget() + "\nОбщие сборы: " + filmEntity.getGrossWorld());
        filmInfo.setStyle("-fx-font-family: Poppins; -fx-font-style: normal; -fx-font-weight: 600; -fx-font-size: 28px; -fx-opacity: 0.5; -fx-text-fill: #6F7AB9");
        vContainer = new VBox(10, title, filmInfo);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FilmEntityWithArea{");
        sb.append("poster=").append(poster);
        sb.append(", filmInfo=").append(filmInfo);
        sb.append('}');
        return sb.toString();
    }

    public ImageView getPoster() {
        return poster;
    }

    public void setPoster(ImageView poster) {
        this.poster = poster;
    }

    public TextArea getFilmInfo() {
        return filmInfo;
    }

    public void setFilmInfo(TextArea filmInfo) {
        this.filmInfo = filmInfo;
    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }

    public VBox getvContainer() {
        return vContainer;
    }

    public void setvContainer(VBox vContainer) {
        this.vContainer = vContainer;
    }
}
