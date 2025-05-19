package nights;

import animatronics.Animatronic;

import java.util.HashMap;

/**
 * Abstract class for a night, handles the animatronic AI levels
 */
public abstract class Night {

    protected HashMap<Integer, Animatronic> animatronics;
    protected int hour;

    public Night() {
        animatronics = new HashMap<>();
        hour = 0;
        animatronicInit();
    }

    public HashMap<Integer, Animatronic> getAnimatronics() {
        return animatronics;
    }

    /**
     * Interrupts all animatronics move threads
     */
    public void deactivateAnimatronics() {
        for (Animatronic animatronic : animatronics.values()) {
            animatronic.getMoveThread().interrupt();
        }
    }

    /**
     * Moves to the next hour, calls corresponding hour method
     */
    public void nextHour() {
        switch (hour) {
            case 0:
                for(Animatronic a : animatronics.values()) {
                    a.activate();
                }
                break;
            case 1:
                am1();
                break;
            case 2:
                am2();
                break;
            case 3:
                am3();
                break;
            case 4:
                am4();
                break;
            case 5:
                am5();
                break;
            default:
                return;
        }
        hour++;
    }

    /**
     * Initializes the animatronic, contains their start AI level
     */
    protected abstract void animatronicInit();

    /**
     * What is supposed to happen when 1 AM is reached
     */
    protected abstract void am1();

    /**
     * What is supposed to happen when 2 AM is reached
     */
    protected abstract void am2();

    /**
     * What is supposed to happen when 3 AM is reached
     */
    protected abstract void am3();

    /**
     * What is supposed to happen when 4 AM is reached
     */
    protected abstract void am4();

    /**
     * What is supposed to happen when 5 AM is reached
     */
    protected abstract void am5();

    /**
     * Factory method for all the nights
     * @param night - number of the night
     * @return - the night object
     */
    public static Night factory(int night) {
        return switch (night) {
            case 2 -> new NightTwo();
            case 3 -> new NightThree();
            case 4 -> new NightFour();
            case 5 -> new NightFive();
            case 6 -> new NightSix();
            case 7 -> new NightSeven();
            default -> new NightOne();
        };
    }
}
