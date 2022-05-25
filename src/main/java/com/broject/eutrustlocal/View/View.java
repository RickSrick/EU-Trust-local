package com.broject.eutrustlocal.View;


import com.broject.eutrustlocal.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * @author Biscaccia Carrara Francesco
 */

public abstract class View {

    public static final int IMG_SIZE=40;
    public static final int COL_NUM=4;
    public static final int ROW_NUM=8;

    private final Scene scene;

    public View(FXMLLoader loader) throws IOException {
        scene = new Scene(loader.load(), Main.LAYOUT_WIDTH,Main.LAYOUT_HEIGHT);
    }

    public Scene getScene() {
        return scene;
    }
}
