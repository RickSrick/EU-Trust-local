package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Controller.SelectTypeServiceController;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Query.Query;

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

        update();
        return instance;
    }

    private static void update(){
        SelectTypeServiceController.update();
    }
}
