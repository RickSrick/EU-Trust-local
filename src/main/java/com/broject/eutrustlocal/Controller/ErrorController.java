package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Controller.Utility.ViewRender;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.HomeView;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

/**
 * @author Biscaccia Carrara Francesco
 */
public class ErrorController {

    private final int MAX_RAND = 4;
    private final int MIN_RAND = 1;
    private final Random rand = new Random(System.currentTimeMillis());

    @FXML
    private ImageView errorImage;
    @FXML
    private ImageView repeatImg;

    @FXML
    private void initialize(){
        int randomNum = rand.nextInt((MAX_RAND - MIN_RAND) + 1) + MIN_RAND;
        errorImage.setImage(new Image(String.valueOf(Main.class.getResource("gif/error-" + randomNum + ".gif"))));

    }
    @FXML
    protected void onRepeatButtonClick() {
        RotateTransition transition = new RotateTransition(Duration.millis(600),repeatImg);
        transition.setByAngle(360);
        transition.setOnFinished(actionEvent -> {
            try {
                Main.STAGE.setScene(HomeView.getInstance(true).getScene());
                ViewRender.resetAllSelectView();
            } catch (BadResponseException e) {
                int randomNum = rand.nextInt((MAX_RAND - MIN_RAND) + 1) + MIN_RAND;
                errorImage.setImage(new Image(String.valueOf(Main.class.getResource("gif/error-" + randomNum + ".gif"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        transition.play();
    }
}
