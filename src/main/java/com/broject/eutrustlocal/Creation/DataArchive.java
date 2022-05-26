package com.broject.eutrustlocal.Creation;

import com.broject.eutrustlocal.Creation.Data.Country;
import com.broject.eutrustlocal.Creation.Data.Provider;
import com.broject.eutrustlocal.Creation.Data.Service;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Zanella Matteo
 */

public class DataArchive {

    //#region All the service types
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
    private static DataArchive instance = null;
    private final ArrayList<Country> countries;
    private Bifrost connection;
    //#endregion

    private DataArchive() throws BadResponseException {

        countries = new ArrayList<>();
        update();

    }

    /**
     * Returns the only DataArchive with default settings.
     *
     * @return the only DataArchive
     * @throws BadResponseException if there is a problem with the POST request
     */
    public static DataArchive newDataArchive() throws BadResponseException {

        if (instance == null)
            instance = new DataArchive();

        return instance;

    }

    public static boolean checkOfflineStatus() {
        try {
            Bifrost.checkConnection();
        }
        catch (BadResponseException e) {
            return true;
        }
        return false;
    }

    //Conversion from json file to an array of jsonObjects
    private static JSONArray jsonToArray(String _json) {

        if (_json == null)
            return new JSONArray(0);

        if (_json.charAt(0) == '[')
            return new JSONArray(_json);

        JSONObject element = new JSONObject(_json);
        JSONArray data = new JSONArray();
        data.put(element);
        return data;

    }

    /**
     * Method that updates the connection with the server and the country list
     *
     * @throws BadResponseException if there is a problem with the POST request
     */
    public void update() throws BadResponseException {

        connection = Bifrost.newBifrost();
        jsonToCountries(connection.getCountries());

    }

    /**
     * Returns a ArrayList containing all the countries in the UE
     *
     * @return all the countries in the UE
     * @throws BadResponseException if there is a problem with the POST request
     */
    public ArrayList<Country> getCountries() throws BadResponseException {

        jsonToCountries(connection.getCountries());
        return new ArrayList<>(countries);

    }

    /**
     * Returns a ArrayList containing all the country codes in the UE
     *
     * @return all the country codes in the UE
     * @throws BadResponseException if there is a problem with the POST request
     */
    public ArrayList<String> getCountryCodes() throws BadResponseException {

        jsonToCountries(connection.getCountries());
        ArrayList<String> countryCodes = new ArrayList<>();
        for (Country country : countries)
            countryCodes.add(country.getCountryCode());

        return countryCodes;

    }

    /**
     * Returns the number of countries in the UE
     *
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
     * - selected countries
     * - selected types
     * then converts the json file obtained in a ArrayList of providers
     *
     * @param _countries    countries selected
     * @param _serviceTypes service types selected
     * @return all the providers that respond to the given filter
     * @throws BadResponseException if there is a problem with the POST request
     */
    public ArrayList<Provider> getProviders(String[] _countries, String[] _serviceTypes) throws BadResponseException {

        StringBuilder jsonPOST = new StringBuilder();

        //Composing POST request
        jsonPOST.append("{ \"countries\": [");
        for (int i = 0; i < _countries.length; i++) {
            jsonPOST.append("\"").append(_countries[i]).append("\"");
            if (i != _countries.length - 1)
                jsonPOST.append(" , ");
        }
        jsonPOST.append("], \"qServiceTypes\": [ ");
        for (int i = 0; i < _serviceTypes.length; i++) {
            jsonPOST.append("\"").append(_serviceTypes[i]).append("\"");
            if (i != _serviceTypes.length - 1)
                jsonPOST.append(" , ");
        }
        jsonPOST.append(" ] }");

        System.out.println(jsonPOST.toString());

        String json = connection.findTrustServices(jsonPOST.toString());

        return jsonToProviders(json);

    }

    //Conversion from json file, obtained from POST request, into a list of providers populated by services
    private ArrayList<Provider> jsonToProviders(String _json) {

        JSONArray jsonPOST = jsonToArray(_json);

        ArrayList<Provider> providers = new ArrayList<>();

        //for each provider
        for (int i = 0; i < jsonPOST.length(); i++) {

            JSONObject JSONProvider = jsonPOST.getJSONObject(i);

            Provider provider = new Provider(JSONProvider.getString("name"), JSONProvider.getString("countryCode"), connection.getFlagImageLink(JSONProvider.getString("countryCode")));

            JSONArray JSONServices = JSONProvider.getJSONArray("services");
            JSONArray JSONProviderServiceTypes = new JSONArray();

            //for each service in current provider
            for (int j = 0; j < JSONServices.length(); j++) {

                JSONObject JSONService = JSONServices.getJSONObject(j);

                String serviceName = JSONService.getString("serviceName");

                JSONArray JSONServiceTypes = JSONService.getJSONArray("qServiceTypes");
                JSONProviderServiceTypes.put(JSONServiceTypes);
                String[] serviceTypes = new String[JSONServiceTypes.length()];
                for (int k = 0; k < serviceTypes.length; k++)
                    serviceTypes[k] = JSONServiceTypes.getString(k);

                String serviceStatus = JSONService.getString("currentStatus").substring(50);

                Service service = new Service(serviceName, serviceTypes, serviceStatus);

                provider.addService(service);

            }

            for (int j = 0; j < JSONProviderServiceTypes.length(); j++) {
                JSONArray temp = JSONProviderServiceTypes.getJSONArray(j);
                for (int k = 0; k < temp.length(); k++)
                    provider.addServiceType(temp.getString(k));
            }

            providers.add(provider);

        }

        return providers;

    }

    //Conversion from json file, obtained from GET request, into a list of countries
    private void jsonToCountries(String _json) {

        JSONArray jsonCountryList = jsonToArray(_json);

        if (jsonCountryList.length() == countries.size())
            return;

        countries.clear();

        for (int i = 0; i < jsonCountryList.length(); i++) {
            String countryName = jsonCountryList.getJSONObject(i).getString("countryName");
            String countryCode = jsonCountryList.getJSONObject(i).getString("countryCode");
            Country country = new Country(countryName, countryCode, connection.getFlagImageLink(countryCode));
            countries.add(country);
        }

    }

}