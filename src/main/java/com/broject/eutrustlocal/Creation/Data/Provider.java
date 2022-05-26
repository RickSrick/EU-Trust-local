package com.broject.eutrustlocal.Creation.Data;

import java.util.ArrayList;

/**
 * @author Zanella Matteo
 */

public class Provider {

    private final String providerName;
    private final String countryCode;
    private final String flagLink;
    private final ArrayList<Service> services;
    private final ArrayList<String> serviceTypes;

    public Provider(String _providerName, String _countryCode, String _flagLink) {

        providerName = _providerName;
        countryCode = _countryCode;
        flagLink = _flagLink;
        services = new ArrayList<>();
        serviceTypes = new ArrayList<>();

    }

    public String getName() {

        return providerName;

    }

    public String getCountryCode() {

        return countryCode;

    }

    public String getFlagLink() {

        return flagLink;

    }

    public ArrayList<Service> getServices() {

        return new ArrayList<>(services);

    }

    public ArrayList<String> getServiceTypes() {

        return new ArrayList<>(serviceTypes);

    }

    public void addService(Service _service) {

        if (!services.contains(_service))
            services.add(_service);

    }

    public void addServiceType(String _serviceType) {

        if (!serviceTypes.contains(_serviceType))
            serviceTypes.add(_serviceType);

    }

    @Override
    public boolean equals(Object _provider) {

        if (_provider == null || _provider.getClass() != Provider.class)
            return false;

        return providerName.equals(((Provider) _provider).providerName);

    }

}
