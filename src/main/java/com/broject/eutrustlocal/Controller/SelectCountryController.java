package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Country;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.Query.Query;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import com.broject.eutrustlocal.View.SelectCountryView;
import com.broject.eutrustlocal.View.SelectTypeServiceView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;

/**
 * @author Biscaccia Carrara Francesco
 */
public class SelectCountryController {

    private final double RATIO=0.75;

    private final Query mainQuery = new Query();

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
                flag.setFitHeight(SelectCountryView.IMG_SIZE*RATIO);
                flag.setFitWidth(SelectCountryView.IMG_SIZE*RATIO);
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

            int elem = 0;
            selCountryPane.add(checkBoxes.get(checkBoxes.size() - 1), 0, 0);
            for (int i = 0; i < SelectCountryView.COL_NUM & elem < countries.size(); i++) {
                for (int j = 1; j < SelectCountryView.ROW_NUM & elem < countries.size(); j++) {
                    selCountryPane.add(checkBoxes.get(elem), i, j);
                    elem++;
                }
            }

            for (int i = 0; i < checkBoxes.size() - 1; i++) {
                checkBoxes.get(i).selectedProperty().addListener((observable, oldValue, newValue) -> {
                            boolean disable = false;
                            for (CheckBox el : checkBoxes) {
                                disable = disable || el.isSelected();
                            }
                            btnCountryForward.setDisable(!disable);
                        }
                );
            }
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.newErrorView().getScene());
        }
    }

    @FXML
    protected void onHomeButtonClick() throws IOException {
        try {
            reset();
            Main.STAGE.setScene(HomeView.newHomeView().getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.newErrorView().getScene());
        }
    }

    @FXML
    protected void onForwardButtonClick() throws IOException {
        try {
            ArrayList<String> countryCodes = new ArrayList<>();
            if (checkBoxes.get(checkBoxes.size() - 1).isSelected()) {
                for (Country c : countries) {
                    countryCodes.add(c.getCountryCode());
                }
            } else {
                for (int i = 0; i < checkBoxes.size() - 1; i++) {
                    if (checkBoxes.get(i).isSelected()) {
                        countryCodes.add(countries.get(i).getCountryCode());
                    }
                }
            }
            mainQuery.editFilterParameter(Query.CRITERIA_FILTERS[0],countryCodes);
            Main.STAGE.setScene(SelectTypeServiceView.newSelectTypeServiceView(mainQuery).getScene());
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
