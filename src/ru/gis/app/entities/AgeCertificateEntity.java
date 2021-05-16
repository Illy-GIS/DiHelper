package ru.gis.app.entities;

import java.util.Objects;

public class AgeCertificateEntity {
    private final String original;
    private final String analogue;
    private final String description;

    public AgeCertificateEntity(String original, String analogue, String description) {
        this.original = original;
        this.analogue = analogue;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgeCertificateEntity that = (AgeCertificateEntity) o;
        return Objects.equals(getOriginal(), that.getOriginal()) && Objects.equals(getAnalogue(), that.getAnalogue()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOriginal(), getAnalogue(), getDescription());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AgeCertificateEntity{");
        sb.append("original='").append(original).append('\'');
        sb.append(", analogue='").append(analogue).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getOriginal() {
        return original;
    }

    public String getAnalogue() {
        return analogue;
    }

    public String getDescription() {
        return description;
    }
}
