package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.SelectCountryView;
import com.broject.eutrustlocal.View.SelectTypeServiceView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class SelectTypeServiceController extends SelectController{

    private static final int COLUMNS=3;
    private static final int ROWS=7;

    private static ArrayList<CheckBox> checkBoxes;

    private static GridPane aux;
    private static Button auxBtn;
    @FXML
    private GridPane selServiceTypePane;
    @FXML
    private Button btnServiceTypeForward;


    @FXML
    private void initialize() throws IOException {
        checkBoxes= new ArrayList<>();
        try {
            initCheckBoxArray(query.getValidServiceTypes(),checkBoxes, btnServiceTypeForward);
            populateGrid(selServiceTypePane,checkBoxes,COLUMNS,ROWS);
            aux=selServiceTypePane;
            auxBtn=btnServiceTypeForward;
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.newErrorView().getScene());
        }
    }

    @FXML
    public static void update () throws BadResponseException {
        initCheckBoxArray(query.getValidServiceTypes(),checkBoxes, auxBtn);
        aux.getChildren().clear();
        populateGrid(aux,checkBoxes,COLUMNS,ROWS);
    }
    public void onForwardButtonClick(ActionEvent actionEvent) {
    }

    public void onBackButtonClick() throws IOException {
        try {
            SelectTypeServiceController.reset();
            Main.STAGE.setScene(SelectCountryView.newSelectionCountryView().getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.newErrorView().getScene());
        }
    }
    @FXML
    public static void reset(){
        checkBoxes.get(checkBoxes.size() - 1).setSelected(true);
        checkBoxes.get(checkBoxes.size() - 1).setSelected(false);
    }
}
