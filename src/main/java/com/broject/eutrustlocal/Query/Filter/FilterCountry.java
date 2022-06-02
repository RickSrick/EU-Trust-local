package com.broject.eutrustlocal.Query.Filter;

import com.broject.eutrustlocal.Creation.Data.Provider;
import com.broject.eutrustlocal.Creation.DataArchive;

import java.util.ArrayList;

/**
 * @author Zanella Matteo
 */

public class FilterCountry extends Filter {

    public FilterCountry() {

        super();
        filterID = 0;

    }

    @Override
    public ArrayList<String> getParameters() {

        if (parameters.isEmpty()) return DataArchive.getCountryCodes();

        return parameters;

    }

    @Override
    public ArrayList<Provider> applyFilter(ArrayList<Provider> _response) {

        if (parameters.isEmpty()) return _response;

        ArrayList<Provider> filteredProviders = new ArrayList<>();

        for (Provider provider : _response)
            if (parameters.contains(provider.getCountryCode()))
                filteredProviders.add(provider);

        return filteredProviders;

    }

}
