package office;

import animatronics.Animatronic;
import cameras.Camera;
import info.InfoPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * An abstract class that serves as a template for all the office classes
 */
public abstract class OfficeTemplate {

    protected AnchorPane root;
    protected Scene scene;
    protected ImageView officeImage;
    protected InfoPane info;
    protected Animatronic animatronic;
    protected Button doorButton;
    protected Camera neighbourCam;

    public OfficeTemplate(InfoPane info, String imagePath, int width, int height, Camera neighbourCam) {
        root = new AnchorPane();

        this.info = info;
        this.neighbourCam = neighbourCam;

        officeImage = new ImageView("file:" + imagePath);
        root.getChildren().add(officeImage);
        root.getChildren().add(info.getRoot());

        doorButton = new Button();
        doorButton.setOpacity(0.0);
        root.getChildren().add(doorButton);

        scene = new Scene(root, width, height);
    }

    /**
     * What is supposed to happen in the office when the power runs out
     */
    public abstract void powerOut();

    public Scene getScene() {
        return scene;
    }

    public Animatronic getAnimatronic() {
        return animatronic;
    }

    public void setAnimatronic(Animatronic animatronic) {
        this.animatronic = animatronic;
    }
}
