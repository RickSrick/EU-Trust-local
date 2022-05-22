package com.broject.eutrustlocal.View;

import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * @author Biscaccia Carrara Francesco
 */
public class ErrorView {

    private Scene errorScreen;

    public ErrorView(Parent node, int width, int height){
        errorScreen = new Scene(node,width,height);
    }

    public Scene getScene(){
        return errorScreen;
    }
}
