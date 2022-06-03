package com.broject.eutrustlocal.Controller.Utility;

import com.broject.eutrustlocal.Controller.Utility.BackgroundTasks.TaskData;
import com.broject.eutrustlocal.Creation.BadResponseException;
import com.broject.eutrustlocal.Creation.Data.Country;
import com.broject.eutrustlocal.Creation.Data.Provider;
import com.broject.eutrustlocal.Creation.DataArchive;
import com.broject.eutrustlocal.History.History;
import com.broject.eutrustlocal.Main;
import com.broject.eutrustlocal.Query.Query;
import com.broject.eutrustlocal.View.ErrorView;
import com.broject.eutrustlocal.View.ResultView;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * DataParser is an auxiliary class that has the task to convert ArrayList given by Query/DataArchive into ArrayList of:
 * - CheckBox
 * - Labels
 *
 * @author Biscaccia Carrara Francesco
 */

public final class DataParser {

    /**
     * Initialize the arrayToFill ArrayList with the data contained in data ArrayList.
     * It also set an EventListener on each label, that, when it's triggered, modify
     * Query search parameters.
     *
     * @param data        Country ArrayList containing the data
     * @param arrayToFill Label ArrayList to be filled
     * @param query       Query to manipulate
     * @param img_size    Image size for the flag, contained in the labels
     */
    public static void labelsFromCountries(ArrayList<Country> data, ArrayList<Label> arrayToFill, Query query, double img_size) {

        for (Country c : data) {
            ImageView flag = new ImageView(c.getFlag());
            flag.setFitHeight(img_size);
            flag.setFitWidth(img_size);
            Label label = new Label(c.getName(), flag);
            label.setId(c.getCountryCode());
            label.getStyleClass().add("countrySrc");
            setListenerForLabel(label, query);
            arrayToFill.add(label);
        }

    }

    /**
     * Initialize the arrayToFill ArrayList with the data contained in data ArrayList.
     * It also set an EventListener on each checkbox, that, when it's triggered, modify
     * Query search parameters.
     *
     * @param data        Country ArrayList containing the data
     * @param arrayToFill CheckBox ArrayList to be filled
     * @param btn         Button to be enabled/disabled
     * @param query       Query to manipulate
     * @param img_size    Image size for the flag, contained in the label
     */
    public static void checkBoxesFromCountries(ArrayList<Country> data, ArrayList<CheckBox> arrayToFill, Button btn, Query query, double img_size) {

        for (Country c : data) {
            CheckBox checkBox = new CheckBox(c.getName());
            ImageView flag = new ImageView(c.getFlag());
            flag.setFitHeight(img_size);
            flag.setFitWidth(img_size);
            checkBox.setId(c.getCountryCode());
            setListenerForCheckBox(arrayToFill, checkBox, query, btn, 0);
            checkBox.setGraphic(flag);
            arrayToFill.add(checkBox);
        }
        addAllCheckBox(arrayToFill);

    }

    /**
     * Initialize the arrayToFill ArrayList with the data contained in data ArrayList.
     * It also set an EventListener on each checkbox, that, when it's triggered, modify
     * Query search parameters.
     *
     * @param data        String ArrayList containing the data
     * @param arrayToFill CheckBox ArrayList to be filled
     * @param btn         Button to be enabled/disabled
     * @param query       Query to manipulate
     * @param filter_type Filter type to modify
     */
    public static void checkBoxesFromStrings(ArrayList<String> data, ArrayList<CheckBox> arrayToFill, Button btn, Query query, int filter_type) {

        arrayToFill.clear();
        for (String s : data) {
            CheckBox checkBox = new CheckBox(s);
            checkBox.setId(s);
            setListenerForCheckBox(arrayToFill, checkBox, query, btn, filter_type);
            arrayToFill.add(checkBox);
        }
        addAllCheckBox(arrayToFill);

    }

