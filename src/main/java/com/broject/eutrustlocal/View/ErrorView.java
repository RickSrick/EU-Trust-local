package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Main;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * @author Biscaccia Carrara Francesco
 */
public class ErrorView {

    private final Scene errorScreen;
    private static ErrorView instance = null;

    private ErrorView() throws IOException {
        errorScreen = new Scene(XMLArchive.ERROR_SCENE.load(), Main.LAYOUT_WIDTH,Main.LAYOUT_HEIGHT);
    }

    public static ErrorView newErrorView() throws IOException {

        if (instance == null)
            instance = new ErrorView();

        return instance;
    }
    public Scene getScene(){
        return errorScreen;
    }
}
