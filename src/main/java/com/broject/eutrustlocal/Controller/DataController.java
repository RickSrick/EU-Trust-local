package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Query.Query;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class DataController {

    protected static final Query QUERY = new Query();

    @FXML
    protected void reset(ArrayList<CheckBox> arrayToReset){
        if(arrayToReset==null) return;
        arrayToReset.get(arrayToReset.size() - 1).setSelected(true);
        arrayToReset.get(arrayToReset.size() - 1).setSelected(false);
    }
}
