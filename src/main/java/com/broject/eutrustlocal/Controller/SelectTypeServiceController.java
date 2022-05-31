package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Controller.Utility.DataParsing;
import com.broject.eutrustlocal.Controller.Utility.ViewRender;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectTypeServiceController extends DataController {

    private static final int COLUMNS=3;
    private static final int ROWS=7;
    private static final int FILTER_TYPE=2; //SERVICE_TYPE

    private static ArrayList<CheckBox> checkBoxes;
    @FXML
    private GridPane selServiceTypePane;
    @FXML
    private Button btnServiceTypeForward;
    private static GridPane aux;
    private static Button auxBtn;


    @FXML
    public void initialize() throws IOException {
        checkBoxes=new ArrayList<>();
        try {
            DataParsing.checkBoxesFromStrings(QUERY.getValidServiceTypes(),checkBoxes,btnServiceTypeForward,QUERY,FILTER_TYPE);
            ViewRender.gridPaneFromCheckBoxes(selServiceTypePane,checkBoxes,COLUMNS,ROWS);
            aux=selServiceTypePane;
            auxBtn=btnServiceTypeForward;
        } catch (BadResponseException e) {
                Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    public static void update() throws BadResponseException {
        DataParsing.checkBoxesFromStrings(QUERY.getValidServiceTypes(),checkBoxes,auxBtn,QUERY,FILTER_TYPE);
        aux.getChildren().clear();
        ViewRender.gridPaneFromCheckBoxes(aux,checkBoxes,COLUMNS,ROWS);
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
            QUERY.clearFilter(Query.CRITERIA_FILTERS[FILTER_TYPE]);
            Main.STAGE.setScene(SelectCountryView.getInstance().getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }
    @FXML
    public void reset() {
        reset(checkBoxes);
    }
}
