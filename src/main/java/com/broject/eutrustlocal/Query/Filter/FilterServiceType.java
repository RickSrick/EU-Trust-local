package com.broject.eutrustlocal.Query.Filter;

import com.broject.eutrustlocal.Creation.Data.Provider;
import com.broject.eutrustlocal.Creation.Data.Service;
import com.broject.eutrustlocal.Creation.DataArchive;

import java.util.Collections;
import java.util.Vector;

/**
 * @author Zanella Matteo
 */

public class FilterServiceType extends Filter {

    public FilterServiceType() {

        super();
        filterID = 2;

    }

    @Override
    public Vector<String> getParameters() {

        if (parameters.isEmpty()) {

            Vector<String> tempParameters = new Vector<>();
            Collections.addAll(tempParameters, DataArchive.SERVICE_TYPES);
            return tempParameters;

        }

        return parameters;

    }

    @Override
    public Vector<Provider> applyFilter(Vector<Provider> _response) {

        if (parameters.isEmpty())
            return _response;

        Vector<Provider> filteredProviders = new Vector<>();

        for (Provider provider : _response) {

            Vector<Service> unfilteredProviderServices = provider.getServices();
            Vector<Service> filteredProviderServices = new Vector<>();

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
                Vector<String> serviceTypes = filteredProviderService.getServiceTypes();
                for (String serviceType : serviceTypes)
                    newProvider.addServiceType(serviceType);
            }

            if (!filteredProviderServices.isEmpty())
                filteredProviders.add(newProvider);

        }

        return filteredProviders;

    }

}