package com.broject.eutrustlocal.Creation;
import java.net.URI;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author Riccardo Modolo
 */

public class Bifrost {

    private final HttpClient client;
    private final String rootRequest = "https://esignature.ec.europa.eu/efda/tl-browser/api/v1/search/";
    private static Bifrost instance = null;

    private Bifrost() {

        client = HttpClient.newHttpClient();

    }

    public static Bifrost newBifrost() {

        if(instance == null)
            instance = new Bifrost();

        return instance;

    }

    private String tryGetResponse(HttpRequest request) {

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch ( IOException | InterruptedException e){ return null; }

        return response.body();

    }

    public String getFlagImageLink(String countryCode) {
        return "https://flagsapi.com/"+CheckISOStandard(countryCode)+"/flat/64.png";

    }

    private String CheckISOStandard(String countryCode) {
        String out = countryCode.toUpperCase();
        if(out.equals("UK")) return "GB";
        if(out.equals("EL")) return "GR";
        return out;
    }

    //#region CRUD operation
    public String getCountries() throws BadResponseException {

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(rootRequest + "countries_list_no_lotl_territory")).build();
        String response = tryGetResponse(request);

        if(response == null)
            throw new BadResponseException("problem with server connection");

        return  response;

    }

    public String findTrustServices(String body) throws BadResponseException {

        if(body == null || body.equals(""))
            throw new BadResponseException();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create(rootRequest + "find_by_type"))
                .header("Content-Type", "application/json")
                .build();

        String out = tryGetResponse(request);
        if(out == null) throw new BadResponseException("problem with server connection");
        if(out.equals("[]")) throw new BadResponseException("Body Data invalid");
        if(out.contains("UNEXPECTED_ERROR")) throw new BadResponseException("Body Data invalid");

        System.out.println(out);

        return out;

    }
    //#endregion

}