package pt.nomorealex.medmanagement.ui.resources;

import javafx.scene.image.Image;

import java.io.InputStream;

public class ImageManager {
    private ImageManager() { }
    public static Image loadImage(String filename) {
        Image image = null;
        try(InputStream inputStreamImage =
                    ImageManager.class.getResourceAsStream("images/"+filename)) {
            image = new Image(inputStreamImage);
        } catch (Exception e) {return null;}
        return image;
    }
}
