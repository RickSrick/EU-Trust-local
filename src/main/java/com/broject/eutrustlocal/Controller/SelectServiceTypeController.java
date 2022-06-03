package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Controller.Utility.DataParser;
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
import java.util.ArrayList;

/**
 * @author Biscaccia Carrara Francesco
 */
public class SelectServiceTypeController extends DataController {

    private static final int COLUMNS = 3;
    private static final int ROWS = 7;
    private static final int FILTER_TYPE = 2; //SERVICE_TYPE

    private static ArrayList<CheckBox> checkBoxes;
    private static GridPane dummyGridPane;
    private static Button dummyBtn;
    @FXML
    private GridPane selServiceTypePane;
    @FXML
    private Button btnServiceTypeForward;



    @FXML
    private void initialize() throws IOException {
        checkBoxes = new ArrayList<>();
        try {
            DataParser.checkBoxesFromStrings(QUERY.getValidServiceTypes(), checkBoxes, btnServiceTypeForward, QUERY, FILTER_TYPE);
            ViewRender.gridPaneFromCheckBoxes(selServiceTypePane, checkBoxes, COLUMNS, ROWS);
            dummyGridPane = selServiceTypePane;
            dummyBtn = btnServiceTypeForward;
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    protected void onForwardButtonClick() throws IOException {
        try {
            Main.STAGE.setScene(SelectProviderView.getInstance(true).getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        try {
            QUERY.clearFilter(Query.CRITERIA_FILTERS[FILTER_TYPE]);
            Main.STAGE.setScene(SelectCountryView.getInstance().getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }
    public static void update() throws BadResponseException {
        DataParser.checkBoxesFromStrings(QUERY.getValidServiceTypes(), checkBoxes, dummyBtn, QUERY, FILTER_TYPE);
        dummyGridPane.getChildren().clear();
        ViewRender.gridPaneFromCheckBoxes(dummyGridPane, checkBoxes, COLUMNS, ROWS);
    }

    public static void reset() {
        reset(checkBoxes);
    }
}
