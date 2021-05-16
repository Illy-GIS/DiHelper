package ru.gis.app.entities;

import javafx.scene.control.Label;

import java.util.Objects;

public class DetailTableEntity {
    private final Label title = new Label();
    private final Label year = new Label();
    private final Label score = new Label();
    private final Label budget = new Label();
    private final Label gross = new Label();
    private final Label value = new Label();

    public DetailTableEntity(String title, String year, String score, String budget, String gross, String value) {
        this.title.setText(title);
        this.title.setWrapText(true);
        this.title.getStyleClass().add("concept-detail-title");
        this.year.setText(year);
        this.year.getStyleClass().add("concept-detail-not-value");
        this.score.setText(score);
        this.score.getStyleClass().add("concept-detail-not-value");
        this.budget.setText(budget);
        this.budget.getStyleClass().add("concept-detail-not-value");
        this.gross.setText(gross);
        this.gross.getStyleClass().add("concept-detail-not-value");
        this.value.setText(value);
        this.value.setWrapText(true);
        this.value.getStyleClass().add("concept-detail-value");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailTableEntity that = (DetailTableEntity) o;
        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getYear(), that.getYear()) && Objects.equals(getScore(), that.getScore()) && Objects.equals(getBudget(), that.getBudget()) && Objects.equals(getGross(), that.getGross()) && Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getYear(), getScore(), getBudget(), getGross(), getValue());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DetailTableEntity{");
        sb.append("title=").append(title);
        sb.append(", year=").append(year);
        sb.append(", score=").append(score);
        sb.append(", budget=").append(budget);
        sb.append(", gross=").append(gross);
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }

    public Label getTitle() {
        return title;
    }

    public Label getYear() {
        return year;
    }

    public Label getScore() {
        return score;
    }

    public Label getBudget() {
        return budget;
    }

    public Label getGross() {
        return gross;
    }

    public Label getValue() {
        return value;
    }
}
