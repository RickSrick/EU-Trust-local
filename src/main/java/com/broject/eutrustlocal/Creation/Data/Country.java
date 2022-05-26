package com.broject.eutrustlocal.Creation.Data;

import javafx.scene.image.Image;

/**
 * @author Zanella Matteo
 */

public class Country {

    private final String countryName;
    private final String countryCode;
    private final Image flag;

    public Country(String _countryName, String _countryCode, String _flagLink) {

        countryName = _countryName;
        countryCode = _countryCode;
        flag = new Image(_flagLink, true);

    }

    public String getName() {

        return countryName;

    }

    public String getCountryCode() {

        return countryCode;

    }

    public Image getFlag() {

        return flag;

    }

}
