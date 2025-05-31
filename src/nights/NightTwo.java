package nights;

/**
 * Handles animatronic AI for night 2
 */
public class NightTwo extends Night {

    public NightTwo() {
        super();
    }

    @Override
    protected void animatronicStartDiffInit() {
        nanobotStart = 2;
        tasemniceStart = 1;
        jecnakStart = 2;
    }

    @Override
    protected void am1() {
        animatronics.get(2).increaseDifficulty(1);
    }

    @Override
    protected void am2() {
        animatronics.get(1).increaseDifficulty(1);
        animatronics.get(3).increaseDifficulty(1);
    }

    @Override
    protected void am3() {

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
