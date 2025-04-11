package cameras;

import animatronics.Animatronic;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Camera {

    private final int ID;
    private ImageView image;
    private int x;
    private int y;
    private int distance;
    private ArrayList<Integer> neighbouringIDs;
    private HashMap<Integer, Animatronic> animatronics;

    public Camera(String[] tokens) {
        ID = Integer.parseInt(tokens[0]);
        neighbouringIDs = new ArrayList<>();

        String[] neighboursTemp = tokens[1].split("\\.");
        for (String s : neighboursTemp) {
            neighbouringIDs.add(Integer.parseInt(s));
        }

        image = new ImageView(new Image("file:res/cameras/cam" + ID + ".png"));
        distance = Integer.parseInt(tokens[2]);
        x = Integer.parseInt(tokens[3]);
        y = Integer.parseInt(tokens[4]);
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

    public HashMap<Integer, Animatronic> getAnimatronics() {
        return animatronics;
    }

    public ArrayList<Integer> getNeighbouringIDs() {
        return neighbouringIDs;
    }

    public static ArrayList<Camera> createCameras(String file) {
        ArrayList<Camera> cameras = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {

                try {
                    cameras.add(new Camera(line.split(",")));
                } catch (Exception e) {
                    System.out.println("A camera failed to be created");
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return cameras;
    }
}
