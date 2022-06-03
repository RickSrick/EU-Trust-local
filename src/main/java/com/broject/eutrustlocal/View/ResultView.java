package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Controller.ResultController;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;

import java.io.IOException;

public class ResultView extends View {
    private static ResultView instance = null;

    private ResultView() throws IOException {
        super(FXMLArchive.RESULT_LIST_SCENE);
    }

    public static ResultView getInstance(boolean reload) throws BadResponseException, IOException {

        if (DataArchive.checkOfflineStatus()) throw new BadResponseException();
        if (instance == null)
            instance = new ResultView();
        else if (reload) update();
        return instance;
    }

    private static void update() throws BadResponseException {
        new ResultController().update();
    }
}
