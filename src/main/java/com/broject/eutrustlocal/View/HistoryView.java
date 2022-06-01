package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Controller.SelectProviderController;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.History.History;

import java.io.IOException;

public class HistoryView extends View{
    private static HistoryView instance = null;

    private HistoryView() throws IOException {
        super(XMLArchive. HISTORY_SCENE);
    }

    public static HistoryView getInstance(boolean goFoward) throws BadResponseException, IOException {

        if (DataArchive.checkOfflineStatus()) throw new BadResponseException();
        if (instance == null)
            instance = new HistoryView();
        else if(goFoward) update();
        return instance;
    }

    public static void update() throws BadResponseException {
        //SelectProviderController.update();
    }
}
