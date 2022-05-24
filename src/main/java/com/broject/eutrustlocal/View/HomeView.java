package com.broject.eutrustlocal.View;

import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Country;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.Main;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Vector;

/**
 * @author Biscaccia Carrara Francesco
 */
public class HomeView {

    private static final int IMG_SIZE=40;
    private static final int GAP=20;
    private static final int COL_NUM=4;
    private static final int ROW_NUM=8;
    private final Scene home;
    private static HomeView instance = null;

    private HomeView() throws BadResponseException, IOException {

       Vector<Country> countries= DataArchive.newDataArchive().getCountries();

       GridPane countryGrid = new GridPane();
       countryGrid.setStyle("-fx-font-family: Arial");
       countryGrid.setHgap(GAP);
       countryGrid.setVgap(GAP);
       int elem=0;
        for(int i =0; i<COL_NUM & elem<countries.size();i++){
            for(int j =0; j<ROW_NUM & elem<countries.size();j++){
                ImageView flag = new ImageView(countries.get(elem).getFlag());
                flag.setFitHeight(IMG_SIZE);
                flag.setFitWidth(IMG_SIZE);
                countryGrid.add(new Label(countries.get(elem).getName(),flag),i,j);
                elem++;
            }
        }

        VBox rightPane= new VBox();
        Text text1=new Text("Service type available:");
        text1.setStyle("-fx-font-weight: bold");
        rightPane.getChildren().add(text1);
        for(String s: DataArchive.SERVICE_TYPES){
            rightPane.getChildren().add(new Label("• "+s));
        }

        Parent node = XMLArchive.HOME_SCENE.load();
        ObservableList<Node> dynamicList = ((Pane) ((Pane) node).getChildren().get(1)).getChildren();
        dynamicList.addAll(countryGrid,rightPane);
        home = new Scene(node,Main.LAYOUT_WIDTH,Main.LAYOUT_HEIGHT);
    }

    public static HomeView newHomeView() throws BadResponseException,IOException {

        if(DataArchive.checkOfflineStatus()) throw new BadResponseException();
        if (instance == null)
            instance = new HomeView();

        return instance;
    }
    public Scene getScene(){
        return home;
    }

}
