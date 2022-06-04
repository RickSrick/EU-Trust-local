package com.broject.eutrustlocal.Controller.Utility.BackgroundTasks;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import javafx.concurrent.Task;

import java.io.IOException;

/**
 * Task that fetch data from DataArchive.
 *
 * @author Biscaccia Carrara Francesco
 */
public class TaskCountry extends Task<Void> {

    @Override
    protected Void call() {

        try {
            DataArchive.newDataArchive().getCountries();
        } catch (BadResponseException e) {
            failed();
        }
        return null;

    }

    @Override
    protected void succeeded() {

        super.succeeded();
        try {
            Main.STAGE.setScene(HomeView.getInstance(true).getScene());
        } catch (BadResponseException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void failed() {

        super.failed();
        try {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

}
