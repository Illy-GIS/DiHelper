package ru.gis.app.entities;

import javafx.scene.text.Font;

import java.util.Objects;

public class FontEntity {
    private String fontName;
    private Font font;

    public FontEntity(String fontName, Font font) {
        this.fontName = fontName;
        this.font = font;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FontEntity that = (FontEntity) o;
        return Objects.equals(fontName, that.fontName) && Objects.equals(font, that.font);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fontName, font);
    }

    @Override
    public String toString() {
        return getFontName();
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
