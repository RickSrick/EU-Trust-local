package com.broject.eutrustlocal.History;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
    void binArrayAccess() throws FileNotFoundException {
        History.clearHistory();
        assertTrue(History.emptyFile());
        for(int i = 0; i <10; i++){
            History.binWriter(""+i);

        }
        Assertions.assertNotEquals(History.binArrayAccess(1), 0);
        for(int i = History.binReader().size()-1; i > 0 ;i--) {
            Assertions.assertNotEquals(History.binArrayAccess(i), i-1);
            System.out.println(i + "Number =" + i);
        }

    }

    @Test
    void clearHistory() throws FileNotFoundException {
        History.binWriter("junit");
        History.clearHistory();
        assertTrue(History.emptyFile());
    }
}