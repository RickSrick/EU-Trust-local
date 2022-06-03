package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Controller.Utility.DataParser;
import com.broject.eutrustlocal.Controller.Utility.ViewRender;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.Query.Query;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import com.broject.eutrustlocal.View.SelectServiceTypeView;
import com.broject.eutrustlocal.View.SelectStatusesView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class SelectProviderController
 *
 * @author Biscaccia Carrara Francesco
 */
public class SelectProviderController extends DataController {

    private static final int SPACING = 20;
    private static final int IMG_SIZE = HomeView.IMG_SIZE / 2;
    private static final int FILTER_TYPE = 1; //SERVICE_PROVIDER
    private static ArrayList<CheckBox> checkBoxes;
    private static VBox dummyVBox;
    private static Button dummyBtn;
    @FXML
    private VBox selProviderPane;
    @FXML
    private Button btnProviderForward;

    public static void update() throws BadResponseException {

        dummyVBox.getChildren().clear();
        DataParser.checkBoxesFromProviders(QUERY.getValidProviders(), checkBoxes, dummyBtn, QUERY, FILTER_TYPE, IMG_SIZE);
        ViewRender.vBoxFromCheckBoxes(dummyVBox, checkBoxes, SPACING);

    }

    public static void reset() {

        reset(checkBoxes);

    }

    @FXML
    private void initialize() throws IOException {

        checkBoxes = new ArrayList<>();
        try {
            DataParser.checkBoxesFromProviders(QUERY.getValidProviders(), checkBoxes, btnProviderForward, QUERY, FILTER_TYPE, IMG_SIZE);
            ViewRender.vBoxFromCheckBoxes(selProviderPane, checkBoxes, SPACING);
            dummyVBox = selProviderPane;
            dummyBtn = btnProviderForward;
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }

    }

    @FXML
    protected void onForwardButtonClick() throws IOException {

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
            Main.STAGE.setScene(SelectServiceTypeView.getInstance(false).getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }

    }

}
