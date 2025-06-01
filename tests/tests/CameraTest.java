package tests;

import cameras.*;
import animatronics.*;
import javafx.application.Platform;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CameraTest {

    /**
     * Tests if animatronics get added in the camera when there is and isn't space
     */
    @Test
    void addAnimatronic() {
        Platform.startup(() -> {});

        Camera c = new Camera(new String[]{"7", "10.5.6.8", "1", "1023", "671", "2"}, null);
        assertTrue(c.addAnimatronic(new Nanobot(1)));
        assertTrue(c.addAnimatronic(new Jecnak(1)));
        assertFalse(c.addAnimatronic(new Tasemnice(1)));
    }

    /**
     * Tests if the name of the image in the camera changes when animatronics in the camera change
     */
    @Test
    void imageNameChange() {
        Platform.startup(() -> {});

        Camera c = new Camera(new String[]{"7", "10.5.6.8", "1", "1023", "671", "2"}, null);
        assertEquals(c.getImages().get("default.png"), c.getCurrentImage());

        c.addAnimatronic(new Nanobot(1));
        assertEquals(c.getImages().get("1.png"), c.getCurrentImage());

        c.addAnimatronic(new Tasemnice(1));
        assertEquals(c.getImages().get("1_2.png"), c.getCurrentImage());

        c.removeAnimatronic(1);
        assertEquals(c.getImages().get("2.png"), c.getCurrentImage());

        c.addAnimatronic(new Jecnak(1));
        assertEquals(c.getImages().get("2_3.png"), c.getCurrentImage());

        c.removeAnimatronic(2);
        assertEquals(c.getImages().get("3.png"), c.getCurrentImage());

        c.addAnimatronic(new Nanobot(1));
        assertEquals(c.getImages().get("1_3.png"), c.getCurrentImage());
    }


}