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
import javafx.scene.layout.VBox;

import java.util.Vector;

/**
 * @author Biscaccia Carrara Francesco
 */
public class HomeView {

    private static final int GAP=20;
    private static final int COL_NUM=4;
    private static final int ROW_NUM=8;
    private final Scene home;

    public HomeView(DataArchive data,Parent node, double width, double height) throws BadResponseException {
       Vector<Country> countries= data.getCountries();
       GridPane countryGrid = new GridPane();
       countryGrid.setStyle("-fx-font-family: Arial");
       countryGrid.setHgap(GAP);
       countryGrid.setVgap(GAP);
       int el=0;
        for(int i =0; i<COL_NUM & el<countries.size();i++){
            for(int j =0; j<ROW_NUM & el<countries.size();j++){
                ImageView flag = new ImageView(new Image(countries.get(el).getFlagLink(),true));
                flag.setFitHeight(30);
                flag.setFitWidth(30);
                countryGrid.add(new Label(countries.get(el).getName(),flag),i,j);
                el++;
            }
        }
       ((Pane) ((Pane) node).getChildren().get(1)).getChildren().add(countryGrid);

        VBox rightPane= new VBox();
        rightPane.getChildren().add(new Label("Service type available: "));

        for(String s: DataArchive.SERVICE_TYPES){
            rightPane.getChildren().add(new Label("• "+s));
        }
        ((Pane) ((Pane) node).getChildren().get(1)).getChildren().add(rightPane);
        home = new Scene(node,width,height);
    }

    public Scene getScene(){
        return home;
    }
}
