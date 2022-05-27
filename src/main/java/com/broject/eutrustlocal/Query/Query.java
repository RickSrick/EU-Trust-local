package com.broject.eutrustlocal.Query;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Provider;
import com.broject.eutrustlocal.Creation.Data.Service;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Query.Filter.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Zanella Matteo
 */

public class Query {

    /**
     * Filter names list;
     * Necessary for identification;
     * - [0] = "COUNTRIES"
     * - [1] = "PROVIDERS"
     * - [2] = "SERVICE_TYPES"
     * - [3] = "SERVICE_STATUSES"
     */
    public static final String[] CRITERIA_FILTERS = {"COUNTRIES", "PROVIDERS", "SERVICE_TYPES", "SERVICE_STATUSES"};

    private static final String CRITERIA_LINE = "--------";

    private ArrayList<Provider> response;
    private ArrayList<Provider> filteredResponse;
    private ArrayList<Filter> filters;

    private ArrayList<String> countriesArchive;
    private ArrayList<String> addedCountries;

    private boolean newFilteringNeeded;
    private boolean newRequestNeeded;

    private boolean fullCountriesArchive;

    /**
     * Default Query constructor
     */
    public Query() {

        newQuery();

    }

    /**
     * Creates a Query out of a criteria sheet;
     * It's suggested to use only criteria sheets made by <Query.getCriteria()> to guarantee the proper functioning of the constructor;
     * If the criteria sheet is not recognised, or it's null, the Query will be created normally from scratch, ignoring the criteria sheet
     *
     * @param _criteria the criteria sheet
     * @throws BadResponseException if there is a problem with the POST request
     */
    public Query(String _criteria) throws BadResponseException {

        newQuery();

        if (_criteria == null)
            throw new BadResponseException();

        String[] parameters = criteriaToParameters(_criteria);

        for (int i = 0; i < parameters.length; i++)
            filters.get(i).addParameters(split(parameters[i]));

    }

    /**
     * Returns a criteria sheet, which is the essence of the query;
     * Used to view the filters currently applied and to create a new Query
     *
     * @return the criteria sheet
     * @throws BadResponseException if there is a problem with the POST request
     */
    public String getCriteria() throws BadResponseException {

        StringBuilder criteria = new StringBuilder();

        for (int i = 0; i < filters.size(); i++) {

            ArrayList<String> parameters = filters.get(i).getParameters();
            criteria.append(CRITERIA_FILTERS[i]).append("\n");
            for (String parameter : parameters)
                criteria.append(parameter).append("\n");
            criteria.append(CRITERIA_LINE).append("\n");

        }

        return criteria.toString();

    }

    /**
     * Edits a filter by adding/removing a parameter;
     * If the parameter already exists inside the filter, it will be removed;
     * If the parameter doesn't exist inside the filter, it will be added;
     * If the _filterType doesn't exist, the Query won't be modified;
     * If the _filterType or the _parameter are null, nothing will happen and the Query won't change
     *
     * @param _filterType the filter to be removed
     * @param _parameter  the parameters to be added/removed
     */
    public void editFilterParameter(String _filterType, String _parameter) {

        if (_filterType == null || _parameter == null)
            return;

        int filterIndex = nameToID(_filterType);

        //updating the archives
        if (filters.get(filterIndex).has(_parameter)) {
            filters.get(filterIndex).removeParameter(_parameter);
            if (filterIndex == 0 && newRequestNeeded && addedCountries.contains(_parameter)) {
                countriesArchive.remove(_parameter);
                addedCountries.remove(_parameter);
                System.out.println("Removing " + _parameter + " : " + addedCountries);                                                      /*------------------------------------------------------------------*/
                if (addedCountries.isEmpty()) {
                    newRequestNeeded = false;
                    System.out.println("Now addedCountries is empty");                                                                      /*------------------------------------------------------------------*/
                }
            }
        } else {
            String[] parameterArray = {_parameter};
            filters.get(filterIndex).addParameters(parameterArray);
            if (filterIndex == 0 && !fullCountriesArchive && !countriesArchive.contains(_parameter)) {
                countriesArchive.add(_parameter);
                addedCountries.add(_parameter);
                newRequestNeeded = true;
                System.out.println("Adding " + _parameter + " : " + addedCountries);                                                        /*------------------------------------------------------------------*/
            }
        }

        newFilteringNeeded = true;

    }

    /**
     * Returns the names of all the valid services due to the current parameters selected
     * IF NECESSARY, makes a post request
     *
     * @return all the services that respect the filters
     * @throws BadResponseException if there is a problem with the POST request
     */
    public ArrayList<String> getValidServices() throws BadResponseException {

        if (newFilteringNeeded)
            applyFilters();

        ArrayList<String> services = new ArrayList<>();

        for (Provider provider : filteredResponse) {
            ArrayList<Service> providerServices = provider.getServices();
            for (Service providerService : providerServices)
                services.add(providerService.getName());
        }

        return services;

    }

    /**
     * Returns the names of all the valid providers due to the current parameters selected
     * IF NECESSARY, makes a post request
     *
     * @return all the providers that respect the filters
     * @throws BadResponseException if there is a problem with the POST request
     */
    public ArrayList<String> getValidProviders() throws BadResponseException {

        if (newFilteringNeeded)
            applyFilters();

        ArrayList<String> validProviders = new ArrayList<>();

        for (Provider provider : filteredResponse)
            validProviders.add(provider.getName());

        return validProviders;

    }

