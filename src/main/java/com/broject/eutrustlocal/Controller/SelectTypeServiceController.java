package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.Query.Query;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.SelectCountryView;
import com.broject.eutrustlocal.View.SelectProviderView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

public class SelectTypeServiceController extends SelectController{

    private static final int COLUMNS=3;
    private static final int ROWS=7;

    private static ArrayList<CheckBox> checkBoxes;
    @FXML
    private GridPane selServiceTypePane;
    @FXML
    private Button btnServiceTypeForward;
    private static GridPane aux;
    private static Button auxBtn;


    @FXML
    private void initialize() throws IOException {
        checkBoxes=new ArrayList<>();
        try {
            initCheckBoxArrayString(QUERY.getValidServiceTypes(), checkBoxes,btnServiceTypeForward,2);
            populateGrid(selServiceTypePane,checkBoxes,COLUMNS,ROWS);
            aux=selServiceTypePane;
            auxBtn=btnServiceTypeForward;
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    public static void update() throws BadResponseException {
        initCheckBoxArrayString(QUERY.getValidServiceTypes(), checkBoxes,auxBtn,2);
        aux.getChildren().clear();
        populateGrid(aux,checkBoxes,COLUMNS,ROWS);
    }
    public void onForwardButtonClick() throws IOException {
        try {
            Main.STAGE.setScene(SelectProviderView.getInstance(true).getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    public void onBackButtonClick() throws IOException {
        try {
            QUERY.clearFilter(Query.CRITERIA_FILTERS[2]);
            Main.STAGE.setScene(SelectCountryView.getInstance().getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }
    @FXML
    public static void reset(){
        if(checkBoxes==null) return;
        checkBoxes.get(checkBoxes.size() - 1).setSelected(true);
        checkBoxes.get(checkBoxes.size() - 1).setSelected(false);
    }
}
