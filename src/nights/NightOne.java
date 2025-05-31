package nights;

/**
 * Handles animatronic AI for night 1
 */
public class NightOne extends Night {

    public NightOne() {
        super();
    }

    @Override
    protected void animatronicStartDiffInit() {
        nanobotStart = 0;
        tasemniceStart = 0;
        jecnakStart = 0;
    }

    @Override
    protected void am1() {
        animatronics.get(1).increaseDifficulty(1);
        animatronics.get(3).increaseDifficulty(1);
    }

    @Override
    protected void am2() {

    }

    @Override
    protected void am3() {
        animatronics.get(1).increaseDifficulty(1);
        animatronics.get(3).increaseDifficulty(1);
    }

    @Override
    protected void am4() {

    }

    @Override
    protected void am5() {

    }
}
