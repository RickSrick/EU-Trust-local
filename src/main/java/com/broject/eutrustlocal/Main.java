package com.broject.eutrustlocal;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Biscaccia Carrara Francesco
 */
public class Main extends Application {

    private final int WIDTH=1080;
    private final int HEIGHT=720;
    private final boolean RESIZE_FLAG=false;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            final DataArchive data = DataArchive.newDataArchive();
            FXMLLoader homeLoader = new FXMLLoader(Main.class.getResource("xml_view/home-view.fxml"));
            HomeView home= new HomeView(data,homeLoader.load(),WIDTH,HEIGHT);
            stage.setResizable(RESIZE_FLAG);
            stage.setTitle("EU Trust Local");
            stage.setScene(home.getScene());
        } catch (BadResponseException e) {
            FXMLLoader errorPageLoader = new FXMLLoader(Main.class.getResource("xml_view/error-view.fxml"));
            ErrorView error= new ErrorView(errorPageLoader.load(),WIDTH,HEIGHT);
            stage.setScene(error.getScene());
        }
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}