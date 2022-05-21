package com.broject.eutrustlocal.Creation.Data;

import java.util.Vector;

/**
 * @author Zanella Matteo
 */

public class Provider {

    private final String providerName;
    private final String countryCode;
    private final String flagLink;
    private final Vector<Service> services;
    private final Vector<String> serviceTypes;

    public Provider(String _name, String _countryCode, String _flagLink) {

        providerName = _name;
        countryCode = _countryCode;
        flagLink = _flagLink;
        services = new Vector<>();
        serviceTypes = new Vector<>();

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

    public Vector<Service> getServices() {

        return new Vector<>(services);

    }

    public Vector<String> getServiceTypes() {

        return new Vector<>(serviceTypes);

    }

    public void addService(Service _service) {

        if (!services.contains(_service)) services.add(_service);

    }

    public void addServiceType(String _serviceType) {

        if (!serviceTypes.contains(_serviceType)) serviceTypes.add(_serviceType);

    }

    @Override
    public boolean equals(Object _provider) {

        if (_provider == null) return false;

        return providerName.equals(((Provider) _provider).providerName);

    }

}
