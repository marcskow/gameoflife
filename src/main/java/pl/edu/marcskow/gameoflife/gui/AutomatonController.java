package pl.edu.marcskow.gameoflife.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import pl.edu.marcskow.gameoflife.automat.Automaton;
import pl.edu.marcskow.gameoflife.automat2dim.GameOfLife;
import pl.edu.marcskow.gameoflife.coordinates.CellCoordinates;
import pl.edu.marcskow.gameoflife.coordinates.Coords2D;
import pl.edu.marcskow.gameoflife.factory.CellStateFactory;
import pl.edu.marcskow.gameoflife.factory.GeneralStateFactory;
import pl.edu.marcskow.gameoflife.neighborhood.CellNeighborhood;
import pl.edu.marcskow.gameoflife.neighborhood.MoreNeighborhood;
import pl.edu.marcskow.gameoflife.state.BinaryState;
import pl.edu.marcskow.gameoflife.state.CellState;

import javax.swing.event.DocumentEvent;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Marcin Skowron on 2016-01-02.
 * GUI controller
 */

public class AutomatonController extends AnchorPane implements Initializable {
    private static final Logger log = Logger.getLogger(AutomatonController.class.getName());

    private static final int DEFAULT_GRID_COLUMNS = 40;
    private static final int DEFAULT_GRID_ROWS = 32;
    private static final String DEFAULT_AUTOMATON = "Game Of Life";

    private final ObservableList<String> gridSizeList = FXCollections.observableArrayList("10x8", "20x16", "40x32", "50x40", "100x80", "125x100");
    private final ObservableList<String> automatonTypeList = FXCollections.observableArrayList("Game Of Life", "Langton Ant", "WireWorld", "Elementary", "QuadLife");
    private final ObservableList<String> neighbourhoodList = FXCollections.observableArrayList("Moore", "Von Neuman");

    private final ObservableList<String> gameOfLifeStructureList = FXCollections.observableArrayList("Glider", "Block", "Toat", "Dakota", "Gosper Glider Gun");
    private final ObservableList<String> langtonStructureList = FXCollections.observableArrayList(""); // TODO: 2016-01-02
    private final ObservableList<String> wireWorldStructureList = FXCollections.observableArrayList(""); // TODO: 2016-01-02
    private final ObservableList<String> elementaryStructureList = FXCollections.observableArrayList(""); // TODO: 2016-01-02

    //Game settings
    public static int[] newCellRule;
    public static int[] surviveRule;
    public static int radius = 1;
    public static boolean isWrapping = false;

    //Game running service
    private Timeline timeline;
    private boolean isRunning;
    private boolean isOnError;
    private double timeInMs;
    private KeyFrame[] frames;

    //Game model and helpers
    private GridModel gridModel;
    private GridDisplayController displayController;
    private OnStartService onStartService;

    //New Automaton Fields
    private Automaton newAutomaton;
    private CellNeighborhood newAutomatonNeighborhood;
    private CellStateFactory newAutomatonFactory;

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


    Set<KeyFrame> s;

    KeyFrame key03;

    KeyFrame key01;
    KeyFrame key15;
    KeyFrame key10;
    KeyFrame key20;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        s = new HashSet<KeyFrame>(1);
        sizeChoiceBox.getItems().addAll(gridSizeList);
        sizeChoiceBox.getSelectionModel().selectedItemProperty().addListener(sizeBoxOnChangeListener);

        automatonChoiceBox.getItems().addAll(automatonTypeList);
        automatonChoiceBox.getSelectionModel().selectedItemProperty().addListener(automatonBoxOnChangeListener);

        neighbourhoodChoiceBox.getItems().addAll(FXCollections.emptyObservableList());
        structureChoiceBox.getItems().addAll(FXCollections.emptyObservableList());

        errorText.setVisible(false);
        errorTitle.setVisible(false);

        setAdditionalOptionsSettings(true, false);

        frames = new KeyFrame[10];
        frames[0] = new KeyFrame(Duration.millis(100), (event -> nextGeneration()));
        for (int i = 1; i < 8; i++) {
            frames[i] = new KeyFrame(Duration.millis(i * 200 + 100), (event -> nextGeneration()));
        }
        frames[9] = new KeyFrame(Duration.millis(2000), (event -> nextGeneration()));

        KeyFrame key03 = new KeyFrame(Duration.millis(300), (event -> nextGeneration()));
        timeline = new Timeline(key03);

        key01 = new KeyFrame(Duration.millis(100), (event -> nextAction()));
        key15 = new KeyFrame(Duration.millis(1500), (event -> nextAction()));
        key10 = new KeyFrame(Duration.millis(1000), (event -> nextAction()));
        key20 = new KeyFrame(Duration.millis(2000), (event -> nextAction()));
        timeline.setCycleCount(Timeline.INDEFINITE);

