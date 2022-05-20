package Creation.Data;

import java.util.Collections;
import java.util.Vector;

/**
 * @author Zanella Matteo
 */

public class Service  {

    private String serviceName;
    private String status;
    private Vector<String> serviceType;

    public Service(String _name, String[] _type, String _status) {

        serviceName = _name;
        serviceType = new Vector<>();
        Collections.addAll(serviceType, _type);
        status = _status;

    }

    public String getName() { return serviceName; }

    public Vector<String> getServiceType() { return serviceType; }

    public String getStatus() { return status; }

    @Override
    public boolean equals(Object _service) {
        
        if (_service == null) return false;

        return serviceName == ((Service)_service).serviceName;
    
    }
    
}
