package main;

import cameras.Monitor;
import info.*;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import office.*;

public class Game implements Runnable {

    private final int WIDTH;
    private final int HEIGHT;

    private Stage stage;
    private Monitor monitor;
    private OfficeFront officeFront;
    private OfficeLeft officeLeft;
    private OfficeRight officeRight;
    private InfoProperties info;

    private Thread gameThread;

    public Game(Stage stage) {
        WIDTH = 16*90;
        HEIGHT = 9*90;

        this.stage = stage;
        gameThread = new Thread(this);

        info = new InfoProperties(1);

        officeLeft = new OfficeLeft(WIDTH, HEIGHT, backButton(stage), new InfoPane(info, WIDTH, HEIGHT), info);
        officeRight = new OfficeRight(WIDTH, HEIGHT, backButton(stage), new InfoPane(info, WIDTH, HEIGHT));

        Button backButton = new Button("Back");
        backButton.setOnMouseEntered(e -> {
            info.decreaseUsage();
            stage.setScene(officeFront.getScene());
        });

        monitor = new Monitor(WIDTH, HEIGHT, backButton, new InfoPane(info, WIDTH, HEIGHT));

        officeFront = new OfficeFront(stage, WIDTH, HEIGHT, officeLeft, officeRight, monitor, new InfoPane(info, WIDTH, HEIGHT), info);

        info.startNight();

        stage.setOnCloseRequest(e -> {
            monitor.closeThreads();
            info.closeThreads();
            gameThread.interrupt();
        });

        stage.setScene(officeFront.getScene());
        stage.sizeToScene();
        stage.setResizable(false);

        gameThread.start();
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

    @Override
    public void run() {

        while (info.getHour() != 6) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                return;
            }

            if(info.getPower() <= 0) {
                info.setUsage(0);
                officeFront.powerOut();
                officeLeft.powerOut();
                officeRight.powerOut();

                if(stage.getScene() == monitor.getScene()) {

                    try {
                        Platform.runLater(() -> {
                            stage.setScene(officeFront.getScene());
                        });
                    } catch (Exception _) {}
                }
            }

        }
    }
}
