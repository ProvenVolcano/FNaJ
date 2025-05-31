package cameras;

import animatronics.*;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import info.InfoPane;
import javafx.util.Duration;
import main.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * The monitor, shows the cameras and controls the movement of the animatronics
 */
public class Monitor {

    private Game game;
    private Scene scene;
    private Pane root;
    private ImageView camImage;
    private ImageView schemeImage;
    private ImageView staticGif;
    private FadeTransition ft;

    private ArrayList<Button> camButtons;
    private HashMap<Integer, Camera> cameras;

    private Random rd;

    public Monitor(Game game, int width, int height, Button backButton, InfoPane info, HashMap<Integer, Animatronic> animatronics) {
        this.game = game;
        root = new Pane();
        scene = new Scene(root, width, height);
        rd = new Random();

        backButton.setPrefWidth(150);
        backButton.setPrefHeight(50);
        backButton.setLayoutX((double) width / 2 - backButton.getPrefWidth() / 2);
        backButton.setLayoutY(height - backButton.getPrefHeight() - 15);

        staticGif = new ImageView(new Image("file:res/images/static.gif"));
        staticGif.setOpacity(0);

        schemeImage = new ImageView(new Image("file:res/cameras/camScheme.png"));
        schemeImage.setX(940);
        schemeImage.setY(400);

        cameras = Camera.createCameras("res/cameras/cameras.txt", this);

        camButtons = new ArrayList<>();

        camImage = cameras.get(1).getCurrentImage();
        root.getChildren().add(camImage);

        ft = new FadeTransition(new Duration(700), staticGif);
        ft.setFromValue(1.0);
        ft.setToValue(0.06);

        //creating cameras
        for (Camera camera : cameras.values()) {
            if (!camera.isOffice()) {

                Button btn = new Button();
                btn.setLayoutX(camera.getX());
                btn.setLayoutY(camera.getY());
                btn.setPrefWidth(46);
                btn.setPrefHeight(32);
                btn.setOpacity(0);
                btn.setStyle("-fx-background-color: #00ff09;");

                camButtons.add(btn);

                btn.setOnAction(e -> {

                    playStaticFade();

                    root.getChildren().remove(camImage);
                    camImage = camera.getCurrentImage();
                    root.getChildren().add(camImage);
                    camImage.toBack();

                    for (Button camButton : camButtons) {
                        camButton.setOpacity(0);
                    }
                    btn.setOpacity(0.3);
                });

                root.getChildren().add(btn);
            }
        }

        root.getChildren().add(staticGif);
        root.getChildren().add(info.getRoot());
        root.getChildren().add(backButton);
        root.getChildren().add(schemeImage);

        for (Button camButton : camButtons) {
            camButton.toFront();
        }

        camButtons.getFirst().setOpacity(0.3);

        // player office
        cameras.put(0, new Camera(new String[]{"0", "0", "0", "0", "0", "1"}, this));
        addAnimatronics(animatronics);
    }

    /**
     * Adds animatronics into their cameras
     * @param animatronics - the animatronics
     */
    public void addAnimatronics(HashMap<Integer, Animatronic> animatronics) {
        for (Animatronic a : animatronics.values()) {
            a.setMonitor(this);
            cameras.get(a.getStartPosition()).addAnimatronic(a);
        }
    }

    /**
     * Plays the camera static gif fade animations
     */
    public void playStaticFade() {
        ft.stop();
        ft.playFromStart();
    }

    /**
     * Moves an animatronic to a camera closer to the office
     * @param animatronic - the animatronic to move
     */
    public void moveCloser(Animatronic animatronic) {
        ArrayList<Integer> closerIDs = closerCameras(animatronic);
        if (closerIDs.isEmpty()) {
            return;
        }
        int newPosition = closerIDs.get(rd.nextInt(closerIDs.size()));

        if (newPosition == 0) {
            if (!cameras.get(animatronic.getCurrentPosition()).isClosed()) {

                System.out.println("JUMPSCARE by " + animatronic.getName());
                game.jumpscare(animatronic.getID());

            } else System.out.println("Door blocked");
            return;
        }

        cameras.get(newPosition).addAnimatronic(animatronic);
        cameras.get(animatronic.getCurrentPosition()).removeAnimatronic(animatronic.getID());
        animatronic.setCurrentPosition(newPosition);
    }

    /**
     * Returns an ArrayList of IDs of free cameras that are closer to the office than the animatronics current position
     * @param animatronic - an animatronic
     * @return - the ArrayList of cameras
     */
    public ArrayList<Integer> closerCameras(Animatronic animatronic) {
        ArrayList<Integer> camIDs = new ArrayList<>();
        for (int id : cameras.get(animatronic.getCurrentPosition()).getNeighbouringIDs()) {
            if (cameras.get(id).getDistance() <= cameras.get(animatronic.getCurrentPosition()).getDistance() && !animatronic.isIllegalCam(id) && cameras.get(id).isFree()) {
                camIDs.add(cameras.get(id).getID());
            }
        }
        return camIDs;
    }

    /**
     * Moves an animatronic into one of gives cameras
     * @param animatronic - the animatronic to move
     * @param camIDs - array of IDs to choose from
     */
    public void moveSomewhere(Animatronic animatronic, int[] camIDs) {
        int moveID = camIDs[rd.nextInt(camIDs.length)];
        cameras.get(animatronic.getCurrentPosition()).removeAnimatronic(animatronic.getID());
        cameras.get(moveID).addAnimatronic(animatronic);
        animatronic.setCurrentPosition(moveID);
    }

    /**
     * Closes all animatronic movement threads
     */
    public void closeThreads() {
        for (Camera camera : cameras.values()) {
            for (Animatronic animatronic : camera.getAnimatronics().values()) {
                animatronic.getMoveThread().interrupt();
            }
        }
    }

    /**
     * Plays the camera static gif on the camera and changes the camera image behind it
     * @param id - ID of the camera to update
     */
    public void updateCameraImage(int id) {
        try {
            Platform.runLater(() -> {
                ft.stop();
                staticGif.setOpacity(1);

                root.getChildren().remove(camImage);
                camImage = cameras.get(id).getCurrentImage();
                root.getChildren().add(camImage);
                camImage.toBack();

                staticGif.setOpacity(1);
            });
        } catch (Exception _) {
        }
    }

    /**
     * Checks if the player is currently looking at a gives camera
     * @param id - ID of the camera to check
     * @return - if the player is looking at the gives camera
     */
    public boolean isCurrentCamera(int id) {
        return cameras.get(id).getCurrentImage() == camImage;
    }

    public Scene getScene() {
        return scene;
    }

    public HashMap<Integer, Camera> getCameras() {
        return cameras;
    }
}
