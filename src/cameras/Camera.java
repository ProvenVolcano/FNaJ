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

/**
 * Class for a camera which can contain animatronics
 */
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
    private Monitor monitor;

    public Camera(String[] tokens, Monitor monitor) {
        ID = Integer.parseInt(tokens[0]);
        neighbouringIDs = new ArrayList<>();
        office = false;
        closed = false;
        this.monitor = null;

        String[] neighboursTemp = tokens[1].split("\\.");
        for (String s : neighboursTemp) {
            neighbouringIDs.add(Integer.parseInt(s));
        }

        try {
            currentImage = new ImageView(new Image("file:res/cameras/cam" + ID + "/default.png"));
            x = Integer.parseInt(tokens[3]);
            y = Integer.parseInt(tokens[4]);
            this.monitor = monitor;

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

    /**
     * Adds an animatronic to this camera if it is free
     * @param animatronic - the animatronic
     * @return - if the animatronic was added
     */
    public boolean addAnimatronic(Animatronic animatronic) {
        if (isFree()) {
            animatronics.put(animatronic.getID(), animatronic);
            updateCamera();
            return true;
        }
        return false;
    }

    /**
     * Updates the image of this camera
     */
    private void updateCamera() {
        if(monitor == null) {
            return;
        }

        if(monitor.isCurrentCamera(ID)){
            currentImage = images.get(newImageName());
            monitor.updateCameraImage(ID);
        } else currentImage = images.get(newImageName());
    }

    /**
     * Returns the name of the camera image file based on what animatronic are in it
     * @return - the name
     */
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
        System.out.println(name + " cam" + ID);
        return name;
    }

    /**
     * Removes an animatronic from the camera
     * @param id - ID of the animatronic to remove
     */
    public void removeAnimatronic(int id) {
        animatronics.remove(id);
        updateCamera();
    }

    /**
     * Checks if there is space in this camera for an animatronic
     * @return - if the camera is free or not
     */
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

    public HashMap<String, ImageView> getImages() {
        return images;
    }

    /**
     * Creates a HashMap of all the cameras from a file
     * @param file - name of the file from which to create the cameras
     * @param monitor - a monitor object
     * @return - the HashMap of cameras
     */
    public static HashMap<Integer, Camera> createCameras(String file, Monitor monitor) {
        HashMap<Integer, Camera> cameras = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {

                try {
                    Camera camera = new Camera(line.split(","), monitor);
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
