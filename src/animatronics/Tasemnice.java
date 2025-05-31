package animatronics;

public class Tasemnice extends Animatronic {

    private int phase;

    public Tasemnice(int difficulty) {
        super(2, 3, difficulty, new int[]{1, 8, 9, 10}, new int[]{3}, 6500, "Tasemnice");
    }

    @Override
    public boolean atDoor() {

        phase = 10;

        do {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return true;
            }

            if (monitor.getCameras().get(11).isClosed()) {
                phase--;
            } else phase++;

            if (phase > 30) {
                monitor.moveCloser(this);
            }
            or.updateStageImage(phase);

        } while (phase > 0);

        monitor.moveSomewhere(this, returnCams);
        return false;
    }
}
