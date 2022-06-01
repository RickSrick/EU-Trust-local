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

public class ResultController extends DataController {

    @FXML
    private TreeView<Label> resultPane;
    private static TreeView<Label> dummyTreeView;

    @FXML
    private void initialize() throws IOException {
        try {
            ViewRender.treeViewFromProviders(resultPane, QUERY.getValidProviders());
            dummyTreeView = resultPane;
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    protected void onSearchByCriteriaClick() throws IOException {
        try {
            History.binWriter(QUERY.getCriteria());
            Main.STAGE.setScene(SelectCountryView.getInstance().getScene());
            ViewRender.resetAllSelectView();
            QUERY.clearAllFilters();
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    protected void onHomeButtonClick() throws IOException {
        try {
            History.binWriter(QUERY.getCriteria());
            Main.STAGE.setScene(HomeView.getInstance().getScene());
            ViewRender.resetAllSelectView();
            QUERY.clearAllFilters();
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    public void update() throws BadResponseException {
        dummyTreeView.getRoot().getChildren().clear();
        ViewRender.treeViewFromProviders(dummyTreeView, QUERY.getValidProviders());
    }
}
