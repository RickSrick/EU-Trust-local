package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Controller.Utility.DataParsing;
import com.broject.eutrustlocal.Controller.Utility.ViewRender;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.Query.Query;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import com.broject.eutrustlocal.View.SelectStatusesView;
import com.broject.eutrustlocal.View.SelectTypeServiceView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectProviderController extends DataController {

    private static final int SPACING=20;
    private static final int IMG_SIZE= HomeView.IMG_SIZE/2;
    private static final int FILTER_TYPE=1; //SERVICE_PROVIDER
    private static ArrayList<CheckBox> checkBoxes;
    @FXML
    public VBox selProviderPane;
    @FXML
    private Button btnProviderForward;
    private static VBox aux;
    private static Button auxBtn;


    @FXML
    private void initialize() throws IOException {
        checkBoxes=new ArrayList<>();
        try {
            DataParsing.checkBoxesFromProviders(QUERY.getValidProviders(),checkBoxes,btnProviderForward,QUERY,FILTER_TYPE,IMG_SIZE);
            ViewRender.vBoxFromCheckBoxes(selProviderPane, checkBoxes,SPACING);
            aux = selProviderPane;
            auxBtn = btnProviderForward;
        }catch (BadResponseException e){
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    public void update() throws BadResponseException {
        DataParsing.checkBoxesFromProviders(QUERY.getValidProviders(),checkBoxes,auxBtn,QUERY,FILTER_TYPE,IMG_SIZE);
        aux.getChildren().clear();
        ViewRender.vBoxFromCheckBoxes(aux, checkBoxes,SPACING);
    }
    @FXML
    public void onForwardButtonClick() throws IOException{
        try {
            Main.STAGE.setScene(SelectStatusesView.getInstance(true).getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }
    @FXML
    protected void onBackButtonClick() throws IOException {
        try {
            QUERY.clearFilter(Query.CRITERIA_FILTERS[FILTER_TYPE]);
            Main.STAGE.setScene(SelectTypeServiceView.getInstance(false).getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }
    @FXML
    public void reset() {
        reset(checkBoxes);
    }
}
