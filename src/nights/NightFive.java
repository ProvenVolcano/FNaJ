package nights;

/**
 * Handles animatronic AI for night 5
 */
public class NightFive extends Night {

    public NightFive() {
        super();
    }

    @Override
    protected void animatronicStartDiffInit() {
        nanobotStart = 9;
        tasemniceStart = 10;
        jecnakStart = 9;
    }

    @Override
    protected void am1() {

    }

    @Override
    protected void am2() {
        animatronics.get(1).increaseDifficulty(2);
        animatronics.get(3).increaseDifficulty(2);
    }

    @Override
    protected void am3() {
        animatronics.get(2).increaseDifficulty(2);
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
