package Query.Filter;

import Creation.Data.Provider;

import java.util.Vector;

public abstract class Filter {

    protected Vector<String> parameters;
    protected int filterID;

    /**
     * Default constructor
     */
    protected Filter() {
        
        parameters = new Vector<>();

    }

    /**
     * Returns all the parameters of the filter
     * Will be overrided in some filters
     * @return
     */
    public Vector<String> getParameters() { return (Vector<String>)parameters.clone(); }

    /**
     * Checks if there are no parameters in the filter
     * @return true/false based on the number of parameters
     */
    public boolean isEmpty() { return parameters.isEmpty(); }

    /**
     * Returns the ID of the filter, used to identify a type of filter
     * @return the filter ID
     */
    public int getFilterID() { return filterID; }

    /**
     * Method that adds a list of parameters to the existing ones
     * @param _parameters parameters to be added
     */
    public void addParameters(String[] _parameters) {

        for (int i = 0; i < _parameters.length; i++)
            if (!parameters.contains(_parameters[i]))
                parameters.add(_parameters[i]);
    
    }

    /**
     * Checks if there is a certain parameter between the existing ones
     * @param _parameter the parameter to be checked
     * @return true/false based on the parameter being already considered or not
     */
    public boolean has(String _parameter) { return parameters.contains(_parameter); }

    /**
     * Method used to remove a parameter
     * @param _parameter the parameter to be removed
     */
    public void removeParameter(String _parameter) { parameters.remove(_parameter); }

    /**
     * Method that clears the filter (removes all the parameters)
     */
    public void clear() { parameters.clear(); }

    /**
     * Method that filters a given vector of providers following the existing parameters stored in the filter
     * To be overrided
     * @param _response the vector to be filtered
     * @return a filtered vector
     */
    public abstract Vector<Provider> execute(Vector<Provider> _response);

}
