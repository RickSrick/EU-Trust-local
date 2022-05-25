package com.broject.eutrustlocal.View;

import java.io.IOException;

/**
 * @author Biscaccia Carrara Francesco
 */
public class ErrorView extends View {

    private static ErrorView instance = null;

    private ErrorView() throws IOException {
        super(XMLArchive.ERROR_SCENE);
    }

    public static ErrorView newErrorView() throws IOException {

        if (instance == null) instance = new ErrorView();

        return instance;
    }
}
