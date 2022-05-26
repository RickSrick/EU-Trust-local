package com.broject.eutrustlocal.Command.ConcreteCommand;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Query.Query;

import java.util.ArrayList;

public class ViewProvidersCommand implements Command{

    Query query;
    String countryCode;

    public ViewProvidersCommand (String _countryCode) {
        query = new Query();
        countryCode = _countryCode;
    }

    @Override
    public void execute() throws BadResponseException {

        ArrayList<String> cc = new ArrayList<>();
        cc.add(countryCode);
        query.editFilterParameter("COUNTRIES", cc);

        //send Data
    }
}
