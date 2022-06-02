package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Query.Query;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;

/**
 * @author Biscaccia Carrara Francesco
 */
public abstract class DataController {

    protected static final Query QUERY = new Query();

    protected static void reset(ArrayList<CheckBox> arrayToReset) {
        if (arrayToReset == null) return;
        arrayToReset.get(arrayToReset.size() - 1).setSelected(true);
        arrayToReset.get(arrayToReset.size() - 1).setSelected(false);
    }
}
