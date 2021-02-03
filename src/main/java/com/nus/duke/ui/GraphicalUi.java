package com.nus.duke.ui;

import com.nus.duke.common.Logic;

import javafx.stage.Stage;

public class GraphicalUi {

    public GraphicalUi(Stage primaryStage, Logic logic) {
        new MainWindow(primaryStage, logic);
    }
}
