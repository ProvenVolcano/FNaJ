package nights;

import animatronics.Animatronic;

/**
 * Handles animatronic AI for night 2
 */
public class NightTwo extends Night {

    public NightTwo() {
        super();
    }

    @Override
    protected void animatronicInit() {
        animatronics.put(1, Animatronic.factory(1, 2));
        animatronics.put(2, Animatronic.factory(2, 1));
        animatronics.put(3, Animatronic.factory(3, 2));
    }

    @Override
    protected void am1() {
        animatronics.get(1).increaseDifficulty(1);
        animatronics.get(2).increaseDifficulty(1);
        animatronics.get(3).increaseDifficulty(1);
    }

    @Override
    protected void am2() {

    }

    @Override
    protected void am3() {
        animatronics.get(2).increaseDifficulty(1);
    }

    @Override
    protected void am4() {
        animatronics.get(1).increaseDifficulty(1);
        animatronics.get(3).increaseDifficulty(1);
    }

    @Override
    protected void am5() {

    }
}
