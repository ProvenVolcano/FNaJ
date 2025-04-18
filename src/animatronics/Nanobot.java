package animatronics;

import cameras.Monitor;

public class Nanobot extends Animatronic {

    public Nanobot(Monitor monitor, int difficulty) {
        super(1, 1, difficulty, monitor, new int[]{9, 11}, new int[]{1, 2, 3}, 4000, "Nanobot");
    }

    @Override
    public boolean atDoor() {
        return false;
    }
}
