package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.HomeView;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Random;

/**
 * @author Biscaccia Carrara Francesco
 */
public class ErrorController {

    private final int MAX_RAND = 4;
    private final int MIN_RAND = 1;
    @FXML
    private ImageView errorImage;

    @FXML
    protected void onRepeatButtonClick() throws IOException {
        try {
            Main.STAGE.setScene(HomeView.newHomeView().getScene());
        } catch (BadResponseException e) {
            Random rand = new Random();
            int randomNum = rand.nextInt((MAX_RAND - MIN_RAND) + 1) + MIN_RAND;
            errorImage.setImage(new Image(String.valueOf(Main.class.getResource("gif/error-" + randomNum + ".gif"))));
        }
    }
}
