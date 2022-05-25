package com.broject.eutrustlocal.Creation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.jupiter.api.Assertions.*;

class BifrostTest {

    void internetFeedback() throws IOException {
        URL url = new URL("https://www.google.com");
        URLConnection uc = url.openConnection();
        uc.connect();
    }

    @Test
    void newBifrost() {
        Bifrost b1 = Bifrost.newBifrost();
        Bifrost b2 = Bifrost.newBifrost();
        assertEquals(b1, b2);
    }

    @Test
    void checkConnection() {

        try { internetFeedback(); }
        catch (IOException e){
            Assertions.assertThrows(BadResponseException.class, () -> {
                Bifrost.newBifrost().checkConnection();
            });
        }
    }

    @Test
    void getFlagImageLink() {
        Assertions.assertNotEquals("", Bifrost.newBifrost().getFlagImageLink(""));
    }

    @Test
    void getCountries() throws BadResponseException {
        try { internetFeedback(); }
        catch (IOException e) {
            Assertions.assertThrows(BadResponseException.class, () -> {
                Bifrost.newBifrost().getCountries();
            });
        }

        Assertions.assertNotEquals("", Bifrost.newBifrost().getCountries());
    }

    @Test
    void findTrustServices() {
        Assertions.assertThrows(BadResponseException.class, () -> { Bifrost.newBifrost().findTrustServices(""); });
        Assertions.assertThrows(BadResponseException.class, () -> { Bifrost.newBifrost().findTrustServices("Patate"); });
        Assertions.assertThrows(BadResponseException.class, () -> { Bifrost.newBifrost().findTrustServices("IT"); });
        Assertions.assertThrows(BadResponseException.class, () -> { Bifrost.newBifrost().findTrustServices("Sossole"); });
    }
}