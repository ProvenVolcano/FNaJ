package nights;

import animatronics.Animatronic;

/**
 * Handles animatronic AI for night 5
 */
public class NightFive extends Night {

    public NightFive() {
        super();
    }

    @Override
    protected void animatronicInit() {
        animatronics.put(1, Animatronic.factory(1, 12));
        animatronics.put(2, Animatronic.factory(2, 13));
        animatronics.put(3, Animatronic.factory(3, 14));
    }

    @Override
    protected void am1() {

    }

    @Override
    protected void am2() {
        animatronics.get(1).increaseDifficulty(2);
        animatronics.get(2).increaseDifficulty(2);
    }

    @Override
    protected void am3() {

    }

    @Override
    protected void am4() {
        animatronics.get(1).increaseDifficulty(1);
        animatronics.get(2).increaseDifficulty(1);
        animatronics.get(3).increaseDifficulty(1);
    }

    @Override
    protected void am5() {

    }
}
