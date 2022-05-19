package Query.Filter;

import Creation.Data.Provider;

import java.util.Vector;

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

        for (int i = 0; i < _response.size(); i++) {
            for (int j = 0; j < parameters.size(); j++) {
                if (_response.get(i).getName().equals(parameters.get(j))) {
                    filteredProviders.add(_response.get(i));
                    break;
                }
            }
        }

        return filteredProviders;

    }
    
}