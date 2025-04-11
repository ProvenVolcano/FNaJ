package animatronics;

import cameras.Monitor;

public abstract class Animatronic implements Runnable {

    protected final int ID;
    protected int currentPosition;
    protected int startPosition;
    protected int difficulty;
    protected Thread moveThread;
    protected Monitor monitor;

    public Animatronic(int ID, int startPosition, int difficulty, Monitor monitor) {
        this.ID = ID;
        this.startPosition = startPosition;
        this.currentPosition = startPosition;
        this.difficulty = difficulty;
        this.monitor = monitor;
        moveThread = new Thread(this);
    }

    public int getID() {
        return ID;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void activate() {
        moveThread.start();
    }
}
