package animatronics;

import cameras.Monitor;

import java.util.Random;

/**
 * Class for an animatronic and its movements
 */
public abstract class Animatronic implements Runnable {

    protected final int ID;
    protected int currentPosition;
    protected int startPosition;
    protected int difficulty;
    protected Thread moveThread;
    protected Monitor monitor;
    protected Random rd;
    protected int[] illegalCams;
    protected int[] returnCams;
    protected int baseMoveTime;
    protected String name;

    public Animatronic(int ID, int startPosition, int difficulty, int[] illegalCams, int[] returnCams, int baseMoveTime, String name) {
        this.ID = ID;
        this.startPosition = startPosition;
        this.currentPosition = startPosition;
        this.difficulty = difficulty;
        this.illegalCams = illegalCams;
        this.returnCams = returnCams;
        this.baseMoveTime = baseMoveTime;
        this.name = name;

        moveThread = new Thread(this);
        rd = new Random();
    }

    /**
     * What is supposed to happen when the animatronic reaches the office doors
     * @return - if the animatronic is at the door or not
     */
    public abstract boolean atDoor();

    /**
     * Movement of the animatronics
     */
    @Override
    public void run() {
        int moveTime = rd.nextInt(10) * 100 + (baseMoveTime - difficulty * 20);

        while (true) {

            try {
                Thread.sleep(moveTime);
            } catch (InterruptedException e) {
                return;
            }

            if (rd.nextInt(20) < difficulty) {
                monitor.moveCloser(this);
                System.out.println(name + " moved to: " + currentPosition);

                if (monitor.getCameras().get(currentPosition).isOffice()) {

                    if (atDoor()) {
                        return;
                    }
                    monitor.moveSomewhere(this, returnCams);

                } else moveTime = rd.nextInt(10) * 100 + (baseMoveTime - difficulty * 20);

            } else System.out.println(name + " slept");

        }
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public String getName() {
        return name;
    }

    public Thread getMoveThread() {
        return moveThread;
    }

    public int getID() {
        return ID;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public int getStartPosition() {
        return startPosition;
    }

    /**
     * Increases the AI level of the animatronic, maximum level is 20
     * @param howMuch - by how much the level should increase
     */
    public void increaseDifficulty(int howMuch) {
        difficulty += howMuch;
        if(difficulty > 20) {
            difficulty = 20;
        }
    }

    /**
     * Checks if a given camera ID isn't of the cameras where the animatronic cannot move
     * @param id - ID of the camera
     * @return - if the camera is illegal to move to
     */
    public boolean isIllegalCam(int id) {
        for (int illegalCam : illegalCams) {
            if (illegalCam == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Activates the move thread of the animatronic
     */
    public void activate() {
        System.out.println(name + " activated in " + currentPosition);
        moveThread.start();
    }

    /**
     * Factory method for the animatronics
     * @param id - ID of the animatronic
     * @param difficulty - AI level of the animatronic
     * @return - the animatronic object
     */
    public static Animatronic factory(int id, int difficulty) {
        return switch (id) {
            case 1 -> new Nanobot(difficulty);
            case 2 -> new Tasemnice(difficulty);
            case 3 -> new Kota(difficulty);
            default -> null;
        };
    }
}
