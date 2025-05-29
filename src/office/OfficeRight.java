package office;

import cameras.Camera;
import info.InfoPane;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Class for the right side of the office
 */
public class OfficeRight extends OfficeTemplate {

    private ImageView closedImage;

    public OfficeRight(int width, int height, Button backButton, InfoPane info, Camera cam) {
        super(info, "res/office/officeRightOpen.png", width, height, cam);
        closedImage = new ImageView("file:res/office/officeRightClosed.png");

        AnchorPane.setLeftAnchor(backButton, 360.0);
        AnchorPane.setBottomAnchor(backButton, 0.0);
        root.getChildren().add(backButton);

        doorButton.setPrefWidth(355);
        doorButton.setPrefHeight(525);
        doorButton.setLayoutX(560);
        doorButton.setLayoutY(188);
        doorButton.setOpacity(0.0);

        doorButton.setOnMousePressed(e -> {
            neighbourCam.setClosed(true);
            System.out.println("Closed");
            root.getChildren().add(closedImage);
            closedImage.toBack();
            root.getChildren().remove(officeImage);
        });

        doorButton.setOnMouseReleased(e -> {
            neighbourCam.setClosed(false);
            System.out.println("Opened");
            root.getChildren().add(officeImage);
            officeImage.toBack();
            root.getChildren().remove(closedImage);
        });
    }

    @Override
    public void powerOut() {

    }
}
