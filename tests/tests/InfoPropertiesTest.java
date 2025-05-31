package tests;

import info.InfoProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfoPropertiesTest {

    /**
     * Tests if power goes down quicker when usage increases
     */
    @Test
    void usage() {
        InfoProperties ip = new InfoProperties(5, null, null);

        double startPower1 = ip.getPower();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        double endPower1 = ip.getPower();

        double difference1 = endPower1 - startPower1;

        ip.increaseUsage();

        double startPower2 = ip.getPower();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        double endPower2 = ip.getPower();

        double difference2 = endPower2 - startPower2;

        assertTrue(Math.abs(difference1) < Math.abs(difference2));

        ip.closeThreads();
    }

    /**
     * Tests if the night ends at 6 AM
     */
    @Test
    void nightEndTest() {
        InfoProperties ip = new InfoProperties(5, null, null);
        try {
            Thread.sleep(37000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals("6", ip.getHourProperty().get());
    }
}