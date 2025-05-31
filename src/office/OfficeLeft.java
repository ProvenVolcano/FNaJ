package office;

import cameras.Camera;
import info.InfoPane;
import info.InfoProperties;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * Class for the left side of the office
 */
public class OfficeLeft extends OfficeTemplate {

    private ImageView door;
    private ImageView eyes;
    private TranslateTransition closeMove;
    private TranslateTransition openMove;
    private Button backButton;

    public OfficeLeft(int width, int height, Button backButton, InfoPane info, InfoProperties ip, Camera cam) {
        super(info, "res/office/left/officeLeft.png", width, height, cam);

        AnchorPane.setLeftAnchor(backButton, 360.0);
        AnchorPane.setBottomAnchor(backButton, 0.0);
        root.getChildren().add(backButton);
        this.backButton = backButton;

        doorButton.setPrefWidth(370);
        doorButton.setPrefHeight(570);
        doorButton.setLayoutX(580);
        doorButton.setLayoutY(130);
        doorButton.setOpacity(0.0);

        door = new ImageView("file:res/office/left/officeLeftDoor.png");
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

        eyes = new ImageView("file:res/office/left/leftEyes.png");
        AnchorPane.setLeftAnchor(eyes, 600.0);
        AnchorPane.setTopAnchor(eyes, 155.0);
        eyes.setVisible(false);

        root.getChildren().addAll(door, eyes);
        door.toBack();
        eyes.toBack();
        bgBlack.toBack();
    }

    public void appearEyes() {
        eyes.setVisible(true);
    }

    public void hideEyes() {
        eyes.setVisible(false);
    }

    @Override
    public void jumpscare(int ID) {
        backButton.setDisable(true);
        super.jumpscare(ID);
    }

    /**
     * Locks the left door so that it cannot be closed
     */
    @Override
    public void powerOut() {
        openMove.play();
        neighbourCam.setClosed(false);
    }
}
