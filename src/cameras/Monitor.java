package cameras;

import animatronics.Animatronic;
import animatronics.Nanobot;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Monitor {

    private Scene scene;
    private Pane root;
    private ImageView camImage;
    private ImageView schemeImage;

    private ArrayList<Button> camButtons;
    private ArrayList<Camera> cameras;

    public Monitor(int width, int height, Button backButton) {
        root = new Pane();
        scene = new Scene(root, width, height);

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
        for (int i = 0; i < cameras.size(); i++) {

            Button btn = new Button();
            btn.setLayoutX(cameras.get(i).getX());
            btn.setLayoutY(cameras.get(i).getY());
            btn.setPrefWidth(46);
            btn.setPrefHeight(32);
            btn.setOpacity(0);
            btn.setStyle("-fx-background-color: #00ff09;");

            camButtons.add(btn);

            final int index = i;
            btn.setOnAction(e -> {

                root.getChildren().remove(camImage);
                camImage = cameras.get(index).getImage();
                root.getChildren().add(camImage);
                camImage.toBack();

                for(int idk = 0; idk < camButtons.size(); idk++){
                    camButtons.get(idk).setOpacity(0);
                }
                btn.setOpacity(0.3);
            });

            root.getChildren().add(btn);
        }

        camImage = cameras.getFirst().getImage();

        root.getChildren().add(camImage);
        root.getChildren().add(backButton);
        root.getChildren().add(schemeImage);

        for (Button camButton : camButtons) {
            camButton.toFront();
        }

        camButtons.getFirst().setOpacity(0.3);
    }

    public ArrayList<Camera> getCameras() {
        return cameras;
    }

    public Scene getScene() {
        return scene;
    }
}
