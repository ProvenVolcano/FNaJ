package office;

import animatronics.Animatronic;
import info.InfoPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class OfficeSide {

    private Scene scene;
    private AnchorPane root;
    private ImageView image;
    private Animatronic animatronic;
    private InfoPane info;

    public OfficeSide(int width, int height, Button backButton, String file, InfoPane info) {
        image = new ImageView(new Image("file:res/office/" + file));
        root = new AnchorPane();

        AnchorPane.setLeftAnchor(backButton, 360.0);
        AnchorPane.setBottomAnchor(backButton, 0.0);

        root.getChildren().add(image);

        this.info = info;
        this.info.getRoot().setPrefSize(width, height);
        root.getChildren().add(this.info.getRoot());

        root.getChildren().add(backButton);

        scene = new Scene(root, width, height);
    }

    public Animatronic getAnimatronic() {
        return animatronic;
    }

    public void setAnimatronic(Animatronic animatronic) {
        this.animatronic = animatronic;
    }

    public Scene getScene() {
        return scene;
    }
}
