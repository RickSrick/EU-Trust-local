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

public final class ViewRender {

    public static void gridPaneFromCheckBoxes(GridPane pane, ArrayList<CheckBox> data, int colNumber, int rowNumber) {
        int elem = 0;
        pane.add(data.get(data.size() - 1), 0, 0);
        for (int i = 0; i <= colNumber & elem < data.size() - 1; i++) {
            for (int j = 1; j < rowNumber & elem < data.size() - 1; j++) {
                pane.add(data.get(elem++), i, j);
            }
        }
    }
    public static void gridPaneFromLabels(GridPane pane, ArrayList<Label> data, int colNumber, int rowNumber) {
        int elem = 0;
        for (int i = 0; i <= colNumber & elem < data.size(); i++) {
            for (int j = 0; j < rowNumber & elem < data.size(); j++) {
                pane.add(data.get(elem++), i, j);
            }
        }
    }

    public static void vBoxFromCheckBoxes(VBox vBox, ArrayList<CheckBox> data,double spacing) {
        vBox.setSpacing(spacing);
        vBox.getChildren().add(data.get(data.size()-1));
        for(int i=0;i<data.size()-1;i++){
            vBox.getChildren().add(data.get(i));
        }
    }

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

    public static void resetAllSelectView(){
        new SelectCountryController().reset();
        new SelectTypeServiceController().reset();
        new SelectProviderController().reset();
        new SelectStatusesController().reset();
    }

}
