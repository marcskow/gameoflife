package pl.edu.marcskow.gameoflife.controller.main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import pl.edu.marcskow.gameoflife.controller.helpers.*;
import pl.edu.marcskow.gameoflife.model.automat.Automaton;
import pl.edu.marcskow.gameoflife.controller.structures.Structures;

import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

/**
 * GUI controller. It's class using to define elements of automaton graphical user interface controls
 * class use FXML adnotations to select elements defined in fxml file. This class consists of
 * action listeners, fields using in symulation to define rules and methods controling symulation.
 * @author Marcin Skowron
 * @version beta
 */

public class AutomatonController extends AnchorPane implements Initializable {
    private static final Logger log = Logger.getLogger(AutomatonController.class.getName());

    /** Default grid display settings */
    private static final int DEFAULT_STROKE_WIDTH = 1;
    private static final Color DEFAULT_STROKE_COLOR = Color.GRAY;

    /** ObservableLists to initialize choice boxes */
    private final ObservableList<String> gridSizeList = FXCollections.observableArrayList("10x8", "20x16", "40x32", "50x40", "100x80", "125x100");
    private final ObservableList<String> automatonTypeList = FXCollections.observableArrayList("Game Of Life", "Langton Ant", "WireWorld", "Elementary", "QuadLife");
    private final ObservableList<String> neighborhoodList = FXCollections.observableArrayList("Moore", "Von Neuman");
    private final ObservableList<String> gameOfLifeStructureList = FXCollections.observableArrayList("Next", "Alive", "Dead", "Orion", "Glider", "Dakota", "Gosper Glider Gun");
    private final ObservableList<String> langtonStructureList = FXCollections.observableArrayList("Next", "Alive", "Dead", "Ant", "Ant West", "Ant North", "Ant South", "Ant East");
    private final ObservableList<String> wireWorldStructureList = FXCollections.observableArrayList("Next", "Wire", "Head", "Tail");
    private final ObservableList<String> elementaryStructureList = FXCollections.observableArrayList("Next", "Alive", "Dead");

    //Game running service
    private Timeline timeline;
    private boolean isRunning;
    private boolean isOnError;
    private boolean isInicialized;
    private KeyFrame[] frames;

    //Game model and helpers
    private GridModel gridModel;
    private OnStartService onStartService;
    private Structures structures;

    //New Automaton
    private Automaton newAutomaton;

    //General settings
    @FXML
    ChoiceBox<String> automatonChoiceBox;
    @FXML
    ChoiceBox<String> neighbourhoodChoiceBox;
    @FXML
    ChoiceBox<String> sizeChoiceBox;

    //Additional settings
    @FXML
    ChoiceBox<String> structureChoiceBox;
    @FXML
    CheckBox wrappingCheckBox;
    @FXML
    Slider generationTimeSlider;
    @FXML
    TextField radiusTextField;

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
    @FXML
    Label newcellLabel;
    @FXML
    Label surviveLabel;

    //Elementary internal rules
    @FXML
    TextField elementaryRuleTextField;
    @FXML
    Label elementaryRuleLabel;

    //Board
    @FXML
    GridPane gridPane;

    //Error
    @FXML
    Label errorText;
    @FXML
    Label errorTitle;

    //Some things like action listeners are set in FXML file

    /**
     * Initialization method using to connect listeners to elements of gui
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sizeChoiceBox.getItems().addAll(gridSizeList);
        automatonChoiceBox.getItems().addAll(automatonTypeList);
        neighbourhoodChoiceBox.getItems().addAll(FXCollections.emptyObservableList());
        structureChoiceBox.getItems().addAll(FXCollections.emptyObservableList());

        initializeTimeLine();

        gridModel = new GridModel(40, 30, "Game Of Life");
        onStartService = new OnStartService();
        structures = new Structures();
        updateGridDisplay();

        //Not supported in fxml have to be added there
        generationTimeSlider.valueProperty().addListener(sliderChangeListener);

        initializeDefault();
        initializeStart();
    }

    @FXML
    public void structureBoxOnChangeListener(){
    // There are nothing inside but to avoid error it's should be defined
    }

    @FXML
    public void neighborhoodBoxOnChangeListener(){
        onStartService.setNeighbourhoodType(neighbourhoodChoiceBox.getSelectionModel().getSelectedItem());
    }

    /**
     * Next generation of automaton.
     * Method called in every timeline loop
     */
    @FXML
    public void nextGeneration() {
        //There can't be next generation if the starting automaton settings isn't initialized
        if(isInicialized) {
            newAutomaton = newAutomaton.nextState();
            gridModel.setCellsMap(newAutomaton.getCells());
            updateGridDisplay();
        }
    }

