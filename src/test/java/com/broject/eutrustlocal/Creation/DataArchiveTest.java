package com.broject.eutrustlocal.Creation;

import com.broject.eutrustlocal.Creation.Data.Country;
import com.broject.eutrustlocal.Creation.Data.Provider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DataArchiveTest {

    @Test
    void newDataArchive() {
        try {
            DataArchive d1 = DataArchive.newDataArchive();
            DataArchive d2 = DataArchive.newDataArchive();
            Assertions.assertEquals(d1, d2);
        } catch (BadResponseException e) {
            Assertions.assertTrue(DataArchive.checkOfflineStatus());
        }
    }

    @Test
    void checkOfflineStatus() {
        try {
            DataArchive d = DataArchive.newDataArchive();
            Assertions.assertFalse(DataArchive.checkOfflineStatus());
        } catch (BadResponseException e) {
            Assertions.assertTrue(DataArchive.checkOfflineStatus());
        }
    }

    @Test
    void getCountryCodes() {
        try {
            ArrayList<String> codes = DataArchive.newDataArchive().getCountryCodes();
            Assertions.assertNotNull(codes);
        } catch (BadResponseException e) {
            Assertions.assertTrue(DataArchive.checkOfflineStatus());
        }
    }

    @Test
    void getCountries() {
        try {
            ArrayList<Country> codes = DataArchive.newDataArchive().getCountries();
            Assertions.assertNotNull(codes);
        } catch (BadResponseException e) {
            Assertions.assertTrue(DataArchive.checkOfflineStatus());
        }
    }

    @Test
    void getCountry() {
        try {
            Country country = DataArchive.newDataArchive().getCountry("IT");
            Assertions.assertEquals("IT", country.getCountryCode());
            Assertions.assertEquals("Italy", country.getName());
        } catch (BadResponseException e) {
            Assertions.assertTrue(DataArchive.checkOfflineStatus());
        }
    }

    @Test
    void getProviders() {
        try {
            DataArchive d = DataArchive.newDataArchive();
            String[] countries = {"UK"};
            String[] services = {"QCertESig"};
            ArrayList<Provider> providers = d.getProviders(countries, services);
            Assertions.assertEquals(2, providers.size());
        } catch (BadResponseException e) {
            Assertions.assertTrue(DataArchive.checkOfflineStatus());
        }
        try {
            DataArchive d = DataArchive.newDataArchive();
            String[] countries = {"ES"};
            String[] services = {};
            Assertions.assertThrows(BadResponseException.class, () -> {
                ArrayList<Provider> providers = d.getProviders(countries, services);
            });
            String[] countries1 = {"Patate"};
            String[] services1 = {"QWAC"};
            Assertions.assertThrows(BadResponseException.class, () -> {
                ArrayList<Provider> providers = d.getProviders(countries1, services1);
            });
        } catch (BadResponseException e) {
            Assertions.assertTrue(DataArchive.checkOfflineStatus());
        }
    }

}