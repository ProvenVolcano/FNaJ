package cameras;

import animatronics.Animatronic;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Camera {

    private final int ID;
    private ImageView currentImage;
    private HashMap<String, ImageView> images;
    private int x;
    private int y;
    private int distance;
    private ArrayList<Integer> neighbouringIDs;
    private HashMap<Integer, Animatronic> animatronics;
    private int capacity;
    private boolean office;
    private boolean closed;

    public Camera(String[] tokens) {
        ID = Integer.parseInt(tokens[0]);
        neighbouringIDs = new ArrayList<>();
        office = false;
        closed = false;

        String[] neighboursTemp = tokens[1].split("\\.");
        for (String s : neighboursTemp) {
            neighbouringIDs.add(Integer.parseInt(s));
        }

        try {
            currentImage = new ImageView(new Image("file:res/cameras/cam" + ID + "/default.png"));
            x = Integer.parseInt(tokens[3]);
            y = Integer.parseInt(tokens[4]);
        } catch (Exception e) {
            office = true;
        }

        distance = Integer.parseInt(tokens[2]);
        capacity = Integer.parseInt(tokens[5]);
        animatronics = new HashMap<>();
        loadImages();
    }

    /**
     * Loads all images of a camera from a directory into a hashmap
     * Source: ChatGPT
     */
    private void loadImages() {
        images = new HashMap<>();

        File dir = new File("res/cameras/cam" + ID + "/");

        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                Image image = new Image(file.toURI().toString());
                images.put(file.getName(), new ImageView(image));
            }
        }

    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public int getID() {
        return ID;
    }

    public ImageView getCurrentImage() {
        return currentImage;
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
        if (isFree()) {
            animatronics.put(animatronic.getID(), animatronic);
            currentImage = images.get(newImageName());
            return true;
        }
        return false;
    }

    private String newImageName() {
        if(animatronics.isEmpty()){
            return "default.png";
        }

        String name = "";

        ArrayList<Integer> IDs = new ArrayList<>(animatronics.keySet());
        Collections.sort(IDs);

        for(int i = 0; i < IDs.size() - 1; i++) {
            name += IDs.get(i).toString() + "_";
        }

        name += IDs.getLast().toString() + ".png";
        return name;
    }

    public void removeAnimatronic(int id) {
        animatronics.remove(id);
        currentImage = images.get(newImageName());
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
