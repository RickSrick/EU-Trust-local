package com.broject.eutrustlocal.Command;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Query.Query;

public class ViewProvidersCommand implements Command{

    Query query;
    String countryCode;

    public ViewProvidersCommand (String _countryCode) {
        query = new Query();
        countryCode = _countryCode;
    }

    @Override
    public void execute() throws BadResponseException {

        query.editFilterParameter("COUNTRIES", countryCode);

        //send Data
    }
}
