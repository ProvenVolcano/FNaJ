package main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Camera {

    private final int ID;
    private ImageView image;
    private int x;
    private int y;

    public Camera(int id, String file, int width, int height, int x, int y) {
        ID = id;
        this.x = x;
        this.y = y;
        image = new ImageView(new Image("file:" + file));
        image.setFitWidth(width);
        image.setFitHeight(height);
    }

    public int getID() {
        return ID;
    }

    public ImageView getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Camera createCamera(int index, int width, int height) {
        return switch (index) {
            case 0 -> new Camera(1, "res/cameras/cam1.png", width, height, 1334, 572);
            case 1 -> new Camera(2, "res/cameras/cam2.png", width, height, 1356, 512);
            case 2 -> new Camera(3, "res/cameras/cam3.png", width, height, 1356, 415);
            case 3 -> new Camera(4, "res/cameras/cam4.png", width, height, 1227, 472);
            case 4 -> new Camera(5, "res/cameras/cam5.png", width, height, 1198, 670);
            case 5 -> new Camera(6, "res/cameras/cam6.png", width, height, 1068, 473);
            case 6 -> new Camera(7, "res/cameras/cam7.png", width, height, 1066, 670);
            case 7 -> new Camera(8, "res/cameras/cam8.png", width, height, 951, 690);
            default -> null;
        };
    }
}
