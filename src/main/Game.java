package main;

import cameras.Monitor;
import info.*;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import nights.Night;
import office.*;

public class Game {

    private Stage stage;
    private Monitor monitor;
    private OfficeFront officeFront;
    private OfficeLeft officeLeft;
    private OfficeRight officeRight;
    private InfoProperties info;

    public Game(Stage stage, int width, int height, int nightNumber) {

        this.stage = stage;

        Night night = Night.factory(nightNumber);
        info = new InfoProperties(nightNumber, this, night);

        Button backButton = new Button("Back");
        backButton.setOnMouseEntered(e -> {
            info.decreaseUsage();
            stage.setScene(officeFront.getScene());
        });

        monitor = new Monitor(width, height, backButton, new InfoPane(info, width, height), night.getAnimatronics());

        officeLeft = new OfficeLeft(width, height, backButton(stage), new InfoPane(info, width, height), info, monitor.getCameras().get(9));
        officeRight = new OfficeRight(width, height, backButton(stage), new InfoPane(info, width, height), monitor.getCameras().get(11));
        officeFront = new OfficeFront(stage, width, height, officeLeft, officeRight, monitor, new InfoPane(info, width, height), info, monitor.getCameras().get(10));

        stage.setOnCloseRequest(e -> {
            monitor.closeThreads();
            info.closeThreads();
        });
    }

    public void startGame() {
        stage.setScene(officeFront.getScene());
        info.startNight();
    }

    private Button backButton(Stage stage) {
        Button back = new Button();
        back.setPrefWidth(720);
        back.setPrefHeight(100);
        back.setOpacity(0);
        back.setOnMouseEntered(e -> {
            stage.setScene(officeFront.getScene());
        });
        return back;
    }

    public void nightOver() {
        // Will do later
    }

    public void powerOut() {

        info.setUsage(0);
        officeFront.powerOut();
        officeLeft.powerOut();
        officeRight.powerOut();

        if (stage.getScene() == monitor.getScene()) {

            try {
                Platform.runLater(() -> {
                    stage.setScene(officeFront.getScene());
                });
            } catch (Exception _) {
            }
        }

    }
}
