package com.broject.eutrustlocal.Query;

import com.broject.eutrustlocal.Creation.BadResponseException;

import java.util.ArrayList;

public class Test1 {

    public static void main(String args[]) throws BadResponseException {

        Query query = new Query();

        ArrayList<String> countries = new ArrayList<>();
        ArrayList<String> providers = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();
        ArrayList<String> statuses = new ArrayList<>();

        countries.add("AT");
        countries.add("IT");

        executeRequest(query, countries , providers, types, statuses);

        countries.clear();
        countries.add("AT");

        executeRequest(query, countries , providers, types, statuses);

        countries.clear();
        countries.add("IT");
        countries.add("AT");

        executeRequest(query, countries , providers, types, statuses);

        countries.clear();
        countries.add("UK");

        executeRequest(query, countries , providers, types, statuses);

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
        ArrayList<String> providers = _query.getValidProviders();
        ArrayList<String> services = _query.getValidServices();

        /*System.out.println("\n-------- RESULTS: --------");

        System.out.println("-- SERVICE TYPES: --");
        for (int i = 0; i < types.size(); i++)
            System.out.println(types.get(i));

        System.out.println("-- SERVICE STATUSES: --");
        for (int i = 0; i < statuses.size(); i++)
            System.out.println(statuses.get(i));

        System.out.println("-- PROVIDERS: --");
        for (int i = 0; i < providers.size(); i++)
            System.out.println(providers.get(i));

        System.out.println("-- SERVICES: --");
        for (int i = 0; i < services.size(); i++)
            System.out.println(services.get(i));*/


        System.out.println("----------------------------");
        System.out.println("------- ENDING QUERY -------");
        System.out.println("----------------------------");

    }

}
