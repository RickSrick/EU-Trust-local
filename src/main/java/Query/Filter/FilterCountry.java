package Query.Filter;

import Creation.Data.Provider;
import Creation.DataArchive;

import java.util.Vector;

public class FilterCountry extends Filter{

    public FilterCountry() {

        super();
        filterID = 0;

    }

    @Override
    public Vector<String> getParameters() {

        if (!parameters.isEmpty())
            return parameters;

        return DataArchive.newDataArchive().getCountriesID();

    }

    @Override
    public Vector<Provider> execute(Vector<Provider> _response) {
        
        if (parameters.isEmpty())
            return _response;
        
        Vector<Provider> filteredProviders = new Vector<>();

        for (int i = 0; i < _response.size(); i++)
            if (parameters.contains(_response.get(i).getCountryCode()))
                filteredProviders.add(_response.get(i));

        return filteredProviders;

    }
    
}
