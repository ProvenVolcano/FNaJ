package info;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Class for the UI that displays hours, night, power and power usage
 */
public class InfoPane {

    private AnchorPane root;
    private Text powerText, usageText, nightText, hourText;

    public InfoPane(InfoProperties ip, int width, int height) {

        powerText = new Text("Power: 100%");
        powerText.setFill(Color.WHITE);
        powerText.setFont(Font.font("Courier New", FontWeight.BOLD, 30));
        powerText.setStyle("-fx-stroke: black;-fx-stroke-width: 0.5;");
        AnchorPane.setLeftAnchor(powerText, 25.0);
        AnchorPane.setBottomAnchor(powerText, 80.0);
        powerText.textProperty().bind(ip.getPowerProperty());

        usageText = new Text("Usage:");
        usageText.setFill(Color.WHITE);
        usageText.setFont(Font.font("Courier New", FontWeight.BOLD, 30));
        usageText.setStyle("-fx-stroke: black;-fx-stroke-width: 0.5;");
        AnchorPane.setLeftAnchor(usageText, 25.0);
        AnchorPane.setBottomAnchor(usageText, 40.0);
        usageText.textProperty().bind(ip.getUsageProperty());

        nightText = new Text("Night " + ip.getNightNumber());
        nightText.setFill(Color.WHITE);
        nightText.setFont(Font.font("Courier New", FontWeight.BOLD, 25));
        nightText.setStyle("-fx-stroke: black;-fx-stroke-width: 0.5;");
        AnchorPane.setRightAnchor(nightText, 50.0);
        AnchorPane.setTopAnchor(nightText, 70.0);

        hourText = new Text("12 AM");
        hourText.setFill(Color.WHITE);
        hourText.setFont(Font.font("Courier New", FontWeight.BOLD, 40));
        hourText.setStyle("-fx-stroke: black;-fx-stroke-width: 0.5;");
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
