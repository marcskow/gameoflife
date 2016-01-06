package pl.edu.marcskow.gameoflife.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Marcin Skowron on 2016-01-02.
 */
public class AutomatonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane automatonRootPane = FXMLLoader.load(this.getClass().getClassLoader().getResource("guiFx.fxml"));
        Scene scene = new Scene(automatonRootPane, automatonRootPane.getPrefWidth(), automatonRootPane.getPrefHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(AutomatonApplication.class);
    }
}

