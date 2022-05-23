package com.broject.eutrustlocal;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Biscaccia Carrara Francesco
 */
public class Main extends Application {

    public static final int LAYOUT_WIDTH =1080;
    public static final int LAYOUT_HEIGHT =720;

    @Override
    public void start(Stage stage) throws IOException {

        try {
            boolean RESIZE_FLAG = false;
            stage.setResizable(RESIZE_FLAG);
            stage.setTitle("EU Trust Local");
            Image icon = new Image(String.valueOf(Main.class.getResource("img/stage-icon.png")));
            stage.getIcons().add(icon);
            stage.setScene(HomeView.newHomeView().getScene());
        } catch (BadResponseException e) {
            stage.setScene(ErrorView.newErrorView().getScene());
        }
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}