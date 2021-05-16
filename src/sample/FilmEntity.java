package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FilmEntity {
    private int id;
    private String title;
    private Integer releaseYear;
    private Double userScore;
    private String posterUrl;
    private Image poster;
    private ImageView posterView = new ImageView();
    private String genres;
    private String ageCertificate;
    private Integer runtime;
    private String budget;
    private String grossWorld;

    public FilmEntity(int id, String title, Integer releaseYear, Double userScore, String posterUrl, String genres, String ageCertificate, Integer runtime, String budget, String grossWorld) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.userScore = userScore;
        this.posterUrl = posterUrl;
        this.genres = genres;
        this.ageCertificate = ageCertificate;
        this.runtime = runtime;
        this.budget = budget;
        this.grossWorld = grossWorld;
        poster = new Image(posterUrl, 540, 800, true, false, true);
        posterView.setImage(poster);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FilmEntity{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", releaseYear=").append(releaseYear);
        sb.append(", userScore=").append(userScore);
        sb.append(", posterUrl='").append(posterUrl).append('\'');
        sb.append(", poster=").append(poster);
        sb.append(", posterView=").append(posterView);
        sb.append(", genres='").append(genres).append('\'');
        sb.append(", ageCertificate='").append(ageCertificate).append('\'');
        sb.append(", runtime=").append(runtime);
        sb.append(", budget='").append(budget).append('\'');
        sb.append(", grossWorld='").append(grossWorld).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Double getUserScore() {
        return userScore;
    }

    public void setUserScore(Double userScore) {
        this.userScore = userScore;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
        poster = new Image(posterUrl);
        posterView.setImage(poster);
    }

    public ImageView getPosterView() {
        return posterView;
    }

    public void setPosterView(ImageView posterView) {
        this.posterView = posterView;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getAgeCertificate() {
        return ageCertificate;
    }

    public void setAgeCertificate(String ageCertificate) {
        this.ageCertificate = ageCertificate;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getGrossWorld() {
        return grossWorld;
    }

    public void setGrossWorld(String grossWorld) {
        this.grossWorld = grossWorld;
    }
}
