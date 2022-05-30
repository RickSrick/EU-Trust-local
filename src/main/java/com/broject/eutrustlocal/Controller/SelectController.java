package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Provider;
import com.broject.eutrustlocal.Creation.Data.Service;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Query.Query;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public abstract class SelectController {

    protected static final Query QUERY = new Query();

    protected static void initCheckBoxArrayString(ArrayList<String> data, ArrayList<CheckBox> arrayToFill, Button btnId, int filter_type) {
        arrayToFill.clear();

        for (String s : data) {
            CheckBox checkBox = new CheckBox(s);
            checkBox.setId(s);
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

        if(arrayToFill.size()!=1) {
            CheckBox allChecked = new CheckBox("All");
            allChecked.selectedProperty().addListener((observable, oldValue, newValue) -> {
                        for (CheckBox checkBox : arrayToFill) {
                            checkBox.setSelected(allChecked.isSelected());
                        }
                    }
            );
            arrayToFill.add(allChecked);
        }
    }

    protected static void initCheckBoxArrayProvider(ArrayList<Provider> data, ArrayList<CheckBox> arrayToFill, Button btnId, int filter_type) throws BadResponseException {
        arrayToFill.clear();

        for (Provider s : data) {
            CheckBox checkBox = new CheckBox(s.getName());
            checkBox.setId(s.getCountryCode()+"/"+s.getName());
            ImageView flag= new ImageView(DataArchive.newDataArchive().getCountry(s.getCountryCode()).getFlag());
            flag.setFitHeight(20);
            flag.setFitWidth(20);
            checkBox.setGraphic(flag);
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

        if(arrayToFill.size()!=1) {
            CheckBox allChecked = new CheckBox("All");
            allChecked.selectedProperty().addListener((observable, oldValue, newValue) -> {
                        for (CheckBox checkBox : arrayToFill) {
                            checkBox.setSelected(allChecked.isSelected());
                        }
                    }
            );
            arrayToFill.add(allChecked);
        }
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
    protected static void initPaneCheckBoxes(VBox vBox, ArrayList<CheckBox> data) {
        vBox.setSpacing(25);
        vBox.getChildren().add(data.get(data.size()-1));
        for(int i=0;i<data.size()-1;i++){
            vBox.getChildren().add(data.get(i));
        }
    }

    protected static void initPaneLabel(TreeView<Label> treeView, ArrayList<Provider> data) throws BadResponseException {
        TreeItem<Label> start = new TreeItem<>(new Label("ONLY FOR START"));
        for(int i=0;i<data.size();i++){
            ImageView flag=new ImageView(DataArchive.newDataArchive().getCountry(data.get(i).getCountryCode()).getFlag());
            flag.setFitHeight(25);
            flag.setFitWidth(25);
            TreeItem<Label> treeItem = new TreeItem<>(new Label(data.get(i).getName(),flag));
            for (Service s: data.get(i).getServices()) {
                Label status = new Label(s.getStatus()+" ");
                status.setStyle("-fx-text-fill:white;-fx-background-color:black;-fx-background-radius:0.2em;");
                if(s.getStatus().equalsIgnoreCase("granted")){
                    status.setStyle("-fx-text-fill:white;-fx-background-color:green;-fx-background-radius:0.2em");
                }
                if(s.getStatus().equalsIgnoreCase("deprecatedatnationallevel")){
                    status.setStyle("-fx-text-fill:white;-fx-background-color:firebrick;-fx-background-radius:0.2em");
                }
                treeItem.getChildren().add(new TreeItem<>(new Label(s.getName(),status)));
            }
            start.getChildren().add(treeItem);
        }
        treeView.setRoot(start);
    }
}
