package com.broject.eutrustlocal.Command.ConcreteCommand;
import com.broject.eutrustlocal.Query.Query;

public class ViewProvidersCommand implements Command{

    Query query;
    String countryCode;

    public ViewProvidersCommand (String _countryCode) {
        query = new Query();
        countryCode = _countryCode;
    }

    @Override
    public void execute() {
        //make query
        //send Data
    }
}
