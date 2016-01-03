package pl.edu.marcskow.gameoflife.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pl.edu.marcskow.gameoflife.state.BinaryState;
import pl.edu.marcskow.gameoflife.state.CellState;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created by Marcin Skowron on 2016-01-02.
 * GUI controller
 */

public class AutomatonController extends AnchorPane implements Initializable{
    private static final Logger log = Logger.getLogger(AutomatonController.class.getName());

    private static final int DEFAULT_GRID_COLUMNS = 40;
    private static final int DEFAULT_GRID_ROWS = 32;
    private static final String DEFAULT_AUTOMATON = "Game Of Life";

    private final ObservableList<String> gridSizeList = FXCollections.observableArrayList("10x8","20x16","40x32","50x40","100x80","125x100");
    private final ObservableList<String> automatonTypeList = FXCollections.observableArrayList("Game Of Life", "Langton Ant", "WireWorld", "Elementary", "QuadLife");
    private final ObservableList<String> neighbourhoodList = FXCollections.observableArrayList("Moore", "Von Neuman");

    private final ObservableList<String> gameOfLifeStructureList = FXCollections.observableArrayList("Glider", "Block", "Toat", "Dakota", "Gosper Glider Gun");
    private final ObservableList<String> langtonStructureList = FXCollections.observableArrayList(""); // TODO: 2016-01-02
    private final ObservableList<String> wireWorldStructureList = FXCollections.observableArrayList(""); // TODO: 2016-01-02
    private final ObservableList<String> elementaryStructureList = FXCollections.observableArrayList(""); // TODO: 2016-01-02

    private GridModel gridModel;
    private GridDisplayController displayController;

    //General settings
    @FXML  ChoiceBox<String> automatonChoiceBox;
    @FXML  ChoiceBox<String> neighbourhoodChoiceBox;
    @FXML  ChoiceBox<String> sizeChoiceBox;

    //Additional settings
    @FXML  ChoiceBox<String> structureChoiceBox;
    @FXML  CheckBox wrappingCheckBox;

    //Game running
    @FXML  Button startButton;
    @FXML  Button nextgenButton;
    @FXML  Button resetButton;

    //GameOfLife internal rules
    @FXML  TextField newRuleTextField;
    @FXML  TextField surviveRuleTextField;
    @FXML  Label newcellLabel;
    @FXML  Label surviveLabel;

    //Elementary internal rules
    @FXML  TextField elementaryRuleTextField;
    @FXML  Label elementaryRuleLabel;

    //Board
    @FXML  GridPane gridPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sizeChoiceBox.getItems().addAll(gridSizeList);
        sizeChoiceBox.getSelectionModel().selectedItemProperty().addListener(sizeBoxOnChangeListener);

        automatonChoiceBox.getItems().addAll(automatonTypeList);
        automatonChoiceBox.getSelectionModel().selectedItemProperty().addListener(automatonBoxOnChangeListener);
        automatonChoiceBox.getSelectionModel().selectFirst();

        displayController = new GridDisplayController();
        gridModel = new GridModel(DEFAULT_GRID_COLUMNS, DEFAULT_GRID_ROWS, automatonChoiceBox.getValue());

