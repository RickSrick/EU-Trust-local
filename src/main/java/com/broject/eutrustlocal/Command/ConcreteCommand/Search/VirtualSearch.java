package com.broject.eutrustlocal.Command.ConcreteCommand.Search;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Query.Query;

import java.util.Vector;

public abstract class VirtualSearch {

    Query query;

    public VirtualSearch(Query mainQuery) {
        query = mainQuery;
    }

    public Query getQuery() {
        return query;
    }

    public Vector<String> getValidType() {
        try {
            return query.getValidServiceTypes();
        } catch (BadResponseException e) {
            //open Error page
        }
        return null;
    }

    public Vector<String> getValidStatus() {
        try {
            return query.getValidServiceStatuses();
        } catch (BadResponseException e) {
            //open Error page
        }
        return null;
    }

    public Vector<String> getValidProviders() {
        try {
            return query.getValidProviders();
        } catch (BadResponseException e) {
            //open Error page
        }
        return null;
    }

}
