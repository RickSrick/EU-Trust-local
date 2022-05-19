package Query;

import Creation.Data.Provider;
import Creation.Data.Service;
import Creation.DataArchive;
import Query.Filter.*;

import java.util.Vector;
import java.util.Scanner;

public class Query {

    /**
     * Filter names list;
     * Necessary for identification;
     *  - [0] = "COUNTRIES"
     *  - [1] = "PROVIDERS"
     *  - [2] = "SERVICE_TYPES"
     *  - [3] = "SERVICE_STATUSES"
     */
    public static final String[] CRITERIA_FILTERS = {"COUNTRIES","PROVIDERS","SERVICE_TYPES","SERVICE_STATUSES"};
    
    private static final String CRITERIA_LINE = "--------";

    private Vector<Provider> response;
    private Vector<Provider> filteredResponse;
    private Vector<Filter> filters;

    private Vector<String> countriesArchive;
    private Vector<String> serviceTypesArchive;

    private boolean newFilteringNeeded;
    private boolean newRequestNeeded;

    private boolean fullCountriesArchive;
    private boolean fullServiceTypesArchive;

    /**
     * Default Query constructor
     */
    public Query() {

        newQuery();

    }

    /**
     * Creates a Query out of a criteria sheet;
     * It's suggested to use only criteria sheets made by <Query.getCriteria()> to guarantee the proper functioning of the constructor;
     * If the criteria sheet is not recognised or it's null, the Query will be created normaly from scratch, ignoring the criteria sheet
     * @param _criteria the criteria sheet
     */
    public Query(String _criteria) {
        
        newQuery();

        if (_criteria == null)
            throw new IllegalArgumentException();

        String[] parameters = criteriaToParameters(_criteria);

        for (int i = 0; i < parameters.length; i++)
            filters.get(i).addParameters(split(parameters[i]));

    }

    /**
     * Returns a criteria sheet, which is the essence of the query;
     * Used to view the filters currently applied and to create a new Query
     * @return the criteria sheet
     */
    public String getCriteria() {

        StringBuilder criteria = new StringBuilder();

        for (int i = 0; i < filters.size(); i++) {

            Vector<String> parameters = filters.get(i).getParameters();
            criteria.append(CRITERIA_FILTERS[i]).append("\n");
            for (int j = 0; j < parameters.size(); j++)
                criteria.append(parameters.get(j)).append("\n");
            criteria.append(CRITERIA_LINE).append("\n");

        }

        return criteria.toString();

    }

    /**
     * Edits a filter by adding/removing a parameter;
     * If the parameter already exists inside the filter, it will be removed;
     * If the parameter doesn't exists inside the filter, it will be added;
     * If the _filterType doesn't exist, the Query won't be modified;
     * If the _filterType or the _parameter are null, nothing will happen and the Query won't change
     * @param _filterType the filter to be removed
     * @param _parameter the parameter to be added/removed
     */
    public void editFilterParameter(String _filterType, String _parameter) {

        if (_filterType == null || _parameter == null)
            return;

        int filterIndex = nameToID(_filterType);
        
        //updating the archives
        if (filters.get(filterIndex).has(_parameter)) {
            filters.get(filterIndex).removeParameter(_parameter);
            newFilteringNeeded = true;
        } else {
            String[] parameter = {_parameter};
            filters.get(filterIndex).addParameters(parameter);
            switch(filterIndex) {
                case 0:
                    if (!countriesArchive.contains(_parameter)) {
                        countriesArchive.add(_parameter);
                        newRequestNeeded = true;
                        newFilteringNeeded = true;
                    }
                    break;
                case 2:
                    if (!serviceTypesArchive.contains(_parameter)) {
                        serviceTypesArchive.add(_parameter);
                        newRequestNeeded = true;
                        newFilteringNeeded = true;
                    }
                    break;
                default:
                    return;
            }
        }

        if (filters.get(0).isEmpty() || filters.get(2).isEmpty()) {

            if (filters.get(0).isEmpty() && !fullCountriesArchive || filters.get(2).isEmpty() && !fullServiceTypesArchive)
                newRequestNeeded = true;
            
            if (filters.get(0).isEmpty() && !fullCountriesArchive) {
                countriesArchive = DataArchive.newDataArchive().getCountriesID();
                fullCountriesArchive = true;
            }
            if (filters.get(2).isEmpty() && !fullServiceTypesArchive) {
                serviceTypesArchive.clear();
                for (int i = 0; i < DataArchive.SERVICE_TYPES.length; i++)
                    serviceTypesArchive.add(DataArchive.SERVICE_TYPES[i]);
                fullServiceTypesArchive = true;
            }

        }

    }

    /**
     * Returns the names of all the valid services due to the current parameters selected
     * IF NECESSARY, makes a post request
     * @return all the services that respect the filters
     */
    public Vector<String> getValidServices() {

        Vector<Provider> providers = filteredResponse;

        if (newFilteringNeeded)
            providers = executeQuery();

        Vector<String> services = new Vector<>();

        for (int i = 0; i < providers.size(); i++) {
            Vector<Service> providerServices = providers.get(i).getServices();
            for (int j = 0; j < providerServices.size(); j++)
                services.add(providerServices.get(j).getName());
        }

        return services;

    }

