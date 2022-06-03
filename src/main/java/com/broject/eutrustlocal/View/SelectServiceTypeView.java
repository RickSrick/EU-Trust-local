package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Controller.SelectServiceTypeController;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;

import java.io.IOException;

public class SelectServiceTypeView extends View {

    private static SelectServiceTypeView instance = null;

    private SelectServiceTypeView() throws IOException {
        super(FXMLArchive.SERVICE_TYPE_LIST_SCENE);
    }

    public static SelectServiceTypeView getInstance(boolean reload) throws BadResponseException, IOException {
        if (DataArchive.checkOfflineStatus()) throw new BadResponseException();
        if (instance == null)
            instance = new SelectServiceTypeView();
        else if (reload) update();
        return instance;
    }

    private static void update() throws BadResponseException {
        SelectServiceTypeController.update();
    }
}
