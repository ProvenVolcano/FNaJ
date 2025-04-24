package info;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import main.Game;
import nights.Night;

public class InfoProperties {

    private double power;
    private int usage;
    private int night;
    private int hour;
    private Thread powerThread;
    private Thread hourThread;
    private StringProperty powerProperty, usageProperty, hourProperty;

    public InfoProperties(int nightNumber, Game game, Night night) {

        this.night = nightNumber;
        usage = 1;
        power = 100;
        hour = 0;

        powerProperty = new SimpleStringProperty("Power: 100%");
        usageProperty = new SimpleStringProperty("Usage: 1");
        hourProperty = new SimpleStringProperty("12 AM");

        powerThread = new Thread(() -> {

            while (power > 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    return;
                }
                power -= 0.1*usage;
                powerProperty.set("Power: " + Math.round(power) + "%");
            }
            game.powerOut();

        });


        hourThread = new Thread(() -> {

            for (int i = 0; i < 6; i++) {
                night.nextHour();

                try{
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    return;
                }
                hour += 1;
                hourProperty.set(hour + " AM");
            }
            game.nightOver();
            night.deactivateAnimatronics();

        });

    }

    public void startNight() {
        powerThread.start();
        hourThread.start();
    }

    public void increaseUsage() {
        usage++;
        usageProperty.set("Usage: " + usage);
    }

    public void decreaseUsage() {
        usage--;
        usageProperty.set("Usage: " + usage);
    }

    public void closeThreads() {
        powerThread.interrupt();
        hourThread.interrupt();
    }

    public double getPower() {
        return power;
    }

    public void setUsage(int usage) {
        this.usage = usage;
        usageProperty.set("Usage: " + this.usage);
    }

    public int getUsage() {
        return usage;
    }

    public int getHour() {
        return hour;
    }

    public int getNight() {
        return night;
    }

    public StringProperty getPowerProperty() {
        return powerProperty;
    }

    public StringProperty getUsageProperty() {
        return usageProperty;
    }

    public StringProperty getHourProperty() {
        return hourProperty;
    }

}
