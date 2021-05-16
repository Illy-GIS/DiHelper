package ru.gis.app.entities;

import java.util.Objects;

public class MovieEntity {
    private final Integer ID;
    private final String title;
    private final Integer releaseYear;
    private final Double userScore;
    private final String genres;
    private final String ageCertificate;
    private final Integer runtime;
    private final String budget;
    private final String grossWorld;
    private final String locations;
    private final String camera;
    private final String cast;
    private final String minorPoster;
    private final String majorPoster;

    public MovieEntity(Integer ID, String title, Integer releaseYear, Double userScore, String genres, String ageCertificate, Integer runtime, String budget, String grossWorld, String locations, String camera, String cast, String minorPoster, String majorPoster) {
        this.ID = ID;
        this.title = title;
        this.releaseYear = releaseYear;
        this.userScore = userScore;
        this.genres = genres;
        this.ageCertificate = ageCertificate;
        this.runtime = runtime;
        this.budget = budget;
        this.grossWorld = grossWorld;
        this.locations = locations;
        this.camera = camera;
        this.cast = cast;
        this.minorPoster = minorPoster;
        this.majorPoster = majorPoster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity that = (MovieEntity) o;
        return Objects.equals(getID(), that.getID()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getReleaseYear(), that.getReleaseYear()) && Objects.equals(getUserScore(), that.getUserScore()) && Objects.equals(getGenres(), that.getGenres()) && Objects.equals(getAgeCertificate(), that.getAgeCertificate()) && Objects.equals(getRuntime(), that.getRuntime()) && Objects.equals(getBudget(), that.getBudget()) && Objects.equals(getGrossWorld(), that.getGrossWorld()) && Objects.equals(getLocations(), that.getLocations()) && Objects.equals(getCamera(), that.getCamera()) && Objects.equals(getCast(), that.getCast()) && Objects.equals(getMinorPoster(), that.getMinorPoster()) && Objects.equals(getMajorPoster(), that.getMajorPoster());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getTitle(), getReleaseYear(), getUserScore(), getGenres(), getAgeCertificate(), getRuntime(), getBudget(), getGrossWorld(), getLocations(), getCamera(), getCast(), getMinorPoster(), getMajorPoster());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MovieEntity{");
        sb.append("ID=").append(ID);
        sb.append(", title='").append(title).append('\'');
        sb.append(", releaseYear=").append(releaseYear);
        sb.append(", userScore=").append(userScore);
        sb.append(", genres='").append(genres).append('\'');
        sb.append(", ageCertificate='").append(ageCertificate).append('\'');
        sb.append(", runtime=").append(runtime);
        sb.append(", budget='").append(budget).append('\'');
        sb.append(", grossWorld='").append(grossWorld).append('\'');
        sb.append(", locations='").append(locations).append('\'');
        sb.append(", camera='").append(camera).append('\'');
        sb.append(", cast='").append(cast).append('\'');
        sb.append(", minorPoster='").append(minorPoster).append('\'');
        sb.append(", majorPoster='").append(majorPoster).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Integer getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public Double getUserScore() {
        return userScore;
    }

    public String getGenres() {
        return genres;
    }

    public String getAgeCertificate() {
        return ageCertificate;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getBudget() {
        return budget;
    }

    public String getGrossWorld() {
        return grossWorld;
    }

    public String getLocations() {
        return locations;
    }

    public String getCamera() {
        return camera;
    }

    public String getCast() {
        return cast;
    }

    public String getMinorPoster() {
        return minorPoster;
    }

    public String getMajorPoster() {
        return majorPoster;
    }
}
