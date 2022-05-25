package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Country;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Main;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Vector;

/**
 * @author Biscaccia Carrara Francesco
 */
public class SelectCountryView {

    private static final int IMG_SIZE=30;
    private static final int GAP=20;
    private static final int COL_NUM=4;
    private static final int ROW_NUM=8;
    private final Scene selCountryView;
    private static SelectCountryView instance = null;

    private SelectCountryView() throws BadResponseException, IOException {

        Vector<Country> countries = DataArchive.newDataArchive().getCountries();
        Vector<CheckBox> checkBoxes = new Vector<>();
        for (Country c:countries) {
            CheckBox checkBox = new CheckBox(c.getName());
            ImageView flag = new ImageView(c.getFlag());
            flag.setFitHeight(IMG_SIZE);
            flag.setFitWidth(IMG_SIZE);
            checkBox.setGraphic(flag);
            checkBoxes.add(checkBox);
        }
        CheckBox allChecked = new CheckBox("All");
        allChecked.selectedProperty().addListener((observable, oldValue, newValue) -> {
            for (CheckBox checkBox : checkBoxes) {
                    if(allChecked.isSelected()) checkBox.setSelected(true);
                    else checkBox.setSelected(false);
                }
            }
        );
        checkBoxes.add(allChecked);



        GridPane countryGrid = new GridPane();
        countryGrid.setStyle("-fx-font-family: Arial");
        countryGrid.setHgap(GAP);
        countryGrid.setVgap(GAP);
        int elem=0;
        countryGrid.add(checkBoxes.lastElement(),0,0);
        for(int i =0; i<COL_NUM & elem<countries.size();i++){
            for(int j =1; j<ROW_NUM & elem<countries.size();j++){
                countryGrid.add(checkBoxes.get(elem),i,j);
                elem++;
            }
        }

        Parent node = XMLArchive.COUNTRY_LIST_SCENE.load();
        ObservableList<Node>  dynamicView =((Pane) ((Pane) node).getChildren().get(2)).getChildren();
        dynamicView.add(countryGrid);
        selCountryView = new Scene(node, Main.LAYOUT_WIDTH,Main.LAYOUT_HEIGHT);
        for(int i =0;i<checkBoxes.size()-1;i++){
            checkBoxes.get(i).selectedProperty().addListener((observable, oldValue, newValue) -> {
                        boolean disable=false;
                        for (CheckBox el : checkBoxes) {
                            disable=disable||el.isSelected();
                        }
                    }
            );
        }
    }

    public static SelectCountryView newSelectionCountryView() throws BadResponseException,IOException {

        if(DataArchive.checkOfflineStatus()) throw new BadResponseException();
        if (instance == null)
            instance = new SelectCountryView();

        return instance;
    }
    public Scene getScene(){
        return selCountryView;
    }
}