    @FXML
    public void automatonBoxOnChangeListener(){
        String newType = automatonChoiceBox.getSelectionModel().getSelectedItem();
        switch (newType) {
            case "Game Of Life": changeAutomatonType(newType, gameOfLifeStructureList, neighborhoodList, true, false); break;
            case "Langton Ant": changeAutomatonType(newType, langtonStructureList, FXCollections.observableArrayList("Von Neuman"), false, false); break;
            case "WireWorld": changeAutomatonType(newType, wireWorldStructureList, FXCollections.observableArrayList("Moore"), false, false); break;
            case "Elementary": changeAutomatonType(newType, elementaryStructureList, FXCollections.observableArrayList("Elementary"), false, true); break;
            case "QuadLife": changeAutomatonType(newType, gameOfLifeStructureList, neighborhoodList, true, false); break;
        }
        gridModel.prepareGridAfterAutomatonChange();
        updateGridDisplay();
    }


    @FXML //size choice box listener
    public void sizeBoxOnChangeListener(){
        String newSize = sizeChoiceBox.getSelectionModel().getSelectedItem();
        switch (newSize) {
            case "10x8": onStartService.setDimension(10,8); break;
            case "20x16": onStartService.setDimension(20,16); break;
            case "40x32": onStartService.setDimension(40,32); break;
            case "50x40": onStartService.setDimension(50,40); break;
            case "100x80": onStartService.setDimension(100,80); break;
            case "125x100": onStartService.setDimension(125,100); break;
        }
        gridModel.setDimension(onStartService.getSizeX(),onStartService.getSizeY());
        gridModel.prepareGridAfterAutomatonChange();
        updateGridDisplay();
    }

    @FXML //Start button listener
    public void onStart() {
        if (isErrorInStartingOptions()) {
            setIsOnError(true);
        } else if (isOnError) {
            setIsOnError(false);
        }
        if (!isOnError) {
            if (!isRunning) {
                startSimulationRunning();
            } else {
                stopSimulationRunning();
            }
        }
    }

    public void setIsOnError(boolean onError){
        errorTitle.setVisible(onError);
        errorText.setVisible(onError);
        isOnError = onError;
    }
    /**
     * This method setting state of simulation on "running" true
     */
    public void startSimulationRunning(){
        isRunning = true;
        startButton.setText("Stop");
        setVisibilityOfControls(true);
        initializeStart();
        timeline.play();
    }

    /**
     * This method setting state of simulation on "running" false
     */
    private void stopSimulationRunning(){
        if(isRunning){
            isRunning = false;
            timeline.stop();
            startButton.setText("Start");
            setVisibilityOfControls(false);
        }
    }

    public void setVisibilityOfControls(boolean isDisable){
        automatonChoiceBox.setDisable(isDisable);
        neighbourhoodChoiceBox.setDisable(isDisable);
        sizeChoiceBox.setDisable(isDisable);
        newRuleTextField.setDisable(isDisable);
        elementaryRuleTextField.setDisable(isDisable);
        surviveRuleTextField.setDisable(isDisable);
        radiusTextField.setDisable(isDisable);
    }

    @FXML
    public void wrappingOnChangeListener(){
        if(wrappingCheckBox.isSelected()){
            newAutomaton.getNeighborsStrategy().setWrapping(true);
            onStartService.setWrapping(true);
        }else{
            newAutomaton.getNeighborsStrategy().setWrapping(false);
            onStartService.setWrapping(false);
        }
    }