        displayController = new GridDisplayController();
        gridModel = new GridModel(DEFAULT_GRID_COLUMNS, DEFAULT_GRID_ROWS, DEFAULT_AUTOMATON);
        onStartService = new OnStartService();
        CoordinatesService.isWrapping = true;
        updateGridDisplay();


        isOnError = false;
        // generationTimeSlider = new Slider(0,10000,5000);

        generationTimeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                timeline.stop();
                isRunning = false;
                startButton.setText("Start");
                ///timeInMs = newValue.doubleValue();
                //s.clear();
                boolean added = false;
                timeline.getKeyFrames().clear();
                if (newValue.intValue() > 1700 && newValue.intValue() < 2200) timeline.getKeyFrames().add(frames[9]);
                else {
                    for (int i = 0; i < 10; i++) {
                        if (newValue.intValue() >= i * 200 && newValue.intValue() < 200 + i * 200) {
                            timeline.getKeyFrames().add(frames[i]);
                            added = true;
                            break;
                        }
                    }
                }
            }
        });
    }

    private ChangeListener<Number> sliderChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
        timeline.stop();

    };

    public void nextAction() {
        log.info("doing");
        onStartService.startSettingsUpdate(automatonChoiceBox.getValue(),
                neighbourhoodChoiceBox.getValue(), gridModel.getCellsMap(), gridModel.getColumns(), gridModel.getRows());
        newAutomaton = onStartService.getAutomaton();
        newAutomaton.setMap(gridModel.getCellsMap());
        newAutomaton = newAutomaton.nextState();
        gridModel.setCellsMap(newAutomaton.getCells());
        updateGridDisplay();
    }

    @FXML
    public void nextGeneration() {
        newAutomaton = newAutomaton.nextState();
        gridModel.setCellsMap(newAutomaton.getCells());
        updateGridDisplay();
    }

    public void initializeStartSettings() {
        onStartService.startSettingsUpdate(automatonChoiceBox.getValue(),
                neighbourhoodChoiceBox.getValue(), gridModel.getCellsMap(), gridModel.getColumns(), gridModel.getRows());
        newAutomaton = onStartService.getAutomaton();
        newAutomaton.setMap(gridModel.getCellsMap());
        newCellRule = updateNewRules(newRuleTextField.getCharacters());
        surviveRule = updateNewRules(surviveRuleTextField.getCharacters());
        gridModel.setCellsMap(newAutomaton.getCells());
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
                color = displayController.setColorToAutomatonType(gridModel.getAutomatonType(), gridModel.getCellFromMap(i, j));// TODO: 2016-01-03
                drowRectangle(color, i, j);
            }
        }
    }

    private void drowRectangle(Color color, int x, int y) {
        Rectangle rectangle = new Rectangle((gridModel.getWidth() / gridModel.getColumns()) - 1, (gridModel.getHeight() / gridModel.getRows()) - 1);
        rectangle.setFill(color);
        rectangle.setStroke(GridDisplayController.DEFAULT_STROKE_COLOR);
        rectangle.setStrokeWidth(GridDisplayController.DEFAULT_STROKE_WIDTH);
        gridPane.add(rectangle, x, y);
    }

    private ChangeListener<String> automatonBoxOnChangeListener = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldType, String newType) {
            switch (newType) {
                case "Game Of Life":
                    changeAutomatonType(newType, gameOfLifeStructureList, neighbourhoodList, true, false);
                    break;
                case "Langton Ant":
                    changeAutomatonType(newType, langtonStructureList, FXCollections.observableArrayList("Moore", "Von Neuman"), false, false);
                    break;
                case "WireWorld":
                    changeAutomatonType(newType, wireWorldStructureList, FXCollections.observableArrayList("Moore"), false, false);
                    break;
                case "Elementary":
                    changeAutomatonType(newType, elementaryStructureList, FXCollections.observableArrayList("Elementary"), false, true);
                    break;
                case "QuadLife":
                    changeAutomatonType(newType, gameOfLifeStructureList, neighbourhoodList, true, false);
                    break;
            }
            gridModel.prepareGridAfterAutomatonChange();
            updateGridDisplay();
        }
    };

    private void changeAutomatonType(String newType, ObservableList<String> structure, ObservableList<String> neighbourhood, boolean areGameOfLifeRulesVisible, boolean areElementaryRulesVisible) {
        structureChoiceBox.getItems().setAll(structure);
        neighbourhoodChoiceBox.getItems().setAll(neighbourhood);
        setAdditionalOptionsSettings(areGameOfLifeRulesVisible, areElementaryRulesVisible);
        gridModel.setAutomatonType(newType);
    }

    private void setAdditionalOptionsSettings(boolean areGameOfLifeRulesVisible, boolean areElementaryRulesVisible) {
        newRuleTextField.setVisible(areGameOfLifeRulesVisible);
        newcellLabel.setVisible(areGameOfLifeRulesVisible);
        surviveRuleTextField.setVisible(areGameOfLifeRulesVisible);
        surviveLabel.setVisible(areGameOfLifeRulesVisible);
        elementaryRuleTextField.setVisible(areElementaryRulesVisible);
        elementaryRuleLabel.setVisible(areElementaryRulesVisible);
    }

    private ChangeListener<String> sizeBoxOnChangeListener = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldSize, String newSize) {
            switch (newSize) {
                case "10x8":
                    gridModel.setDimension(10, 8);
                    CoordinatesService.setDimension(10, 8);
                    break;
                case "20x16":
                    gridModel.setDimension(20, 16);
                    CoordinatesService.setDimension(20, 16);
                    break;
                case "40x32":
                    gridModel.setDimension(40, 32);
                    CoordinatesService.setDimension(40, 32);
                    break;
                case "50x40":
                    gridModel.setDimension(50, 40);
                    CoordinatesService.setDimension(50, 40);
                    break;
                case "100x80":
                    gridModel.setDimension(100, 80);
                    CoordinatesService.setDimension(100, 80);
                    break;
                case "125x100":
                    gridModel.setDimension(125, 100);
                    CoordinatesService.setDimension(125, 100);
                    break;
            }
            gridModel.prepareGridAfterAutomatonChange(); // TODO: 2016-01-03 after big changes
            updateGridDisplay();
        }
    };

    @FXML
    public void onStart() {
        if (automatonChoiceBox.getSelectionModel().isEmpty() || neighbourhoodChoiceBox.getSelectionModel().isEmpty()) {
            errorTitle.setVisible(true);
            errorText.setVisible(true);
            isOnError = true;
        } else if (isOnError) {
            errorTitle.setVisible(false);
            errorText.setVisible(false);
            isOnError = false;
        }
        if (!isOnError) {
            if (!isRunning) {
                log.info(timeInMs + "");
                startButton.setText("Stop");
                isRunning = true;
                initializeStartSettings();
                timeline.play();
            } else {
                startButton.setText("Start");
                isRunning = false;
                timeline.stop();
            }
        }
    }


    @FXML
    public void reset() {

    }

    @FXML
    public void newCellRuleAction(DocumentEvent e) {
        String newCellRules = e.getDocument().toString();
        int[] rules = new int[newCellRules.length()];
        for (int i = 0; i < newCellRules.length(); i++) {
            rules[i] = newCellRules.charAt(i);
        }
        log.info(newCellRules);
    }

    public BinaryState[][] makeModel(){
        BinaryState[][] gliderBinary = new BinaryState[9][36];
        int[][] glider = {
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
        {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 36; j++){
                if(glider[i][j] == 1){
                    gliderBinary[i][j] = BinaryState.ALIVE;
                }
                else {
                    gliderBinary[i][j] = BinaryState.DEAD;
                }
            }
        }
        return gliderBinary;
    }



    public Map<Coords2D, BinaryState> putStructureOnMap(BinaryState[][] structureModel, int xStruct, int yStruct, int xWhere, int yWhere) {
        Map<Coords2D, BinaryState> map = new HashMap<>();
        for (int i = 0; i < xStruct; i++) {
            for (int j = 0; j < yStruct; j++) {
                map.put(new Coords2D(j + xWhere, i + yWhere), structureModel[i][j]);
            }
        }
        return map;
    }


    @FXML
    public void mouseClickedOnGrid(MouseEvent event) {
        int y = (int) event.getY();
        int x = (int) event.getX();
        int oneCellSize = (int) gridModel.getWidth() / gridModel.getColumns();
        int roundedY = y / oneCellSize;
        int roundedX = x / oneCellSize;

        gridModel.addStructure(putStructureOnMap(makeModel(),9,36,roundedX,roundedY));

      // gridModel.changeCellStateToNext(roundedX,roundedY);
        //gridModel.changeOnMapCellStateToNext(roundedX,roundedY); //!!!!!!!!!!!1

        updateGridDisplay();

        //Color newCellStateColor = displayController.setColorToAutomatonType(gridModel.getAutomatonType(),gridModel.getCellFromMap(roundedX,roundedY));
        //drowRectangle(newCellStateColor,roundedX,roundedY);
    }
}
