package com.broject.eutrustlocal.Query.Filter;

import com.broject.eutrustlocal.Creation.Data.Provider;
import com.broject.eutrustlocal.Creation.Data.Service;

import java.util.ArrayList;

/**
 * Class Filter
 *
 * @author Zanella Matteo
 */

public abstract class Filter {

    protected ArrayList<String> parameters;
    protected int filterID;

    /**
     * Default constructor
     */
    protected Filter() {

        parameters = new ArrayList<>();

    }

    /**
     * Returns all the parameters of the filter
     *
     * @return the parameters of the filter
     */
    public ArrayList<String> getParameters() {

        return new ArrayList<>(parameters);

    }

    /**
     * Method that adds a list of parameters to the existing ones
     *
     * @param _parameters parameters to be added
     */
    public void addParameters(String[] _parameters) {

        for (String parameter : _parameters)
            if (!parameters.contains(parameter)) parameters.add(parameter);

    }

    /**
     * Checks if there is a certain parameter between the existing ones
     *
     * @param _parameter the parameter to be checked
     * @return true/false based on the parameter being already considered or not
     */
    public boolean has(String _parameter) {

        return parameters.contains(_parameter);

    }

    /**
     * Method used to remove a parameter
     *
     * @param _parameter the parameter to be removed
     */
    public void removeParameter(String _parameter) {

        parameters.remove(_parameter);

    }

    /**
     * Method that clears the filter (removes all the parameters)
     */
    public void clear() {

        parameters.clear();

    }

    /**
     * Method that filters a given ArrayList of providers following the existing parameters stored in the filter
     * To override
     *
     * @param _response the ArrayList to be filtered
     * @return a filtered ArrayList
     */
    public abstract ArrayList<Provider> applyFilter(ArrayList<Provider> _response);

    protected Provider duplicateProvider(Provider _provider, ArrayList<Service> _services) {

        Provider newProvider = new Provider(_provider.getName(), _provider.getCountryCode(), _provider.getProviderID());

        for (Service filteredProviderService : _services) {
            newProvider.addService(filteredProviderService);
            ArrayList<String> serviceTypes = filteredProviderService.getServiceTypes();
            for (String serviceType : serviceTypes)
                newProvider.addServiceType(serviceType);
        }

        return newProvider;

    }

}
