package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Controller.Utility.DataParser;
import com.broject.eutrustlocal.Controller.Utility.ViewRender;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.History.History;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import com.broject.eutrustlocal.View.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class HistoryController
 *
 * @author Biscaccia Carrara Francesco
 */
public class HistoryController extends DataController {

    private static final int SPACING = 20;
    private static final int IMG_SIZE = View.IMG_SIZE / 2;
    private static ArrayList<Label> labels;
    private static VBox dummyVBox;
    @FXML
    private VBox historyPane;

    public static void update() throws FileNotFoundException {

        dummyVBox.getChildren().clear();
        DataParser.labelsFromCriteriaSheet(History.binReader(), labels, QUERY, IMG_SIZE);
        Collections.reverse(labels);
        ViewRender.historyVBoxFromLabels(dummyVBox, labels, SPACING);

    }

    @FXML
    private void initialize() throws FileNotFoundException {

        labels = new ArrayList<>();
        DataParser.labelsFromCriteriaSheet(History.binReader(), labels, QUERY, IMG_SIZE);
        Collections.reverse(labels);
        ViewRender.historyVBoxFromLabels(historyPane, labels, SPACING);
        dummyVBox = historyPane;

    }

    @FXML
    protected void onHomeButtonClick() throws IOException {

        try {
            Main.STAGE.setScene(HomeView.getInstance(true).getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }

    }

    @FXML
    protected void onDeleteHistoryClick() throws FileNotFoundException {

        historyPane.getChildren().clear();
        History.clearHistory();

    }

}
