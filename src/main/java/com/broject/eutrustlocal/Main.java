package com.broject.eutrustlocal;

import com.broject.eutrustlocal.History.History;
import com.broject.eutrustlocal.View.SplashScreenView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class Main
 *
 * @author Biscaccia Carrara Francesco
 */
public class Main extends Application {

    public static final int LAYOUT_WIDTH = 1080;
    public static final int LAYOUT_HEIGHT = 720;

    public static Stage STAGE = null;

    @Override
    public void start(Stage stage) throws IOException {

        History.newHistory();
        boolean RESIZE_FLAG = false;
        STAGE = stage;
        stage.setResizable(RESIZE_FLAG);
        stage.setTitle("EU Trust Local");
        stage.getIcons().add(new Image(String.valueOf(Main.class.getResource("img/stage-icon.png"))));
        stage.setScene(SplashScreenView.getInstance().getScene());
        stage.show();

    }

    public static void main(String[] args) {

        launch();

    }

}