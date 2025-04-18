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
    protected int[] illegalCams;
    protected int[] returnCams;
    protected int baseMoveTime;
    protected String name;

    public Animatronic(int ID, int startPosition, int difficulty, Monitor monitor, int[] illegalCams, int[] returnCams, int baseMoveTime, String name) {
        this.ID = ID;
        this.startPosition = startPosition;
        this.currentPosition = startPosition;
        this.difficulty = difficulty;
        this.monitor = monitor;
        this.illegalCams = illegalCams;
        this.returnCams = returnCams;
        this.baseMoveTime = baseMoveTime;
        this.name = name;

        moveThread = new Thread(this);
        rd = new Random();
    }

    public abstract boolean atDoor();

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

    public boolean isIllegalCam(int id) {
        for (int illegalCam : illegalCams) {
            if (illegalCam == id) {
                return true;
            }
        }
        return false;
    }

    public void activate() {
        System.out.println(name + " activated in " + currentPosition);
        moveThread.start();
    }
}
