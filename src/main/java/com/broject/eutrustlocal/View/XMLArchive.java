package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Main;
import javafx.fxml.FXMLLoader;

/**
 * @author Biscaccia Carrara Francesco
 */
public final class XMLArchive {
    public static FXMLLoader HOME_SCENE = new FXMLLoader(Main.class.getResource("xml_view/home-view.fxml"));
    public static FXMLLoader ERROR_SCENE = new FXMLLoader(Main.class.getResource("xml_view/error-view.fxml"));
    public static FXMLLoader COUNTRY_LIST_SCENE = new FXMLLoader(Main.class.getResource("xml_view/countryList-view.fxml"));

}
