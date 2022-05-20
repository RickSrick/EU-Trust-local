package Creation.Data;

import Creation.Data.Service;
import java.util.Vector;

/**
 * @author Zanella Matteo
 */

public class Provider {

    private String providerName;
    private String countryCode;
    private String flagLink;
    private Vector<Service> services;
    private Vector<String> serviceTypes;

    public Provider(String _name, String _countryCode, String _flagLink) {
    
        providerName = _name;
        countryCode = _countryCode;
        flagLink = _flagLink;
        services = new Vector<>();
        serviceTypes = new Vector<>();

    }

    public String getName() { return providerName; }

    public String getCountryCode() { return countryCode; }

    public String getFlagLink() { return flagLink; }

    public Vector<Service> getServices() {
        
        return (Vector<Service>)services.clone();

    }

    public Vector<String> getServiceTypes() {
        
        return (Vector<String>)serviceTypes.clone();

    }

    public void addService(Service _service) {
        
        if (!services.contains(_service)) services.add(_service);

    }

    public void addServiceType(String _serviceType) {

        if (!serviceTypes.contains(_serviceType))
            serviceTypes.add(_serviceType);

    }

    @Override
    public boolean equals(Object _provider) {
        
        if (_provider == null) return false;

        return providerName == ((Provider)_provider).providerName;
    
    }
    
}
