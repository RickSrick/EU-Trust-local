package com.broject.eutrustlocal;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import com.broject.eutrustlocal.View.SelectCountryView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller  {

    @FXML
    protected void onRepeatButtonClick(ActionEvent actionEvent) throws IOException{
        Stage mainStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try {
            mainStage.setScene(HomeView.newHomeView().getScene());
        } catch (BadResponseException e) {
            System.out.println("A bit stupid!");
            //TODO: Change image on error view
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