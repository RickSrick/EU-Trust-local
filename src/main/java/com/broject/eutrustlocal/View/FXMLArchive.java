package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Main;
import javafx.fxml.FXMLLoader;

/**
 * It's an archive that contain all the reference for the FXML file.
 *
 * @author Biscaccia Carrara Francesco
 */
public final class FXMLArchive {

    public static final FXMLLoader HOME_SCENE = new FXMLLoader(Main.class.getResource("xml_view/home-view.fxml"));
    public static final FXMLLoader ERROR_SCENE = new FXMLLoader(Main.class.getResource("xml_view/error-view.fxml"));
    public static final FXMLLoader COUNTRY_LIST_SCENE = new FXMLLoader(Main.class.getResource("xml_view/countryList-view.fxml"));
    public static final FXMLLoader SERVICE_TYPE_LIST_SCENE = new FXMLLoader(Main.class.getResource("xml_view/serviceTypeList-view.fxml"));
    public static final FXMLLoader PROVIDER_LIST_SCENE = new FXMLLoader(Main.class.getResource("xml_view/providerList-view.fxml"));
    public static final FXMLLoader STATUSES_LIST_SCENE = new FXMLLoader(Main.class.getResource("xml_view/statusesList-view.fxml"));
    public static final FXMLLoader RESULT_LIST_SCENE = new FXMLLoader(Main.class.getResource("xml_view/resultList-view.fxml"));
    public static final FXMLLoader SPLASH_SCREEN_SCENE = new FXMLLoader(Main.class.getResource("xml_view/splashScreen-view.fxml"));
    public static final FXMLLoader HISTORY_SCENE = new FXMLLoader(Main.class.getResource("xml_view/history-view.fxml"));

}
