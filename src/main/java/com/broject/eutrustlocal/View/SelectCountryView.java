package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;

import java.io.IOException;

/**
 * @author Biscaccia Carrara Francesco
 */
public class SelectCountryView extends View {

    private static SelectCountryView instance = null;

    private SelectCountryView() throws IOException {
        super(XMLArchive.COUNTRY_LIST_SCENE);
    }

    public static SelectCountryView newSelectionCountryView() throws BadResponseException, IOException {

        if (DataArchive.checkOfflineStatus()) throw new BadResponseException();
        if (instance == null)
            instance = new SelectCountryView();

        return instance;
    }
}
