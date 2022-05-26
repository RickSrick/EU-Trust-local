package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Controller.SelectTypeServiceController;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Query.Query;
import javafx.scene.Parent;

import java.io.IOException;

public class SelectTypeServiceView extends View {

    private static SelectTypeServiceView instance = null;

    private SelectTypeServiceView() throws IOException {
        super(XMLArchive.SERVICE_TYPE_LIST_SCENE);
    }

    public static SelectTypeServiceView newSelectTypeServiceView() throws BadResponseException, IOException {

        if (DataArchive.checkOfflineStatus()) throw new BadResponseException();
        if (instance == null)
            instance = new SelectTypeServiceView();
        else update();
        return instance;
    }

    private static void update() throws BadResponseException {
        SelectTypeServiceController.update();
    }
}
