package com.broject.eutrustlocal.Creation;

import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

class DataArchiveTest {

    @Test
    void newDataArchive() throws BadResponseException {

        DataArchive data1 = DataArchive.newDataArchive();
        DataArchive data2 = DataArchive.newDataArchive();

        assertEquals(data1, data2);

    }

}