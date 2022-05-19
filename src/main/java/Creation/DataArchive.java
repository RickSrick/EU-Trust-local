package Creation;

import java.util.Vector;

import Creation.Data.Country;
import Creation.Data.Provider;
import Creation.Data.Service;
import org.json.JSONArray;
import org.json.JSONObject;

public class DataArchive {

    private final Vector<Country> countries;
    private Bifrost connection;
    private static DataArchive instance = null;

    //#region Service Types
    public static final String[] serviceTypes = {
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
    //#endregion

    private DataArchive() throws BadResponseException {
        countries = new Vector<>();
        update();
    }

    /**
     * Returns the only <code>DataArchive</code> with default settings.<br>
     * @return the only DataArchive
     */
    public static DataArchive newDataArchive() throws BadResponseException {
        if (instance == null) instance = new DataArchive();
        return instance;
    }

    /**
     * Method that updates the connection with the server and the country list
     */
    public void update() throws BadResponseException {

        connection = Bifrost.newBifrost();
        jsonToCountries(connection.getCountries());

    }

    /**
     * Returns a vector containing all the countries in the UE
     * @return all the countries in the UE
     */
    public Vector<Country> getCountries() throws BadResponseException {

        jsonToCountries(connection.getCountries());
        return (Vector<Country>)countries.clone();
    }

    /**
     * Create a POST request with the following filters:
     *  - selected countries
     *  - selected types
     * then converts the json file obtained in a Vector of providers
     * @param _countries countries selected
     * @param _types service types selected
     * @return all the providers corresponding to the given filter
     */
    public Vector<Provider> getProviders(String[] _countries, String[] _types) throws BadResponseException {

        StringBuilder jsonPOST = new StringBuilder();

        //Composing POST request
        jsonPOST.append("{ \"countries\": [");
        for (int i = 0; i < _countries.length; i++) {
            jsonPOST.append("\"").append(_countries[i]).append("\"");
            if (i != _countries.length - 1) jsonPOST.append(" , ");
        }
        jsonPOST.append("], \"qServiceTypes\": [ ");
        for (int i = 0; i < _types.length; i++) {
            jsonPOST.append("\"").append(_types[i]).append("\"");
            if (i != _types.length - 1) jsonPOST.append(" , ");
        }
        jsonPOST.append(" ] }");

        String json = connection.findTrustServices(jsonPOST.toString());

        return jsonToProviders(json);

    }

    public Vector<Provider> getProviders(String[] countries) throws BadResponseException {
        return getProviders(countries, serviceTypes);
    }

    //Conversion from json file, obtained from POST request, into a list of providers populated by services
    private static Vector<Provider> jsonToProviders(String _json) {

        JSONArray jsonPOST = jsonToArray(_json);
        Vector<Provider> providers = new Vector<>();

        //for each provider
        for (int i = 0; i < jsonPOST.length(); i++) {

            JSONObject JSONProvider = jsonPOST.getJSONObject(i);

            Provider provider = new Provider(JSONProvider.getString("name"));

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
            Country country = new Country(jsonCountryList.getJSONObject(i).get("countryName").toString(), jsonCountryList.getJSONObject(i).get("countryCode").toString(), "_flagLink");
            countries.add(country);
        }

    }

    //Conversion from json file to an array of jsonObjects
    private static JSONArray jsonToArray(String _json) {

        if (_json.charAt(0)=='[')
            return new JSONArray(_json);

        JSONObject element = new JSONObject(_json);
        JSONArray data = new JSONArray();
        data.put(element);
        return data;

    }

}