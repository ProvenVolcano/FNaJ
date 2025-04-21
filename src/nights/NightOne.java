package nights;

import animatronics.Animatronic;

public class NightOne extends Night {

    public NightOne() {
        super();
    }

    @Override
    protected void animatronicInit() {
        animatronics.put(1, Animatronic.factory(1, 0));
        animatronics.put(2, Animatronic.factory(2, 0));
        animatronics.put(3, Animatronic.factory(3, 0));
    }

    @Override
    protected void am1() {
        animatronics.get(1).increaseDifficulty(2);
        animatronics.get(3).increaseDifficulty(2);
    }

    @Override
    protected void am2() {

    }

    @Override
    protected void am3() {
        animatronics.get(1).increaseDifficulty(1);
        animatronics.get(3).increaseDifficulty(2);
    }

    @Override
    protected void am4() {

    }

    @Override
    protected void am5() {

    }
}
