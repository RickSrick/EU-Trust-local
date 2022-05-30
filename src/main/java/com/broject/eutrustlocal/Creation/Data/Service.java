package com.broject.eutrustlocal.Creation.Data;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Zanella Matteo
 */

public class Service {

    private final String serviceName;
    private final String serviceStatus;
    private final ArrayList<String> serviceTypes;
    private final int serviceID;

    public Service(String _serviceName, String[] _serviceTypes, String _serviceStatus, int _serviceID) {

        serviceName = _serviceName;
        serviceTypes = new ArrayList<>();
        Collections.addAll(serviceTypes, _serviceTypes);
        serviceStatus = _serviceStatus;
        serviceID = _serviceID;

    }

    public String getName() {

        return serviceName;

    }

    public ArrayList<String> getServiceTypes() {

        return serviceTypes;

    }

    public String getStatus() {

        return serviceStatus;

    }

    public int getServiceID() {

        return serviceID;

    }

    @Override
    public boolean equals(Object _service) {

        if (_service == null || _service.getClass() != Service.class)
            return false;

        return serviceName.equals(((Service) _service).serviceName) && serviceID == ((Service)_service).getServiceID();

    }

}
