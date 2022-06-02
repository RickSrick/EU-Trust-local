package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Controller.Utility.BackgroundTasks.TaskData;
import com.broject.eutrustlocal.Controller.Utility.DataParser;
import com.broject.eutrustlocal.Controller.Utility.ViewRender;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Biscaccia Carrara Francesco
 */
public class SelectCountryController extends DataController {

    private final double RATIO = 0.75;

    private static ArrayList<CheckBox> checkBoxes;
    @FXML
    private Button btnCountryForward;
    @FXML
    private GridPane selCountryPane;

    @FXML
    private void initialize() throws IOException {
        try {
            checkBoxes = new ArrayList<>();
            DataParser.checkBoxesFromCountries(DataArchive.newDataArchive().getCountries(), checkBoxes, btnCountryForward, QUERY, SelectCountryView.IMG_SIZE * RATIO);
            ViewRender.gridPaneFromCheckBoxes(selCountryPane, checkBoxes, View.COL_NUM, View.ROW_NUM);
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    protected void onHomeButtonClick() throws IOException {
        try {
            Main.STAGE.setScene(HomeView.getInstance(false).getScene());
            ViewRender.resetAllSelectView();
            QUERY.clearAllFilters();
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    protected void onForwardButtonClick() throws IOException {
        try {
            Thread th = new Thread(new TaskData(QUERY));
            th.setDaemon(true);
            th.start();
            th.join();
            Main.STAGE.setScene(SelectTypeServiceView.getInstance(true).getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void reset() {
        reset(checkBoxes);
    }
}
