package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Controller.Utility.BackgroundTasks.TaskCountry;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenController{

    @FXML
    private ImageView loadingImage;

    @FXML
    public void initialize(){
        RotateTransition transition = new RotateTransition(Duration.millis(750),loadingImage);
        transition.setByAngle(360);
        transition.setCycleCount(Transition.INDEFINITE);
        transition.play();
        Thread th =new Thread(new TaskCountry());
        th.setDaemon(true);
        th.start();
    }
}
