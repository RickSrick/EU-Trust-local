package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import com.broject.eutrustlocal.View.SelectCountryView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeView;

import java.io.IOException;

public class ResultController extends SelectController{

    @FXML
    private TreeView<Label> resultPane;
    @FXML
    private Button btnResultNewSearch;
    private static TreeView aux;
    private static Button auxBtn;


    @FXML
    private void initialize() throws IOException {
        try {
            initPaneLabel(resultPane, QUERY.getValidProviders());
            aux= resultPane;
            auxBtn= btnResultNewSearch;
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    public static void update() throws BadResponseException {
        aux.getRoot().getChildren().clear();
        initPaneLabel(aux, QUERY.getValidProviders());
    }
    public void onSearchByCriteriaClick() throws IOException{
        try {
            Main.STAGE.setScene(SelectCountryView.getInstance().getScene());
            SelectCountryController.reset();
            SelectTypeServiceController.reset();
            SelectProviderController.reset();
            SelectStatusesController.reset();
            QUERY.clearAllFilters();
            QUERY.clear();
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    public void onHomeButtonClick() throws IOException {
        try {
            Main.STAGE.setScene(HomeView.getInstance().getScene());
            SelectCountryController.reset();
            SelectTypeServiceController.reset();
            SelectProviderController.reset();
            SelectStatusesController.reset();
            QUERY.clearAllFilters();
            QUERY.clear();
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }
}
