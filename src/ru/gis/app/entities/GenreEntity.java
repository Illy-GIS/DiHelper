package ru.gis.app.entities;

import java.util.Objects;

public class GenreEntity {
    private final String genre;
    private final String description;

    public GenreEntity(String genre, String description) {
        this.genre = genre;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreEntity that = (GenreEntity) o;
        return Objects.equals(getGenre(), that.getGenre()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGenre(), getDescription());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GenreEntity{");
        sb.append("genre='").append(genre).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }
}
