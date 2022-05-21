package com.broject.eutrustlocal.Creation.Data;

import java.util.Collections;
import java.util.Vector;

/**
 * @author Zanella Matteo
 */

public class Service {

    private final String serviceName;
    private final String serviceStatus;
    private final Vector<String> serviceType;

    public Service(String _serviceName, String[] _serviceTypes, String _serviceStatus) {

        serviceName = _serviceName;
        serviceType = new Vector<>();
        Collections.addAll(serviceType, _serviceTypes);
        serviceStatus = _serviceStatus;

    }

    public String getName() {

        return serviceName;

    }

    public Vector<String> getServiceTypes() {

        return serviceType;

    }

    public String getStatus() {

        return serviceStatus;

    }

    @Override
    public boolean equals(Object _service) {

        if (_service == null || _service.getClass() != Service.class)
            return false;

        return serviceName.equals(((Service) _service).serviceName);

    }

}
