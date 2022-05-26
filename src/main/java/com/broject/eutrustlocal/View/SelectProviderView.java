package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Controller.SelectProviderController;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;

import java.io.IOException;

public class SelectProviderView extends View{

    private static SelectProviderView instance = null;

    private SelectProviderView() throws IOException {
        super(XMLArchive. PROVIDER_LIST_SCENE);
    }

    public static SelectProviderView newSelectProviderView() throws BadResponseException, IOException {

        if (DataArchive.checkOfflineStatus()) throw new BadResponseException();
        if (instance == null)
            instance = new SelectProviderView();

        return instance;
    }

    public static void update() throws BadResponseException {
        SelectProviderController.update();
    }
}
