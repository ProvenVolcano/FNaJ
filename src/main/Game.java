package main;

import cameras.Monitor;
import info.*;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import office.*;

public class Game {

    private final int WIDTH;
    private final int HEIGHT;

    private Stage stage;
    private Monitor monitor;
    private OfficeFront officeFront;
    private OfficeLeft officeLeft;
    private OfficeRight officeRight;
    private InfoProperties info;

    public Game(Stage stage) {
        WIDTH = 16 * 90;
        HEIGHT = 9 * 90;

        this.stage = stage;
        info = new InfoProperties(1, this);

        Button backButton = new Button("Back");
        backButton.setOnMouseEntered(e -> {
            info.decreaseUsage();
            stage.setScene(officeFront.getScene());
        });

        monitor = new Monitor(WIDTH, HEIGHT, backButton, new InfoPane(info, WIDTH, HEIGHT));

        officeLeft = new OfficeLeft(WIDTH, HEIGHT, backButton(stage), new InfoPane(info, WIDTH, HEIGHT), info, monitor.getCameras().get(9));
        officeRight = new OfficeRight(WIDTH, HEIGHT, backButton(stage), new InfoPane(info, WIDTH, HEIGHT), monitor.getCameras().get(11));
        officeFront = new OfficeFront(stage, WIDTH, HEIGHT, officeLeft, officeRight, monitor, new InfoPane(info, WIDTH, HEIGHT), info, monitor.getCameras().get(10));

        info.startNight();

        stage.setOnCloseRequest(e -> {
            monitor.closeThreads();
            info.closeThreads();
        });

        stage.setScene(officeFront.getScene());
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
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
