package main;

import cameras.Monitor;
import info.*;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import nights.Night;
import office.*;

/**
 * Controls the night
 */
public class Game {

    private Stage stage;
    private Monitor monitor;
    private OfficeFront officeFront;
    private OfficeLeft officeLeft;
    private OfficeRight officeRight;
    private InfoProperties info;
    private Night night;
    private int nightNumber;
    private Menu menu;

    public Game(Stage stage, int width, int height, int nightNumber, Menu menu) {

        this.stage = stage;
        this.menu = menu;
        this.nightNumber = nightNumber;

        night = Night.factory(nightNumber);
        info = new InfoProperties(nightNumber, this, night);

        Button backButton = new Button("Back");
        backButton.setOnMouseEntered(e -> {
            info.decreaseUsage();
            stage.setScene(officeFront.getScene());
        });

        monitor = new Monitor(this, width, height, backButton, new InfoPane(info, width, height), night.getAnimatronics());

        officeLeft = new OfficeLeft(width, height, backButton(stage), new InfoPane(info, width, height), info, monitor.getCameras().get(9));
        officeRight = new OfficeRight(width, height, backButton(stage), new InfoPane(info, width, height), monitor.getCameras().get(11));
        officeFront = new OfficeFront(stage, width, height, officeLeft, officeRight, monitor, new InfoPane(info, width, height), info, monitor.getCameras().get(10));

        night.setOffices(officeLeft, officeFront, officeRight);

        stage.setOnCloseRequest(e -> {
            monitor.closeThreads();
            info.closeThreads();
        });
    }

    /**
     * Changes the scene to the night scene and starts the night
     */
    public void startGame() {
        stage.setScene(officeFront.getScene());
        info.startNight();
    }

    /**
     * Creates a button that returns to the scene of the front of the office
     * @param stage - the stage
     * @return - the button
     */
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

    /**
     * Ends the night
     */
    public void nightOver() {
        info.closeThreads();
        menu.endNightSuccess(nightNumber);
    }

    /**
     * Called when power runs out, blocks buttons and the monitor
     */
    public void powerOut() {

        info.setUsage(0);
        officeFront.powerOut();
        officeLeft.powerOut();
        officeRight.powerOut();

        closeMonitor();
    }

    /**
     * Changes the scene to the front of the office if the monitor is active
     */
    private void closeMonitor() {
        if (stage.getScene() == monitor.getScene()) {

            try {
                Platform.runLater(() -> {
                    stage.setScene(officeFront.getScene());
                });
            } catch (Exception _) {
            }
        }
    }

    /**
     * Starts the jumpscare
     * @param animID - ID of the animatronic who jumpscares
     */
    public void jumpscare(int animID) {
        closeMonitor();

        officeFront.jumpscare(animID);
        officeLeft.jumpscare(animID);
        officeRight.jumpscare(animID);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        info.closeThreads();
        menu.endNightJumpScare();
    }
}
