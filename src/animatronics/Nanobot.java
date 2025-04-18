package animatronics;

import cameras.Monitor;

public class Nanobot extends Animatronic {

    public Nanobot(Monitor monitor, int difficulty) {
        super(1, 1, difficulty, monitor, new int[]{9, 11}, new int[]{1, 2, 3}, 5000, "Nanobot");
    }

    @Override
    public boolean atDoor() {

        try {
            Thread.sleep(rd.nextInt(20) * 100 + (6000 - difficulty * 100L));
        } catch (InterruptedException e) {
            return true;
        }

        for (int i = 0; i < rd.nextInt(difficulty) + 4; i++) {
            monitor.moveCloser(this);

            try {
                Thread.sleep(900 - difficulty * 10L);
            } catch (InterruptedException e) {
                return true;
            }
        }

        return false;
    }
}
