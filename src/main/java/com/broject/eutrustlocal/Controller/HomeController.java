package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Controller.Utility.DataParsing;
import com.broject.eutrustlocal.Controller.Utility.ViewRender;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import com.broject.eutrustlocal.View.SelectCountryView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Biscaccia Carrara Francesco
 */
public class HomeController extends DataController {

    @FXML
    private GridPane countryGrid;

    @FXML
    private VBox serTypePane;

    @FXML
    public void initialize() throws IOException {
        try {
            ArrayList<Label> labels = new ArrayList<>();
            DataParsing.labelsFromCountries(DataArchive.newDataArchive().getCountries(), labels, QUERY, HomeView.IMG_SIZE);
            ViewRender.gridPaneFromLabels(countryGrid, labels, HomeView.COL_NUM, HomeView.ROW_NUM);

            for (String s : DataArchive.SERVICE_TYPES) {
                serTypePane.getChildren().add(new Label("• " + s));
            }
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    protected void onSearchByCriteriaClick() throws IOException {
        try {
            Main.STAGE.setScene(SelectCountryView.getInstance().getScene());
        } catch (Exception e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }
}
