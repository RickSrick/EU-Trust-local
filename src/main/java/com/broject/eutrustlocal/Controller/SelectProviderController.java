package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class SelectProviderController extends SelectController{

    private static ArrayList<CheckBox> checkBoxes;
    @FXML
    private ScrollPane selProviderPane;
    @FXML
    private Button btnProviderForward;
    private static ScrollPane aux;
    private static Button auxBtn;


    @FXML
    private void initialize() throws IOException {
        try {
            checkBoxes=new ArrayList<>();
            initCheckBoxArray(QUERY.getValidProviders(),checkBoxes, btnProviderForward,1);
            initPane(selProviderPane,checkBoxes);
            aux=selProviderPane;
            auxBtn=btnProviderForward;
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.newErrorView().getScene());
        }
    }

    @FXML
    public static void update() throws BadResponseException {
        initCheckBoxArray(QUERY.getValidProviders(),checkBoxes, auxBtn,1);
        ((Pane)aux.getContent()).getChildren().clear();
        initPane(aux,checkBoxes);
    }
    public void onForwardButtonClick() {

    }

    public void onBackButtonClick() throws IOException {
        try {
            SelectProviderController.reset();
            Main.STAGE.setScene(HomeView.newHomeView().getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.newErrorView().getScene());
        }
    }
    @FXML
    public static void reset(){
        checkBoxes.get(checkBoxes.size() - 1).setSelected(true);
        checkBoxes.get(checkBoxes.size() - 1).setSelected(false);
    }
}
