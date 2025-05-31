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

    protected ImageView jecnakJumpscareImage;
    protected ImageView tasemniceJumpscareImage;
    protected ImageView nanobotJumpscareImage;

    public OfficeTemplate(InfoPane info, String imagePath, int width, int height, Camera neighbourCam) {
        root = new AnchorPane();

        this.info = info;
        this.neighbourCam = neighbourCam;

        officeImage = new ImageView("file:" + imagePath);

        doorButton = new Button();
        doorButton.setOpacity(0.0);

        jecnakJumpscareImage = new ImageView("file:res/images/jecnakJumpscare.png");
        jecnakJumpscareImage.setVisible(false);
        tasemniceJumpscareImage = new ImageView("file:res/images/tasemniceJumpscare.png");
        tasemniceJumpscareImage.setVisible(false);
        nanobotJumpscareImage = new ImageView("file:res/images/nanobotJumpscare.png");
        nanobotJumpscareImage.setVisible(false);

        root.getChildren().addAll(officeImage, info.getRoot(), doorButton, jecnakJumpscareImage, tasemniceJumpscareImage, nanobotJumpscareImage);

        scene = new Scene(root, width, height);
    }

    /**
     * What is supposed to happen in the office when the power runs out
     */
    public abstract void powerOut();

    public void jumpscare(int ID) {
        switch(ID) {
            case 1:
                nanobotJumpscareImage.setVisible(true);
                break;
            case 2:
                tasemniceJumpscareImage.setVisible(true);
                break;
            case 3:
                jecnakJumpscareImage.setVisible(true);

        }
    }

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
