package main;

import cameras.Monitor;
import info.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import office.*;

public class Game {

    private final int WIDTH;
    private final int HEIGHT;

    private Office office;
    private OfficeSide officeLeft;
    private OfficeSide officeRight;

    public Game(Stage stage) {
        WIDTH = 16*90;
        HEIGHT = 9*90;

        InfoProperties info = new InfoProperties(1);

        officeLeft = new OfficeSide(WIDTH, HEIGHT, backButton(stage), "officeLeft.png", new InfoPane(info));
        officeRight = new OfficeSide(WIDTH, HEIGHT, backButton(stage), "officeRight.png", new InfoPane(info));

        Button backButton = new Button("Back");
        backButton.setOnMouseEntered(e -> {
            info.decreaseUsage();
            stage.setScene(office.getOfficeScene());
        });

        Monitor monitor = new Monitor(WIDTH, HEIGHT, backButton, new InfoPane(info));

        office = new Office(stage, WIDTH, HEIGHT, officeLeft, officeRight, monitor, new InfoPane(info), info);

        info.startNight();

        stage.setOnCloseRequest(e -> {
            monitor.closeThreads();
            info.closeThreads();
        });
        stage.setScene(office.getOfficeScene());
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
            stage.setScene(office.getOfficeScene());
        });
        return back;
    }
}
