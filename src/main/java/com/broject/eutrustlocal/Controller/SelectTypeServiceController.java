package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.SelectCountryView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class SelectTypeServiceController extends SelectController{

    private ArrayList<CheckBox> checkBoxes;
    @FXML
    private GridPane selServiceTypePane;

    @FXML
    private Button btnSerTypeForward;


    @FXML
    private void initialize()  {
        try {
            checkBoxes = new ArrayList<>();
            System.out.println(getQuery().getValidServiceTypes());
            //initCheckBoxArray(getQuery().getValidServiceTypes(),checkBoxes,btnSerTypeForward);
            //populateGrid(selServiceTypePane,checkBoxes);
        } catch (BadResponseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update () {
        //TODO: Update service type available
    }
    public void onForwardButtonClick(ActionEvent actionEvent) {
    }

    public void onBackButtonClick() throws IOException {
        try {
            Main.STAGE.setScene(SelectCountryView.newSelectionCountryView().getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.newErrorView().getScene());
        }
    }
}
