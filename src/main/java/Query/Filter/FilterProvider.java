package Query.Filter;

import Creation.Data.Provider;

import java.util.Vector;

/**
 * @author Zanella Matteo
 */

public class FilterProvider extends Filter {
    
    public FilterProvider() { 
    
        super();
        filterID = 1;

    }
    
    @Override
    public Vector<Provider> execute(Vector<Provider> _response) {
        
        if (parameters.isEmpty())
            return _response;

        Vector<Provider> filteredProviders = new Vector<>();

        for (Provider provider : _response) {
            for (String parameter : parameters) {
                if (provider.getName().equals(parameter)) {
                    filteredProviders.add(provider);
                    break;
                }
            }
        }

        return filteredProviders;

    }
    
}