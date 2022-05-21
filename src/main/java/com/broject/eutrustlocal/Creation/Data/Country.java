package com.broject.eutrustlocal.Creation.Data;

/**
 * @author Zanella Matteo
 */

public class Country {

    private final String countryName;
    private final String countryID;
    private final String flagLink;

    public Country(String _name, String _ID, String _flagLink) {

        countryName = _name;
        countryID = _ID;
        flagLink = _flagLink;

    }

    public String getName() {

        return countryName;

    }

    public String getID() {

        return countryID;

    }

    public String getFlagLink() {

        return flagLink;

    }

}
