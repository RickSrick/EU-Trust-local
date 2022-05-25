package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Country;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import com.broject.eutrustlocal.View.SelectCountryView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Vector;

/**
 * @author Biscaccia Carrara Francesco
 */
public class HomeController {

    @FXML
    private GridPane countryGrid;

    @FXML
    private VBox serTypePane;

    @FXML
    protected void initialize() throws IOException {
        Vector<Country> countries;
        try {
            countries = DataArchive.newDataArchive().getCountries();
            int elem = 0;
            for (int i = 0; i < HomeView.COL_NUM & elem < countries.size(); i++) {
                for (int j = 0; j < HomeView.ROW_NUM & elem < countries.size(); j++) {
                    ImageView flag = new ImageView(countries.get(elem).getFlag());
                    flag.setFitHeight(HomeView.IMG_SIZE);
                    flag.setFitWidth(HomeView.IMG_SIZE);
                    countryGrid.add(new Label(countries.get(elem).getName(), flag), i, j);
                    elem++;
                }
            }

            for (String s : DataArchive.SERVICE_TYPES) {
                serTypePane.getChildren().add(new Label("• " + s));
            }

        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.newErrorView().getScene());
        }
    }

    @FXML
    protected void onSearchByCriteriaClick() throws IOException {
        try {
            Main.STAGE.setScene(SelectCountryView.newSelectionCountryView().getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.newErrorView().getScene());
        }
    }
}
