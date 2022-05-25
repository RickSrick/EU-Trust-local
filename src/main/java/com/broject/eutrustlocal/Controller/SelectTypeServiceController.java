package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.SelectCountryView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Vector;

public class SelectTypeServiceController {

    private Vector<CheckBox> checkBoxes;
    @FXML
    private GridPane selServiceTypePane;


    @FXML
    private void initialize()  {
        /*
        try {

            for (Country c : countries) {
                CheckBox checkBox = new CheckBox(c.getName());
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
            selServiceTypePane.add(checkBoxes.lastElement(), 0, 0);
            for (int i = 0; i < SelectCountryView.COL_NUM & elem < countries.size(); i++) {
                for (int j = 1; j < SelectCountryView.ROW_NUM & elem < countries.size(); j++) {
                    selServiceTypePane.add(checkBoxes.get(elem), i, j);
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

*/

    }

    public static void update () {
        //TODO: Update service type available
    }
    public void onForwardButtonClick(ActionEvent actionEvent) {
    }

    public void onBackButtonClick() throws IOException {
        try {
            Main.STAGE.setScene(SelectCountryView.newSelectionCountryView().getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.newErrorView().getScene());
        }
    }
}
