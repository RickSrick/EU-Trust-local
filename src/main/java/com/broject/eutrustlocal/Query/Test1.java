package com.broject.eutrustlocal.Query;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Provider;
import com.broject.eutrustlocal.Creation.Data.Service;
import com.broject.eutrustlocal.History.History;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Test1 {

    public static void main(String[] args) throws BadResponseException, FileNotFoundException {

        Query query = new Query();

        ArrayList<String> countries = new ArrayList<>();
        ArrayList<String> providers = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();
        ArrayList<String> statuses = new ArrayList<>();

        countries.add("CZ");
        countries.add("SK");
        //providers.add("First certification authority, a.s.");

        executeRequest(query, countries , providers, types, statuses);


        System.out.println("KABIR " + History.binReader());


    }

    public static void executeRequest(Query _query, ArrayList<String> _countries, ArrayList<String> _providers, ArrayList<String> _types, ArrayList<String> _statuses) throws BadResponseException {

        System.out.println("----------------------------");
        System.out.println("------ STARTING QUERY ------");
        System.out.println("----------------------------");

        for (String country : _countries) _query.editFilterParameter(Query.CRITERIA_FILTERS[0], country);
        for (String provider : _providers) _query.editFilterParameter(Query.CRITERIA_FILTERS[1], provider);
        for (String type : _types) _query.editFilterParameter(Query.CRITERIA_FILTERS[2], type);
        for (String status : _statuses) _query.editFilterParameter(Query.CRITERIA_FILTERS[3], status);

        System.out.println("SEARCH CRITERIA:\n"+_query.getCriteria()+"\n");

        ArrayList<String> types = _query.getValidServiceTypes();
        ArrayList<String> statuses = _query.getValidServiceStatuses();
        ArrayList<Provider> providers = _query.getValidProviders();
        //ArrayList<String> services = _query.getValidServices();

        System.out.println("\n-------- RESULTS: --------");

        System.out.println("-- SERVICE TYPES: --");
        for (String type : types)
            System.out.println(type);

        System.out.println("-- SERVICE STATUSES: --");
        for (String status : statuses)
            System.out.println(status);

        System.out.println("-- PROVIDERS: --");
        for (Provider provider : providers)
            System.out.println(provider.getName());

        System.out.println("-- SERVICES: --");
        for (Provider provider : providers)
            for (Service service : provider.getServices())
                System.out.println(service.getName());


        System.out.println("----------------------------");
        System.out.println("------- ENDING QUERY -------");
        System.out.println("----------------------------");

    }

}
