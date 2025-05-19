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

        doorButton.setPrefWidth(450);
        doorButton.setPrefHeight(650);
        doorButton.setLayoutX(450);
        doorButton.setLayoutY(70);

        doorButton.setOnAction(e -> {
            if(ip.getPower() > 0) {
                if (!neighbourCam.isClosed()) {
                    ip.increaseUsage();
                } else ip.decreaseUsage();

                neighbourCam.setClosed(!neighbourCam.isClosed());
            }
        });
    }

    @Override
    public void powerOut() {
        neighbourCam.setClosed(false);
    }
}
