package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Controller.Utility.DataParsing;
import com.broject.eutrustlocal.Controller.Utility.ViewRender;
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

public class SelectStatusesController extends DataController {

    private static final int COLUMNS = 1;
    private static final int ROWS = 5;
    private static final int FILTER_TYPE = 3; //SERVICE_STATUS

    private static ArrayList<CheckBox> checkBoxes;
    @FXML
    private GridPane selStatusesPane;
    @FXML
    private Button btnFinishQueryForward;
    private static GridPane aux;
    private static Button auxBtn;


    @FXML
    private void initialize() throws IOException {
        checkBoxes = new ArrayList<>();
        try {
            DataParsing.checkBoxesFromStrings(QUERY.getValidServiceStatuses(), checkBoxes, btnFinishQueryForward, QUERY, FILTER_TYPE);
            ViewRender.gridPaneFromCheckBoxes(selStatusesPane, checkBoxes, COLUMNS, ROWS);
            aux = selStatusesPane;
            auxBtn = btnFinishQueryForward;
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }


    @FXML
    protected void onForwardButtonClick() throws IOException {
        try {
            Main.STAGE.setScene(ResultView.getInstance(true).getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        try {
            QUERY.clearFilter(Query.CRITERIA_FILTERS[FILTER_TYPE]);
            Main.STAGE.setScene(SelectProviderView.getInstance(false).getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    public static void update() throws BadResponseException {
        DataParsing.checkBoxesFromStrings(QUERY.getValidServiceStatuses(), checkBoxes, auxBtn, QUERY, FILTER_TYPE);
        aux.getChildren().clear();
        ViewRender.gridPaneFromCheckBoxes(aux, checkBoxes, COLUMNS, ROWS);
    }

    public void reset() {
        reset(checkBoxes);
    }
}
