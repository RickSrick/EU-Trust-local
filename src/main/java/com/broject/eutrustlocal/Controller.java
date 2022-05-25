package com.broject.eutrustlocal;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import com.broject.eutrustlocal.View.SelectCountryView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class Controller  {

    private final int MIN_RAND=1;
    private final int MAX_RAND=4;

    @FXML
    protected ImageView errorImage;
    @FXML
    protected void onRepeatButtonClick(ActionEvent actionEvent) throws IOException{
        Stage mainStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try {
            mainStage.setScene(HomeView.newHomeView().getScene());
        } catch (BadResponseException e) {
            Random rand = new Random();
            int randomNum = rand.nextInt((MAX_RAND -MIN_RAND) + 1) + MIN_RAND;
            errorImage.setImage(new Image(String.valueOf(Main.class.getResource("gif/error-"+randomNum+".gif"))));
        }
    }
    @FXML
    protected void onSearchByCriteriaClick(ActionEvent actionEvent) throws IOException {
        Stage mainStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try {
            mainStage.setScene(SelectCountryView.newSelectionCountryView().getScene());
        } catch (BadResponseException e) {
            mainStage.setScene(ErrorView.newErrorView().getScene());
        }
    }

    @FXML
    protected void onForwardButtonClick(){

    }

    @FXML
    protected void onHomeButtonClick(ActionEvent actionEvent) throws IOException {
        Stage mainStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try {
            mainStage.setScene(HomeView.newHomeView().getScene());
        } catch (BadResponseException e) {
            mainStage.setScene(ErrorView.newErrorView().getScene());
        }
    }
}