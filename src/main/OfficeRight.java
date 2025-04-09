package main;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class OfficeRight {

    private Scene scene;
    private AnchorPane root;
    private ImageView image;

    public OfficeRight(int width, int height, Button backButton) {
        image = new ImageView(new Image("file:res/office/officeRight.png"));
        root = new AnchorPane();

        AnchorPane.setLeftAnchor(backButton, 360.0);
        AnchorPane.setBottomAnchor(backButton, 0.0);

        root.getChildren().add(image);
        root.getChildren().add(backButton);

        scene = new Scene(root, width, height);
    }

    public Scene getScene() {
        return scene;
    }
}
