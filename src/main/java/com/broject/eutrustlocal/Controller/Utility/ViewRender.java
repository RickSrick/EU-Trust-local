package com.broject.eutrustlocal.Controller.Utility;

import com.broject.eutrustlocal.Controller.SelectCountryController;
import com.broject.eutrustlocal.Controller.SelectProviderController;
import com.broject.eutrustlocal.Controller.SelectStatusesController;
import com.broject.eutrustlocal.Controller.SelectTypeServiceController;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Provider;
import com.broject.eutrustlocal.Creation.Data.Service;
import com.broject.eutrustlocal.Creation.DataArchive;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * ViewRender is an auxiliary class that has the task to convert ArrayList of CheckBox/Labels into:
 * - VBox
 * - GridPane
 * - TreeView
 *
 * @author Biscaccia Carrara Francesco
 */
public final class ViewRender {

    /**
     * Populate the gridPane GridPane of colNumber columns and rowNumber rows, with the data contained in the ArrayList.
     *
     * @param gridPane  GridPane to populate
     * @param data      CheckBox ArrayList containing the data
     * @param colNumber Number of columns
     * @param rowNumber Number of rows
     */
    public static void gridPaneFromCheckBoxes(GridPane gridPane, ArrayList<CheckBox> data, int colNumber, int rowNumber) {
        int elem = 0;
        gridPane.add(data.get(data.size() - 1), 0, 0);
        for (int i = 0; i <= colNumber & elem < data.size() - 1; i++) {
            for (int j = 1; j < rowNumber & elem < data.size() - 1; j++) {
                gridPane.add(data.get(elem++), i, j);
            }
        }
    }

    /**
     * Populate the gridPane GridPane of colNumber columns and rowNumber rows, with the data contained in the ArrayList.
     *
     * @param gridPane  GridPane to populate
     * @param data      Label ArrayList containing the data
     * @param colNumber Number of columns
     * @param rowNumber Number of rows
     */
    public static void gridPaneFromLabels(GridPane gridPane, ArrayList<Label> data, int colNumber, int rowNumber) {
        int elem = 0;
        for (int i = 0; i <= colNumber & elem < data.size(); i++) {
            for (int j = 0; j < rowNumber & elem < data.size(); j++) {
                gridPane.add(data.get(elem++), i, j);
            }
        }
    }

    /**
     * Populate the vBox VBox with the data contained in the ArrayList.
     *
     * @param vBox    GridPane to populate
     * @param data    CheckBox ArrayList containing the data
     * @param spacing Spacing between rows
     */
    public static void vBoxFromCheckBoxes(VBox vBox, ArrayList<CheckBox> data, double spacing) {
        vBox.setSpacing(spacing);
        vBox.getChildren().add(data.get(data.size() - 1));
        for (int i = 0; i < data.size() - 1; i++) {
            vBox.getChildren().add(data.get(i));
        }
    }

    /**
     * Populate the vBox VBox with the data contained in the ArrayList.
     *
     * @param vBox    VBox to populate
     * @param data    Labels ArrayList containing the data
     * @param spacing Spacing between rows
     */
    public static void historyVBoxFromLabels(VBox vBox, ArrayList<Label> data, double spacing) {
        vBox.setSpacing(spacing);
        for (Label label:data){
            vBox.getChildren().add(label);
        }
    }

    /**
     * Populate the treeView TreeView with the data contained in the ArrayList.
     *
     * @param treeView TreeView to populate
     * @param data     Provider ArrayList containing the data
     */
    public static void treeViewFromProviders(TreeView<Label> treeView, ArrayList<Provider> data) throws BadResponseException {
        TreeItem<Label> start = new TreeItem<>(new Label("ONLY FOR START"));
        for (Provider datum : data) {
            ImageView flag = new ImageView(DataArchive.newDataArchive().getCountry(datum.getCountryCode()).getFlag());
            flag.setFitHeight(25);
            flag.setFitWidth(25);
            TreeItem<Label> treeItem = new TreeItem<>(new Label(datum.getName(), flag));
            for (Service s : datum.getServices()) {
                Label status = new Label(s.getStatus() + " ");
                status.getStyleClass().add("status");
                if (s.getStatus().equalsIgnoreCase("granted")) {
                    status.getStyleClass().add("granted");
                }
                if (s.getStatus().equalsIgnoreCase("deprecatedatnationallevel")) {
                    status.getStyleClass().add("deprecated");
                }
                treeItem.getChildren().add(new TreeItem<>(new Label(s.getName(), status)));
            }
            start.getChildren().add(treeItem);
        }
        treeView.setRoot(start);
    }

    /**
     * Reset the selections of all View
     */
    public static void resetAllSelectView() {
        SelectCountryController.reset();
        SelectTypeServiceController.reset();
        SelectProviderController.reset();
        SelectStatusesController.reset();
    }

}
