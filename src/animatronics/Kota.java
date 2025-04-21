package animatronics;

public class Kota extends Animatronic {

    public Kota(int difficulty) {
        super(3, 1, difficulty, new int[]{11}, new int[]{1, 2, 3, 4}, 6000, "Kota");
    }

    @Override
    public boolean atDoor() {

        try {
            Thread.sleep(rd.nextInt(20) * 100 + (7000 - difficulty * 100L));
        } catch (InterruptedException e) {
            return true;
        }

        for (int i = 0; i < rd.nextInt(difficulty) + 5; i++) {
            monitor.moveCloser(this);

            try {
                Thread.sleep(1000 - difficulty * 10L);
            } catch (InterruptedException e) {
                return true;
            }
        }

        return false;
    }
}
