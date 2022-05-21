package com.broject.eutrustlocal.Query.Filter;

import com.broject.eutrustlocal.Creation.Data.Provider;
import com.broject.eutrustlocal.Creation.Data.Service;

import java.util.Vector;

/**
 * @author Zanella Matteo
 */

public class FilterServiceStatus extends Filter {

    public FilterServiceStatus() {

        super();
        filterID = 3;

    }

    @Override
    public Vector<Provider> execute(Vector<Provider> _response) {

        if (parameters.isEmpty()) return _response;

        Vector<Provider> filteredProviders = new Vector<>();

        for (Provider provider : _response) {

            Vector<Service> unfilteredProviderServices = provider.getServices();
            Vector<Service> filteredProviderServices = new Vector<>();

            for (Service unfilteredProviderService : unfilteredProviderServices) {

                for (String parameter : parameters) {

                    if (unfilteredProviderService.getStatus().equals(parameter)) {

                        filteredProviderServices.add(unfilteredProviderService);
                        break;

                    }

                }

            }

            Provider newProvider = new Provider(provider.getName(), provider.getCountryCode(), provider.getFlagLink());
            for (Service filteredProviderService : filteredProviderServices) {
                newProvider.addService(filteredProviderService);
                Vector<String> serviceTypes = filteredProviderService.getServiceType();
                for (String serviceType : serviceTypes) newProvider.addServiceType(serviceType);
            }

            if (!filteredProviderServices.isEmpty()) filteredProviders.add(newProvider);

        }

        return filteredProviders;

    }

}