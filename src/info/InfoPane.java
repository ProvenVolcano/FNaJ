package info;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class InfoPane {

    private AnchorPane root;
    private Text powerText, usageText, nightText, hourText;

    public InfoPane(InfoProperties ip, int width, int height) {

        powerText = new Text("Power: 100%");
        powerText.setFill(Color.RED);
        AnchorPane.setLeftAnchor(powerText, 20.0);
        AnchorPane.setBottomAnchor(powerText, 50.0);
        powerText.textProperty().bind(ip.getPowerProperty());

        usageText = new Text("Usage:");
        usageText.setFill(Color.RED);
        AnchorPane.setLeftAnchor(usageText, 20.0);
        AnchorPane.setBottomAnchor(usageText, 20.0);
        usageText.textProperty().bind(ip.getUsageProperty());

        nightText = new Text("Night " + ip.getNight());
        nightText.setFill(Color.RED);
        AnchorPane.setRightAnchor(nightText, 50.0);
        AnchorPane.setTopAnchor(nightText, 50.0);

        hourText = new Text("12 AM");
        hourText.setFill(Color.RED);
        AnchorPane.setRightAnchor(hourText, 50.0);
        AnchorPane.setTopAnchor(hourText, 20.0);
        hourText.textProperty().bind(ip.getHourProperty());

        root = new AnchorPane(powerText, usageText, nightText, hourText);
        root.setPrefWidth(width);
        root.setPrefHeight(height);
    }

    public AnchorPane getRoot() {
        return root;
    }
}