    @FXML //reset button listener
    public void reset() {
        if(isRunning){
            stopSimulationRunning();
        }
        automatonChoiceBox.getSelectionModel().selectFirst();
        changeAutomatonType("Game Of Life", gameOfLifeStructureList, neighborhoodList, true, false);
        gridModel.prepareGridAfterAutomatonChange();
        updateGridDisplay();
    }

    /**
     * If there are mouse clicked on map (grid) this method is called. Basic actions is controlled
     * immediately, but to put complex structure there are Putter object called
     * @param event MouseEvent
     */
    @FXML //grid on mouse click listener
    public void mouseClickedOnGrid(MouseEvent event) {
        Putter put = new Putter(gridModel);
        int yWhereClicked = (int) event.getY();
        int xWhereClicked = (int) event.getX();
        int oneCellSize = (int) gridModel.getWidth() / gridModel.getColumns();
        int roundedY = yWhereClicked / oneCellSize;
        int roundedX = xWhereClicked / oneCellSize;

        String action = structureChoiceBox.getSelectionModel().getSelectedItem();
        switch(action) {
            case "Next":
                gridModel.changeOnMapCellStateToNext(roundedX,roundedY);
                drowRectangle(DisplayService.setColorToAutomatonType(onStartService.getAutomatonType(),gridModel.getCellFromMap(roundedX,roundedY)),roundedX,roundedY);
                break;
            case "Alive":
                gridModel.putElementOnMap(roundedX,roundedY,gridModel.getCellFromMap(roundedX,roundedY).alive());
                drowRectangle(DisplayService.setColorToAutomatonType(onStartService.getAutomatonType(),gridModel.getCellFromMap(roundedX,roundedY)),roundedX,roundedY);
                break;
            case "Dead":
                gridModel.putElementOnMap(roundedX,roundedY,DisplayService.setDefaultStateToAutomatonType(onStartService.getAutomatonType()));
                drowRectangle(DisplayService.setColorToAutomatonType(onStartService.getAutomatonType(),gridModel.getCellFromMap(roundedX,roundedY)),roundedX,roundedY);
                break;
            default:
                gridModel = put.putOnMap(roundedX, roundedY, action, automatonChoiceBox.getSelectionModel().getSelectedItem(), structures);
                updateGridDisplay();
                break;
        }
    }

    /**
     * Method responsible for set default fields on choice boxes when automaton started
     */
    private void initializeDefault(){
        automatonChoiceBox.getSelectionModel().selectFirst();
        neighbourhoodChoiceBox.getSelectionModel().select("Moore");
        structureChoiceBox.getSelectionModel().selectFirst();
        sizeChoiceBox.getSelectionModel().select("40x32");
    }

