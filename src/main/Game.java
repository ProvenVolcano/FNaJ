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
    private Button left, right;
    private Monitor monitor;
    private OfficeLeft officeLeft;
    private OfficeRight officeRight;

    public Game(Stage stage) {
        WIDTH = 16*90;
        HEIGHT = 9*90;

        root = new AnchorPane();

        Image officeImg = new Image("file:res/office/office.png");
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

        Button backLeft = new Button();
        backLeft.setPrefWidth(720);
        backLeft.setPrefHeight(100);
        backLeft.setOpacity(0);
        backLeft.setOnMouseEntered(e -> {
            stage.setScene(officeScene);
        });

        officeLeft = new OfficeLeft(WIDTH, HEIGHT, backLeft);

        Button backRight = new Button();
        backRight.setPrefWidth(720);
        backRight.setPrefHeight(100);
        backRight.setOpacity(0);
        backRight.setOnMouseEntered(e -> {
            stage.setScene(officeScene);
        });

        officeRight = new OfficeRight(WIDTH, HEIGHT, backRight);

        left = new Button();
        left.setPrefWidth(100);
        left.setPrefHeight(410);
        left.setOpacity(0);
        AnchorPane.setLeftAnchor(left, 0.0);
        AnchorPane.setBottomAnchor(left, 200.0);

        left.setOnMouseEntered(e -> {
            stage.setScene(officeLeft.getScene());
        });

        right = new Button();
        right.setPrefWidth(100);
        right.setPrefHeight(410);
        right.setOpacity(0);
        AnchorPane.setRightAnchor(right, 0.0);
        AnchorPane.setBottomAnchor(right, 200.0);

        right.setOnMouseEntered(e -> {
            stage.setScene(officeRight.getScene());
        });

        Button backButton = new Button("Back");
        backButton.setOnMouseEntered(e -> {
            stage.setScene(officeScene);
        });
        monitor = new Monitor(WIDTH, HEIGHT, backButton);

        root.getChildren().add(monitorButton);
        root.getChildren().add(left);
        root.getChildren().add(right);

        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }
}
