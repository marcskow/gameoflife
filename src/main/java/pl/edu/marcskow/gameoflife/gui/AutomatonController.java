package pl.edu.marcskow.gameoflife.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.StringJoiner;

/**
 * Created by Marcin Skowron on 2016-01-02.
 */
public class AutomatonController extends AnchorPane implements Initializable{

    private final ObservableList<String> automatonTypeList = FXCollections.observableArrayList("Game Of Life",
            "Langton Ant", "WireWorld", "Ellementary", "QuadLife");

    private final ObservableList<String> neighbourhoodList = FXCollections.observableArrayList("Moore", "Von Neuman");
    private final ObservableList<String> onlyMooreList = FXCollections.observableArrayList("Moore");
    private final ObservableList<String> onlyVonNeumanList = FXCollections.observableArrayList("Von Neuman");
    private final ObservableList<String> oneDimNeighbourhoodList = FXCollections.observableArrayList("OneDim");

    private final ObservableList<String> gameOfLifeStructureList = FXCollections.observableArrayList("Glider", "Block", "Toat", "Dakota",
            "Gosper Glider Gun");

    // TODO: 2016-01-02  langtonAntStructureList wireWorldStructureList ellementaryStructureList
    private final ObservableList<String> langtonAntStructureList = FXCollections.observableArrayList("");
    private final ObservableList<String> wireWorldStructureList = FXCollections.observableArrayList("");
    private final ObservableList<String> ellementaryStructureList = FXCollections.observableArrayList("");

    //General settings
    @FXML
    ChoiceBox<String> automatonChoiceBox;
    @FXML
    ChoiceBox<String> neighbourhoodChoiceBox;

    //Additional settings
    @FXML
    ChoiceBox<String> structureChoiceBox;
    @FXML
    CheckBox wrappingCheckBox;

    //Game running
    @FXML
    Button startButton;
    @FXML
    Button nextgenButton;
    @FXML
    Button resetButton;

    //GameOfLife internal rules
    @FXML
    TextField newRuleTextField;
    @FXML
    TextField surviveRuleTextField;

    //Ellementary internal rules
    @FXML
    TextField ellementaryRuleTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert automatonChoiceBox != null : "fx:id=\"automatonChoiceBox\" was not injected: check your FXML file 'guiFX.fxml'.";

        //It's false until user choose Game of Life
        newRuleTextField.setVisible(false);
        surviveRuleTextField.setVisible(false);
        ellementaryRuleTextField.setVisible(false);

        automatonChoiceBox.getItems().addAll(automatonTypeList);
        automatonChoiceBox.getSelectionModel().selectedItemProperty().addListener(automatonChoiceOnChangeListener);
    }

    private ChangeListener<String> automatonChoiceOnChangeListener = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if(oldValue != null) {

                // TODO: 2016-01-02 if oldValue not equals new Value
                switch (newValue) {
                    case "Game Of Life": changeAutomatonType(gameOfLifeStructureList, neighbourhoodList, true, false); break;
                    case "Langton Ant": changeAutomatonType(langtonAntStructureList, onlyVonNeumanList, false, false); break;
                    case "WireWorld": changeAutomatonType(wireWorldStructureList, onlyMooreList, false, false); break;
                    case "Ellementary": changeAutomatonType(ellementaryStructureList, oneDimNeighbourhoodList ,false, true); break;
                    case "QuadLife": changeAutomatonType(gameOfLifeStructureList, neighbourhoodList, true, false); break;
                }
            }
        }
    };

    private void changeAutomatonType(ObservableList<String> structure, ObservableList<String> neighbourhood, boolean areGameOfLifeRulesVisible,
                                     boolean areEllementaryRulesVisible){
        structureChoiceBox.getItems().setAll(structure);
        neighbourhoodChoiceBox.getItems().setAll(neighbourhoodList);
        newRuleTextField.setVisible(areGameOfLifeRulesVisible);
        surviveRuleTextField.setVisible(areGameOfLifeRulesVisible);
        ellementaryRuleTextField.setVisible(areEllementaryRulesVisible);
    }

    @FXML
    public void onStart(){

    }

}
