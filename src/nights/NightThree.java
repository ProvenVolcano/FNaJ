package nights;

/**
 * Handles animatronic AI for night 3
 */
public class NightThree extends Night {

    public NightThree() {
        super();
    }

    @Override
    protected void animatronicStartDiffInit() {
        nanobotStart = 3;
        tasemniceStart = 3;
        jecnakStart = 4;
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
