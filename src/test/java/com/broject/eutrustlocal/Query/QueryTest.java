package com.broject.eutrustlocal.Query;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Provider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class QueryTest {

    //private Platform

    @Test
    void getCriteria() {

        Query query = new Query();
        String expectedCriteria = """
                COUNTRIES
                --------
                PROVIDERS
                --------
                SERVICE_TYPES
                --------
                SERVICE_STATUSES
                --------
                """;
        Assertions.assertEquals(expectedCriteria, query.getCriteria());

    }

    @Test
    void setCriteria() {

        Query query = new Query();
        String criteria = """
                COUNTRIES
                AT
                DE
                --------
                PROVIDERS
                --------
                SERVICE_TYPES
                QWAC
                --------
                SERVICE_STATUSES
                --------
                """;
        query.setCriteria(criteria);
        Assertions.assertEquals(criteria, query.getCriteria());

    }

    @Test
    void editFilterParameter() {

        Query query = new Query();
        query.editFilterParameter(Query.CRITERIA_FILTERS[0], "IT");
        query.editFilterParameter(Query.CRITERIA_FILTERS[1], "IT/Actalis S.p.A.");
        query.editFilterParameter(Query.CRITERIA_FILTERS[2], "QWAC");
        query.editFilterParameter(Query.CRITERIA_FILTERS[3], "granted");
        String expectedCriteria = """
                COUNTRIES
                IT
                --------
                PROVIDERS
                IT/Actalis S.p.A.
                --------
                SERVICE_TYPES
                QWAC
                --------
                SERVICE_STATUSES
                granted
                --------
                """;
        Assertions.assertEquals(expectedCriteria, query.getCriteria());

    }

    @Test
    void getValidProviders() {

        Query query = new Query();
        query.editFilterParameter(Query.CRITERIA_FILTERS[0], "IT");
        query.editFilterParameter(Query.CRITERIA_FILTERS[1], "IT/Actalis S.p.A.");
        query.editFilterParameter(Query.CRITERIA_FILTERS[2], "QWAC");
        query.editFilterParameter(Query.CRITERIA_FILTERS[3], "granted");
        try {
            ArrayList<Provider> providers = query.getValidProviders();
            Assertions.assertEquals(1, providers.size());
            Assertions.assertEquals("Actalis S.p.A.", providers.get(0).getName());
            Assertions.assertEquals(2, providers.get(0).getServices().size());
            Assertions.assertEquals("CN=Actalis EU Qualified Certificates CA G2, OU=Qualified Trust Service Provider, OID.2.5.4.97=VATIT-03358520967, O=Actalis S.p.A., L=Ponte San Pietro, C=IT", providers.get(0).getServices().get(0).getName());
            Assertions.assertEquals("CN=Actalis Authentication Root CA, O=Actalis S.p.A./03358520967, L=Milan, C=IT", providers.get(0).getServices().get(1).getName());
        } catch (BadResponseException ignored) {
        }

    }

    @Test
    void getValidServiceTypes() {

        Query query = new Query();
        query.editFilterParameter(Query.CRITERIA_FILTERS[0], "IT");
        query.editFilterParameter(Query.CRITERIA_FILTERS[1], "IT/Actalis S.p.A.");
        query.editFilterParameter(Query.CRITERIA_FILTERS[3], "granted");
        try {
            ArrayList<String> types = query.getValidServiceTypes();
            Assertions.assertEquals(4, types.size());
            Assertions.assertEquals("QCertESig", types.get(0));
            Assertions.assertEquals("QTimestamp", types.get(1));
            Assertions.assertEquals("QCertESeal", types.get(2));
            Assertions.assertEquals("QWAC", types.get(3));
        } catch (BadResponseException ignored) {
        }

    }

    @Test
    void getValidServiceStatuses() {

        Query query = new Query();
        query.editFilterParameter(Query.CRITERIA_FILTERS[0], "IT");
        query.editFilterParameter(Query.CRITERIA_FILTERS[1], "IT/Actalis S.p.A.");
        try {
            ArrayList<String> statuses = query.getValidServiceStatuses();
            Assertions.assertEquals(2, statuses.size());
            Assertions.assertEquals("granted", statuses.get(0));
            Assertions.assertEquals("recognisedatnationallevel", statuses.get(1));
        } catch (BadResponseException ignored) {
        }

    }

    @Test
    void clearFilter() {

        Query query = new Query();
        query.editFilterParameter(Query.CRITERIA_FILTERS[0], "IT");
        query.editFilterParameter(Query.CRITERIA_FILTERS[1], "IT/Actalis S.p.A.");
        query.editFilterParameter(Query.CRITERIA_FILTERS[2], "QWAC");
        query.editFilterParameter(Query.CRITERIA_FILTERS[3], "granted");
        query.clearFilter(Query.CRITERIA_FILTERS[1]);
        String expectedCriteria = """
                COUNTRIES
                IT
                --------
                PROVIDERS
                --------
                SERVICE_TYPES
                QWAC
                --------
                SERVICE_STATUSES
                granted
                --------
                """;
        Assertions.assertEquals(expectedCriteria, query.getCriteria());

    }

    @Test
    void clearAllFilters() {

        Query query = new Query();
        query.editFilterParameter(Query.CRITERIA_FILTERS[0], "IT");
        query.editFilterParameter(Query.CRITERIA_FILTERS[1], "IT/Actalis S.p.A.");
        query.editFilterParameter(Query.CRITERIA_FILTERS[2], "QWAC");
        query.editFilterParameter(Query.CRITERIA_FILTERS[3], "granted");
        query.clearAllFilters();
        String expectedCriteria = """
                COUNTRIES
                --------
                PROVIDERS
                --------
                SERVICE_TYPES
                --------
                SERVICE_STATUSES
                --------
                """;
        Assertions.assertEquals(expectedCriteria, query.getCriteria());

    }
}