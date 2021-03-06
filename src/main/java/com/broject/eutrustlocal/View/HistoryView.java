package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Controller.HistoryController;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HistoryView extends View {

    private static HistoryView instance = null;

    private HistoryView() throws IOException {

        super(FXMLArchive.HISTORY_SCENE);

    }

    public static HistoryView getInstance(boolean reload) throws BadResponseException, IOException {

        if (DataArchive.checkOfflineStatus()) throw new BadResponseException();
        if (instance == null) instance = new HistoryView();
        else if (reload) update();

        return instance;

    }

    private static void update() throws FileNotFoundException {

        HistoryController.update();

    }

}
