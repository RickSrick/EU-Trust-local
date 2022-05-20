package com.broject.eutrustlocal.Creation;

import java.util.Vector;

import com.broject.eutrustlocal.Creation.Data.Country;
import com.broject.eutrustlocal.Creation.Data.Provider;
import com.broject.eutrustlocal.Creation.Data.Service;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Zanella Matteo
 */

public class DataArchive {

    private Vector<Country> countries;
    private Bifrost connection;
    private static DataArchive instance = null;

    //All the service types
    public static final String[] SERVICE_TYPES = {
        "QCertESig",
        "QCertESeal",
        "QWAC",
        "QValQESig",
        "QPresQESig",
        "QValQESeal",
        "QPresQESeal",
        "QTimestamp",
        "QeRDS",
        "CertESig",
        "CertESeal",
        "WAC",
        "ValESig",
        "GenESig",
        "PresESig",
        "ValESeal",
        "GenESeal",
        "PresESeal",
        "Timestamp",
        "eRDS",
        "NonRegulatory",
        "CertUndefined"
    };

    private DataArchive() throws BadResponseException {
        
        countries = new Vector<>();
        update();
    
    }

    /**
     * Returns the only DataArchive with default settings.
     * @return the only DataArchive
     * @throws BadResponseException if there is a problem with the POST request
     */
    public static DataArchive newDataArchive() throws BadResponseException {

        if (instance == null) instance = new DataArchive();
        return instance;

    }

    /**
     * Method that updates the connection with the server and the country list
     * @throws BadResponseException if there is a problem with the POST request
     */
    public void update() throws BadResponseException {

        connection = Bifrost.newBifrost();

        jsonToCountries(connection.getCountries());

    }

    /**
     * Returns a vector containing all the countries in the UE
     * @return all the countries in the UE
     * @throws BadResponseException if there is a problem with the POST request
     */
    public Vector<Country> getCountries() throws BadResponseException {
        
        jsonToCountries(connection.getCountries());

        return new Vector<>(countries);
    
    }

    /**
     * Returns a vector containing all the countries in the UE
     * @return all the countries in the UE
     * @throws BadResponseException if there is a problem with the POST request
     */
    public Vector<String> getCountriesID() throws BadResponseException {

        jsonToCountries(connection.getCountries());
        Vector<String> countriesID = new Vector<>();
        for (Country country : countries)
            countriesID.add(country.getID());

        return countriesID;
    
    }

    /**
     * Returns the number of countries in the UE
     * @return the number of countries in the UE
     * @throws BadResponseException if there is a problem with the POST request
     */
    public int getCountriesSize() throws BadResponseException {

        if (countries.isEmpty())
            jsonToCountries(connection.getCountries());

        return countries.size();

    }

    /**
     * Create a POST request with the following filters:
     *  - selected countries
     *  - selected types
     * then converts the json file obtained in a Vector of providers
     * @param _countries countries selected
     * @param _serviceTypes service types selected
     * @return all the providers that respond to the given filter
     * @throws BadResponseException if there is a problem with the POST request
     */
    public Vector<Provider> getProviders(String[] _countries, String[] _serviceTypes) throws BadResponseException {

        StringBuilder jsonPOST = new StringBuilder();
        
        //Composing POST request
        jsonPOST.append("{ \"countries\": [");
        for (int i = 0; i < _countries.length; i++) {
            jsonPOST.append("\"").append(_countries[i]).append("\"");
            if (i != _countries.length - 1) jsonPOST.append(" , ");
        }
        jsonPOST.append("], \"qServiceTypes\": [ ");
        for (int i = 0; i < _serviceTypes.length; i++) {
            jsonPOST.append("\"").append(_serviceTypes[i]).append("\"");
            if (i != _serviceTypes.length - 1) jsonPOST.append(" , ");
        }
        jsonPOST.append(" ] }");

        String json = connection.findTrustServices(jsonPOST.toString());

        return jsonToProviders(json);

    }

    //Conversion from json file, obtained from POST request, into a list of providers populated by services
    private Vector<Provider> jsonToProviders(String _json) {

        JSONArray jsonPOST = jsonToArray(_json);

        Vector<Provider> providers = new Vector<>();

        //for each provider
        for (int i = 0; i < jsonPOST.length(); i++) {

            JSONObject JSONProvider = jsonPOST.getJSONObject(i);

            Provider provider = new Provider(JSONProvider.getString("name"), JSONProvider.getString("countryCode"), connection.getFlagImageURL(JSONProvider.getString("countryCode")));

            JSONArray services = JSONProvider.getJSONArray("services");

            //for each service in current provider
            for (int j = 0; j < services.length(); j++) {

                JSONObject JSONService = services.getJSONObject(j);

                String serviceName = JSONService.getString("serviceName");

                JSONArray JSONServiceTypes = JSONService.getJSONArray("qServiceTypes");
                String[] serviceTypes = new String[JSONServiceTypes.length()];
                for (int k = 0; k < serviceTypes.length; k++) serviceTypes[k] = JSONServiceTypes.getString(k);
                
                String serviceStatus = JSONService.getString("currentStatus").substring(50);

                Service service = new Service(serviceName, serviceTypes, serviceStatus);

                provider.addService(service);

            }

            JSONArray JSONServiceTypes = JSONProvider.getJSONArray("qServiceTypes");

            for (int j = 0; j < JSONServiceTypes.length(); j++)
                provider.addServiceType(JSONServiceTypes.getString(j));

            providers.add(provider);

        }

        return providers;

    }

    //Conversion from json file, obtained from GET request, into a list of countries
    private void jsonToCountries(String _json) {

        JSONArray jsonCountryList = jsonToArray(_json);

        if (jsonCountryList.length() == countries.size()) return;

        countries.clear();

        for (int i = 0; i < jsonCountryList.length(); i++) {
            String countryCode = jsonCountryList.getJSONObject(i).getString("countryCode");
            Country country = new Country(jsonCountryList.getJSONObject(i).getString("countryName"), countryCode, connection.getFlagImageURL(countryCode));
            countries.add(country);
        }

    }

    //Conversion from json file to an array of jsonObjects
    private static JSONArray jsonToArray(String _json) {

        if (_json == null)
            return new JSONArray(0);

        if (_json.charAt(0)=='[')
            return new JSONArray(_json);

        JSONObject element = new JSONObject(_json);
        JSONArray data = new JSONArray();
        data.put(element);
        return data;

    }

}