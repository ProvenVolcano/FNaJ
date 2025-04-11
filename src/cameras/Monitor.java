package cameras;

import animatronics.Animatronic;
import animatronics.Nanobot;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Monitor {

    private Scene scene;
    private Pane root;
    private ImageView camImage;
    private ImageView schemeImage;

    private ArrayList<Button> camButtons;
    private HashMap<Integer, Camera> cameras;

    private Random rd;

    public Monitor(int width, int height, Button backButton) {
        root = new Pane();
        scene = new Scene(root, width, height);
        rd = new Random();

        backButton.setPrefWidth(150);
        backButton.setPrefHeight(50);
        backButton.setLayoutX((double) width / 2 - backButton.getPrefWidth() / 2);
        backButton.setLayoutY(height - backButton.getPrefHeight() - 15);

        schemeImage = new ImageView(new Image("file:res/camScheme.png"));
        schemeImage.setX(940);
        schemeImage.setY(400);

        cameras = Camera.createCameras("res/cameras.txt");

        camButtons = new ArrayList<>();

        //creating cameras
        for (Camera camera : cameras.values()) {

            Button btn = new Button();
            btn.setLayoutX(camera.getX());
            btn.setLayoutY(camera.getY());
            btn.setPrefWidth(46);
            btn.setPrefHeight(32);
            btn.setOpacity(0);
            btn.setStyle("-fx-background-color: #00ff09;");

            camButtons.add(btn);

            btn.setOnAction(e -> {

                root.getChildren().remove(camImage);
                camImage = camera.getImage();
                root.getChildren().add(camImage);
                camImage.toBack();

                for (Button camButton : camButtons) {
                    camButton.setOpacity(0);
                }
                btn.setOpacity(0.3);
            });

            root.getChildren().add(btn);
        }

        camImage = cameras.get(1).getImage();

        root.getChildren().add(camImage);
        root.getChildren().add(backButton);
        root.getChildren().add(schemeImage);

        for (Button camButton : camButtons) {
            camButton.toFront();
        }

        camButtons.getFirst().setOpacity(0.3);

        // player office
        cameras.put(0, new Camera(new String[]{"0","0","0","0","0"}));

        cameras.get(1).getAnimatronics().put(1, new Nanobot(this, 5));
        cameras.get(1).getAnimatronics().get(1).activate();
    }

    public void moveCloser(Animatronic animatronic) {
        ArrayList<Integer> closerIDs = closerCameras(animatronic.getCurrentPosition());
        int newPosition = closerIDs.get(rd.nextInt(closerIDs.size()));

        if(newPosition == 0) {
            System.out.println("JUMPSCARE");
            cameras.get(animatronic.getCurrentPosition()).getAnimatronics().remove(animatronic.getID());
            cameras.get(animatronic.getStartPosition()).getAnimatronics().put(animatronic.getID(), animatronic);
            animatronic.setCurrentPosition(animatronic.getStartPosition());
            return;
        }

        cameras.get(newPosition).getAnimatronics().put(animatronic.getID(), animatronic);
        cameras.get(animatronic.getCurrentPosition()).getAnimatronics().remove(animatronic.getID());
        animatronic.setCurrentPosition(newPosition);
    }

    public ArrayList<Integer> closerCameras(int currentID) {
        ArrayList<Integer> camIDs = new ArrayList<>();
        for(int id : cameras.get(currentID).getNeighbouringIDs()) {
            if(cameras.get(id).getDistance() <= cameras.get(currentID).getDistance()) {
                camIDs.add(cameras.get(id).getID());
            }
        }
        return camIDs;
    }

    public Scene getScene() {
        return scene;
    }
}
