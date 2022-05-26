package com.broject.eutrustlocal.Query;

import com.broject.eutrustlocal.Creation.BadResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class QueryTest {

    @Test
    void editFilterParameter() throws BadResponseException {

        Query query = new Query();
        query.editFilterParameter(Query.CRITERIA_FILTERS[0], "IT");
        query.editFilterParameter(Query.CRITERIA_FILTERS[2], "QWAC");
        String actual = query.getCriteria();
        String expected = "COUNTRIES\n" +
                "IT\n" +
                "--------\n" +
                "PROVIDERS\n" +
                "--------\n" +
                "SERVICE_TYPES\n" +
                "QWAC\n" +
                "--------\n" +
                "SERVICE_STATUSES\n" +
                "--------\n";

        assertEquals(expected, actual);

    }

    @Test
    void newQueryFromCriteria() throws BadResponseException {

        Query query1 = new Query();
        query1.editFilterParameter(Query.CRITERIA_FILTERS[0], "IT");
        query1.editFilterParameter(Query.CRITERIA_FILTERS[2], "QWAC");

        Query query2 = new Query(query1.getCriteria());

        assertEquals(query2.getCriteria(), query1.getCriteria());

    }

    @Test
    void getValidProviders() throws BadResponseException {

        Query query = new Query();
        query.editFilterParameter(Query.CRITERIA_FILTERS[0], "IT");
        query.editFilterParameter(Query.CRITERIA_FILTERS[2], "QWAC");

        Vector<String> providers = query.getValidProviders();

        assertEquals(1, providers.size());
        assertEquals("Actalis S.p.A", providers.get(0));

    }

}