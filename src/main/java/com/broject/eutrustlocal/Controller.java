package com.broject.eutrustlocal;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.SelectCountryView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller  {

    @FXML
    protected void onRepeatButtonClick(ActionEvent actionEvent){
        //Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

    }
    @FXML
    protected void onSearchByCriteriaClick(ActionEvent actionEvent) throws IOException {
        Stage mainStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader countryPageLoader = new FXMLLoader(Main.class.getResource("xml_view/countryList-view.fxml"));
        try {
            DataArchive data= DataArchive.newDataArchive();
            SelectCountryView selectCountryView = new SelectCountryView(data,countryPageLoader.load(),mainStage.getScene().getWidth(),mainStage.getScene().getHeight());
            mainStage.setScene(selectCountryView.getScene());
        } catch (BadResponseException e) {
            FXMLLoader errorPageLoader = new FXMLLoader(Main.class.getResource("xml_view/error-view.fxml"));
            ErrorView error= new ErrorView(errorPageLoader.load(),mainStage.getScene().getWidth(),mainStage.getScene().getHeight());
            mainStage.setScene(error.getScene());
        }
    }

    @FXML
    protected void onForwardButtonClick(){

    }
}