package com.broject.eutrustlocal.Query.Filter;

import com.broject.eutrustlocal.Creation.Data.Provider;
import com.broject.eutrustlocal.Creation.Data.Service;

import java.util.ArrayList;

/**
 * @author Zanella Matteo
 */

public class FilterServiceStatus extends Filter {

    public FilterServiceStatus() {

        super();
        filterID = 3;

    }

    @Override
    public ArrayList<Provider> applyFilter(ArrayList<Provider> _response) {

        if (parameters.isEmpty()) return _response;

        ArrayList<Provider> filteredProviders = new ArrayList<>();

        for (Provider provider : _response) {

            ArrayList<Service> unfilteredProviderServices = provider.getServices();
            ArrayList<Service> filteredProviderServices = new ArrayList<>();

            for (Service unfilteredProviderService : unfilteredProviderServices) {

                for (String parameter : parameters) {

                    if (unfilteredProviderService.getStatus().equals(parameter)) {

                        filteredProviderServices.add(unfilteredProviderService);
                        break;

                    }

                }

            }

            Provider newProvider = new Provider(provider.getName(), provider.getCountryCode(), provider.getFlagLink(), provider.getProviderID());
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