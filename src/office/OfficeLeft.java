package office;

import info.InfoPane;
import info.InfoProperties;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class OfficeLeft extends OfficeTemplate {

    private Button backButton;

    public OfficeLeft(int width, int height, Button backButton, InfoPane info, InfoProperties ip) {
        super(info, "res/office/officeLeft.png", width, height);

        AnchorPane.setLeftAnchor(backButton, 360.0);
        AnchorPane.setBottomAnchor(backButton, 0.0);
        root.getChildren().add(backButton);

        doorButton.setPrefWidth(450);
        doorButton.setPrefHeight(650);
        doorButton.setLayoutX(450);
        doorButton.setLayoutY(70);

        doorButton.setOnAction(e -> {
            if(ip.getPower() > 0) {
                if (!doorClosed) {
                    ip.increaseUsage();
                } else ip.decreaseUsage();

                doorClosed = !doorClosed;
            }
        });
    }

    @Override
    public void powerOut() {
        doorClosed = false;
    }
}
