package com.broject.eutrustlocal.Command.Search;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Query.Query;

import java.util.ArrayList;

public abstract class VirtualSearch {

    Query query;

    public VirtualSearch(Query mainQuery) {
        query = mainQuery;
    }

    public Query getQuery() {
        return query;
    }

    public ArrayList<String> getValidType() {
        try {
            return query.getValidServiceTypes();
        } catch (BadResponseException e) {
            //open Error page
        }
        return null;
    }

    public ArrayList<String> getValidStatus() {
        try {
            return query.getValidServiceStatuses();
        } catch (BadResponseException e) {
            //open Error page
        }
        return null;
    }

   /* public ArrayList<String> getValidProviders() {
        try {
            return query.getValidProviders();
        } catch (BadResponseException e) {
            //open Error page
        }
        return null;
    }*/

}
