package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import com.broject.eutrustlocal.View.SelectCountryView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ResultController extends SelectController{

    private static ArrayList<Label> Labels;
    @FXML
    private VBox resultPane;
    @FXML
    private Button btnResultNewSearch;
    private static VBox aux;
    private static Button auxBtn;


    @FXML
    private void initialize() throws IOException {
        Labels =new ArrayList<>();
        try {
            initLabel(QUERY.getValidProviders(), Labels);
            initPaneLabel(resultPane, Labels);
            aux= resultPane;
            auxBtn= btnResultNewSearch;
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    public static void update() throws BadResponseException {
        initLabel(QUERY.getValidProviders(), Labels);
        aux.getChildren().clear();
        initPaneLabel(aux, Labels);
    }
    public void onSearchByCriteriaClick() throws IOException{
        try {
            Main.STAGE.setScene(SelectCountryView.getInstance().getScene());
            SelectCountryController.reset();
            SelectTypeServiceController.reset();
            SelectProviderController.reset();
            SelectStatusesController.reset();
            QUERY.clearFilters();
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
            QUERY.clearFilters();
            QUERY.clear();
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }
}
