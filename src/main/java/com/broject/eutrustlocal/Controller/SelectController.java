package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Query.Query;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public abstract class SelectController {

    protected static final Query QUERY = new Query();

    protected static void initCheckBoxArray(ArrayList<String> data, ArrayList<CheckBox> arrayToFill, Button btnId, int filter_type) {
        arrayToFill.clear();

        for (String s : data) {
            CheckBox checkBox = new CheckBox(s);
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                boolean disable = false;
                for (CheckBox el : arrayToFill) {
                    disable = disable || el.isSelected();
                }
                QUERY.editFilterParameter(Query.CRITERIA_FILTERS[filter_type], checkBox.getId());
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

    protected static void populateGrid(GridPane pane, ArrayList<CheckBox> data, int colNumber, int rowNumber) {
        int elem = 0;
        pane.add(data.get(data.size() - 1), 0, 0);
        for (int i = 0; i <= colNumber & elem < data.size() - 1; i++) {
            for (int j = 1; j < rowNumber & elem < data.size() - 1; j++) {
                pane.add(data.get(elem++), i, j);
            }
        }
    }
    protected static void initPane(ScrollPane _pane, ArrayList<CheckBox> data) {
        VBox root = new VBox();
        root.setPrefWidth(600);
        root.setPrefHeight(700);
        root.getChildren().add(data.get(data.size()-1));
        for(int i=0;i<data.size()-1;i++){
            root.getChildren().add(data.get(i));
        }
        _pane.setContent(root);
    }
}
