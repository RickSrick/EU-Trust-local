package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Main;
import javafx.fxml.FXMLLoader;

/**
 * @author Biscaccia Carrara Francesco
 */
public final class XMLArchive {
    public static final FXMLLoader HOME_SCENE = new FXMLLoader(Main.class.getResource("xml_view/home-view.fxml"));
    public static final FXMLLoader ERROR_SCENE = new FXMLLoader(Main.class.getResource("xml_view/error-view.fxml"));
    public static final FXMLLoader COUNTRY_LIST_SCENE = new FXMLLoader(Main.class.getResource("xml_view/countryList-view.fxml"));
    public static  final FXMLLoader SERVICE_TYPE_LIST_SCENE= new FXMLLoader(Main.class.getResource("xml_view/serviceTypeList-view.fxml"));

    public static  final FXMLLoader PROVIDER_LIST_SCENE= new FXMLLoader(Main.class.getResource("xml_view/providerList-view.fxml"));
}
