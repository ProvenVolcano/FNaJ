package nights;

/**
 * Handles animatronic AI for night 6
 */
public class NightSix extends Night {

    public NightSix() {
        super();
    }

    @Override
    protected void animatronicStartDiffInit() {
        nanobotStart = 16;
        tasemniceStart = 14;
        jecnakStart = 16;
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
