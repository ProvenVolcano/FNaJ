package info;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InfoProperties {

    private int power;
    private int usage;
    private int night;
    private int hour;
    private Thread powerThread;
    private Thread hourThread;
    private StringProperty powerProperty, usageProperty, hourProperty;

    public InfoProperties(int night) {

        this.night = night;
        usage = 0;
        power = 100;
        hour = 0;

        powerProperty = new SimpleStringProperty("Power: 100%");
        usageProperty = new SimpleStringProperty("Usage: 0");
        hourProperty = new SimpleStringProperty("12 AM");

        powerThread = new Thread(() -> {

            while (power > 0) {
                try {
                    Thread.sleep(3010 - (usage * 1000L) );
                } catch (InterruptedException e) {
                    return;
                }
                power -= 1;
                powerProperty.set("Power: " + power + "%");
            }

        });


        hourThread = new Thread(() -> {

            for (int i = 0; i < 6; i++) {
                try{
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    return;
                }
                hour += 1;
                hourProperty.set(hour + " AM");
            }

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

    public int getPower() {
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
