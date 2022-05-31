package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Controller.BackgroundTasks.TaskData;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Country;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.Query.Query;
import com.broject.eutrustlocal.View.*;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Biscaccia Carrara Francesco
 */
public class SelectCountryController extends SelectController {

    private final double RATIO = 0.75;

    private static ArrayList<CheckBox> checkBoxes;
    private ArrayList<Country> countries;
    @FXML
    private Button btnCountryForward;
    @FXML
    private GridPane selCountryPane;

    @FXML
    private void initialize() throws IOException {
        checkBoxes = new ArrayList<>();
        try {
            countries = DataArchive.newDataArchive().getCountries();

            for (Country c : countries) {
                CheckBox checkBox = new CheckBox(c.getName());
                ImageView flag = new ImageView(c.getFlag());
                flag.setFitHeight(SelectCountryView.IMG_SIZE * RATIO);
                flag.setFitWidth(SelectCountryView.IMG_SIZE * RATIO);
                checkBox.setId(c.getCountryCode());
                checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                            boolean disable = false;
                            for (CheckBox el : checkBoxes) {
                                disable = disable || el.isSelected();
                            }
                            QUERY.editFilterParameter(Query.CRITERIA_FILTERS[0], checkBox.getId());
                            btnCountryForward.setDisable(!disable);
                        });
                checkBox.setGraphic(flag);
                checkBoxes.add(checkBox);
            }
            CheckBox allChecked = new CheckBox("All");
            allChecked.selectedProperty().addListener((observable, oldValue, newValue) -> {
                        for (CheckBox checkBox : checkBoxes) {
                            checkBox.setSelected(allChecked.isSelected());
                        }
                    }
            );
            checkBoxes.add(allChecked);
            populateGrid(selCountryPane, checkBoxes, View.COL_NUM, View.ROW_NUM);
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    protected void onHomeButtonClick() throws IOException {
        try {
            Main.STAGE.setScene(HomeView.getInstance().getScene());
            reset();
            SelectTypeServiceController.reset();
            SelectProviderController.reset();
            SelectStatusesController.reset();
            QUERY.clearAllFilters();
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    protected void onForwardButtonClick() throws IOException {
        try{
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

    @FXML
    public static void reset() {
        if(checkBoxes==null) return;
        checkBoxes.get(checkBoxes.size() - 1).setSelected(true);
        checkBoxes.get(checkBoxes.size() - 1).setSelected(false);
    }

}