    /**
     * Slider change listener can't be set in FXML file and it's defined there.
     */
    private ChangeListener<Number> sliderChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
        stopSimulationRunning();
        timeline.getKeyFrames().clear();
        if (newValue.intValue() > 0 && newValue.intValue() <= 166) timeline.getKeyFrames().add(frames[0]);
        else if (newValue.intValue() > 166 && newValue.intValue() <= 332) timeline.getKeyFrames().add(frames[1]);
        else if (newValue.intValue() > 332 && newValue.intValue() <= 498) timeline.getKeyFrames().add(frames[2]);
        else if (newValue.intValue() > 498 && newValue.intValue() <= 664) timeline.getKeyFrames().add(frames[3]);
        else if (newValue.intValue() > 664 && newValue.intValue() <= 830) timeline.getKeyFrames().add(frames[4]);
        else if (newValue.intValue() > 830 && newValue.intValue() <= 1000) timeline.getKeyFrames().add(frames[5]);
    };

    /**
     * KeyFrames are using in timeline to control time of one generation exist and grid refreshing
     */
    private void initializeTimeLine(){
        frames = new KeyFrame[10];
        frames[0] = new KeyFrame(Duration.millis(150), (event -> nextGeneration()));
        frames[1] = new KeyFrame(Duration.millis(200), (event -> nextGeneration()));
        frames[2] = new KeyFrame(Duration.millis(300), (event -> nextGeneration()));
        frames[3] = new KeyFrame(Duration.millis(450), (event -> nextGeneration()));
        frames[4] = new KeyFrame(Duration.millis(550), (event -> nextGeneration()));
        frames[5] = new KeyFrame(Duration.millis(999), (event -> nextGeneration()));
        timeline = new Timeline(frames[2]);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }


    public void initializeStart() {
        onStartService.startSettingsUpdate(gridModel.getCellsMap());
        if(wrappingCheckBox.isSelected())onStartService.setWrapping(true);
        else onStartService.setWrapping(false);
        if(automatonChoiceBox.getSelectionModel().getSelectedItem().equals("Game Of Life")) {
            onStartService.setBornRule(updateNewRules(newRuleTextField.getCharacters()));
            onStartService.setSurviveRule(updateNewRules(surviveRuleTextField.getCharacters()));
        }
        if(onStartService.getAutomatonType().equals("Elementary"))onStartService.setElementaryRule(updateElementaryRules(elementaryRuleTextField.getCharacters()));
        onStartService.setRadius(updateElementaryRules(radiusTextField.getCharacters()));
        newAutomaton = onStartService.getAutomaton();
        newAutomaton.setMap(gridModel.getCellsMap());
        gridModel.setCellsMap(newAutomaton.getCells());
        isInicialized = true;
    }

    public int updateElementaryRules(CharSequence charSequence) {
        String ruleString = "";
        for (int i = 0; i < charSequence.length(); i++){
            ruleString += charSequence.charAt(i);
        }
        return Integer.parseInt(ruleString);
    }

    public int[] updateNewRules(CharSequence charSequence) {
        int[] rule = new int[charSequence.length()];
        for (int i = 0; i < charSequence.length(); i++) {
            rule[i] = Character.getNumericValue(charSequence.charAt(i));
        }
        return rule;
    }

    public void updateGridDisplay() {
        Color color;
        gridPane.getChildren().clear();

        for (int i = 0; i < gridModel.getColumns(); i++) {
            for (int j = 0; j < gridModel.getRows(); j++) {
                color = DisplayService.setColorToAutomatonType(gridModel.getAutomatonType(), gridModel.getCellFromMap(i, j));// TODO: 2016-01-03
                drowRectangle(color, i, j);
            }
        }
    }

    private void drowRectangle(Color color, int x, int y) {
        Rectangle rectangle = new Rectangle((gridModel.getWidth() / gridModel.getColumns()) - 1, (gridModel.getHeight() / gridModel.getRows()) - 1);
        rectangle.setFill(color);
        rectangle.setStroke(DEFAULT_STROKE_COLOR);
        rectangle.setStrokeWidth(DEFAULT_STROKE_WIDTH);
        gridPane.add(rectangle, x, y);
    }


    private void changeAutomatonType(String newType, ObservableList<String> structure, ObservableList<String> neighbourhood, boolean areGameOfLifeRulesVisible, boolean areElementaryRulesVisible) {
        structureChoiceBox.getItems().setAll(structure);
        neighbourhoodChoiceBox.getItems().setAll(neighbourhood);
        setAdditionalOptionsSettings(areGameOfLifeRulesVisible, areElementaryRulesVisible);
        onStartService.setAutomatonType(newType);
        gridModel.setAutomatonType(newType);
        isInicialized = false;
    }

    private void setAdditionalOptionsSettings(boolean areGameOfLifeRulesVisible, boolean areElementaryRulesVisible) {
        newRuleTextField.setVisible(areGameOfLifeRulesVisible);
        newcellLabel.setVisible(areGameOfLifeRulesVisible);
        surviveRuleTextField.setVisible(areGameOfLifeRulesVisible);
        surviveLabel.setVisible(areGameOfLifeRulesVisible);
        elementaryRuleTextField.setVisible(areElementaryRulesVisible);
        elementaryRuleLabel.setVisible(areElementaryRulesVisible);
    }

    public boolean isErrorInStartingOptions(){
        return automatonChoiceBox.getSelectionModel().isEmpty() || neighbourhoodChoiceBox.getSelectionModel().isEmpty() ||
                sizeChoiceBox.getSelectionModel().isEmpty() || structureChoiceBox.getSelectionModel().isEmpty();
    }

}