    /**
     * Initialize the arrayToFill ArrayList with the data contained in data ArrayList.
     * It also set an EventListener on each checkbox, that, when it's triggered, modify
     * Query search parameters.
     *
     * @param data        Provider ArrayList containing the data
     * @param arrayToFill CheckBox ArrayList to be filled
     * @param btn         Button to be enabled/disabled
     * @param query       Query to manipulate
     * @param filter_type Filter type to modify
     * @param img_size    Image size for the flag, contained in the checkbox
     */
    public static void checkBoxesFromProviders(ArrayList<Provider> data, ArrayList<CheckBox> arrayToFill, Button btn, Query query, int filter_type, int img_size) throws BadResponseException {

        arrayToFill.clear();
        for (Provider s : data) {
            CheckBox checkBox = new CheckBox(s.getName());
            checkBox.setId(s.getCountryCode() + "/" + s.getName());
            ImageView flag = new ImageView(DataArchive.newDataArchive().getCountry(s.getCountryCode()).getFlag());
            flag.setFitHeight(img_size);
            flag.setFitWidth(img_size);
            checkBox.setGraphic(flag);
            setListenerForCheckBox(arrayToFill, checkBox, query, btn, filter_type);
            arrayToFill.add(checkBox);
        }
        addAllCheckBox(arrayToFill);

    }

    /**
     * Initialize the arrayToFill ArrayList with the data contained in data ArrayList.
     * It also set an EventListener on each label, that, when it's triggered, modify
     * Query search parameters.
     *
     * @param data        Provider ArrayList containing the data
     * @param arrayToFill Label ArrayList to be filled
     * @param query       Query to manipulate
     * @param img_size    Image size for the search icon, contained in the checkbox
     */
    public static void labelsFromCriteriaSheet(ArrayList<String> data, ArrayList<Label> arrayToFill, Query query, int img_size) {

        arrayToFill.clear();
        for (String s : data) {
            ImageView icon = new ImageView(new Image(String.valueOf(Main.class.getResource("img/search-history-icon.png"))));
            icon.setFitWidth(img_size);
            icon.setFitHeight(img_size);
            Label label = new Label("Search: " + compressCriteriaSheet(s), icon);
            label.setId(s);
            setListenerForHistoryLabel(label, query);
            arrayToFill.add(label);
        }

    }

    //Method that set a EventListener for a Label.
    private static void setListenerForLabel(Label label, Query query) {

        label.onMouseClickedProperty().set(mouseEvent -> {
            query.editFilterParameter(Query.CRITERIA_FILTERS[0], label.getId());
            try {
                Thread th = new Thread(new TaskData(query));
                th.setDaemon(true);
                th.start();
                th.join();
                History.binWriter(query.getCriteria());
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

    }

    //Method that set a EventListener for a CheckBox.
    private static void setListenerForCheckBox(ArrayList<CheckBox> checkBoxes, CheckBox checkBox, Query query, Button btn, int criteria_type) {

        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            boolean disable = false;
            for (CheckBox el : checkBoxes) {
                disable = disable || el.isSelected();
            }
            query.editFilterParameter(Query.CRITERIA_FILTERS[criteria_type], checkBox.getId());
            btn.setDisable(!disable);
        });

    }

    //Method that add the "all" checkBox as the last element in a CheckBox ArrayList.
    private static void addAllCheckBox(ArrayList<CheckBox> checkBoxes) {

        if (checkBoxes.size() != 1) {
            CheckBox allChecked = new CheckBox("All");
            allChecked.selectedProperty().addListener((observable, oldValue, newValue) -> {
                for (CheckBox checkBox : checkBoxes) {
                    checkBox.setSelected(allChecked.isSelected());
                    checkBox.setDisable(allChecked.isSelected());
                }
                allChecked.setDisable(false);
            });
            checkBoxes.add(allChecked);
        }

    }

    //Method that compress a multiple string text, into a single one.
    private static String compressCriteriaSheet(String _criteria) {

        Scanner tokenizer = new Scanner(_criteria);
        StringBuilder compressedCriteria = new StringBuilder();

        ArrayList<StringBuilder> parameters = new ArrayList<>();

        for (int i = 0; i < 4; i++) {

            tokenizer.nextLine();
            String line = tokenizer.nextLine();

            parameters.add(new StringBuilder());

            while (!line.equals(Query.CRITERIA_LINE)) {
                parameters.get(i).append(line);
                line = tokenizer.nextLine();
                if (!line.equals(Query.CRITERIA_LINE)) parameters.get(i).append("\\");
            }

        }

        for (int i = 0; i < parameters.size(); i++) {
            compressedCriteria.append(parameters.get(i).toString());
            if (i != 3 && parameters.get(i + 1).length() != 0) compressedCriteria.append(" - ");
        }

        return compressedCriteria.toString();

    }

    //Method that set a EventListener for a Label.
    private static void setListenerForHistoryLabel(Label label, Query query) {

        label.onMouseClickedProperty().set(mouseEvent -> {
            query.setCriteria(label.getId());
            try {
                Thread th = new Thread(new TaskData(query));
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

    }

}