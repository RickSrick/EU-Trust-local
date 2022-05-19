package Query.Filter;

import Creation.Data.Provider;
import Creation.Data.Service;
import Creation.DataArchive;

import java.util.Vector;

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

        for (int i = 0; i < DataArchive.SERVICE_TYPES.length; i++)
            tempParameters.add(DataArchive.SERVICE_TYPES[i]);

        return tempParameters;

    }
    
    @Override
    public Vector<Provider> execute(Vector<Provider> _response) {

        if (parameters.isEmpty())
            return _response;

        Vector<Provider> filteredProviders = new Vector<>();

        for (int i = 0; i < _response.size(); i++) {

            Vector<Service> unfilteredProviderServices = _response.get(i).getServices();
            Vector<Service> filteredProviderServices = new Vector<>();

            for (int j = 0; j < unfilteredProviderServices.size(); j++) {

                for (int k = 0; k < parameters.size(); k++) {
                 
                    if (unfilteredProviderServices.get(j).getServiceType().contains(parameters.get(k))) {

                        filteredProviderServices.add(unfilteredProviderServices.get(j));
                        break;

                    }
                
                }

            }

            Provider newProvider = new Provider(_response.get(i).getName(), _response.get(i).getCountryCode(), _response.get(i).getFlagLink());
            for (int j = 0; j < filteredProviderServices.size(); j++) {
                newProvider.addService(filteredProviderServices.get(j));
                Vector<String> serviceTypes = filteredProviderServices.get(j).getServiceType();
                for (int k = 0; k < serviceTypes.size(); k++)
                    newProvider.addServiceType(serviceTypes.get(k));
            }
                
            if(!filteredProviderServices.isEmpty())
                filteredProviders.add(newProvider);

        }

        return filteredProviders;

    }
    
}