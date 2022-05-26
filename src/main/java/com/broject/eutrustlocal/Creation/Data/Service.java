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

    public Service(String _serviceName, String[] _serviceTypes, String _serviceStatus) {

        serviceName = _serviceName;
        serviceTypes = new ArrayList<>();
        Collections.addAll(serviceTypes, _serviceTypes);
        serviceStatus = _serviceStatus;

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

    @Override
    public boolean equals(Object _service) {

        if (_service == null || _service.getClass() != Service.class)
            return false;

        return serviceName.equals(((Service) _service).serviceName) && serviceStatus.equals(((Service)_service).serviceStatus) && serviceTypes.equals(((Service)_service).getServiceTypes());

    }

}
