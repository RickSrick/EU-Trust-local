package Query.Filter;

import Creation.Data.Provider;
import Creation.Data.Service;
import Creation.DataArchive;

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

        if (!parameters.isEmpty())
            return parameters;

        Vector<String> tempParameters = new Vector<>();

        Collections.addAll(tempParameters, DataArchive.SERVICE_TYPES);

        return tempParameters;

    }
    
    @Override
    public Vector<Provider> execute(Vector<Provider> _response) {

        if (parameters.isEmpty())
            return _response;

        Vector<Provider> filteredProviders = new Vector<>();

        for (Provider provider : _response) {

            Vector<Service> unfilteredProviderServices = provider.getServices();
            Vector<Service> filteredProviderServices = new Vector<>();

            for (Service unfilteredProviderService : unfilteredProviderServices) {

                for (String parameter : parameters) {

                    if (unfilteredProviderService.getServiceType().contains(parameter)) {

                        filteredProviderServices.add(unfilteredProviderService);
                        break;

                    }

                }

            }

            Provider newProvider = new Provider(provider.getName(), provider.getCountryCode(), provider.getFlagLink());
            for (Service filteredProviderService : filteredProviderServices) {
                newProvider.addService(filteredProviderService);
                Vector<String> serviceTypes = filteredProviderService.getServiceType();
                for (String serviceType : serviceTypes)
                    newProvider.addServiceType(serviceType);
            }

            if (!filteredProviderServices.isEmpty())
                filteredProviders.add(newProvider);

        }

        return filteredProviders;

    }
    
}