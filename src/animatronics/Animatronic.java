package animatronics;

import cameras.Monitor;

import java.util.Random;

public abstract class Animatronic implements Runnable {

    protected final int ID;
    protected int currentPosition;
    protected int startPosition;
    protected int difficulty;
    protected Thread moveThread;
    protected Monitor monitor;
    protected Random rd;

    public Animatronic(int ID, int startPosition, int difficulty, Monitor monitor) {
        this.ID = ID;
        this.startPosition = startPosition;
        this.currentPosition = startPosition;
        this.difficulty = difficulty;
        this.monitor = monitor;
        moveThread = new Thread(this);
        rd = new Random();
    }

    public int getID() {
        return ID;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void activate() {
        System.out.println("Activated in " + currentPosition);
        moveThread.start();
    }
}
