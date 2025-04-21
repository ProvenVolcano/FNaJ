package nights;

import animatronics.Animatronic;

public class NightSix extends Night {

    public NightSix() {
        super();
    }

    @Override
    protected void animatronicInit() {
        animatronics.put(1, Animatronic.factory(1, 16));
        animatronics.put(2, Animatronic.factory(2, 14));
        animatronics.put(3, Animatronic.factory(3, 16));
    }

    @Override
    protected void am1() {
        animatronics.get(2).increaseDifficulty(1);
    }

    @Override
    protected void am2() {
        animatronics.get(1).increaseDifficulty(1);
        animatronics.get(2).increaseDifficulty(1);
    }

    @Override
    protected void am3() {
        animatronics.get(2).increaseDifficulty(1);
    }

    @Override
    protected void am4() {
        animatronics.get(3).increaseDifficulty(2);
    }

    @Override
    protected void am5() {
        animatronics.get(1).increaseDifficulty(1);
        animatronics.get(3).increaseDifficulty(1);
    }
}
