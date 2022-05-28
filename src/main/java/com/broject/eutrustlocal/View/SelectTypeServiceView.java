package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Controller.SelectTypeServiceController;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;

import java.io.IOException;

public class SelectTypeServiceView extends View {

    private static SelectTypeServiceView instance = null;

    private SelectTypeServiceView() throws IOException {
        super(XMLArchive.SERVICE_TYPE_LIST_SCENE);
    }

    public static SelectTypeServiceView getInstance(boolean goFoward) throws BadResponseException, IOException {
        if (DataArchive.checkOfflineStatus()) throw new BadResponseException();
        if (instance == null)
            instance = new SelectTypeServiceView();
        else if(goFoward) update();
        return instance;
    }

    private static void update() throws BadResponseException {
        SelectTypeServiceController.update();
    }
}
