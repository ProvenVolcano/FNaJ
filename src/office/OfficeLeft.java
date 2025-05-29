package office;

import cameras.Camera;
import info.InfoPane;
import info.InfoProperties;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Class for the left side of the office
 */
public class OfficeLeft extends OfficeTemplate {

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

        doorButton.setOnAction(e -> {
            if(ip.getPower() > 0) {
                if (!neighbourCam.isClosed()) {
                    ip.increaseUsage();
                } else ip.decreaseUsage();

                neighbourCam.setClosed(!neighbourCam.isClosed());
            }
        });
    }

    /**
     * Locks the left door so that it cannot be closed
     */
    @Override
    public void powerOut() {
        neighbourCam.setClosed(false);
    }
}
