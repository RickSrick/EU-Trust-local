package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Controller.SelectTypeServiceController;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Query.Query;

import java.io.IOException;

public class SelectTypeServiceView extends View {

    private Query countryCodesQuery;

    private static SelectTypeServiceView instance = null;

    private SelectTypeServiceView(Query _countryCodesQuery) throws IOException {
        super(XMLArchive.SERVICE_TYPE_LIST_SCENE);
        countryCodesQuery=_countryCodesQuery;
    }

    public static SelectTypeServiceView newSelectTypeServiceView(Query _countryCodesQuery) throws BadResponseException, IOException {

        if (DataArchive.checkOfflineStatus()) throw new BadResponseException();
        if (instance == null)
            instance = new SelectTypeServiceView(_countryCodesQuery);

        update();
        return instance;
    }

    public Query getCountryCodes(){
        return countryCodesQuery;
    }

    private static void update(){
        SelectTypeServiceController.update();
    }
}
