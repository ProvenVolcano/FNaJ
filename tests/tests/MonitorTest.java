package tests;

import cameras.*;
import animatronics.Animatronic;
import animatronics.Jecnak;
import animatronics.Nanobot;
import animatronics.Tasemnice;
import javafx.application.Platform;
import javafx.scene.control.Button;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MonitorTest {

    /**
     * Tests if the correct neighbouring cameras are returned
     */
    @Test
    void closerCameras() {
        Platform.startup(() -> {});

        HashMap<Integer, Animatronic> animatronics = new HashMap<>();
        animatronics.put(1, new Nanobot(20));
        animatronics.put(2, new Tasemnice(20));
        animatronics.put(3, new Jecnak(20));

        Monitor m = new Monitor(null, 1440, 810, new Button(), null, animatronics);

        ArrayList<Integer> neiCams = new ArrayList<>();
        neiCams.add(2);
        assertEquals(neiCams, m.closerCameras(animatronics.get(1)));


    }
}