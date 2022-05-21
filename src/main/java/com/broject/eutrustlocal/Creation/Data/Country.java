package com.broject.eutrustlocal.Creation.Data;

/**
 * @author Zanella Matteo
 */

public class Country {

    private final String countryName;
    private final String countryCode;
    private final String flagLink;

    public Country(String _countryName, String _countryCode, String _flagLink) {

        countryName = _countryName;
        countryCode = _countryCode;
        flagLink = _flagLink;

    }

    public String getName() {

        return countryName;

    }

    public String getCountryCode() {

        return countryCode;

    }

    public String getFlagLink() {

        return flagLink;

    }

}
