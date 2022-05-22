package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Country;
import com.broject.eutrustlocal.Creation.DataArchive;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Vector;

/**
 * @author Biscaccia Carrara Francesco
 */
public class HomeView {

    private final int GAP=20;
    private final int COL_NUM=6;
    private final int ROW__NUM=6;
    private Scene home;

    public HomeView(DataArchive data,Parent node, int width, int height) throws BadResponseException {
       Vector<Country> countries= data.getCountries();
       GridPane countryGrid = new GridPane();
       countryGrid.setStyle("-fx-font-family: Arial");
       countryGrid.setHgap(GAP);
       countryGrid.setVgap(GAP);
       int el=0;
        for(int i =0; i<COL_NUM & el<countries.size();i++){
            for(int j =0; j<ROW__NUM & el<countries.size();j++){
                ImageView flag = new ImageView(new Image(countries.get(el).getFlagLink(),true));
                flag.setFitHeight(30);
                flag.setFitWidth(30);
                countryGrid.add(new Label(countries.get(el).getName(),flag),i,j);
                el++;
            }
        }
       ((Pane) ((Pane) node).getChildren().get(1)).getChildren().add(countryGrid);
       home = new Scene(node,width,height);
    }

    public Scene getScene(){
        return home;
    }
}
