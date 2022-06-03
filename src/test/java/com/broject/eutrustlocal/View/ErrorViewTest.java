package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Creation.Bifrost;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ErrorViewTest {

    @Test
    public void test() throws Exception {
        JFXPanel fxPanel = new JFXPanel();
    [.. Begin tests ..]
    }
    @Test
    void getIstance() throws IOException {

        ErrorView b1 = ErrorView.getInstance();
        ErrorView b2 = ErrorView.getInstance();
        assertEquals(b1, b2);
    }
    @Test
    void getScene() throws IOException {
        ErrorView b1 = ErrorView.getInstance();
        assertNotNull(b1);
    }




}