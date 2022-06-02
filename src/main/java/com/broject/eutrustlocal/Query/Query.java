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

    public static final String CRITERIA_LINE = "--------";

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
     */
    public Query(String _criteria) {

        newQuery();

        if (_criteria == null) return;

        try {
            String[] parameters = criteriaToParameters(_criteria);

            for (int i = 0; i < parameters.length; i++)
                filters.get(i).addParameters(split(parameters[i]));

        } catch (IllegalArgumentException ignored) {
        }

    }

    /**
     * Returns a criteria sheet, which is the essence of the query;
     * Used to view the filters currently applied and to create a new Query
     *
     * @return the criteria sheet
     */
    public String getCriteria() {

        StringBuilder criteria = new StringBuilder();

        for (int i = 0; i < filters.size(); i++) {

            ArrayList<String> parameters = filters.get(i).getParameters();
            if (i == 2 && filters.get(i).isEmpty()) parameters.clear();
            criteria.append(CRITERIA_FILTERS[i]).append("\n");
            for (String parameter : parameters)
                criteria.append(parameter).append("\n");
            criteria.append(CRITERIA_LINE).append("\n");

        }

        return criteria.toString();

    }

    /**
     * Clears the Query filters and fills them with the new parameters contained in the criteria sheet
     * It's suggested to use only criteria sheets made by <Query.getCriteria()> to guarantee the proper functioning of the constructor;
     * If the criteria sheet is not recognised, or it's null, the Query won't be modified, ignoring the criteria sheet
     *
     * @param _criteria the criteria sheet the Query must respect
     */
    public void setCriteria(String _criteria) {

        if (_criteria == null) return;

        String[] parameters;

        try {
            parameters = criteriaToParameters(_criteria);
        } catch (IllegalArgumentException e) {
            return;
        }

        clearAllFilters();

        for (int i = 0; i < parameters.length; i++)
            filters.get(i).addParameters(split(parameters[i]));

    }

    /**
     * Edits a filter by adding/removing a parameter;
     * If the parameter already exists inside the filter, it will be removed;
     * If the parameter doesn't exist inside the filter, it will be added;
     * If the _filterType doesn't exist, the Query won't be modified;
     * If the _filterType or the _parameter are null, nothing will happen and the Query won't change
     * It's suggested to use the <Query.CRITERIA_FILTERS[]> array to choose the _filterType
     *
     * @param _filterType the filter to be removed
     * @param _parameter  the parameters to be added/removed
     */
    public void editFilterParameter(String _filterType, String _parameter) {

        if (_filterType == null || _parameter == null) return;

        int filterID = nameToID(_filterType);

        if (filterID < 0) return;

        //updating the archives
        if (filters.get(filterID).has(_parameter)) {
            filters.get(filterID).removeParameter(_parameter);
            if (filterID == 0 && newRequestNeeded && addedCountries.contains(_parameter)) {
                countriesArchive.remove(_parameter);
                addedCountries.remove(_parameter);
                if (addedCountries.isEmpty()) newRequestNeeded = false;
            }
        } else {
            String[] parameterArray = {_parameter};
            filters.get(filterID).addParameters(parameterArray);
            if (filterID == 0 && !fullCountriesArchive && !countriesArchive.contains(_parameter)) {
                countriesArchive.add(_parameter);
                addedCountries.add(_parameter);
                newRequestNeeded = true;
            }
        }

        newFilteringNeeded = true;

    }

    /**
     * Returns all the valid providers due to the current parameters selected
     * IF NECESSARY, makes a post request
     *
     * @return all the providers that respect the filters
     * @throws BadResponseException if there is a problem with the POST request
     */
    public ArrayList<Provider> getValidProviders() throws BadResponseException {

        if (newFilteringNeeded) applyFilters();

        return new ArrayList<>(filteredResponse);

    }

    /**
     * Returns all the valid service types due to the current parameters selected
     * IF NECESSARY, makes a post request
     *
     * @return all the service types that respect the filters
     * @throws BadResponseException if there is a problem with the POST request
     */
    public ArrayList<String> getValidServiceTypes() throws BadResponseException {

        if (newFilteringNeeded) applyFilters();

        ArrayList<String> validServiceTypes = new ArrayList<>();

        for (Provider provider : filteredResponse) {
            ArrayList<String> providerServiceTypes = provider.getServiceTypes();
            for (String providerServiceType : providerServiceTypes)
                if (!validServiceTypes.contains(providerServiceType)) validServiceTypes.add(providerServiceType);
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

        if (newFilteringNeeded) applyFilters();

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
     * This method clears a selected filter, maintaining unaltered all the others.
     * If the _filterType doesn't exist, the query won't be modified.
     * It's suggested to use the <Query.CRITERIA_FILTERS[]> array to choose the _filterType
     *
     * @param _filterType The filter to be cleared
     */
    public void clearFilter(String _filterType) {

        int filterID = nameToID(_filterType);

        if (filterID < 0) return;

        filters.get(filterID).clear();

        newFilteringNeeded = true;

    }

    /**
     * This method erases all the parameters from the filters
     */
    public void clearAllFilters() {

        for (Filter filter : filters)
            filter.clear();

        newFilteringNeeded = true;

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

        if (filters.get(0).isEmpty() || filters.get(2).isEmpty()) {

            if (!fullCountriesArchive && filters.get(0).isEmpty()) {
                countriesArchive.clear();
                fullCountriesArchive = true;
                newRequestNeeded = true;
            }

        }

        if (newRequestNeeded) {
            if (addedCountries.isEmpty()) addedCountries = filters.get(0).getParameters();
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

            if (tokenizer.hasNextLine()) {
                line = tokenizer.nextLine();
                if (!line.equals(CRITERIA_FILTERS[i])) throw new IllegalArgumentException();
            } else {
                tokenizer.close();
                return new String[0];
            }

            if (!tokenizer.hasNextLine()) {
                tokenizer.close();
                return new String[0];
            }

            while (!(line = tokenizer.nextLine()).equals(CRITERIA_LINE)) filter.append(line).append("\n");

            parameters[i] = filter.toString();

        }

        tokenizer.close();

        return parameters;

    }

    //Converts a string containing all the parameters into an array of parameters
    private String[] split(String _parameters) {

        ArrayList<String> parameters = new ArrayList<>();

        Scanner tokenizer = new Scanner(_parameters);

        while (tokenizer.hasNextLine()) parameters.add(tokenizer.nextLine());

        tokenizer.close();

        return parameters.toArray(new String[0]);

    }

    //Converts a filter name to it's ID
    private int nameToID(String _name) {

        for (int i = 0; i < CRITERIA_FILTERS.length; i++)
            if (CRITERIA_FILTERS[i].equals(_name)) return i;

        return -1;

    }

}
