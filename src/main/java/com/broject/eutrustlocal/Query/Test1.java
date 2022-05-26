package com.broject.eutrustlocal.Query;

import com.broject.eutrustlocal.Creation.BadResponseException;

import java.util.ArrayList;

public class Test1 {

    public static void main(String args[]) throws BadResponseException {

        Query query = new Query();

        ArrayList<String> countries1 = new ArrayList<>();
        ArrayList<String> providers1 = new ArrayList<>();
        ArrayList<String> types1 = new ArrayList<>();
        ArrayList<String> statuses1 = new ArrayList<>();

        countries1.add("AT");

        executeRequest(query, countries1 , providers1, types1, statuses1);

    }

    public static void executeRequest(Query _query, ArrayList<String> _countries, ArrayList<String> _providers, ArrayList<String> _types, ArrayList<String> _statuses) throws BadResponseException {

        System.out.println("----------------------------");
        System.out.println("------ STARTING QUERY ------");
        System.out.println("----------------------------");

        /*_query.editFilterParameter(Query.CRITERIA_FILTERS[0], _countries);
        _query.editFilterParameter(Query.CRITERIA_FILTERS[1], _providers);
        _query.editFilterParameter(Query.CRITERIA_FILTERS[2], _types);
        _query.editFilterParameter(Query.CRITERIA_FILTERS[3], _statuses);*/

        System.out.println("SEARCH CRITERIA:\n"+_query.getCriteria()+"\n");

        ArrayList<String> types = _query.getValidServiceTypes();
        ArrayList<String> statuses = _query.getValidServiceStatuses();
        ArrayList<String> providers = _query.getValidProviders();
        ArrayList<String> services = _query.getValidServices();

        System.out.println("\n-------- RESULTS: --------");

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
            System.out.println(services.get(i));


        System.out.println("----------------------------");
        System.out.println("------- ENDING QUERY -------");
        System.out.println("----------------------------");

    }

}
