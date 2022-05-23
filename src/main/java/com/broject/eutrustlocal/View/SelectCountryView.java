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
import java.net.InetAddress;
import java.util.Vector;

/**
 * @author Biscaccia Carrara Francesco
 */
public class SelectCountryView {

    private static final int GAP=20;
    private static final int COL_NUM=4;
    private static final int ROW_NUM=8;
    private final Scene selCountryView;

    private static SelectCountryView instance = null;

    private SelectCountryView() throws BadResponseException, IOException {

        Vector<Country> countries = DataArchive.newDataArchive().getCountries();

        GridPane countryGrid = new GridPane();
        countryGrid.setStyle("-fx-font-family: Arial");
        countryGrid.setHgap(GAP);
        countryGrid.setVgap(GAP);
        int elem=0;
        for(int i =0; i<COL_NUM & elem<countries.size();i++){
            for(int j =0; j<ROW_NUM & elem<countries.size();j++){
                countryGrid.add(new ImageView(countries.get(elem).getFlag()),i,j);
                //countryGrid.add(new CheckBox(countries.get(elem).getName()),i,j);
                elem++;
            }
        }

        Parent node = XMLArchive.COUNTRY_LIST_SCENE.load();
        ObservableList<Node>  dynamicView =((Pane) ((Pane) node).getChildren().get(2)).getChildren();
        dynamicView.add(countryGrid);
        selCountryView = new Scene(node, Main.LAYOUT_WIDTH,Main.LAYOUT_HEIGHT);
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
