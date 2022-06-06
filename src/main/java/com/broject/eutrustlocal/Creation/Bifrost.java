package com.broject.eutrustlocal.Creation;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Class Bifrost
 *
 * @author Riccardo Modolo
 */

public class Bifrost {

    private static Bifrost instance = null;
    private final HttpClient client;
    private final String rootRequest = "https://esignature.ec.europa.eu/efda/tl-browser/api/v1/search/";

    private Bifrost() {

        client = HttpClient.newHttpClient();

    }

    /**
     * Returns the only Bifrost with default settings, or create one in first call.
     *
     * @return the only Bifrost
     */
    public static Bifrost newBifrost() {

        if (instance == null) instance = new Bifrost();

        return instance;

    }

    /**
     * This function is a fast method to check connection
     *
     * @throws BadResponseException when you are offline.
     */
    public static void checkConnection() throws BadResponseException {

        try {
            URL u = new URL("https://www.google.com");
            URLConnection conn = u.openConnection();
            conn.connect();
        } catch (Exception e) {
            throw new BadResponseException();
        }   

    }

    /**
     * Try to ask response from a httpRequest
     *
     * @param request The http request
     * @return response body if connection was established, or null when you are offline
     */
    private String tryGetResponse(HttpRequest request) {

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return null;
        }

        return response.body();

    }

    /**
     * This function use https://flagsapi.com/ to provide link to flag png file
     *
     * @param countryCode the code of the country whose flag you wish to obtain
     * @return the link to the flag, if it exists
     */
    public String getFlagImageLink(String countryCode) {
        return "https://flagsapi.com/" + CheckISOStandard(countryCode) + "/flat/64.png";

    }

    /**
     * A converter for unstandardized EU API countryCode format, to correct ISO 3166 format
     *
     * @param countryCode from EU API
     * @return the correct version according to ISO3166
     */
    private String CheckISOStandard(String countryCode) {

        String out = countryCode.toUpperCase();
        if (out.equals("UK")) return "GB";
        else if (out.equals("EL")) return "GR";

        return out;

    }

    /**
     * this method provide response from the following server GET request: https://esignature.ec.europa.eu/efda/tl-browser/api/v1/search/countries_list_no_lotl_territory
     *
     * @return the body of response
     * @throws BadResponseException when you are offline
     */
    public String getCountries() throws BadResponseException {

        checkConnection();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(rootRequest + "countries_list_no_lotl_territory")).build();
        String response = tryGetResponse(request);

        if (response == null) throw new BadResponseException("problem with server connection");

        return response;

    }

    /**
     * this method provide response from the following server POST request: https://esignature.ec.europa.eu/efda/tl-browser/api/v1/search/find_by_type
     *
     * @param body in json format string
     * @return the body of response
     * @throws BadResponseException when you are offline, insert invalid body, or the query return zero values
     */
    public String findTrustServices(String body) throws BadResponseException {

        checkConnection();
        if (body == null || body.equals("")) throw new BadResponseException();

        HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(body)).uri(URI.create(rootRequest + "find_by_type")).header("Content-Type", "application/json").build();

        String out = tryGetResponse(request);
        if (out == null) throw new BadResponseException("problem with server connection");
        if (out.equals("[]")) throw new BadResponseException("Body Data invalid");
        if (out.contains("UNEXPECTED_ERROR")) throw new BadResponseException("Body Data invalid");

        return out;

    }

}