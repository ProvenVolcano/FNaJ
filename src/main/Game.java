package main;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Game {

    private final int WIDTH;
    private final int HEIGHT;

    private AnchorPane root;
    private Scene officeScene;
    private ImageView officePic;
    private Button monitorButton;
    private Monitor monitor;

    public Game(Stage stage) {
        WIDTH = 16*90;
        HEIGHT = 9*90;

        root = new AnchorPane();

        Image officeImg = new Image("file:res/office.png");
        officePic = new ImageView(officeImg);
        root.getChildren().add(officePic);

        officePic.fitWidthProperty().bind(root.widthProperty().multiply(1));
        officePic.fitHeightProperty().bind(root.heightProperty().multiply(1));

        officeScene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(officeScene);

        monitorButton = new Button("Monitor");
        monitorButton.setPrefWidth(450);
        monitorButton.setPrefHeight(350);
        monitorButton.setOpacity(0);
        AnchorPane.setRightAnchor(monitorButton, 140.0);
        AnchorPane.setBottomAnchor(monitorButton, 150.0);

        monitorButton.setOnAction(e -> {
            stage.setScene(monitor.getScene());
        });

        Button backButton = new Button("Back");
        backButton.setOnMouseEntered(e -> {
            stage.setScene(officeScene);
        });
        monitor = new Monitor(WIDTH, HEIGHT, backButton);

        root.getChildren().add(monitorButton);

        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }
}
