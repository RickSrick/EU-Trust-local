package com.broject.eutrustlocal.Controller.BackgroundTasks;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.Query.Query;
import com.broject.eutrustlocal.View.ErrorView;
import javafx.concurrent.Task;
import javafx.scene.Cursor;

import java.io.IOException;

public class TaskData extends Task<Void> {

    private final Query query;
    public TaskData(Query _query){
        query=_query;
    }

    @Override
    protected Void call(){
        try{
            Main.STAGE.getScene().getRoot().setCursor(Cursor.WAIT);
            query.getValidProviders();
            Main.STAGE.getScene().getRoot().setCursor(Cursor.DEFAULT);
        }catch (BadResponseException e){
            failed();
        }
        return null;
    }

    @Override
    protected void succeeded() {
        super.succeeded();
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
