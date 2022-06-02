package com.broject.eutrustlocal.Creation.Data;

import java.util.ArrayList;

/**
 * @author Zanella Matteo
 */

public class Provider {

    private final String providerName;
    private final String countryCode;
    private final ArrayList<Service> services;
    private final ArrayList<String> serviceTypes;
    private final int providerID;

    /**
     * Constructor needing:
     *
     * @param _providerName the name of the provider
     * @param _countryCode  the code of the country that it belongs to
     * @param _providerID   the ID of the provider
     */
    public Provider(String _providerName, String _countryCode, int _providerID) {

        providerName = _providerName;
        countryCode = _countryCode;
        providerID = _providerID;
        services = new ArrayList<>();
        serviceTypes = new ArrayList<>();

    }

    /**
     * Returns the name of the provider
     *
     * @return the name of the provider
     */
    public String getName() {

        return providerName;

    }

    /**
     * Returns the countryCode of the provider
     *
     * @return the countryCode of the provider
     */
    public String getCountryCode() {

        return countryCode;

    }

    /**
     * Returns the services of the provider
     *
     * @return the services of the provider
     */
    public ArrayList<Service> getServices() {

        return new ArrayList<>(services);

    }

    /**
     * Returns the service types of the provider
     *
     * @return the service types of the provider
     */
    public ArrayList<String> getServiceTypes() {

        return new ArrayList<>(serviceTypes);

    }

    /**
     * Returns the provider ID
     *
     * @return the provider ID
     */
    public int getProviderID() {

        return providerID;

    }

    /**
     * Adds a service to the provider
     *
     * @param _service the service to be added
     */
    public void addService(Service _service) {

        if (!services.contains(_service)) services.add(_service);

    }

    /**
     * Adds a service type to the provider
     *
     * @param _serviceType the service type to be added
     */
    public void addServiceType(String _serviceType) {

        if (!serviceTypes.contains(_serviceType)) serviceTypes.add(_serviceType);

    }

    @Override
    public boolean equals(Object _provider) {

        if (_provider == null || _provider.getClass() != Provider.class) return false;

        return providerName.equals(((Provider) _provider).providerName) && countryCode.equals(((Provider) _provider).getCountryCode()) && providerID == ((Provider) _provider).getProviderID();

    }

}
