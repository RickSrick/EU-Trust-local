package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Country;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Query.Query;
import com.broject.eutrustlocal.View.SelectCountryView;
import com.broject.eutrustlocal.View.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public abstract class SelectController {

    protected static final Query query = new Query();
    protected static void reset(){};

    protected static void update() throws BadResponseException {};

    protected static void initCheckBoxArray(ArrayList<String> data,ArrayList<CheckBox> arrayToFill, Button btnId){
        for(String s: data){
            CheckBox checkBox = new CheckBox(s);
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                boolean disable = false;
                for (CheckBox el : arrayToFill) {
                    disable = disable || el.isSelected();
                }
                btnId.setDisable(!disable);
            });
            arrayToFill.add(checkBox);
        }

        CheckBox allChecked = new CheckBox("All");
        allChecked.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    for (CheckBox checkBox : arrayToFill) {
                        checkBox.setSelected(allChecked.isSelected());
                    }
                }
        );
        arrayToFill.add(allChecked);
    }

    protected static void populateGrid(GridPane pane, ArrayList<CheckBox> data,int colNumber,int rowNumber){
        int elem=0;
        pane.add(data.get(data.size() - 1), 0, 0);
        for (int i = 0; i <= colNumber & elem< data.size()-1; i++) {
            for (int j = 1; j < rowNumber & elem< data.size()-1; j++) {
                pane.add(data.get(elem++), i, j);
            }
        }
    }
}
