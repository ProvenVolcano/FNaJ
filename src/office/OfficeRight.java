package office;

import cameras.Camera;
import info.InfoPane;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

/**
 * Class for the right side of the office
 */
public class OfficeRight extends OfficeTemplate {

    private ImageView closedImage;
    private ArrayList<ImageView> tasemniceStages;
    private Button backButton;

    public OfficeRight(int width, int height, Button backButton, InfoPane info, Camera cam) {
        super(info, "res/office/right/officeRightOpen.png", width, height, cam);
        closedImage = new ImageView("file:res/office/right/officeRightClosed.png");

        AnchorPane.setLeftAnchor(backButton, 360.0);
        AnchorPane.setBottomAnchor(backButton, 0.0);
        root.getChildren().add(backButton);
        this.backButton = backButton;

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
            for(ImageView image : tasemniceStages) {
                image.toBack();
            }
            bgBlack.toBack();
        });

        doorButton.setOnMouseReleased(e -> {
            neighbourCam.setClosed(false);
            System.out.println("Opened");
            root.getChildren().add(officeImage);
            officeImage.toBack();
            root.getChildren().remove(closedImage);
            for(ImageView image : tasemniceStages) {
                image.toBack();
            }
            bgBlack.toBack();
        });

        tasemniceStages = new ArrayList<>();
        tasemniceStages.add(new ImageView("file:res/office/right/stage1.png"));
        tasemniceStages.add(new ImageView("file:res/office/right/stage2.png"));
        tasemniceStages.add(new ImageView("file:res/office/right/stage3.png"));

        for(ImageView image : tasemniceStages) {
            image.setX(580);
            image.setY(194);
            image.setVisible(false);
            root.getChildren().add(image);
        }

        bgBlack.toBack();
    }

    /**
     * Updates the eyes image visible in the window based on the phase of Tasemnice
     * @param phase - the phase of Tasemnice
     */
    public void updateStageImage(int phase) {
        if(phase < 1) {
            for(ImageView image : tasemniceStages) {
                image.setVisible(false);
            }
            return;
        }

        if(phase < 10) {
            tasemniceStages.getFirst().setVisible(true);
            tasemniceStages.get(1).setVisible(false);
            tasemniceStages.get(2).setVisible(false);
            return;
        }

        if(phase < 20) {
            tasemniceStages.getFirst().setVisible(false);
            tasemniceStages.get(1).setVisible(true);
            tasemniceStages.get(2).setVisible(false);
            return;
        }

        if(phase < 30) {
            tasemniceStages.getFirst().setVisible(false);
            tasemniceStages.get(1).setVisible(false);
            tasemniceStages.get(2).setVisible(true);
        }
    }

    @Override
    public void jumpscare(int ID) {
        backButton.setDisable(true);
        super.jumpscare(ID);
    }

    @Override
    public void powerOut() {

    }
}
