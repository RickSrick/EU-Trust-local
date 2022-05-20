package Query.Filter;

import Creation.Data.Provider;
import Creation.Data.Service;

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
        
        if (parameters.isEmpty())
            return _response;

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
            for (int j = 0; j < filteredProviderServices.size(); j++) {
                newProvider.addService(filteredProviderServices.get(j));
                Vector<String> serviceTypes = filteredProviderServices.get(j).getServiceType();
                for (int k = 0; k < serviceTypes.size(); k++)
                    newProvider.addServiceType(serviceTypes.get(k));
            }

            if (!filteredProviderServices.isEmpty())
                filteredProviders.add(newProvider);

        }

        return filteredProviders;

    }
    
}