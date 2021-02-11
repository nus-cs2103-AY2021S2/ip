package com.nus.duke;

import com.nus.duke.common.Logic;
import com.nus.duke.ui.GraphicalUi;

import javafx.application.Application;
import javafx.stage.Stage;

public class Duke extends Application {

    @Override
    public void start(Stage primaryStage) {
        new GraphicalUi(primaryStage, new Logic());
    }
}
