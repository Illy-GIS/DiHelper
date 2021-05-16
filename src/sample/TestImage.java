package sample;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TestImage {
    @FXML
    public ImageView image;

    @FXML
    void initialize() {
        String url = "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg";
        Image imageFromUrl = new Image(url);
        image.setImage(imageFromUrl);
        image.setFitHeight(200);
        System.out.println(image);
        System.out.println(imageFromUrl.getHeight());
    }
}
