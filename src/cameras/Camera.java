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
    private int capacity;
    private boolean office;

    public Camera(String[] tokens) {
        ID = Integer.parseInt(tokens[0]);
        neighbouringIDs = new ArrayList<>();
        office = false;

        String[] neighboursTemp = tokens[1].split("\\.");
        for (String s : neighboursTemp) {
            neighbouringIDs.add(Integer.parseInt(s));
        }

        try {
            image = new ImageView(new Image("file:res/cameras/cam" + ID + "/default.png"));
            x = Integer.parseInt(tokens[3]);
            y = Integer.parseInt(tokens[4]);
        } catch (Exception e) {
            office = true;
        }

        distance = Integer.parseInt(tokens[2]);
        capacity = Integer.parseInt(tokens[5]);
        animatronics = new HashMap<>();
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

    public boolean addAnimatronic(Animatronic animatronic) {
        if(isFree()) {
            animatronics.put(animatronic.getID(), animatronic);
            return true;
        }
        return false;
    }

    public void removeAnimatronic(int id) {
        animatronics.remove(id);
    }

    public boolean isFree() {
        return animatronics.size() < capacity;
    }

    public ArrayList<Integer> getNeighbouringIDs() {
        return neighbouringIDs;
    }

    public int getDistance() {
        return distance;
    }

    public boolean isOffice() {
        return office;
    }

    public int getCapacity() {
        return capacity;
    }

    public static HashMap<Integer, Camera> createCameras(String file) {
        HashMap<Integer, Camera> cameras = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {

                try {
                    Camera camera = new Camera(line.split(","));
                    cameras.put(camera.getID(), camera);
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
