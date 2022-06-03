package com.broject.eutrustlocal.View;

import java.io.IOException;

/**
 * Class ErrorView
 *
 * @author Biscaccia Carrara Francesco
 */
public class ErrorView extends View {

    private static ErrorView instance = null;

    private ErrorView() throws IOException {

        super(FXMLArchive.ERROR_SCENE);

    }

    public static ErrorView getInstance() throws IOException {

        if (instance == null) instance = new ErrorView();

        return instance;

    }

}
