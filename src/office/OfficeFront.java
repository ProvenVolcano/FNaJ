package office;

import cameras.Camera;
import info.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import cameras.Monitor;

public class OfficeFront extends OfficeTemplate {

    private Button monitorButton;
    private Button left, right;
    private Monitor monitor;

    public OfficeFront(Stage stage, int width, int height, OfficeLeft officeLeft, OfficeRight officeRight, Monitor monitor, InfoPane info, InfoProperties ip, Camera cam) {
        super(info, "res/office/office.png", width, height, cam);

        monitorButton = new Button("Monitor");
        monitorButton.setPrefWidth(450);
        monitorButton.setPrefHeight(350);
        monitorButton.setOpacity(0);
        AnchorPane.setRightAnchor(monitorButton, 140.0);
        AnchorPane.setBottomAnchor(monitorButton, 150.0);

        monitorButton.setOnAction(e -> {
            if(ip.getPower() > 0) {
                ip.increaseUsage();
                stage.setScene(monitor.getScene());
            }
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

        doorButton.setPrefWidth(250);
        doorButton.setPrefHeight(210);
        doorButton.setLayoutX(470);
        doorButton.setLayoutY(70);

        doorButton.setOnAction(e -> {
            if(ip.getPower() > 0) {
                if (!neighbourCam.isClosed()) {
                    ip.increaseUsage();
                } else ip.decreaseUsage();

                neighbourCam.setClosed(!neighbourCam.isClosed());
            }
        });

        root.getChildren().add(monitorButton);
        root.getChildren().add(left);
        root.getChildren().add(right);
    }

    @Override
    public void powerOut() {
        neighbourCam.setClosed(false);
    }
}
