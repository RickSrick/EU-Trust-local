package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Controller.SelectStatusesController;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;

import java.io.IOException;

public class SelectStatusesView extends View {
    private static SelectStatusesView instance = null;

    private SelectStatusesView() throws IOException {
        super(FXMLArchive.STATUSES_LIST_SCENE);
    }

    public static SelectStatusesView getInstance(boolean reload) throws BadResponseException, IOException {
        if (DataArchive.checkOfflineStatus()) throw new BadResponseException();
        if (instance == null)
            instance = new SelectStatusesView();
        else if (reload) update();
        return instance;
    }

    private static void update() throws BadResponseException {
        SelectStatusesController.update();
    }
}
