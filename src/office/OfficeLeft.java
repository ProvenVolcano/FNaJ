package office;

import cameras.Camera;
import info.InfoPane;
import info.InfoProperties;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Class for the left side of the office
 */
public class OfficeLeft extends OfficeTemplate {

    private Rectangle bgBlack;
    private ImageView door;
    private TranslateTransition closeMove;
    private TranslateTransition openMove;

    public OfficeLeft(int width, int height, Button backButton, InfoPane info, InfoProperties ip, Camera cam) {
        super(info, "res/office/officeLeft.png", width, height, cam);

        AnchorPane.setLeftAnchor(backButton, 360.0);
        AnchorPane.setBottomAnchor(backButton, 0.0);
        root.getChildren().add(backButton);

        doorButton.setPrefWidth(370);
        doorButton.setPrefHeight(570);
        doorButton.setLayoutX(580);
        doorButton.setLayoutY(130);
        doorButton.setOpacity(0.0);

        bgBlack = new Rectangle(1200, 900);
        bgBlack.setFill(Color.BLACK);

        door = new ImageView("file:res/office/officeLeftDoor.png");
        AnchorPane.setLeftAnchor(door, 600.0);
        AnchorPane.setTopAnchor(door, 155.0 - 737);

        closeMove = new TranslateTransition(new Duration(200), door);
        closeMove.setByY(737);
        closeMove.setOnFinished(e -> {
            doorButton.setDisable(false);
        });

        openMove = new TranslateTransition(new Duration(200), door);
        openMove.setByY(-737);
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

        root.getChildren().addAll(bgBlack, door);
        door.toBack();
        bgBlack.toBack();
    }

    /**
     * Locks the left door so that it cannot be closed
     */
    @Override
    public void powerOut() {
        neighbourCam.setClosed(false);
    }
}
