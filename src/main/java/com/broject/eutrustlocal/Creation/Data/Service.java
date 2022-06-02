package com.broject.eutrustlocal.Creation.Data;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Zanella Matteo
 */

public class Service {

    private final String serviceName;
    private final String serviceStatus;
    private final int serviceID;
    private final ArrayList<String> serviceTypes;

    /**
     * Constructor needing:
     *
     * @param _serviceName   the name of the service
     * @param _serviceTypes  the types of the service
     * @param _serviceStatus the status of the service
     * @param _serviceID     the ID of the service
     */
    public Service(String _serviceName, String[] _serviceTypes, String _serviceStatus, int _serviceID) {

        serviceName = _serviceName;
        serviceStatus = _serviceStatus;
        serviceID = _serviceID;
        serviceTypes = new ArrayList<>();
        Collections.addAll(serviceTypes, _serviceTypes);

    }

    /**
     * Returns the name of the service
     *
     * @return the name of the service
     */
    public String getName() {

        return serviceName;

    }

    /**
     * Returns the types of the service
     *
     * @return the types of the service
     */
    public ArrayList<String> getServiceTypes() {

        return new ArrayList<>(serviceTypes);

    }

    /**
     * Returns the status of the service
     *
     * @return the status of the service
     */
    public String getStatus() {

        return serviceStatus;

    }

    /**
     * Returns the ID of the service
     *
     * @return the ID of the service
     */
    public int getServiceID() {

        return serviceID;

    }

    @Override
    public boolean equals(Object _service) {

        if (_service == null || _service.getClass() != Service.class) return false;

        return serviceName.equals(((Service) _service).serviceName) && serviceID == ((Service) _service).getServiceID();

    }

}