    /**
     * Returns the names of all the valid providers due to the current parameters selected
     * IF NECESSARY, makes a post request
     * @return all the providers that respect the filters
     */
    public Vector<String> getValidProviders() {

        Vector<Provider> providers = filteredResponse;

        if (newFilteringNeeded)
            providers = executeQuery();

        Vector<String> validProviders = new Vector<>();

        for (int i = 0; i < providers.size(); i++)
            validProviders.add(providers.get(i).getName());

        return validProviders;

    }

    /**
     * Returns all of the valid service types due to the current parameters selected
     * IF NECESSARY, makes a post request
     * @return all the service types that respect the filters
     */
    public Vector<String> getValidServiceTypes() {

        Vector<Provider> providers = filteredResponse;

        if (newFilteringNeeded)
            providers = executeQuery();

        Vector<String> validServiceTypes = new Vector<>();

        for (int i = 0; i < providers.size(); i++) {
            Vector<String> providerServiceTypes = providers.get(i).getServiceTypes();
            for (int j = 0; j < providerServiceTypes.size(); j++)
                if (!validServiceTypes.contains(providerServiceTypes.get(j)))
                    validServiceTypes.add(providerServiceTypes.get(j));
        }

        return validServiceTypes;

    }

    /**
     * Returns all of the valid service statuses due to the current parameters selected
     * IF NECESSARY, makes a post request
     * @return all the service statuses that respect the filters
     */
    public Vector<String> getValidServiceStatuses() {

        Vector<Provider> providers = filteredResponse;

        if (newFilteringNeeded)
            providers = executeQuery();

        Vector<String> validServiceStatuses = new Vector<>();

        for (int i = 0; i < providers.size(); i++) {
            Vector<Service> providerServices = providers.get(i).getServices();
            for (int j = 0; j < providerServices.size(); j++) {
                if (!validServiceStatuses.contains(providerServices.get(j).getStatus()))
                    validServiceStatuses.add(providerServices.get(j).getStatus());
            }
        }

        return validServiceStatuses;

    }

    /**
     * This method clears all the temporary archives contained in the Query;
     * ! THE FILTERS WILL REMAIN UNCHANGED !;
     * This method also prepares the Query to actuate a POST request (doesn't make it)
     */
    public void clear() {

        countriesArchive.clear();
        serviceTypesArchive.clear();

        newRequestNeeded = true;
        newFilteringNeeded = true;
        fullCountriesArchive = false;
        fullServiceTypesArchive = false;

    }

    /**
     * This method erases all the parameters from the filters
     */
    public void clearFilters() {

        for (int i = 0; i < filters.size(); i++)
            filters.get(i).clear();

    }

    //Basic commands to create a Query from scratch
    private void newQuery() {

        response = new Vector<>();
        filteredResponse = new Vector<>();

        filters = new Vector<>();
        filters.add(new FilterCountry());
        filters.add(new FilterProvider());
        filters.add(new FilterServiceType());
        filters.add(new FilterServiceStatus());

        countriesArchive = new Vector<>();
        serviceTypesArchive = new Vector<>();

        fullCountriesArchive = false;
        fullServiceTypesArchive = false;
        newRequestNeeded = true;

    }

    //IF NEEDED creates a post request and IF NEEDED filters the response
    private Vector<Provider> executeQuery() {

        if (newRequestNeeded) {
            System.out.println("Comunicating with the server...");                                                                  /*------------------------------------------------------------------*/
            response = DataArchive.newDataArchive().getProviders(filters.get(0).getParameters().toArray(new String[0]), filters.get(2).getParameters().toArray(new String[0]));
            newRequestNeeded = false;
        }

        filteredResponse = response;
        
        for (int i = 0; i < filters.size(); i++)
            filteredResponse = filters.get(i).execute(filteredResponse);       
        
        return filteredResponse;

    }

    //Converts the criteria sheed into an array of parameters divided by type of filter
    private String[] criteriaToParameters(String _criteria) {

        Scanner tokenizer = new Scanner(_criteria);

        if (!tokenizer.hasNextLine()) {
            tokenizer.close();
            return new String[0];
        }

        String line = "";
        String[] parameters = new String[4];

        for (int i = 0; i < filters.size(); i++) {
            
            StringBuilder filter = new StringBuilder();

            if (tokenizer.hasNextLine())
                line = tokenizer.nextLine();
            else {
                tokenizer.close();
                return new String[0];
            }

            if (!tokenizer.hasNextLine()) {
                tokenizer.close();
                return new String[0];
            }

            while(!(line = tokenizer.nextLine()).equals(CRITERIA_LINE))
                filter.append(line).append("\n");

            parameters[i] = filter.toString();

        }

        tokenizer.close();

        return parameters;

    }

    //Converts a string containing all the parameters into an array of parameters
    private String[] split(String _parameters) {

        Vector<String> parameters = new Vector<>();

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
