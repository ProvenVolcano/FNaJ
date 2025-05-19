package nights;

import animatronics.Animatronic;

public class NightSeven extends Night {

    public NightSeven() {
        super();
    }

    @Override
    protected void animatronicInit() {
        animatronics.put(1, Animatronic.factory(1, 20));
        animatronics.put(2, Animatronic.factory(2, 20));
        animatronics.put(3, Animatronic.factory(3, 20));
    }

    @Override
    protected void am1() {

    }

    @Override
    protected void am2() {

    }

    @Override
    protected void am3() {

    }

    @Override
    protected void am4() {

    }

    @Override
    protected void am5() {

    }
}
