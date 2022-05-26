package com.broject.eutrustlocal.Query.Filter;

import com.broject.eutrustlocal.Creation.Data.Provider;
import com.broject.eutrustlocal.Creation.Data.Service;
import com.broject.eutrustlocal.Creation.DataArchive;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Zanella Matteo
 */

public class FilterServiceType extends Filter {

    public FilterServiceType() {

        super();
        filterID = 2;

    }

    @Override
    public ArrayList<String> getParameters() {

        if (parameters.isEmpty()) {

            ArrayList<String> tempParameters = new ArrayList<>();
            Collections.addAll(tempParameters, DataArchive.SERVICE_TYPES);
            return tempParameters;

        }

        return parameters;

    }

    @Override
    public ArrayList<Provider> applyFilter(ArrayList<Provider> _response) {

        if (parameters.isEmpty())
            return _response;

        ArrayList<Provider> filteredProviders = new ArrayList<>();

        for (Provider provider : _response) {

            ArrayList<Service> unfilteredProviderServices = provider.getServices();
            ArrayList<Service> filteredProviderServices = new ArrayList<>();

            for (Service unfilteredProviderService : unfilteredProviderServices) {

                for (String parameter : parameters) {

                    if (unfilteredProviderService.getServiceTypes().contains(parameter)) {

                        filteredProviderServices.add(unfilteredProviderService);
                        break;

                    }

                }

            }

            Provider newProvider = new Provider(provider.getName(), provider.getCountryCode(), provider.getFlagLink());
            for (Service filteredProviderService : filteredProviderServices) {
                newProvider.addService(filteredProviderService);
                ArrayList<String> serviceTypes = filteredProviderService.getServiceTypes();
                for (String serviceType : serviceTypes)
                    newProvider.addServiceType(serviceType);
            }

            if (!filteredProviderServices.isEmpty())
                filteredProviders.add(newProvider);

        }

        return filteredProviders;

    }

}