package nights;

import animatronics.Animatronic;

/**
 * Handles animatronic AI for night 4
 */
public class NightFour extends Night {

    public NightFour() {
        super();
    }

    @Override
    protected void animatronicInit() {
        animatronics.put(1, Animatronic.factory(1, 8));
        animatronics.put(2, Animatronic.factory(2, 8));
        animatronics.put(3, Animatronic.factory(3, 8));
    }

    @Override
    protected void am1() {

    }

    @Override
    protected void am2() {
        animatronics.get(1).increaseDifficulty(1);
        animatronics.get(2).increaseDifficulty(2);
        animatronics.get(3).increaseDifficulty(1);
    }

    @Override
    protected void am3() {
        animatronics.get(2).increaseDifficulty(1);
    }

    @Override
    protected void am4() {
        animatronics.get(1).increaseDifficulty(1);
        animatronics.get(2).increaseDifficulty(1);
        animatronics.get(3).increaseDifficulty(2);
    }

    @Override
    protected void am5() {

    }
}
