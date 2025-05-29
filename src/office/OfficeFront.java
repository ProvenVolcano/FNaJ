package office;

import cameras.Camera;
import info.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import cameras.Monitor;

/**
 * Class of the front of the office
 */
public class OfficeFront extends OfficeTemplate {

    private Button monitorButton;
    private Button left, right;

    public OfficeFront(Stage stage, int width, int height, OfficeLeft officeLeft, OfficeRight officeRight, Monitor monitor, InfoPane info, InfoProperties ip, Camera cam) {
        super(info, "res/office/officeFront.png", width, height, cam);

        monitorButton = new Button("Monitor");
        monitorButton.setPrefWidth(460);
        monitorButton.setPrefHeight(310);
        monitorButton.setOpacity(0);
        monitorButton.setRotate(2);
        AnchorPane.setRightAnchor(monitorButton, 170.0);
        AnchorPane.setBottomAnchor(monitorButton, 110.0);

        monitorButton.setOnAction(e -> {
            if(ip.getPower() > 0) {
                ip.increaseUsage();
                monitor.playStaticFade();
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

        doorButton.setPrefWidth(190);
        doorButton.setPrefHeight(110);
        doorButton.setLayoutX(573);
        doorButton.setLayoutY(225);
        doorButton.setOpacity(0);

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

    /**
     * Locks the front vent so that it cannot be closed
     */
    @Override
    public void powerOut() {
        neighbourCam.setClosed(false);
    }
}
