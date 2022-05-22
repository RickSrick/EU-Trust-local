package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Country;
import com.broject.eutrustlocal.Creation.DataArchive;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Vector;

/**
 * @author Biscaccia Carrara Francesco
 */
public class SelectCountryView {

    private static final int GAP=20;
    private static final int COL_NUM=4;
    private static final int ROW_NUM=8;
    private final Scene selCountryView;

    public SelectCountryView(DataArchive data,Parent node, double width, double height) throws BadResponseException {
        Vector<Country> countries= data.getCountries();
        GridPane countryGrid = new GridPane();
        countryGrid.setStyle("-fx-font-family: Arial");
        countryGrid.setHgap(GAP);
        countryGrid.setVgap(GAP);
        int el=0;
        for(int i =0; i<COL_NUM & el<countries.size();i++){
            for(int j =0; j<ROW_NUM & el<countries.size();j++){
                countryGrid.add(new CheckBox(countries.get(el).getName()),i,j);
                el++;
            }
        }
        ((Pane) ((Pane) node).getChildren().get(2)).getChildren().add(countryGrid);

        selCountryView = new Scene(node,width,height);
    }

    public Scene getScene(){
        return selCountryView;
    }
}