    /**
     * Returns all the valid service types due to the current parameters selected
     * IF NECESSARY, makes a post request
     *
     * @return all the service types that respect the filters
     * @throws BadResponseException if there is a problem with the POST request
     */
    public ArrayList<String> getValidServiceTypes() throws BadResponseException {

        if (newFilteringNeeded)
            applyFilters();

        ArrayList<String> validServiceTypes = new ArrayList<>();

        for (Provider provider : filteredResponse) {
            ArrayList<String> providerServiceTypes = provider.getServiceTypes();
            for (String providerServiceType : providerServiceTypes)
                if (!validServiceTypes.contains(providerServiceType))
                    validServiceTypes.add(providerServiceType);
        }

        return validServiceTypes;

    }

    /**
     * Returns all the valid service statuses due to the current parameters selected
     * IF NECESSARY, makes a post request
     *
     * @return all the service statuses that respect the filters
     * @throws BadResponseException if there is a problem with the POST request
     */
    public ArrayList<String> getValidServiceStatuses() throws BadResponseException {

        if (newFilteringNeeded)
            applyFilters();

        ArrayList<String> validServiceStatuses = new ArrayList<>();

        for (Provider provider : filteredResponse) {
            ArrayList<Service> providerServices = provider.getServices();
            for (Service providerService : providerServices) {
                if (!validServiceStatuses.contains(providerService.getStatus()))
                    validServiceStatuses.add(providerService.getStatus());
            }
        }

        return validServiceStatuses;

    }

    /**
     * Returns the full response of the Query
     * IF NECESSARY, filters again the response
     *
     * @return a ArrayList of Provider, the response of the Query
     * @throws BadResponseException if there is a problem with the POST request
     */
    public ArrayList<Provider> getResponse() throws BadResponseException {

        if (newFilteringNeeded)
            applyFilters();

        return new ArrayList<>(filteredResponse);

    }

    /**
     * This method clears all the temporary archives contained in the Query;
     * ! THE FILTERS WILL REMAIN UNCHANGED !;
     * This method also prepares the Query to actuate a POST request (doesn't make it)
     */
    public void clear() {

        countriesArchive.clear();

        newRequestNeeded = true;
        newFilteringNeeded = true;
        fullCountriesArchive = false;

    }

    /**
     * This method erases all the parameters from the filters
     */
    public void clearFilters() {

        for (Filter filter : filters)
            filter.clear();

    }

    //Basic commands to create a Query from scratch
    private void newQuery() {

        response = new ArrayList<>();
        filteredResponse = new ArrayList<>();

        filters = new ArrayList<>();
        filters.add(new FilterCountry());
        filters.add(new FilterProvider());
        filters.add(new FilterServiceType());
        filters.add(new FilterServiceStatus());

        countriesArchive = new ArrayList<>();
        addedCountries = new ArrayList<>();

        fullCountriesArchive = false;
        newRequestNeeded = true;

    }

    //IF NEEDED creates a post request and IF NEEDED filters the response
    private void applyFilters() throws BadResponseException {

        System.out.println(getCriteria());                                                                                                  /*------------------------------------------------------------------*/

        if (filters.get(0).isEmpty() || filters.get(2).isEmpty()) {

            if (!fullCountriesArchive && filters.get(0).isEmpty()) {
                countriesArchive.clear();
                fullCountriesArchive = true;
                newRequestNeeded = true;
            }

        }

        if (newRequestNeeded) {
            if (addedCountries.isEmpty())
                addedCountries = filters.get(0).getParameters();
            System.out.println("Contacting the server; asking for: ");
            System.out.println(addedCountries);                                                                                             /*------------------------------------------------------------------*/
            ArrayList<Provider> newResponse = DataArchive.newDataArchive().getProviders(addedCountries.toArray(new String[0]), DataArchive.SERVICE_TYPES);
            response.addAll(newResponse);
            newRequestNeeded = false;
        }

        addedCountries.clear();

        filteredResponse = response;

        for (Filter filter : filters)
            filteredResponse = filter.applyFilter(filteredResponse);

        newFilteringNeeded = false;

    }

    //Converts the criteria sheet into an array of parameters divided by type of filter
    private String[] criteriaToParameters(String _criteria) {

        Scanner tokenizer = new Scanner(_criteria);

        if (!tokenizer.hasNextLine()) {
            tokenizer.close();
            return new String[0];
        }

        String line;
        String[] parameters = new String[4];

        for (int i = 0; i < filters.size(); i++) {

            StringBuilder filter = new StringBuilder();

            if (tokenizer.hasNextLine()) tokenizer.nextLine();
            else {
                tokenizer.close();
                return new String[0];
            }

            if (!tokenizer.hasNextLine()) {
                tokenizer.close();
                return new String[0];
            }

            while (!(line = tokenizer.nextLine()).equals(CRITERIA_LINE))
                filter.append(line).append("\n");

            parameters[i] = filter.toString();

        }

        tokenizer.close();

        return parameters;

    }

    //Converts a string containing all the parameters into an array of parameters
    private String[] split(String _parameters) {

        ArrayList<String> parameters = new ArrayList<>();

        Scanner tokenizer = new Scanner(_parameters);

        while (tokenizer.hasNextLine())
            parameters.add(tokenizer.nextLine());

        tokenizer.close();

        return parameters.toArray(new String[0]);

    }

    //Converts a filter name to it's ID
    private int nameToID(String _name) {

        for (int i = 0; i < CRITERIA_FILTERS.length; i++)
            if (CRITERIA_FILTERS[i].equals(_name))
                return i;

        return -1;

    }

}
