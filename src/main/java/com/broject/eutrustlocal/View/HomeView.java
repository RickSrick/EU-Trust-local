package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Controller.HomeController;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Biscaccia Carrara Francesco
 */
public class HomeView extends View {
    private static HomeView instance = null;

    private HomeView() throws IOException {
        super(XMLArchive.HOME_SCENE);
    }

    public static HomeView getInstance(boolean reload) throws BadResponseException, IOException {

        if (DataArchive.checkOfflineStatus()) throw new BadResponseException();
        if (instance == null)
            instance = new HomeView();
        else if (reload) update();
        return instance;
    }

    private static void update() throws FileNotFoundException {
        HomeController.update();
    }
}
