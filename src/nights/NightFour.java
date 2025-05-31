package nights;

/**
 * Handles animatronic AI for night 4
 */
public class NightFour extends Night {

    public NightFour() {
        super();
    }

    @Override
    protected void animatronicStartDiffInit() {
        nanobotStart = 5;
        tasemniceStart = 6;
        jecnakStart = 5;
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
        animatronics.get(1).increaseDifficulty(2);
        animatronics.get(3).increaseDifficulty(2);
    }

    @Override
    protected void am5() {

    }
}
