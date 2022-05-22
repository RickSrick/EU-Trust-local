package com.broject.eutrustlocal.View;

import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * @author Biscaccia Carrara Francesco
 */
public class ErrorView {

    private final Scene errorScreen;

    public ErrorView(Parent node, double width, double height){
        errorScreen = new Scene(node,width,height);
    }

    public Scene getScene(){
        return errorScreen;
    }
}
