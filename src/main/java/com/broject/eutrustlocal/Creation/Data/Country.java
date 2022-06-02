package com.broject.eutrustlocal.Creation.Data;

import javafx.scene.image.Image;

/**
 * @author Zanella Matteo
 */

public class Country {

    private final String countryName;
    private final String countryCode;
    private final Image flag;

    /**
     * Constructor needing:
     *
     * @param _countryName the name of the country
     * @param _countryCode the code of the country
     * @param _flagLink the url of the flag of the country
     */
    public Country(String _countryName, String _countryCode, String _flagLink) {

        countryName = _countryName;
        countryCode = _countryCode;
        flag = new Image(_flagLink, true);

    }

    /**
     * Returns the name of the country
     *
     * @return the name of the country
     */
    public String getName() {

        return countryName;

    }

    /**
     * Returns the code of the country
     *
     * @return the code of the country
     */
    public String getCountryCode() {

        return countryCode;

    }

    /**
     * Returns the flag of the country
     *
     * @return the flag of the country
     */
    public Image getFlag() {

        return flag;

    }

}
