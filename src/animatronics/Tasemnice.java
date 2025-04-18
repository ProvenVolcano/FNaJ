package animatronics;

import cameras.Monitor;

public class Tasemnice extends Animatronic {

    public Tasemnice(Monitor monitor, int difficulty) {
        super(2, 3, difficulty, monitor, new int[]{1, 8, 9, 10}, new int[]{3}, 3000, "Tasemnice");
    }

    @Override
    public boolean atDoor() {
        return false;
    }
}
