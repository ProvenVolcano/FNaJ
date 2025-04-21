package nights;

import animatronics.Animatronic;

import java.util.HashMap;

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

    protected abstract void animatronicInit();

    protected abstract void am1();

    protected abstract void am2();

    protected abstract void am3();

    protected abstract void am4();

    protected abstract void am5();

    public static Night factory(int night) {
        return switch (night) {
            case 2 -> new NightTwo();
            case 3 -> new NightThree();
            case 4 -> new NightFour();
            case 5 -> new NightFive();
            case 6 -> new NightSix();
            default -> new NightOne();
        };
    }
}
