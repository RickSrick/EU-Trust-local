package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.SelectStatusesView;
import com.broject.eutrustlocal.View.SelectTypeServiceView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class SelectProviderController extends SelectController{

    private static ArrayList<CheckBox> checkBoxes;
    @FXML
    private VBox selProviderPane;
    @FXML
    private Button btnProviderForward;
    private static VBox aux;
    private static Button auxBtn;


    @FXML
    private void initialize() throws IOException {
        checkBoxes=new ArrayList<>();
        try {
            initCheckBoxArray(QUERY.getValidProviders(),checkBoxes,btnProviderForward,1);
            initPaneCheckBoxes(selProviderPane,checkBoxes);
            aux=selProviderPane;
            auxBtn=btnProviderForward;
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    public static void update() throws BadResponseException {
        initCheckBoxArray(QUERY.getValidProviders(),checkBoxes, auxBtn,1);
        aux.getChildren().clear();
        initPaneCheckBoxes(aux,checkBoxes);
    }
    public void onForwardButtonClick() throws IOException{
        try {
            Main.STAGE.setScene(SelectStatusesView.getInstance(true).getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    public void onBackButtonClick() throws IOException {
        try {
            Main.STAGE.setScene(SelectTypeServiceView.getInstance(false).getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }
    @FXML
    public static void reset(){
        if(checkBoxes==null) return;
        checkBoxes.get(checkBoxes.size() - 1).setSelected(true);
        checkBoxes.get(checkBoxes.size() - 1).setSelected(false);
    }
}
