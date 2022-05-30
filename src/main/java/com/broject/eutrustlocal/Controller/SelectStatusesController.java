package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.Query.Query;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.ResultView;
import com.broject.eutrustlocal.View.SelectProviderView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

public class SelectStatusesController extends SelectController{

    private static final int COLUMNS=1;
    private static final int ROWS=5;

    private static ArrayList<CheckBox> checkBoxes;
    @FXML
    private GridPane selStatusesPane;
    @FXML
    private Button btnFinishQueryForward;
    private static GridPane aux;
    private static Button auxBtn;


    @FXML
    private void initialize() throws IOException {
        checkBoxes=new ArrayList<>();
        try {
            initCheckBoxArrayString(QUERY.getValidServiceStatuses(), checkBoxes, btnFinishQueryForward,3);
            populateGrid(selStatusesPane,checkBoxes,COLUMNS,ROWS);
            aux= selStatusesPane;
            auxBtn= btnFinishQueryForward;
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    public static void update() throws BadResponseException {
        initCheckBoxArrayString(QUERY.getValidServiceStatuses(), checkBoxes,auxBtn,3);
        aux.getChildren().clear();
        populateGrid(aux,checkBoxes,COLUMNS,ROWS);
    }
    public void onForwardButtonClick() throws IOException {
        try {
            Main.STAGE.setScene(ResultView.getInstance(true).getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    public void onBackButtonClick() throws IOException {
        try {
            QUERY.clearFilter(Query.CRITERIA_FILTERS[3]);
            Main.STAGE.setScene(SelectProviderView.getInstance(false).getScene());
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
