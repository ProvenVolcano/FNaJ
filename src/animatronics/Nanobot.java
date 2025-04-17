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
                Thread.sleep(rd.nextInt(10)*100 + 3000);
            } catch (InterruptedException e) {
                return;
            }

            if (rd.nextInt(20) < difficulty) {
                monitor.moveCloser(this);
                System.out.println("Nanobot moved to: " + currentPosition);
            } else System.out.println("slept");
        }
    }
}
