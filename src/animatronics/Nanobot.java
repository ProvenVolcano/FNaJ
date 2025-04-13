package animatronics;

import cameras.Monitor;

public class Nanobot extends Animatronic {

    public Nanobot(Monitor monitor, int difficulty) {
        super(1, 1, difficulty, monitor);
    }

    @Override
    public void run() {
        while(true) {

            try {
                Thread.sleep(rd.nextInt(3000) + 500);
            } catch (InterruptedException e) {
                return;
            }

            if (rd.nextBoolean()) {
                monitor.moveCloser(this);
                System.out.println("Nanobot moved to: " + currentPosition);
            } else System.out.println("slept");
        }
    }
}
