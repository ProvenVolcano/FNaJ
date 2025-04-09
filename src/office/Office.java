package office;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import cameras.Monitor;

public class Office {

    private AnchorPane root;
    private Scene officeScene;
    private ImageView officePic;
    private Button monitorButton;
    private Button left, right;
    private Monitor monitor;

    public Office(Stage stage, int width, int height, OfficeSide officeLeft, OfficeSide officeRight) {

        root = new AnchorPane();

        Image officeImg = new Image("file:res/office/office.png");
        officePic = new ImageView(officeImg);
        root.getChildren().add(officePic);

        officePic.fitWidthProperty().bind(root.widthProperty().multiply(1));
        officePic.fitHeightProperty().bind(root.heightProperty().multiply(1));

        officeScene = new Scene(root, width, height);

        monitorButton = new Button("Monitor");
        monitorButton.setPrefWidth(450);
        monitorButton.setPrefHeight(350);
        monitorButton.setOpacity(0);
        AnchorPane.setRightAnchor(monitorButton, 140.0);
        AnchorPane.setBottomAnchor(monitorButton, 150.0);

        monitorButton.setOnAction(e -> {
            stage.setScene(monitor.getScene());
        });

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

        monitor = new Monitor(width, height, backButton);

        root.getChildren().add(monitorButton);
        root.getChildren().add(left);
        root.getChildren().add(right);
    }

    public Scene getOfficeScene() {
        return officeScene;
    }
}
