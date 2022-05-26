package com.broject.eutrustlocal.Query.Filter;

import com.broject.eutrustlocal.Creation.Data.Provider;

import java.util.ArrayList;

/**
 * @author Zanella Matteo
 */

public class FilterProvider extends Filter {

    public FilterProvider() {

        super();
        filterID = 1;

    }

    @Override
    public ArrayList<Provider> applyFilter(ArrayList<Provider> _response) {

        if (parameters.isEmpty()) return _response;

        ArrayList<Provider> filteredProviders = new ArrayList<>();

        for (Provider provider : _response) {
            for (String parameter : parameters) {
                if (provider.getName().equals(parameter)) {
                    filteredProviders.add(provider);
                    break;
                }
            }
        }

        return filteredProviders;

    }

}