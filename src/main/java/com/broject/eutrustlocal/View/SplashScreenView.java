package com.broject.eutrustlocal.View;

import java.io.IOException;

public class SplashScreenView extends View{

    private static SplashScreenView instance = null;

    private SplashScreenView() throws IOException {
        super(XMLArchive.SPLASH_SCREEN_SCENE);
    }

    public static SplashScreenView getInstance() throws IOException {

        if (instance == null)
            instance = new SplashScreenView();

        return instance;
    }
}