        setDefaultWindowSettings();
    }

    private void setDefaultWindowSettings(){
        setAdditionalOptionsSettings(false, false);

        neighbourhoodChoiceBox.getItems().addAll(FXCollections.emptyObservableList());
        structureChoiceBox.getItems().addAll(FXCollections.emptyObservableList());

        updateGridDisplay();
    }

    public void updateGridDisplay(){
        Color color;
        gridPane.getChildren().clear();
        gridPane.setVisible(false);
        for (int i = 0; i < gridModel.getColumns(); i++) {
            for (int j = 0; j < gridModel.getRows(); j++) {
                color = displayController.setColorToAutomatonType(automatonChoiceBox.getValue(),gridModel.getCell(i,j));
                drowRectangle(color, i, j);
            }
        }
        gridPane.setVisible(true);
    }

    private void drowRectangle(Color color, int x, int y){
        Rectangle rectangle = new Rectangle((gridModel.getWidth()/gridModel.getColumns())-1, (gridModel.getHeight()/gridModel.getRows())-1);
        rectangle.setFill(color);
        rectangle.setStroke(GridDisplayController.DEFAULT_STROKE_COLOR);
        rectangle.setStrokeWidth(GridDisplayController.DEFAULT_STROKE_WIDTH);
        gridPane.add(rectangle,x,y);
    }

    private ChangeListener<String> automatonBoxOnChangeListener = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldType, String newType) {
            switch (newType) {
                case "Game Of Life":changeAutomatonType(newType, gameOfLifeStructureList, neighbourhoodList, true, false); break;
                case "Langton Ant":changeAutomatonType(newType, langtonStructureList, FXCollections.observableArrayList("Von Neuman"), false, false); break;
                case "WireWorld": changeAutomatonType(newType, wireWorldStructureList, FXCollections.observableArrayList("Moore"), false, false); break;
                case "Elementary": changeAutomatonType(newType, elementaryStructureList, FXCollections.observableArrayList("Elementary") ,false, true); break;
                case "QuadLife": changeAutomatonType(newType, gameOfLifeStructureList, neighbourhoodList, true, false); break;
            }
            updateGridDisplay();
        }
    };

    private void changeAutomatonType(String newType, ObservableList<String> structure, ObservableList<String> neighbourhood, boolean areGameOfLifeRulesVisible, boolean areElementaryRulesVisible){
        updatePossibleStructure(structure);
        updateNeighbourhood(neighbourhood);
        setAdditionalOptionsSettings(areGameOfLifeRulesVisible, areElementaryRulesVisible);
        gridModel.setAutomatonType(newType);
    }

    private void setAdditionalOptionsSettings(boolean areGameOfLifeRulesVisible, boolean areElementaryRulesVisible){
        newRuleTextField.setVisible(areGameOfLifeRulesVisible);
        newcellLabel.setVisible(areGameOfLifeRulesVisible);
        surviveRuleTextField.setVisible(areGameOfLifeRulesVisible);
        surviveLabel.setVisible(areGameOfLifeRulesVisible);
        elementaryRuleTextField.setVisible(areElementaryRulesVisible);
        elementaryRuleLabel.setVisible(areElementaryRulesVisible);
    }

    private void updatePossibleStructure(ObservableList<String> structure){
        structureChoiceBox.getItems().setAll(structure);
    }

    private void updateNeighbourhood(ObservableList<String> neighbourhood){
        neighbourhoodChoiceBox.getItems().setAll(neighbourhood);
    }


    private ChangeListener<String> sizeBoxOnChangeListener = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldSize, String newSize) {
            switch (newSize) {
                case "10x8":    gridModel.setDimension(10,8); break;
                case "20x16":   gridModel.setDimension(20,16); break;
                case "40x32":   gridModel.setDimension(40,32); break;
                case "50x40":   gridModel.setDimension(50,40); break;
                case "100x80":  gridModel.setDimension(100,80); break;
                case "125x100": gridModel.setDimension(125,100); break;
            }
            gridModel.prepareGridAfterAutomatonChange(); // TODO: 2016-01-03 after big changes
            updateGridDisplay();
        }
    };

    @FXML
    public void onStart(){

    }

    private EventHandler<MouseEvent> gridOnMouseClickedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            int y = (int) event.getY();
            int x = (int) event.getX();
            int roundedY = y / gridModel.getRows();
            int roundedX = x / gridModel.getColumns();
            CellState current = gridModel.getCell(roundedX,roundedY);
            gridModel.setCell(roundedX,roundedY,current.nextState());
            drowRectangle(displayController.setColorToAutomatonType(automatonChoiceBox.getValue(),current),roundedX,roundedY);

        }

    };

    @FXML
    public void mouseClickedOnGrid(MouseEvent e){
        Node source = (Node)e.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        log.info("Mousce clicked on y: " + e.getY() + " x: " + e.getX());
    }

}
