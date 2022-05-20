package com.broject.eutrustlocal.Query.Filter;

import com.broject.eutrustlocal.Creation.Data.Provider;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.DataArchive;

import java.util.Vector;

/**
 * @author Zanella Matteo
 */

public class FilterCountry extends Filter{

    public FilterCountry() {

        super();
        filterID = 0;

    }

    @Override
    public Vector<String> getParameters() throws BadResponseException {

        if (!parameters.isEmpty())
            return parameters;

        return DataArchive.newDataArchive().getCountriesID();

    }

    @Override
    public Vector<Provider> execute(Vector<Provider> _response) {
        
        if (parameters.isEmpty())
            return _response;
        
        Vector<Provider> filteredProviders = new Vector<>();

        for (Provider provider : _response)
            if (parameters.contains(provider.getCountryCode()))
                filteredProviders.add(provider);

        return filteredProviders;

    }
    
}
