package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Country;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import com.broject.eutrustlocal.View.SelectCountryView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Vector;

/**
 * @author Biscaccia Carrara Francesco
 */
public class SelectCountryController {

    private final double RATIO=0.75;

    private Vector<CheckBox> checkBoxes;
    private Vector<Country> countries;
    @FXML
    private Button btnForward;
    @FXML
    private GridPane selCountryPane;

    @FXML
    private void initialize() throws IOException {
        checkBoxes = new Vector<>();
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
            selCountryPane.add(checkBoxes.lastElement(), 0, 0);
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
                            btnForward.setDisable(!disable);
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
            checkBoxes.lastElement().setSelected(true);
            checkBoxes.lastElement().setSelected(false);
            Main.STAGE.setScene(HomeView.newHomeView().getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.newErrorView().getScene());
        }
    }

    @FXML
    protected void onForwardButtonClick() throws IOException {
        try {
            Vector<String> countryID = new Vector<>();
            if (checkBoxes.lastElement().isSelected()) {
                for (Country c : countries) {
                    countryID.add(c.getCountryCode());
                }
            } else {
                for (int i = 0; i < checkBoxes.size() - 1; i++) {
                    if (checkBoxes.get(i).isSelected()) {
                        countryID.add(countries.get(i).getCountryCode());
                    }
                }
            }
            System.out.println(countryID);
            Main.STAGE.setScene(HomeView.newHomeView().getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.newErrorView().getScene());
        }
    }
}
