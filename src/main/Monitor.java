package main;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Monitor {

    private Scene scene;
    private Pane root;

    public Monitor(int width, int height, Button backButton) {
        root = new Pane();
        scene = new Scene(root, width, height);

        backButton.setPrefWidth(150);
        backButton.setPrefHeight(50);
        backButton.setLayoutX((double) width / 2 - backButton.getPrefWidth() / 2);
        backButton.setLayoutY(height - backButton.getPrefHeight() - 15);
        root.getChildren().add(backButton);
    }

    public Scene getScene() {
        return scene;
    }
}
