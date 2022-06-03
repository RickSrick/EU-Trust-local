package com.broject.eutrustlocal.History;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HistoryTest {

    @Test
    void binWriter() throws FileNotFoundException {
        History.binWriter("ABCDEF");
        assertFalse(History.emptyFile());

    }

    @Test
    void binReader() throws FileNotFoundException {
        History.clearHistory();
        ArrayList<String> tst = History.binReader();
        Assertions.assertEquals(tst.size(), 0);
        History.binWriter("123456");
        Assertions.assertNotEquals(History.binReader().size(), 0);
    }

    @Test
    void emptyFile() throws FileNotFoundException {
        History.clearHistory();
        assertTrue(History.emptyFile());
    }


    @Test
    void clearHistory() throws FileNotFoundException {
        History.binWriter("junit");
        History.clearHistory();
        assertTrue(History.emptyFile());
    }
}