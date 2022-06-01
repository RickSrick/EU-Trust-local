package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Controller.DataController;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.Query.Query;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import com.broject.eutrustlocal.View.SelectTypeServiceView;
import javafx.fxml.FXML;

import java.io.IOException;

public class HistoryController extends DataController {
    @FXML
    protected void onHomeButtonClick() throws IOException {
        try {
            QUERY.clearAllFilters();
            Main.STAGE.setScene(HomeView.getInstance().getScene());
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }
}
