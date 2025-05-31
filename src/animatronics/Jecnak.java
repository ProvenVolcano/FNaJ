package animatronics;

public class Jecnak extends Animatronic {

    public Jecnak(int difficulty) {
        super(3, 1, difficulty, new int[]{11}, new int[]{1, 2, 3, 4}, 6000, "Jecnak");
    }

    @Override
    public boolean atDoor() {

        // Makes the eyes appear in the appropriate door
        switch (currentPosition) {
            case 9:
                ol.appearEyes();
                break;
            case 10:
                of.appearEyes();
        }

        // Waits some time at the door, before attempting a jumpscare
        try {
            Thread.sleep(rd.nextInt(20) * 100 + (7000 - difficulty * 100L));
        } catch (InterruptedException e) {
            return true;
        }

        // Attempts a jumpscare every iteration, waits in between
        for (int i = 0; i < rd.nextInt(difficulty) + 5; i++) {
            monitor.moveCloser(this);

            try {
                Thread.sleep(1000 - difficulty * 10L);
            } catch (InterruptedException e) {
                return true;
            }
        }

        ol.hideEyes();
        of.hideEyes();
        // If jumpscare wasn't successful in monitor.moveCloser(this), animatronic moves to starting cams
        return false;
    }

}
