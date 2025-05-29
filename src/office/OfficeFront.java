package office;

import cameras.Camera;
import info.*;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import cameras.Monitor;
import javafx.util.Duration;

/**
 * Class of the front of the office
 */
public class OfficeFront extends OfficeTemplate {

    private Button monitorButton;
    private Button left, right;
    private Rectangle bgBlack;
    private ImageView door;
    private TranslateTransition closeMove;
    private TranslateTransition openMove;

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

        bgBlack = new Rectangle(900, 400);
        bgBlack.setFill(Color.BLACK);

        door = new ImageView("file:res/office/officeFrontDoor.png");
        AnchorPane.setLeftAnchor(door, 585.0);
        AnchorPane.setTopAnchor(door, 235.0 - 92);

        doorButton.setPrefWidth(190);
        doorButton.setPrefHeight(110);
        doorButton.setLayoutX(573);
        doorButton.setLayoutY(225);
        doorButton.setOpacity(0);

        closeMove = new TranslateTransition(new Duration(150), door);
        closeMove.setByY(92);
        closeMove.setOnFinished(e -> {
            doorButton.setDisable(false);
        });

        openMove = new TranslateTransition(new Duration(150), door);
        openMove.setByY(-92);
        openMove.setOnFinished(e -> {
            doorButton.setDisable(false);
        });

        doorButton.setOnAction(e -> {
            if(ip.getPower() > 0) {
                doorButton.setDisable(true);

                if (!neighbourCam.isClosed()) {
                    closeMove.play();
                    ip.increaseUsage();
                } else {
                    openMove.play();
                    ip.decreaseUsage();
                }

                neighbourCam.setClosed(!neighbourCam.isClosed());
            }
        });

        root.getChildren().addAll(bgBlack, monitorButton, left, right, door);
        door.toBack();
        bgBlack.toBack();
    }

    /**
     * Locks the front vent so that it cannot be closed
     */
    @Override
    public void powerOut() {
        neighbourCam.setClosed(false);
    }
}
