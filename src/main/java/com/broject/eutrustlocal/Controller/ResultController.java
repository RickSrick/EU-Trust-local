package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Controller.Utility.ViewRender;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.History.History;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import com.broject.eutrustlocal.View.SelectCountryView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResultController extends DataController {

    @FXML
    private TreeView<Label> resultPane;
    private static TreeView<Label> aux;

    @FXML
    public void initialize() throws IOException {
        try {
            ViewRender.treeViewFromProviders(resultPane, QUERY.getValidProviders());
            aux = resultPane;
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    public static void update() throws BadResponseException {
        aux.getRoot().getChildren().clear();
        ViewRender.treeViewFromProviders(aux, QUERY.getValidProviders());
    }

    public void onSearchByCriteriaClick() throws IOException {
        try {
            History.binWriter(QUERY.getCriteria());
            Main.STAGE.setScene(SelectCountryView.getInstance().getScene());
            ViewRender.resetAllSelectView();
            QUERY.clearAllFilters();
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    public void onHomeButtonClick() throws IOException {
        try {
            History.binWriter(QUERY.getCriteria());
            Main.STAGE.setScene(HomeView.getInstance().getScene());
            ViewRender.resetAllSelectView();
            QUERY.clearAllFilters();
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }
}
