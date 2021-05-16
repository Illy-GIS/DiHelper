package ru.gis.app.entities;

import java.util.Objects;

public class ConceptEntity {
    private Integer ID;
    private Integer userOwnerID;
    private String title;
    private String budget;
    private String genres;
    private String ageCertificate;
    private Integer runtime;
    private String locations;
    private String camera;
    private String cast;

    public ConceptEntity(Integer ID, Integer userOwnerID, String title, String budget, String genres, String ageCertificate, Integer runtime, String locations, String camera, String cast) {
        this.ID = ID;
        this.userOwnerID = userOwnerID;
        this.title = title;
        this.budget = budget;
        this.genres = genres;
        this.ageCertificate = ageCertificate;
        this.runtime = runtime;
        this.locations = locations;
        this.camera = camera;
        this.cast = cast;
    }

    public ConceptEntity(Integer userOwnerID, String title, String budget, String genres, String ageCertificate, Integer runtime, String locations, String camera, String cast) {
        this(-1, userOwnerID, title, budget, genres, ageCertificate, runtime, locations, camera, cast);
    }

    public ConceptEntity(Integer userOwnerID, String title) {
        this(-1, userOwnerID, title, null, null, null, null, null, null, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConceptEntity that = (ConceptEntity) o;
        return Objects.equals(getID(), that.getID()) && Objects.equals(getUserOwnerID(), that.getUserOwnerID()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getBudget(), that.getBudget()) && Objects.equals(getGenres(), that.getGenres()) && Objects.equals(getAgeCertificate(), that.getAgeCertificate()) && Objects.equals(getRuntime(), that.getRuntime()) && Objects.equals(getLocations(), that.getLocations()) && Objects.equals(getCamera(), that.getCamera()) && Objects.equals(getCast(), that.getCast());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getUserOwnerID(), getTitle(), getBudget(), getGenres(), getAgeCertificate(), getRuntime(), getLocations(), getCamera(), getCast());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConceptEntity{");
        sb.append("ID=").append(ID);
        sb.append(", userOwnerID=").append(userOwnerID);
        sb.append(", title='").append(title).append('\'');
        sb.append(", budget='").append(budget).append('\'');
        sb.append(", genres='").append(genres).append('\'');
        sb.append(", ageCertificate='").append(ageCertificate).append('\'');
        sb.append(", runtime=").append(runtime);
        sb.append(", locations='").append(locations).append('\'');
        sb.append(", camera='").append(camera).append('\'');
        sb.append(", cast='").append(cast).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getUserOwnerID() {
        return userOwnerID;
    }

    public void setUserOwnerID(Integer userOwnerID) {
        this.userOwnerID = userOwnerID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
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

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }
}
