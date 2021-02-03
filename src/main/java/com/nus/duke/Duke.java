package com.nus.duke;

import com.nus.duke.common.Logic;
import com.nus.duke.ui.GraphicalUi;
import com.nus.duke.ui.TextUi;

import javafx.application.Application;
import javafx.stage.Stage;


public class Duke extends Application {

    private TextUi textUi;
    private GraphicalUi graphicalUi;

    @Override
    public void start(Stage primaryStage) throws Exception {
        new GraphicalUi(primaryStage, new Logic());
    }
}
