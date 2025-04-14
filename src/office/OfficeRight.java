package office;

import info.InfoPane;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class OfficeRight extends OfficeTemplate {

    private Button backButton;

    public OfficeRight(int width, int height, Button backButton, InfoPane info) {
        super(info, "res/office/officeRight.png", width, height);

        AnchorPane.setLeftAnchor(backButton, 360.0);
        AnchorPane.setBottomAnchor(backButton, 0.0);
        root.getChildren().add(backButton);

        doorButton.setPrefWidth(600);
        doorButton.setPrefHeight(600);
        doorButton.setLayoutX(450);
        doorButton.setLayoutY(100);

        doorButton.setOnMouseClicked(e -> {
            doorClosed = true;
        });

        doorButton.setOnMouseReleased(e -> {
            doorClosed = false;
        });
    }

    @Override
    public void powerOut() {

    }
}
