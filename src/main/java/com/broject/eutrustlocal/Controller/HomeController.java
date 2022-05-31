package com.broject.eutrustlocal.Controller;

import com.broject.eutrustlocal.Controller.BackgroundTasks.TaskData;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Country;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.Query.Query;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.HomeView;
import com.broject.eutrustlocal.View.ResultView;
import com.broject.eutrustlocal.View.SelectCountryView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Biscaccia Carrara Francesco
 */
public class HomeController extends SelectController{

    @FXML
    private GridPane countryGrid;

    @FXML
    private VBox serTypePane;

    @FXML
    protected void initialize() throws IOException {
        ArrayList<Country> countries;
        try {
            countries = DataArchive.newDataArchive().getCountries();
            int elem = 0;
            for (int i = 0; i < HomeView.COL_NUM & elem < countries.size(); i++) {
                for (int j = 0; j < HomeView.ROW_NUM & elem < countries.size(); j++) {
                    ImageView flag = new ImageView(countries.get(elem).getFlag());
                    flag.setFitHeight(HomeView.IMG_SIZE);
                    flag.setFitWidth(HomeView.IMG_SIZE);
                    Label label=new Label(countries.get(elem).getName(), flag);
                    label.setId(countries.get(elem).getCountryCode());
                    elem++;
                    label.getStyleClass().add("countrySrc");
                    label.onMouseClickedProperty().set(mouseEvent -> {
                        QUERY.editFilterParameter(Query.CRITERIA_FILTERS[0], label.getId());
                        try {
                            Thread th = new Thread(new TaskData(QUERY));
                            th.setDaemon(true);
                            th.start();
                            th.join();
                            Main.STAGE.setScene(ResultView.getInstance(true).getScene());
                        } catch (BadResponseException | IOException e) {
                            try {
                                Main.STAGE.setScene(ErrorView.getInstance().getScene());
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    countryGrid.add(label, i, j);
                }
            }

            for (String s : DataArchive.SERVICE_TYPES) {
                serTypePane.getChildren().add(new Label("• " + s));
            }
        } catch (BadResponseException e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }

    @FXML
    protected void onSearchByCriteriaClick() throws IOException {
        try {
            Main.STAGE.setScene(SelectCountryView.getInstance().getScene());
        } catch (Exception e) {
            Main.STAGE.setScene(ErrorView.getInstance().getScene());
        }
    }
}
